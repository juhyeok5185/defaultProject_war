package com.ein.domain.todo;

import com.ein.common.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;

    @PostMapping
    public Long saveTodo(@RequestBody TodoRequest request, final Authentication authentication) {
        return todoService.saveTodo(request, Utils.getMemberId(authentication));
    }

    @GetMapping
    public List<TodoResponse> findAll(@ModelAttribute final TodoSearch search) {
        return todoService.findAll(search);
    }

    @GetMapping("{todoId}")
    public TodoResponse findById(@PathVariable final Long todoId) {
        return todoService.findById(todoId);
    }

    @PatchMapping("{todoId}")
    public Long updateNotice(@RequestBody TodoRequest request,
                             @PathVariable final Long todoId,
                             final Authentication authentication) {

        return todoService.updateTodo(request, todoId, Utils.getMemberId(authentication));
    }
}
