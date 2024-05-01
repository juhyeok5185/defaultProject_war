package com.ein.domain.notice;

import com.ein.common.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping
    public Long saveNotice(@ModelAttribute final NoticeRequest request, final Authentication authentication) throws IOException {
        return noticeService.saveNotice(request, Utils.getMemberId(authentication));
    }

    @GetMapping
    public Page<NoticeResponse> findAllWithPaging(@ModelAttribute final NoticeSearch search) {
        return noticeService.findAllWithPaging(search);
    }

    @GetMapping("{noticeId}")
    public NoticeResponse findById(@PathVariable final Long noticeId) {
        return noticeService.findById(noticeId);
    }

    @PatchMapping("{noticeId}")
    public Long updateNotice(@RequestBody NoticeRequest request,
                             @PathVariable final Long noticeId,
                             final Authentication authentication) {

        return noticeService.updatNotice(request, noticeId, Utils.getMemberId(authentication));
    }
}
