package com.aimprosoft.models;

import com.aimprosoft.validation.employee.IsUniqueEmailCheck;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@With
@Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "employeeId")
    private Integer id;
    @JoinColumn(name = "departmentId")
    @ManyToOne
    private Department department;
    @NotNull(message = "This field is required!")
    @Length(max = 32, min = 2, message = "Incorrect name!")
    @Column(name = "name")
    private String name;
    @NotNull(message = "This field is required!")
    @Email(message = "Incorrect email!")
    @CheckWith(value = IsUniqueEmailCheck.class, message = "Email must be unique!")
    @Column(name = "email")
    private String email;
    @NotNull(message = "This field is required!")
    @Range(min = 18, max = 65, message = "Incorrect age!")
    @Column(name = "age")
    private Integer age;
    @NotNull(message = "This field is required!")
    @DateRange(max = "now", message = "Incorrect date!")
    @Temporal(TemporalType.DATE)
    @Column(name = "startWorkingDate")
    private Date startWorkingDate;
}
