package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "User")// used for JPA repository
@Data // to generate getter and setter of the Movie class
@NoArgsConstructor  // to generate constructor with no arguments
@AllArgsConstructor   //to generate constructor with all arguments
@Builder
public class User {
    @Id //primary key
    private int id;
    private String firstName;
    private String lastName;

}
