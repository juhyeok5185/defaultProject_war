package com.ein.domain.todo;

import com.ein.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface TodoMapper {
    Long save(TodoRequest request);

    TodoResponse findById(Long todoId);

    Long update(TodoRequest request);

    List<TodoResponse> findAll(TodoSearch search);
}
