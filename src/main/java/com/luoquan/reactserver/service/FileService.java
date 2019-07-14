package com.luoquan.reactserver.service;

import com.luoquan.reactserver.util.RetCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * FileService
 *
 * @author LuoQuan
 * @date 2019/7/13 12:57
 */
public interface FileService {
    RetCode uploadFile(MultipartFile file) throws IOException;

    RetCode deleteFile(Map<String,String> map);
}
