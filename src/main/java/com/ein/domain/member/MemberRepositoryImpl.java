package com.ein.domain.member;

import com.ein.common.util.QuerydslRepositorySupport;

import static com.ein.domain.member.QMember.member;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberCustomRepository {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Member findByLoginId(String loginId) {
        return selectFrom(member)
                .where(member.loginId.eq(loginId))
                .fetchOne();
    }
}
