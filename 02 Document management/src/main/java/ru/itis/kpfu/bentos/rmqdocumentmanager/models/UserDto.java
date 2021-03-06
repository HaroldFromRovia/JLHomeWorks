package ru.itis.kpfu.bentos.rmqdocumentmanager.models;

import com.beust.jcommander.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Profile({"confirm", "all"})
public class UserDto {

    @Parameter(names = "--name", description = "User name")
    private String name;
    @Parameter(names = "--surname", description = "User's surname")
    private String surname;
    @Parameter(names = "--passportId", description = "User's passport id")
    private String passportId;
    @Parameter(names = "--age", description = "How old user is")
    private Integer age;
    @Parameter(names = "--issueDate", description = "Date of passport issue")
    private String dateOfIssue;
    @Parameter(names = "--phone", description = "User's phone")
    private String phone;

    @Parameter(names = "-commit", description = "Flag to recognise that commit command in use")
    private boolean commit = false;

    @Parameter(help = true, names = "--help")
    private boolean help = false;

}
