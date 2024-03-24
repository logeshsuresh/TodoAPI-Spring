package com.example.TodoAPISpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList ;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "todo - 1", 1));
        todoList.add(new Todo(2, true, "todo - 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Object> getTodoById(@PathVariable int todoId) {
        for (Todo todo : todoList) {
            if (todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        Map<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("message", "ID not found");
        return new ResponseEntity<Object>(errorMessage, HttpStatus.NOT_FOUND);
    }

}