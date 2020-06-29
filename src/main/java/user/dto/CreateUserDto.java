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

    public byte[] salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
