package ru.itis.kpfu.bentos.rabbitmq.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String name;
    private String surname;
    private String passportId;
    private Integer age;
    private String dateOfIssue;

    public static User from(UserDto userDto) {
        return User.builder()
                .dateOfIssue(userDto.getDateOfIssue())
                .passportId(userDto.getPassportId())
                .age(userDto.getAge())
                .surname(userDto.getSurname())
                .name(userDto.getName())
                .build();
    }
}
