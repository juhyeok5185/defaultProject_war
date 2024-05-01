package com.ein.domain.notice;

import com.ein.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoticeRequest {
    private String title;
    private String content;
    private List<MultipartFile> files;

    public NoticeRequest(String title, String content, List<MultipartFile> files) {
        this.title = title;
        this.content = content;
        this.files = files;
    }

    public Notice toEntity(Member member) {
        return Notice.builder()
                .title(title)
                .content(content)
                .member(member)
                .useYn(true)
                .build();
    }
}
