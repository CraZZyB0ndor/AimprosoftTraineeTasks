package com.aimprosoft.models;

import com.aimprosoft.validation.employee.IsUniqueEmailCheck;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import net.sf.oval.constraint.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@With
public class Employee implements Serializable {

    private Integer id;

    private Integer departmentId;
    @NotNull(message = "This field is required!")
    @Length(max = 32, min = 2, message = "Incorrect name!")
    private String name;
    @NotNull(message = "This field is required!")
    @Email(message = "Incorrect email!")
    @CheckWith(value = IsUniqueEmailCheck.class, message = "Email must be unique!")
    private String email;
    @NotNull(message = "This field is required!")
    @Range(min = 18, max = 65, message = "Incorrect age!")
    private Integer age;
    @NotNull(message = "This field is required!")
    @DateRange(max = "now", message = "Incorrect date!")
    private Date startWorkingDate;

    public Employee() {}
}
