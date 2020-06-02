package auth.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import user.model.User;

@RegisterForReflection
public class LoginResponse {
    private User user;
    private String token;
    private String message;

    public LoginResponse(User user, String token, String message) {
        this.user = user;
        this.token = token;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
