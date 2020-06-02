package auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDto {
    @NotBlank
    @NotNull
    @Email
    public String username;

    public String password;
}
