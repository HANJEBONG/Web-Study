package com.sist.change;
//<%-- ../ => change directory => 다른 폴더에 있을때 쓴다 (한 폴더 뒤로)
//같은 폴더에서는 안쓴다           
//--%>
public class JspChange {
   private String[] jsp={"../goods/home.jsp",
		   "../goods/detail.jsp"};
   public String change(int no)
   {
	   return jsp[no];
   }
}