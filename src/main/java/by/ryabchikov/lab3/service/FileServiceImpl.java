package by.ryabchikov.lab3.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileServiceImpl implements FileService {
    private static final String FILE_DIRECTORY = "/Users/nikitaryabchikov/Desktop/";
    @Override
    public ResponseEntity<byte[]> getFile(String filename) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + filename);
        if (Files.exists(filePath)) {
            byte[] fileBytes = Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + filename)
                    .body(fileBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> saveFile(String filename, String fileData) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + filename);
        Files.writeString(filePath, fileData);
        return ResponseEntity.ok("File updated successfully!");
    }
    @Override
    public ResponseEntity<String> deleteFile(String filename) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + filename);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
            return ResponseEntity.ok("File deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> appendToFile(String filename, String fileData) throws IOException {
        Path filePath = Paths.get(FILE_DIRECTORY + filename);
        Files.writeString(filePath, fileData, StandardOpenOption.APPEND);
        return ResponseEntity.ok("Data appended to file successfully!");
    }

    @Override
    public ResponseEntity<String> copyFile(String sourceFileName, String targetFileName) throws IOException {
        Path sourcePath = Paths.get(FILE_DIRECTORY + sourceFileName);
        Path targetPath = Paths.get(FILE_DIRECTORY + targetFileName);

        if (Files.exists(sourcePath)) {
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("File copied successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> moveFile(MultipartFile file, String targetDirectory) {
        if (file.isEmpty() || targetDirectory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please input file and target directory.");
        }

        try {
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.startsWith(".")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot move system file or file with hidden name.");
            }
            String targetPath = FILE_DIRECTORY + targetDirectory + File.separator + fileName;
            Path path = Paths.get(targetPath);
            Files.createDirectories(path.getParent());
            file.transferTo(path.toFile());
            return ResponseEntity.ok("File moved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error moving file.");
        }
    }
}
