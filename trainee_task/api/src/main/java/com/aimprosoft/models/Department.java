package com.aimprosoft.models;

import com.aimprosoft.validation.department.IsUniqueNameCheck;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.sf.oval.constraint.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@With
@Data
public class Department implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "departmentId")
    private Integer id;
    @NotNull(message = "This field is required!")
    @Length(max = 32, min = 2, message = "Incorrect name!")
    @CheckWith(
            value = IsUniqueNameCheck.class,
            message = "Department name must be unique!"
    )
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "department")
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    @JsonIgnoreProperties(value = "department")
    private List<Employee> employees;
}
