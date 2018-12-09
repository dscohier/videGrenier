package be.icc.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Student on 27-01-16.
 */
public class ConnectForm {

    @NotBlank(message = "connect.userName")
    private String userName;

    @NotBlank(message = "{connect.userName}")
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