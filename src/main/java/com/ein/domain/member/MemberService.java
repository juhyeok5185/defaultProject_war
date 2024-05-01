package com.ein.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long saveMember(MemberReqeust memberReqeust) {
        return memberRepository.save(memberReqeust.toEntity(passwordEncoder)).getId();
    }

}
