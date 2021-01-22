package pl.application.cocktailVibe.dto;

import com.sun.istack.NotNull;
import pl.application.cocktailVibe.annotations.PasswordMatches;
import pl.application.cocktailVibe.annotations.PasswordValidate;
import pl.application.cocktailVibe.annotations.UniqueAccount;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@UniqueAccount
@PasswordMatches
public class UserDTO {

    @NotNull
    @NotEmpty
    private String nickName;

    @NotNull
    @NotEmpty
    @PasswordValidate
    private String password;
    private String matchingPassword;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
