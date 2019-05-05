package be.icc.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Scohier Dorian on 11-12-18.
 */
public class LoginForm {

    @NotBlank(message = "{error.notBlank}")
    private String userName;

    @NotBlank(message = "{error.notBlank}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}