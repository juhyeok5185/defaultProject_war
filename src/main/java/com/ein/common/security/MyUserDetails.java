package com.ein.common.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// UserDetails : 사용자 정보와 권한 정보를 담은 객체
// UserDetailsService : 로그인 처리를 하는 객체가 아니다
//						DB에 저장된 정보를 읽어서 UserDetails를 SS에 넘겨주는 역할
//						로그인 작업은 SS가 한다

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyUserDetails implements UserDetails {
	private Long id;
	private String username;
	private String password;
	private Boolean disabled;
	private Collection<GrantedAuthority> authorities;

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
