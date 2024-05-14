package com.example.TodoAPISpring;

import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService {

    @TimeMonitor
    public String doSomething() {
        for (int i = 0; i < 1000000009; i ++) {}
        return "Something";
    }
}
