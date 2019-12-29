package com.xcods.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	public static String UploadDirectory =  System.getProperty("user.dir")+"/uploads";
	@RequestMapping("/")
	public String UploadPage(Model model)
	{
		return "uploadview";
	}
	@RequestMapping("/upload")
	//jis model  ka  view pe access hoga
	public String upload(Model model,@RequestParam("files") MultipartFile[]files,@RequestParam("basic") String userPath) throws IOException
	{
		String basic = userPath;
		String messageThrow = "";
		for(MultipartFile file:files)
		{

			String ext = getExtension(file.getOriginalFilename()).trim();
			//basic = "D:\\ananYa\\test";
			basic += "\\";
			
			if(ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("xls")) {
				basic += "Documents";
			}
			if(ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg")) {
				basic += "Pictures";
			}
			if(ext.equalsIgnoreCase("rar") || ext.equalsIgnoreCase("zip") || ext.equalsIgnoreCase("tar.gz")) {
				basic += "Compressed";
			}
			if(ext.equalsIgnoreCase("mp3") || ext.equalsIgnoreCase("aac")) {
				basic += "Music";
			}
			if(ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("avi")) {
				basic += "Videos";
			}
			if(ext.equalsIgnoreCase("txt")) {
				basic += "Notes";
			}
			
			System.out.println(basic);
			
			new File(basic).mkdir();
			Path fileNameAndPath2 = Paths.get(basic,file.getOriginalFilename());
			
			System.out.println(fileNameAndPath2);
			
			try
			{
				Files.write(fileNameAndPath2, file.getBytes());
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			messageThrow += "File: "+file.getOriginalFilename() +" Location : "+ basic+ "";
			
		}			
		model.addAttribute("msg",messageThrow);
		return "uploadstatusview";
	}
	public static String getExtension(String fileName)
	{
		int index=fileName.lastIndexOf(".");
		String ext=fileName.substring(index+1).toString();
		System.out.println(ext);
	    if(ext.trim().equalsIgnoreCase("gz"))		
	    {
	    	return "tar.gz";
		}
		return ext;
		
	}
}
