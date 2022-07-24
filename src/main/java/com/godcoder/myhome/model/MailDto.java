package com.godcoder.myhome.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDto {

    private String address;
    private String title;
    private String content;
}
