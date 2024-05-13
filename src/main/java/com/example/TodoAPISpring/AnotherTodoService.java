package com.example.TodoAPISpring;

import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService  implements TodoService {
    public String doSomething() {
        return "Something from AnotherTodoService";
    }
}
