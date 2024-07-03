<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
 <%
 		 GoodsDAO dao=GoodsDAO.newInstance();
 		 String no=request.getParameter("no");
 		 GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no));
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.container {
	margin-top:50px;
}
.row{
	width: 960px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<div class="container">
	  <div class="row">
	    <table class="table table-striped">
	      <tr>
	        <td width="30%" class="text-center" rowspan="6">
	          <img src="<%=vo.getGoods_poster()%>" style="width:100%" class="img-rounded">
	        </td>
	        <td colspan="2"><h3><%=vo.getGoods_name() %></h3></td>
	      </tr>
	      <tr>
	        <td><%=vo.getGoods_sub() %></td>
	      </tr>
	      <tr>
	        <th><%=vo.getGoods_discount() %></th>
	        <td><%=vo.getGoods_price() %></td>
	      </tr>
	      <tr>
	        <td><%=Integer.parseInt(vo.getGoods_price().replaceAll("[^0-9]", ""))/(1-(vo.getGoods_discount()*0.01))%></td>
	      </tr>
	      <tr>
	        <th>첫구매할인가</th>
	        <td><%=vo.getGoods_first_price()%></td>
	      </tr>
	      <tr>
	        <th></th>
	        <td><%=vo.getGoods_delivery() %></td>
	      </tr>
	      <tr>
	        <td colspan="2" class="text-right">
	          <input type="button" value="담기" class="btn-xs btn-primary">
	          <input type="button" value="좋아요" class="btn-xs btn-success">
	          <input type="button" value="추천" class="btn-xs btn-info">
	          <input type="button" value="목록" class="btn-xs btn-warning"
	           onclick="javascript:history.back()"
	          >
	        </td>
	      </tr>
	    </table>
	  </div>
	</div>
</body>
</html>