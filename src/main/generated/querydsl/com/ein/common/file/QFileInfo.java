package com.ein.common.file;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileInfo is a Querydsl query type for FileInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileInfo extends EntityPathBase<FileInfo> {

    private static final long serialVersionUID = 439519434L;

    public static final QFileInfo fileInfo = new QFileInfo("fileInfo");

    public final com.ein.common.dto.QBaseTimeEntity _super = new com.ein.common.dto.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<com.ein.common.enums.FileType> fileType = createEnum("fileType", com.ein.common.enums.FileType.class);

    public final NumberPath<Long> fileTypeId = createNumber("fileTypeId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath orgFilename = createString("orgFilename");

    public final StringPath saveFilename = createString("saveFilename");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final BooleanPath useYn = createBoolean("useYn");

    public QFileInfo(String variable) {
        super(FileInfo.class, forVariable(variable));
    }

    public QFileInfo(Path<? extends FileInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileInfo(PathMetadata metadata) {
        super(FileInfo.class, metadata);
    }

}

