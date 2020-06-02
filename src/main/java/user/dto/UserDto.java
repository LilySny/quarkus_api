package user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RegisterForReflection
public class UserDto {
    public UUID id;
    @NotBlank
    @NotNull
    public String username;
}
