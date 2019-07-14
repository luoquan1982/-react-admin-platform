package com.luoquan.reactserver.controller;

import com.luoquan.reactserver.service.FileService;
import com.luoquan.reactserver.util.RetCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * FileContorller
 *
 * @author LuoQuan
 * @date 2019/7/11 21:13
 */
@RestController
public class FileContorller {

    private FileService fileService;

    @Autowired
    public FileContorller(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/manage/image/upload")
    public RetCode uploadFile(@RequestParam("image") MultipartFile image) throws IOException {
        return fileService.uploadFile(image);
    }

    @PostMapping("/manage/image/delete")
    public RetCode removeFile(@RequestBody Map<String,String> map) {
        return fileService.deleteFile(map);
    }
}
