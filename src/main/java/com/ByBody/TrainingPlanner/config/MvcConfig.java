package com.ByBody.TrainingPlanner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.exercises.photo.path}")
    private String exercisesPhotoPath;

    @Value("${upload.users.photo.path}")
    private String usersPhotoPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("static/img/exercises/**")
                .addResourceLocations("file://" + exercisesPhotoPath + "/");

        registry.addResourceHandler("/img/users/**")
                .addResourceLocations("file://" + usersPhotoPath + "/");
    }
}
