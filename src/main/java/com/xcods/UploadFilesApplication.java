package com.xcods;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xcods.controller.FileUploadController;

@Configuration
@SpringBootApplication
@ComponentScan({"com.xcods","com.xcods.controller"})
public class UploadFilesApplication {

	public static void main(String[] args) {
		new File(FileUploadController.UploadDirectory).mkdir();

		SpringApplication.run(UploadFilesApplication.class, args);
	}

}
