package com.ssafy.pjt.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {

//    @JsonIgnore
    @Id
    @Column(name = "UUID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long uuid;
    
    @Column(name = "EMAIL", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;
    
    @JsonIgnore
    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;
    
    @Column(name = "PHONE", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String phone;
    
    @Column(name = "NAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @Column(name = "ROLE", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String role;
    
    @Column(name = "JOIN_DATE", length = 50)
    @Size(min = 4, max = 50)
    private String join_date;

}