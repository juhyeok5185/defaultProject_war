package com.ein.domain.notice;

import com.ein.common.enums.FileType;
import com.ein.common.exception.MyException;
import com.ein.common.file.FileInfoService;
import com.ein.domain.member.Member;
import com.ein.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;
    private final FileInfoService fileInfoService;


    @Transactional
    public Long saveNotice(NoticeRequest request, Long memberId) throws IOException {
        Member member = memberRepository.findById(memberId).orElseThrow(null);
        Notice notice = noticeRepository.save(request.toEntity(member));
        fileInfoService.saveFile(request.getFiles() , FileType.NOTICE , notice.getId());
        return notice.getId();
    }

    public Page<NoticeResponse> findAllWithPaging(NoticeSearch search) {
            return noticeRepository.findAllWithPaging(search)
            .map(NoticeResponse::decrypt);
    }

    public NoticeResponse findById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(null);
        return new NoticeResponse(notice);
    }

    public Long updatNotice(NoticeRequest request, Long noticeId, Long memberId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(null);

        if(!notice.getMember().getId().equals(memberId)){
            throw new MyException("업데이트 권한이 없습니다.");
        }
        notice.updateNotice(request);
        return noticeRepository.save(notice).getId();
    }
}
