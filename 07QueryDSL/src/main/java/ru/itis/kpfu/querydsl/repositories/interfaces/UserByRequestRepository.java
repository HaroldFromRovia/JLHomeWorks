package ru.itis.kpfu.querydsl.repositories.interfaces;

import ru.itis.kpfu.querydsl.dto.UserDto;
import ru.itis.kpfu.querydsl.dto.UserRequest;

import java.util.List;

public interface UserByRequestRepository {

    List<UserDto> findByRequest(UserRequest request);

}
