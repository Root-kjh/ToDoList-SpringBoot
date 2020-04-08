package com.drk.todolist.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller(value = "/todo")
public class Todo {

    @GetMapping("/")
    public String showTodoList(){
        return "showTodoList";
    }

    @PostMapping("/insert")
    public void insertTodo(){

    }

    @GetMapping("/delete")
    public void deleteTodo(@RequestParam String todoIdx) {
        
    }
    
    @PostMapping("/update")
    public void updateTodo(){
        
    }
}