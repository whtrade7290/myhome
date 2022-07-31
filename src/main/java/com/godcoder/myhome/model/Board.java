package com.godcoder.myhome.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// @Entity < JPA가 직접 관리하는 VO
@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // .IDENTITY < 기본키 생성을 DB에 위임
    private Long id;
    @NotNull
    @Size (min=2, max=30, message = "제목은 2자 이상 30자 이하입니다.")
    //primary key 및 size 및 메시지 지정 가능
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

}
