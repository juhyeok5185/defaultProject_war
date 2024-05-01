package com.ein.domain.notice;

import com.ein.common.dto.BaseTimeEntity;
import com.ein.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_notice")
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id; // 회원 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 학생

    @Column(name = "title")
    private String title; // 제목

    @Column(name = "content")
    private String content; // 내용

    @Column(name = "use_yn")
    private Boolean useYn; // 사용여부

    @Builder
     public Notice(Long id, Member member, String title, String content, Boolean useYn) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.useYn = useYn;
    }

    public void updateNotice(NoticeRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}
