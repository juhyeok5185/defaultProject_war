package com.ein.domain.notice;

import org.springframework.data.domain.Page;

import java.util.List;

public interface NoticeCustomRepository {
    Page<NoticeResponse> findAllWithPaging(NoticeSearch search);
}
