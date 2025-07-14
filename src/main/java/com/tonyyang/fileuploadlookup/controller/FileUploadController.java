package com.tonyyang.fileuploadlookup.controller;
//这个类是后端对外暴露的接口，前端会通过它把上传元数据发给我们。

import com.tonyyang.fileuploadlookup.model.FileMetadata;
import com.tonyyang.fileuploadlookup.service.FileMetadataService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/files") //定义所有接口以 /api/files 开头
@CrossOrigin // 允许跨域访问，方便前端调试

public class FileUploadController {

    //这个依赖只在构造函数中注入，之后不会改变。
    private final FileMetadataService fileMetadataService;

    public FileUploadController(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    //👉 上传元数据接口（前端通过 JSON 发 metadata）
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileAndMetadata(@RequestParam("file") MultipartFile file) throws IOException {
        String s3Url = fileMetadataService.uploadFileToS3(file);

        FileMetadata metadata = new FileMetadata();
        metadata.setFilename(file.getOriginalFilename());
        metadata.setUploadTime(java.time.Instant.now().toString());
        metadata.setS3Url(s3Url);

        fileMetadataService.saveFileMetadata(metadata);
        return "Uploaded and metadata saved";
    }




}
