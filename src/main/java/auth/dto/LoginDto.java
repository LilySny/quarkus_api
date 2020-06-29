package auth.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RegisterForReflection
public class LoginDto {
    @NotBlank
    @NotNull
    @Email
    public String username;

    public String password;
}
