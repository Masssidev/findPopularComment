package com.naver.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comment {

    private int id;
    private int post_id;
    private int user_id;
    private String body;
    private int empathy;
    private int not_empathy;

}