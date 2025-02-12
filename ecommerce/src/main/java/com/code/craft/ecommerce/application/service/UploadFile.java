package com.code.craft.ecommerce.application.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFile {

    private static final String FOLDER = "images//";
    private static final String IMG_DEFAULT = "default.jpg";
    private final File directory;

    public UploadFile() {
        this.directory = new File(FOLDER);
    }

    public String upload(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            createFolder();
            try {
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(FOLDER + multipartFile.getOriginalFilename());
                Files.write(path, bytes);
                return multipartFile.getOriginalFilename();
            } catch (IOException e) {
                throw new IOException("El archivo o directorio especificado no existe: " + e.getMessage());
            }
        }
        return IMG_DEFAULT;
    }

    private void createFolder() {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void delete(String fileName) {
        File file = new File(FOLDER + fileName);
        if (file.exists()) file.delete();
    }

}
