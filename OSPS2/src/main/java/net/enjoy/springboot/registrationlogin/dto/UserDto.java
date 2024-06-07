package net.enjoy.springboot.registrationlogin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = "請輸入資料")
    private String firstName;
    @NotEmpty(message = "請輸入資料")
    private String lastName;
    @NotEmpty(message = "請輸入資料")
    @Email
    private String email;
    @NotEmpty(message = "請輸入資料")
    private String password;
}