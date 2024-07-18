package com.sist.dao;
import java.util.*;

import lombok.Data;
/*
ID	VARCHAR2(20 BYTE)
PWD	VARCHAR2(10 BYTE)
NAME	VARCHAR2(51 BYTE)
SEX	CHAR(6 BYTE)
BIRTHDAY	VARCHAR2(10 BYTE)
POST	VARCHAR2(7 BYTE)
ADDR1	VARCHAR2(150 BYTE)
ADDR2	VARCHAR2(150 BYTE)
PHONE	VARCHAR2(13 BYTE)
EMAIL	VARCHAR2(100 BYTE)
CONTENT	CLOB
REGDATE	DATE
ADMIN	CHAR(1 BYTE)
 */
@Data
public class MemberVO {
	private String id,pwd,name,sex,birthday,post,addr1,addr2,phone,email,content,admin;
	private String msg;
	private Date regdate;
}
