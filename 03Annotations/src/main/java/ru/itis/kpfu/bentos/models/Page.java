package ru.itis.kpfu.bentos.models;

import ru.itis.kpfu.bentos.annotations.Html;
import ru.itis.kpfu.bentos.annotations.HtmlForm;
import ru.itis.kpfu.bentos.annotations.HtmlInput;
import ru.itis.kpfu.bentos.annotations.MacroUse;

@Html
@MacroUse(macroName = "form")
@HtmlForm(method = "post", action = "/users")
public class Page {
    @HtmlInput(type = "text", name = "first_name", placeholder = "First name")
    private String firstName;
    @HtmlInput(type = "text", name = "last_name", placeholder = "Second name")
    private String lastName;
    @HtmlInput(type = "email", name = "email", placeholder = "Email")
    private String email;
    @HtmlInput(type = "password", name = "password", placeholder = "Password")
    private String password;
}
