package com.sist.main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainClass().foodData();
	}
	public void foodData() {
		FoodDAO dao=FoodDAO.newInstance();
		try {
			int k=1;
			for(int i=1;i<=347;i++) {
				Document doc=Jsoup.connect("https://www.menupan.com/restaurant/bestrest/bestrest.asp?page="+i+"&trec=8674&pt=rt").get();
				Elements link=doc.select("ul.list p.listName a");
				for(int j=0;j<link.size();j++) {
					try {
					System.out.println("업체번호:"+(k++));
					System.out.println(link.get(j).attr("href"));
					String url="https://www.menupan.com"+link.get(j).attr("href");
					// 상세보기로 이동
					Document doc2=Jsoup.connect(url).get();
					// 여러개를 가지고올땐 select 첫번째 하나만 가지고 올땐 selectFirst
					Element poster=doc2.selectFirst("div.areaThumbnail ul#id_restphoto_list_src img");
					System.out.println(poster.attr("src"));
					Element name=doc2.selectFirst("div.areaBasic dd.name");
					// .text() 가 태그에 내용을 가져옴
					System.out.println(name.text().substring(0,name.text().indexOf("[")));
					Element type=doc2.selectFirst("div.areaBasic dd.type");
					System.out.println(type.text());
					Element phone=doc2.selectFirst("div.areaBasic dd.tel1");
					System.out.println(phone.text());
					Element address=doc2.selectFirst("div.areaBasic dd.add1");
					System.out.println(address.text());
					Element score=doc2.selectFirst("div.areaBasic span.total");
					System.out.println(score.text());
					Element theme=doc2.selectFirst("div.areaBasic dd.Theme");
					System.out.println(theme.text());
					
					Element content=doc2.selectFirst("div.article div#info_ps_f");
					System.out.println(type.text());
					
					FoodVO vo=new FoodVO();
					vo.setName(name.text());
					vo.setType(type.text());
					vo.setPhone(phone.text());
					vo.setAddress(address.text());
					vo.setScore(Double.parseDouble(score.text()));
					vo.setTheme(theme.text());
					vo.setPoster(poster.attr("src"));
					
					dao.foodInsert(vo);
					}catch(Exception ex) {}
				}
			}
		}catch(Exception ex) {}
	}
}
