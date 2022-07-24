package com.godcoder.myhome.model;


import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
