package com.example.TeamTracker.Model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

//Setting the name of the Entity within class
@Table(
        name = "Users",
        uniqueConstraints = {@UniqueConstraint(
                name = "id",
                columnNames = {"id"}
        )}
)
@Entity(
        name = "User"
)
//Generating constructors, getters,and setters for this Obj
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User   {

        //Creating Auto-Generated primary key ID
        @Id
        @JoinColumn(name = "id")
        @GeneratedValue(
                strategy = GenerationType.AUTO
        )
        private Long id;

        //Creating Columns based on all attributes
        @Column(
        )
        private String username;


        @Column(
        )
        private String password;

        @Column(
        )
        @ManyToMany(fetch = FetchType.EAGER)
        private Collection<Role> roles = new ArrayList<>();

        @Column(
        )
        private String email;

        public Long getId() {
                return id;
        }
}