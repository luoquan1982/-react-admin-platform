package com.luoquan.reactserver;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Test {

    public static void main(String[] args) throws IOException {
       /* LocalDate date = LocalDate.now();
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(date.format(DateTimeFormatter.ISO_DATE));
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(date);*/

//        InputStream in = Files.newInputStream(Paths.get("123.jpg"));
//        OutputStream out = Files.newOutputStream(Paths.get("a.jpg"));
//        Files.copy(Paths.get("123.jpg"),Paths.get("abc.jpg"));

        System.out.println(System.getProperty("file.encoding"));
//        SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get("123.jpg"));

    }
}
