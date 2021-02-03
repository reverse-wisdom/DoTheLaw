package com.ssafy.pjt.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {

//    @JsonIgnore
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long uuid;
    
    @Column(name = "email", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;
    
    @JsonIgnore
    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;
    
    @Column(name = "phone", length = 50, unique = true)
    @Size(min = 4, max = 50)
    private String phone;
    
    @Column(name = "name", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @Column(name = "role", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String role;
    
    @Column(name = "join_date", length = 50)
    @Size(min = 4, max = 50)
    private String joinDate;
    
    @Column(name = "image", length = 300)
    private String image;
}