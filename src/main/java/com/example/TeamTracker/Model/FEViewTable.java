package com.example.TeamTracker.Model;


import lombok.*;
import org.hibernate.annotations.Type;

import java.util.List;

@ToString
@EqualsAndHashCode
//Generating constructors, getters,and setters for this Obj
@Setter
@Getter
@AllArgsConstructor

public class FEViewTable {

    private Integer id;
    private String  name;
    private String json;
    private String type;

    @Type(type = "string-array" )
    private List select;
}
