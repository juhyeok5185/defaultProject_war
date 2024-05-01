package com.ein.domain.member;

public interface MemberCustomRepository {

    Member findByLoginId(String loginId);

}
