package com.ein.common.file;

import com.ein.common.enums.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileInfoService {
    private final FileInfoRepository fileInfoRepository;

    @Value("${file.default-upload-dir}")
    private String uploadPath;

    @Transactional
    public void saveFile(List<MultipartFile> files, FileType fileType, Long fileTypeId) throws IOException {
        FileUtils fileUtils = new FileUtils();
        if (files != null) {
            for (MultipartFile item : files) {
                FileInfo fileInfo = fileUtils.toEntity(item, fileType, fileTypeId);
                fileInfoRepository.save(fileInfo);
                createPaths();
                uploadFile(item, fileInfo.getSaveFilename());
            }
        }
    }

    private void createPaths() throws IOException {
        Path path = Paths.get(uploadPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    private void uploadFile(MultipartFile file, String filename) throws IOException {
        String filePath = uploadPath + filename;
        File dest = new File(filePath);
        file.transferTo(dest);
    }
}
