package ru.itis.kpfu.querydsl.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String login;
    private String nickname;
    private String shopName;

}
