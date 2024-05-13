package com.example.TodoAPISpring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Qualifier("fakeTodoService")
    private TodoService todoService;

    private static List<Todo> todoList ;

    private static final String TODO_NOT_FOUND = "Todo not found";

    public TodoController(@Qualifier("fakeTodoService") TodoService todoService) {
        this.todoService = todoService;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "todo - 1", 1));
        todoList.add(new Todo(2, true, "todo - 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println(todoService .doSomething());
        if (isCompleted == null) { return ResponseEntity.ok(todoList); }
        List<Todo> todos = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.isCompleted() == isCompleted) {
                todos.add(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoById(@PathVariable int todoId) {
        for (Todo todo : todoList) {
            if (todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        Map<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("message", "ID not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }

}