package com.example.demo.service;

import com.example.demo.config.YamlConfig;
import com.example.demo.request.DeployRequest;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FilesStorageServiceImpl {

    @Autowired
    YamlConfig yamlConfig;

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), Paths
                    .get(yamlConfig.getTmpUploadPath() + file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public void copy(DeployRequest deployRequest) {
        try {
            String destPath = yamlConfig.getDeployFolderPath() + deployRequest.getFileFrom();
            if (!(deployRequest.getFileTo() == null || deployRequest.getFileTo().isEmpty())) {
                destPath = deployRequest.getFileTo();
            }

            String srcPath = yamlConfig.getTmpUploadPath() + deployRequest.getFileFrom();

            File srcFile = new File(srcPath);
            File desFile = new File(destPath);

            if (srcFile.exists() && !srcFile.isDirectory()) {
                FileUtils.copyFile(srcFile, desFile);
            }else{
                throw new Exception("File not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Deploy failed: " + e.getMessage());
        }
    }

}
