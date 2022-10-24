package com.example.TeamTracker.Model;

import lombok.*;

import javax.persistence.*;
//Allowing this class to Create a TABLE based on Entity"s Attributes

@Table(
        name = "Views",
        uniqueConstraints = {@UniqueConstraint(
                name = "id",
                columnNames = {"id"}
        )}
)

//Setting the name of the Entity within class
@Entity(
        name = "view"
)

//Generating constructors, getters,and setters for this Obj
@ToString
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class View {

    //Creating Auto-Generated primary key ID
    @Id
    @JoinColumn(name = "id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "view_sequence"
    )

    private Long id;

    //Creating Columns based on all attributes
    @Column()
    private String nameOfView;

    @Column
    private String owner;

    @Column(columnDefinition = "LONGTEXT")
    private String selectedColumns;

    public Long getID() {
        return this.id;
    }

    public String getSelectedColumns() {
        return selectedColumns;
    }
}
