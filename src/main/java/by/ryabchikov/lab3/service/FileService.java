package by.ryabchikov.lab3.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    ResponseEntity<byte[]> getFile(String filename) throws IOException;
    ResponseEntity<String> saveFile(String filename, String fileData) throws IOException;
    ResponseEntity<String> deleteFile(String filename) throws IOException;
    ResponseEntity<String> appendToFile(String filename, String fileData) throws IOException;
    ResponseEntity<String> copyFile(String sourceFilename, String targetFilename) throws IOException;
    ResponseEntity<String> moveFile(MultipartFile file, String targetDirectory) throws IOException;
 }
