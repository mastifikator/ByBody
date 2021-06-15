package com.ByBody.TrainingPlanner.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @Enumerated(value= EnumType.STRING)
    private Role role;
    @Enumerated(value= EnumType.STRING)
    private Status status;

}
