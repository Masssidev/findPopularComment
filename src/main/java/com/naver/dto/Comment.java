package com.naver.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Customer DTO
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comment implements Serializable {

	private static final long serialVersionUID = -3727192529926155993L;
	
	private int id;
    private int post_id;
    private int user_id;
    private String body;
    private int empathy;
    private int not_empathy;

}