package com.drk.todolist.Config;

import javax.transaction.Transactional;

import com.drk.todolist.DTO.User.SignupDTO;
import com.drk.todolist.Entitis.TodoEntity;
import com.drk.todolist.Entitis.UserEntity;
import com.drk.todolist.Repositories.TodoRepository;
import com.drk.todolist.Repositories.UserRepository;
import com.drk.todolist.lib.TestLib;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestInit {

    // Repositories
    public UserRepository userRepository;

    public TodoRepository todoRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }    
    
    @Autowired
    public void setTodoRepository(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    protected final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    // DB Init
    @BeforeEach
    @AfterEach
    public void clearDB(){
        userRepository.deleteAll();
        todoRepository.deleteAll();
    }


    // Make Test user&todo methods
    @Transactional
    public UserEntity makeTestUser() throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(TestLib.testUser.name);
        userEntity.setNickname(TestLib.testUser.nickName);
        userEntity.setPassword(passwordEncoder.encode(TestLib.testUser.password));
        UserEntity saveUserEntity = this.userRepository.save(userEntity);
        if (userEntity.getIdx() == saveUserEntity.getIdx())
            return saveUserEntity;
        else
            throw new Exception("userEntity was not save nomarlly");
    }

    @Transactional
    public UserEntity makeTestUser(SignupDTO signupDTO) throws Exception{
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signupDTO.getUserName());
        userEntity.setNickname(signupDTO.getNickName());
        userEntity.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        UserEntity saveUserEntity = this.userRepository.save(userEntity);
        if (userEntity.getIdx() == saveUserEntity.getIdx())
            return saveUserEntity;
        else
            throw new Exception("userEntity was not save nomarlly");
    }

    @Transactional
    public TodoEntity makeTodo(final UserEntity userEntity) throws Exception{
        TodoEntity todo = new TodoEntity();
        todo.setTitle(TestLib.testTodo.title);
        todo.setContext(TestLib.testTodo.context);
        userEntity.addTodo(todo);
        this.userRepository.saveAndFlush(userEntity);
        return this.todoRepository.findAll().get(0);
    }

}