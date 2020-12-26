package ru.itis.kpfu.querydsl.controllers;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.kpfu.querydsl.dto.UserDto;
import ru.itis.kpfu.querydsl.dto.UserRequest;
import ru.itis.kpfu.querydsl.models.Shop;
import ru.itis.kpfu.querydsl.models.User;
import ru.itis.kpfu.querydsl.repositories.interfaces.UserByRequestRepository;
import ru.itis.kpfu.querydsl.repositories.interfaces.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserByRequestRepository userByRequestRepository;

    @GetMapping("/user/search/req")
    public ResponseEntity<List<UserDto>> searchByRequest(UserRequest userRequest) {
        return ResponseEntity.ok(userByRequestRepository.findByRequest(userRequest));
    }

    @GetMapping("/user/search/con/almost")
    public ResponseEntity<List<UserDto>> searchByPredicateAlmost(@QuerydslPredicate(root = User.class, bindings = UserRepository.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(userRepository.findAll(predicate).spliterator(), true)
                        .map(user ->
                                UserDto.builder()
                                        .login(user.getLogin())
                                        .nickname(user.getNickname())
                                        .shopName(user
                                                .getShops()
                                                .stream()
                                                .map(Shop::getName)
                                                .collect(Collectors.toList()).get(0))
                                        .build())
                        .collect(Collectors.toList()));
    }

    @GetMapping("/user/search/con/final")
    public ResponseEntity<List<UserDto>> searchByPredicateFinal(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(userRepository.findAll(predicate).spliterator(), true)
                        .map(user ->
                                UserDto.builder()
                                        .login(user.getLogin())
                                        .nickname(user.getNickname())
                                        .shopName(user
                                                .getShops()
                                                .stream()
                                                .map(Shop::getName)
                                                .collect(Collectors.toList()).get(0))
                                        .build())
                        .collect(Collectors.toList()));
    }

}
