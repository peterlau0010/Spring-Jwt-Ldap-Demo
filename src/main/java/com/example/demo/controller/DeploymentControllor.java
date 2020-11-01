package com.example.demo.controller;

import com.example.demo.request.DeployRequest;
import com.example.demo.request.LoginRequest;
import com.example.demo.service.FilesStorageServiceImpl;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/deploy")
public class DeploymentControllor {

    @Autowired
    FilesStorageServiceImpl filesStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            filesStorageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
//            e.printStackTrace();
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/doDeploy")
    public ResponseEntity<String> doDeploy(@RequestBody DeployRequest deployRequest) {
        String message = "";
        deployRequest.getFileTo().isEmpty();
        try{
            filesStorageService.copy(deployRequest);
            message = "Deploy success";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            message = e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }


    }

}
