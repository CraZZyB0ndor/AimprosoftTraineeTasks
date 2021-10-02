package com.aimprosoft.models;

import com.aimprosoft.validation.department.IsUniqueNameCheck;
import lombok.Data;
import net.sf.oval.constraint.*;

import java.io.Serializable;

@Data
public class Department implements Serializable {

    private Integer id;
    @NotNull(message = "This field is required!")
    @Length(max = 32, min = 2, message = "Incorrect name!")
    @CheckWith(
            value = IsUniqueNameCheck.class,
            message = "Department name must be unique!"
    )
    private String name;

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
