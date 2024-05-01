package com.ein.domain.todo;

import com.ein.domain.member.Member;
import com.ein.domain.notice.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoRequest {
    private Long todoId;
    private String title;
    private String content;
    private Long memberId;



    public void addMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void addMemberIdAndTodoId(Long memberId , Long todoId){
        this.memberId = memberId;
        this.todoId = todoId;
    };
}
