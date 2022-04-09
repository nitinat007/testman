package com.testman.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Author: kunitin
 * Created: 09/04/22
 * Info: User information to persist in DB
 **/


@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name; //actual full name

    @NonNull
    @Column(unique = true, nullable = false, updatable = false)
    @NotEmpty(message = "loginName can not be empty")
    @Size(min = 3, message = "login name must be between 3 & 20 character", max = 20)
    private String loginName; // can be ldap or shortname

    @Column(length = 20)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password constraints (atleast): 8 char, one upper case, one lower case , one special character & one number")
    private String password; //ToDo: to  encrypt later

    @Column
    private boolean isActive;

    @Column
    private Long prodIds; //products in which the user is working. ToDo: Reference ID of Product table

    @Column
    private String userRoles; // ToDo: Make it accept only Enum values: Admin,Tester,Reviewer. See if Annotation can be added for this

}
