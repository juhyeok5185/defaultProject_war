package com.ein.common.security;

import com.ein.common.exception.MyException;
import com.ein.common.util.AES256Utils;
import com.ein.domain.member.Member;
import com.ein.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        try {
            Member member = memberRepository.findByLoginId(AES256Utils.encrypt(loginId));
            if (member == null) {
                throw new MyException("사용자를 찾을 수 없습니다");
            }

            String role = member.getType().toString();

            Collection<GrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority(role));
            return new MyUserDetails(member.getId() ,loginId, member.getPassword(), true, list);

        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
        }
    }

}
