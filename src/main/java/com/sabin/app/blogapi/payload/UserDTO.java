package com.sabin.app.blogapi.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    @NotEmpty
    @Size(min = 4,message = "Username must contain more than 4 letters")
    private String name;
    @Email(message = "This email address is invalid")
    private String email;
    @NotEmpty
    @Size(min = 4,max = 10,message = "Password not good")
    private String password;
    @NotEmpty
    private String about;
}
