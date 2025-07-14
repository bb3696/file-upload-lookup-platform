package com.tonyyang.fileuploadlookup.service;
// 创建 Service 类：把 FileMetadata 写入 DynamoDB。

import com.tonyyang.fileuploadlookup.model.FileMetadata; //引入你刚创建的模型类，它是要存入 DynamoDB 的对象。
import org.apache.tomcat.util.net.IPv6Utils;
import org.springframework.stereotype.Service; //用于标记这个类为 Spring 的 Service 组件，会被自动注册到 Spring 容器

// 从 AWS SDK v2 引入的类：
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient; //DynamoDB 客户端，用来发请求
import software.amazon.awssdk.services.dynamodb.model.AttributeValue; //表示 DynamoDB 中的字段值
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest; //表示要写入一个新 item 的请求

//S3
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


//Java 的 Map 用于构建 DynamoDB 中的一条记录（item）:
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service // 表示这是一个业务逻辑类（service），会被 Spring Boot 自动注入和管理。
public class FileMetadataService {

    private final DynamoDbClient dynamoDbClient; // 用于和 DynamoDB 通信的客户端，设为成员变量以供整个类使用
    private final S3Client s3Client;


    public FileMetadataService(DynamoDbClient dynamoDbClient, S3Client s3Client) {
        this.dynamoDbClient = dynamoDbClient;
        this.s3Client = s3Client;
    }
        //DynamoDbClient.create() 会尝试读取你的本地或 EC2 的 AWS 配置信息：
        //~/.aws/credentials
        //或 EC2 的 IAM Role

    public String uploadFileToS3(MultipartFile file) throws IOException {

        String key = "uploads/" + file.getOriginalFilename();

        String bucketName = "tony-upload-bucket";
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(putRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return "https://" + bucketName + ".s3.amazonaws.com/" + key;

    }

    public void saveFileMetadata(FileMetadata metadata) {

        //创建一个键值对 Map，用来构建 DynamoDB 中的 item。DynamoDB 不接受普通的 Java 对象，而是接受 AttributeValue 类型的 map。
        Map<String,AttributeValue> item = new HashMap<>();

        // 把 Java 对象的属性转换为 DynamoDB 的字段：
        item.put("filename", AttributeValue.fromS(metadata.getFilename()));
        item.put("uploadTime", AttributeValue.fromS(metadata.getUploadTime()));
        item.put("s3Url", AttributeValue.fromS(metadata.getS3Url()));

        // 创建一个写入请求，指定表名和刚刚构建好的 item
        // 表示你要操作的 DynamoDB 表名。必须和 AWS 控制台创建的表名一致。
        String tableName = "FileMetadata";
        PutItemRequest request =  PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request); //最终执行写入 DynamoDB 的操作。如果成功，将数据写入表中。
    }




}

