package com.drk.todolist.DTO.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJwtDTO {
    private Long userIdx;
    private String userNickName;
    private String userName;
}