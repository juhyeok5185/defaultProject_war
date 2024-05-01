package com.ein.domain.notice;

import com.ein.common.util.QuerydslRepositorySupport;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;

import static com.ein.domain.member.QMember.member;
import static com.ein.domain.notice.QNotice.notice;

public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeCustomRepository {

    public NoticeRepositoryImpl() {
        super(Notice.class);
    }

    @Override
    public Page<NoticeResponse> findAllWithPaging(NoticeSearch search) {
        return applyPagination(search.getPageable(), query -> query
                .select(Projections.fields(NoticeResponse.class,
                        member.id.as("memberId"),
                        member.loginId.as("loginId"),
                        notice.id.as("noticeId"),
                        notice.title.as("title"),
                        notice.content.as("content")
                ))
                .from(notice)
                .leftJoin(member).on(notice.member.id.eq(member.id))
                .where(searchCondition(search) , useCheck())
                .orderBy(notice.id.desc())
        );
    }

    public BooleanExpression searchCondition(NoticeSearch search){
        if(search.getKeyword().isEmpty()){
            return null;
        }

        switch (search.getSearchType()){
            case 0 : return notice.title.contains(search.getKeyword()).or(notice.content.contains(search.getKeyword()));
            case 1 : return notice.title.contains(search.getKeyword());
            case 2 : return notice.content.contains(search.getKeyword());
            default: return null;
        }
    }

    public BooleanExpression useCheck(){
        return notice.useYn.eq(true);
    }





}
