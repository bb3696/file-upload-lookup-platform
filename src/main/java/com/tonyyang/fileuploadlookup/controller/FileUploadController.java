package com.tonyyang.fileuploadlookup.controller;
//这个类是后端对外暴露的接口，前端会通过它把上传元数据发给我们。

import com.tonyyang.fileuploadlookup.model.FileMetadata;
import com.tonyyang.fileuploadlookup.service.FileMetadataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files") //定义所有接口以 /api/files 开头
@CrossOrigin // 允许跨域访问，方便前端调试

public class FileUploadController {

    //这个依赖只在构造函数中注入，之后不会改变。
    private final FileMetadataService fileMetadataService;

    public FileUploadController(FileMetadataService fileMetadataService) {
        this.fileMetadataService = fileMetadataService;
    }

    @PostMapping("/upload") //接口路径是 POST /api/files/upload

    public String uploadFileMetadata(@RequestBody FileMetadata fileMetadata) {  //自动把前端传来的 JSON 解析成对象
        fileMetadataService.saveFileMetadata(fileMetadata);
        return "File metadata saved successfully";
    }

}
