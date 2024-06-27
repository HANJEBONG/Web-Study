package com.sist.main;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.MusicDAO;
import com.sist.dao.MusicVO;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainClass().genieData();
	}
	public void genieData(){
		MusicVO vo=new MusicVO();
		try {
			int k=1;
			for(int i=1 ;i<=4;i++) {
				Document doc=Jsoup.connect("https://www.genie.co.kr/chart/top200?ditc=D&ymd=20240627&hh=15&rtm=Y&pg="+i).get();
				Elements title=doc.select("table.list-wrap td.info a.title");
				Elements singer=doc.select("table.list-wrap td.info a.artist");
				Elements album=doc.select("table.list-wrap td.info a.albumtitle");
				Elements poster=doc.select("table.list-wrap a.cover img");
				Elements etc=doc.select("table.list-wrap span.rank");
				for(int j=0;j<title.size();j++) {
					System.out.println(k);
					System.out.println(title.get(j).text());
					System.out.println(singer.get(j).text());
					System.out.println(album.get(j).text());
					System.out.println(poster.get(j).attr("src"));
					//System.out.println(etc.get(j).text());
					String ss=etc.get(j).text();
					String id="";
					String state="";
					if(ss.equals("유지")) {
						id="0";
						state="유지";
					}else {
						id=ss.replaceAll("[^0-9]", "");
						state=ss.replaceAll("[^가-힣]", "");
					}
					System.out.println("상태:"+state);
					System.out.println("등록:"+id);
					System.out.println("=========================");
					k++;
					vo.setTitle(title.get(j).text());
					vo.setSinger(singer.get(j).text());
					vo.setAlbum(album.get(j).text());
					vo.setPoster(poster.get(j).attr("src")); // attr => jsoup기능구성요소 값을 반환 src="값"
					vo.setState(state);
					vo.setIDcrement(Integer.parseInt(id));
					
					MusicDAO.newInstance().musicInsert(vo);
		
				}
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
