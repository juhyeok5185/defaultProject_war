package com.ein.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class CommonSearch {

    private int page = 1;               // 페이지 번호
    private int size = 10;              // 페이지당 출력할 레코드 개수
    private int pageSize = 10;          // 화면 하단에 출력할 페이지 개수
    private Integer searchType;          // 검색 유형
    private String keyword;             // 키워드
    private boolean pagination = true;  // 페이지네이션 적용 여부

    public Pageable getPageable() {
        return PageRequest.of(page - 1, size);
    }

    public boolean shouldPagination() {
        return pagination;
    }

}
