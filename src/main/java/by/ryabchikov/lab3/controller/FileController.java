package by.ryabchikov.lab3.controller;

import by.ryabchikov.lab3.service.FileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
    private final FileServiceImpl fileService;

    @GetMapping("/{fileName}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileName) throws IOException {
        return fileService.getFile(fileName);
    }

    @PutMapping("/{fileName}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> saveFile(@PathVariable String fileName,
                                           @RequestBody String fileData) throws IOException {
        return fileService.saveFile(fileName, fileData);
    }

    @DeleteMapping("/{fileName}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) throws IOException {
        return fileService.deleteFile(fileName);
    }

    @PostMapping("/{fileName}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> appendToFile(@PathVariable String fileName,
                                               @RequestBody String fileData) throws IOException {
        return fileService.appendToFile(fileName, fileData);
    }

    @PostMapping("/copy")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> copyFile(@RequestParam("sourceFileName") String sourceFileName,
                                           @RequestParam("targetFileName") String targetFileName) throws IOException {
        return fileService.copyFile(sourceFileName, targetFileName);
    }

    @PostMapping("/move")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> moveFile(@RequestParam("file") MultipartFile file,
                                           @RequestParam("targetDirectory") String targetDirectory) {
        return fileService.moveFile(file, targetDirectory);
    }
}

