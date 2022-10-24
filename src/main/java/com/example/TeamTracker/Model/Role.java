package com.example.TeamTracker.Model;

import lombok.*;
import javax.persistence.*;
//Allowing this class to Create a TABLE based on Entity"s Attributes
@Table(
        name = "Roles",
        uniqueConstraints = {@UniqueConstraint(
                name = "id",
                columnNames = {"id"}
        )}
)
//Setting the name of the Entity within class
@Entity(
        name = "Role"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    //Creating Auto-Generated primary key ID
    @Id
    @JoinColumn(name = "id")
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )


    private Long id;

    @Column(
    )
    private String name;

}
