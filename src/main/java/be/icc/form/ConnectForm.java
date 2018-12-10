package be.icc.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

/**
 * Created by Student on 27-01-16.
 */
public class ConnectForm {

    @NotBlank(message = "{error.notBlank}")
    private String userName;

    @NotBlank(message = "{error.notBlank}")
    private String password;

    @NotBlank(message = "{error.notBlank}" )
    private String firstName;
    @NotBlank(message = "{error.notBlank}" )
    private String lastName;
    @NotBlank(message = "{error.notBlank}" )
    private String city;
    @NotBlank(message = "{error.notBlank}")
    @Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})", message = "{error.mail}")
    private String email;
    @NotBlank(message = "{error.notBlank}")
    private String passwordCheck;

    @AssertTrue(message = "error.passwordMatch")
    public boolean isPasswordMatch(){
        if(password==null||passwordCheck==null){
            return false;
        }else{
            return password.equals(passwordCheck);
        }
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}