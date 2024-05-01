package com.ein.domain.member;

import com.ein.common.dto.BaseTimeEntity;
import com.ein.common.enums.Type;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 회원 고유번호

    @Column(name = "login_id")
    private String loginId; // 회원 아이디

    @Column(name = "password")
    private String password; // 회원 비밀번호

    @Column(name = "type")
    private Type type; // 회원 종류

    @Column(name = "use_yn")
    private Boolean useYn; // 회원 종류

    @Builder
    public Member(Long id, String loginId, String password, Type type, Boolean useYn) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.type = type;
        this.useYn = useYn;
    }
}
