package com.ein.domain.todo;

import com.ein.common.util.AES256Utils;
import com.ein.domain.notice.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponse {

    //Member
    private Long memberId;
    private String loginId;

    //Todo
    private Long todoId;
    private String title;
    private String content;

    public TodoResponse decrypt(){
        this.loginId = AES256Utils.decrypt(loginId);
        return this;
    }

    public TodoResponse(Todo todo) {
        this.memberId = todo.getMember().getId();
        this.loginId = AES256Utils.decrypt(todo.getMember().getLoginId());
        this.todoId = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
    }
}
