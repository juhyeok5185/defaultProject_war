package com.ein.common.file;

import com.ein.common.dto.BaseTimeEntity;
import com.ein.common.enums.FileType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_file")
public class FileInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id; // 파일 고유번호

    @Column(name = "file_type")
    private FileType fileType; // 파일 타입

    @Column(name = "file_type_id")
    private Long fileTypeId; // 파일 타입 일련번호

    @Column(name = "org_filename")
    private String orgFilename; // 파일 원본 이름

    @Column(name = "save_filename")
    private String saveFilename; // 파일 저장 이름

    @Column(name = "use_yn")
    private Boolean useYn; // 사용 여부

    @Builder
    public FileInfo(Long id, FileType fileType, Long fileTypeId, String orgFilename, String saveFilename , boolean useYn) {
        this.id = id;
        this.fileType = fileType;
        this.fileTypeId = fileTypeId;
        this.orgFilename = orgFilename;
        this.saveFilename = saveFilename;
        this.useYn = useYn;
    }


}
