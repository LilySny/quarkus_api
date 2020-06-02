package user.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class RegisterResponse {
    private String message;

    public RegisterResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
