package com.example.websport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    // Đường dẫn thư mục lưu trữ ảnh. Cần khớp với UPLOAD_DIR trong controller!
    private static final String UPLOAD_DIR = "/Users/khanhhuy/IdeaProjects/websport/uploaded_images/"; // <--- Đã thay
                                                                                                       // đổi

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + UPLOAD_DIR); // Sử dụng "file:" prefix để truy cập file hệ thống
    }
}