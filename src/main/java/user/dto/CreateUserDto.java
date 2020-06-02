package user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RegisterForReflection
public class CreateUserDto {
    @Email
    @NotBlank
    @NotNull
    public String username;

    @Length(min = 8, max = 16)
    public String password;
}
