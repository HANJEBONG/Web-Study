package com.sist.dao;

import lombok.Data;

/*
 * fno NUMBER,
	name VARCHAR2(200) CONSTRAINT fh_name_nn NOT NULL,
	type VARCHAR2(200) CONSTRAINT fh_type_nn NOT NULL,
	phone VARCHAR2(30),
	address VARCHAR2(700),
	score NUMBER(2,1),
	theme CLOB,
	poster VARCHAR2(260) CONSTRAINT fh_poster_nn NOT NULL,
	content CLOB,
	hit NUMBER DEFAULT 0,
	jjimcount NUMBER DEFAULT 0,
	likecount NUMBER DEFAULT 0,
	CONSTRAINT fh_fno_pk PRIMARY KEY(fno)

 */
@Data
public class FoodVO {
	private int fno,hit,jjimcount,likecount;
	private double score;
	private String name,type,phone,address,theme,content,poster;
}
