package com.tonyyang.fileuploadlookup.model;
import lombok.Data;

//这个类是用来表示每个上传文件的元数据：
@Data
public class FileMetadata {
    private String filename; //文件名称（用户上传的）
    private String uploadTime; //上传时间戳（前端传ISO格式）
    private String s3Url; //文件在 S3 上的地址
}
