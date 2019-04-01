package be.icc.form;

import be.icc.service.UserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

/**
 * Created by Student on 11-12-18.
 */
public class SignupForm {

    @Autowired
    UserService userService;

    @NotBlank(message = "{error.notBlank}")
    private String userName;

    @NotBlank(message = "{error.notBlank}")
    private String password;

    @NotBlank(message = "{error.notBlank}" )
    private String firstName;
    @NotBlank(message = "{error.notBlank}" )
    private String lastName;

    private MultipartFile file;

    @NotBlank(message = "{error.notBlank}" )
    private String city;
    @NotBlank(message = "{error.selectValueFromGoogle}" )
    private String state;
    @NotBlank(message = "{error.notBlank}")
    @Pattern(regexp = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})", message = "{error.mail}")
    private String email;
    @NotBlank(message = "{error.notBlank}")
    private String passwordCheck;
    private boolean isPasswordMatch;

    public void setPasswordMatch(boolean passwordMatch) {
        isPasswordMatch = passwordMatch;
    }

    @AssertTrue(message = "{error.passwordMatch}")
    public boolean getIsPasswordMatch() {
        if(password==null||passwordCheck==null){
            isPasswordMatch = false;
        }else{
            isPasswordMatch = password.equals(passwordCheck);
        }
        return  isPasswordMatch;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}