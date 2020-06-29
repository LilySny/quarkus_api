package user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RegisterForReflection
public class UserDto {
    public int id;
    @NotBlank
    @NotNull
    public String username;
}
