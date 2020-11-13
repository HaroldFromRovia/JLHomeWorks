package ru.itis.kpfu.bentos.models;

import ru.itis.kpfu.bentos.annotations.*;

@Macro(name = "form", file = "page.ftl")
@Import(name = "page", path = "/page.ftl")
@Html
@HtmlForm(method = "post", action = "/users")
public class User {
    @HtmlInput(type = "text", name = "first_name", placeholder = "First name")
    private String firstName;
    @HtmlInput(type = "text", name = "last_name", placeholder = "Second name")
    private String lastName;
    @HtmlInput(type = "email", name = "email", placeholder = "Email")
    private String email;
    @HtmlInput(type = "password", name = "password", placeholder = "Password")
    private String password;
}
