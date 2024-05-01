package com.ein.domain.notice;

import com.ein.common.util.AES256Utils;
import com.ein.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeResponse {

    //Member
    private Long memberId;
    private String loginId;

    //Notice
    private Long noticeId;
    private String title;
    private String content;

    public NoticeResponse decrypt(){
        this.loginId = AES256Utils.decrypt(loginId);
        return this;
    }

    public NoticeResponse(Notice notice) {
        this.memberId = notice.getMember().getId();
        this.loginId = AES256Utils.decrypt(notice.getMember().getLoginId());
        this.noticeId = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }
}
