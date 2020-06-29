package groups.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RegisterForReflection
public class CreateGroupDto {
    @NotBlank
    @NotNull
    public String name;
}
