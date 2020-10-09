package ru.itis.kpfu.bentos.rmqdocumentmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Profile({"confirm","all"})
public class User {

    private String name;
    private String surname;
    private String passportId;
    private String dateOfIssue;
    private Integer age;
    private String phone;

    public static User from(UserDto userDto) {
        return User.builder()
                .dateOfIssue(userDto.getDateOfIssue())
                .passportId(userDto.getPassportId())
                .age(userDto.getAge())
                .surname(userDto.getSurname())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .build();
    }

}
