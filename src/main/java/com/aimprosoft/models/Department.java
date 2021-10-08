package com.aimprosoft.models;

import com.aimprosoft.validation.department.IsUniqueNameCheck;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.sf.oval.constraint.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@With
@Data
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
