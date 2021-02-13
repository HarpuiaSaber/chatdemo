package com.toan.chatdemo.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface Constants {
    public static final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    public static final String folderImage = "/images";
    public static final String UPLOAD_FOLDER = "C:\\Users\\ztoan\\IdeaProjects\\chatdemo\\src\\main\\resources\\static";
}
