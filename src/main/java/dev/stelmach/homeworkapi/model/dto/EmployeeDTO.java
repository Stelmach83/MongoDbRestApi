package dev.stelmach.homeworkapi.model.dto;

import dev.stelmach.homeworkapi.validation.UniqueEmail;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeDTO {

    private long id;
    private String firstName;
    private String lastName;
    @Email(message = "Field 'email' must be a valid email format", groups = {EmployeeDTO.New.class, EmployeeDTO.Existing.class})
    @NotNull(message = "Field 'email' cannot be empty", groups = {EmployeeDTO.New.class, EmployeeDTO.Existing.class})
    @UniqueEmail(message = "This 'email' is already taken", groups = {EmployeeDTO.New.class, EmployeeDTO.Existing.class})
    private String email;
    @Pattern(regexp = "^([\\+]?[1]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$",
            message = "Field 'phoneNumber' must be a US phone number", groups = {EmployeeDTO.New.class, EmployeeDTO.Existing.class})
    private String phoneNumber;

    public EmployeeDTO() {
    }

    public interface Existing {
    }

    public interface New {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
