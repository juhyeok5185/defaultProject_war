package com.ein.domain.todo;

import com.ein.common.exception.MyException;
import com.ein.domain.member.Member;
import com.ein.domain.member.MemberRepository;
import com.ein.domain.notice.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoService {

    private final TodoMapper todoMapper;

    public Long saveTodo(TodoRequest request, Long memberId) {
        request.addMemberId(memberId);
        return todoMapper.save(request);
    }

    public List<TodoResponse> findAll(TodoSearch search) {
        return todoMapper.findAll(search)
                .stream()
                .map(TodoResponse::decrypt)
                .collect(Collectors.toList());
    }

    public TodoResponse findById(Long todoId) {
        return todoMapper.findById(todoId).decrypt();
    }

    public Long updateTodo(TodoRequest request, Long todoId, Long memberId) {
        request.addMemberIdAndTodoId(memberId,todoId);
        return todoMapper.update(request);
    }
}
