package com.ein.domain.todo;

import com.ein.common.dto.BaseTimeEntity;
import com.ein.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_todo")
public class Todo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
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
     public Todo(Long id, Member member, String title, String content, Boolean useYn) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.useYn = useYn;
    }


}
