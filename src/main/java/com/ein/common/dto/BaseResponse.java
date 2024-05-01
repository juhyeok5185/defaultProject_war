package com.ein.common.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseResponse {

    protected Boolean useYn;              // 사용 여부
    protected LocalDateTime createdAt;    // 저장일시
    protected LocalDateTime updatedAt;    // 최종 수정일시

}
