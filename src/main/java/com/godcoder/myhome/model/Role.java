package com.godcoder.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // .IDENTITY < 기본키 생성을 DB에 위임
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
