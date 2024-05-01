package com.ein.domain.member;

import com.ein.common.enums.Type;
import com.ein.common.util.AES256Utils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class MemberReqeust {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 8, max = 20, message = "아이디는 8자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", message = "아이디는 숫자와 영문 조합이어야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$", message = "비밀번호는 숫자와 영문 조합이어야 합니다.")
    private String password;

    @NotNull(message = "유형을 선택해주세요.")
    private Type type;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .loginId(AES256Utils.encrypt(loginId))
                .password(passwordEncoder.encode(password))
                .type(type)
                .useYn(true)
                .build();
    }
}
