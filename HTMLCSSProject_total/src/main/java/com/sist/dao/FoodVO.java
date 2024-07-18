package com.sist.dao;

import lombok.Data;
/*
 * 	  desc table_name : 확인
 * 
 * 	  변수명 = 컬럼명 동일
 *    => 데이터를 브라우저로 전송할 목록 VO (~DTO) => JSP (Bean)
 *       =============== 해킹
 *         | 캡슐화 사용 => 변수 (private) , 메소드를 이용해서 접근
 *      데이터형 일치
 *      ======== 테이블 한개 => VO / DAO
 *        오라클 데이터형
 *        =========
 *         CHAR , VARCHAR2 , CLOB => String
 *         NUMBER => int / double
 *         DATE => java.util.Date (java.sql.date)
 *         		   => 오늘 : SYSDATE
 *         		   => 예약일 : YY/MM/DD => TO_DATE로 변환후에 INSERT
 *         MyBatis / JPA => 컬럼이 다르면 변경
 *         					아예 변수명을 컬럼명과 맞춰주는게 편하다
 *         
 *         goods_name
 *         
 *         @Column(name="goods_name")
 *         String name;
 */
@Data
public class FoodVO {
	private int fno;
	private String name,type,phone,address,theme,poster,content;
	private double score;
}
