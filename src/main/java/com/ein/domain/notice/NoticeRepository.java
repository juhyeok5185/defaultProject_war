package com.ein.domain.notice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> , NoticeCustomRepository{
}
