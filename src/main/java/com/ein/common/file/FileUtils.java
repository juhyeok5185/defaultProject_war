package com.ein.common.file;

import com.ein.common.enums.FileType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileUtils {




    /**
     * 저장 파일명 생성
     * @param filename 원본 파일명
     * @return 서버(디스크)에 저장할 파일명
     */
    public String getSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = FilenameUtils.getExtension(filename);
        return uuid + "." + extension;
    }

    /**
     * File Entity
     * @return entity
     */
    protected FileInfo toEntity(MultipartFile file , FileType fileType , Long fileTypeId) {
        String orgFilename = file.getOriginalFilename();
        String saveFilename = getSaveFilename(orgFilename);
        return FileInfo.builder()
                .fileType(fileType)
                .fileTypeId(fileTypeId)
                .orgFilename(orgFilename)
                .saveFilename(saveFilename)
                .useYn(true)
                .build();
    }

}
