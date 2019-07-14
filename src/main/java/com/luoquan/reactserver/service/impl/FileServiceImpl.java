package com.luoquan.reactserver.service.impl;

import com.luoquan.reactserver.service.FileService;
import com.luoquan.reactserver.util.RetCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * FileServiceImpl
 *
 * @author LuoQuan
 * @date 2019/7/13 12:58
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public RetCode uploadFile(MultipartFile file) {
        LocalDate uploadDate = LocalDate.now();
        URI root;
        Path uploadPath;
        String uploadFolder = uploadDate.format(DateTimeFormatter.BASIC_ISO_DATE);

        String originalFileName = file.getOriginalFilename();
        if (null == originalFileName) {
            return new RetCode(401, "文件名为空");
        }
        String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String saveName = UUID.randomUUID().toString().replaceAll("-", "");
        String saveNameWithSuffix = saveName + "." + suffix;

        try {
            root = ResourceUtils.getURL("classpath:").toURI();
            uploadPath = Paths.get(root).resolve("static").resolve("upload");
            if (!Files.exists(uploadPath.resolve(uploadFolder))) {
                Files.createDirectories(uploadPath.resolve(uploadFolder));
            }
            file.transferTo(uploadPath.resolve(Paths.get(uploadFolder, saveNameWithSuffix)));
            Map<String, String> result = new HashMap<>(2);
            result.put("url", "/upload/" + uploadFolder + "/" + saveNameWithSuffix);
            result.put("fileName", saveNameWithSuffix);
            List<Map> ret = new ArrayList<>();
            ret.add(result);
            return RetCode.success(ret);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new RetCode(-1,"服务器错误");
    }

    @Override
    public RetCode deleteFile(Map<String, String> map) {
        if (null == map) {
            return new RetCode(401, "参数不能为空");
        }
        String url = map.get("url");
        if (StringUtils.isBlank(url)) {
            return new RetCode(401, "参数不能为空");
        }

        URI root;
        Path path;

        try {
            root = ResourceUtils.getURL("classpath:").toURI();
            System.out.println(root);
            System.out.println("url:" + url);
            path = Paths.get(root).resolve("static").resolve(url);
            System.out.println(path);
        } catch (URISyntaxException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
