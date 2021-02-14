package com.ssafy.pjt.provider.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.core.repository.mapper.EasylawMapper;
import com.ssafy.pjt.web.dto.EasylawDTO;

@Service
public class EasylawService {
	@Autowired
	EasylawMapper mapper;
	
	public EasylawDTO searchEasylawByKeyword(String keyword) throws SQLException {
		return mapper.selectByKeyword(keyword);
	}
	
	public EasylawDTO searchEasylawById(int id) throws SQLException {
		return mapper.selectById(id);
	}
	
	public List<EasylawDTO> searchAllEasylawByKeyword(String keyword) throws SQLException{
		return mapper.searchBykeyword(keyword);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getMenu() {
		List result = new ArrayList<>();
		Map m1, m2;
		m1 = new HashMap();
		m1.put("name", "가정법률");
		m1.put("type", 1);
		m2 = new HashMap();
		m2.put("on", "https://www.easylaw.go.kr/CSP/images/common/bg_cate1_on.png");
		m2.put("off", "https://www.easylaw.go.kr/CSP/images/common/bg_cate1.png");
		m1.put("img", m2);
		result.add(m1);
		m1 = new HashMap();
		m1.put("name", "부동산/임대차");
		m1.put("type", 3);
		m2 = new HashMap();
		m2.put("on", "https://www.easylaw.go.kr/CSP/images/common/bg_cate3_on.png");
		m2.put("off", "https://www.easylaw.go.kr/CSP/images/common/bg_cate3.png");
		m1.put("img", m2);
		result.add(m1);
		m1 = new HashMap();
		m1.put("name", "금융/금전");
		m1.put("type", 4);
		m2 = new HashMap();
		m2.put("on", "https://www.easylaw.go.kr/CSP/images/common/bg_cate4_on.png");
		m2.put("off", "https://www.easylaw.go.kr/CSP/images/common/bg_cate4.png");
		m1.put("img", m2);
		result.add(m1);
		m1 = new HashMap();
		m1.put("name", "교통/운전");
		m1.put("type", 11);
		m2 = new HashMap();
		m2.put("on", "https://www.easylaw.go.kr/CSP/images/common/bg_cate11_on.png");
		m2.put("off", "https://www.easylaw.go.kr/CSP/images/common/bg_cate11.png");
		m1.put("img", m2);
		result.add(m1);
		m1 = new HashMap();
		m1.put("name", "근로/노동");
		m1.put("type", 12);
		m2 = new HashMap();
		m2.put("on", "https://www.easylaw.go.kr/CSP/images/common/bg_cate12_on.png");
		m2.put("off", "https://www.easylaw.go.kr/CSP/images/common/bg_cate12.png");
		m1.put("img", m2);
		result.add(m1);
		m1 = new HashMap();
		m1.put("name", "정보통신/기술");
		m1.put("type", 15);
		m2 = new HashMap();
		m2.put("on", "https://www.easylaw.go.kr/CSP/images/common/bg_cate15_on.png");
		m2.put("off", "https://www.easylaw.go.kr/CSP/images/common/bg_cate15.png");
		m1.put("img", m2);
		result.add(m1);
		return result;
	}

	@Cacheable(value = "lawType", key = "#type")
	public List crawlingEasyLawList(int type) {
		final String URL = "https://www.easylaw.go.kr/CSP/CsmSortRetrieveLst.laf?sortType=cate&csmAstSeq=";
		List result = new ArrayList();
		try{
			Connection conn = Jsoup.connect(URL + type).followRedirects(false).timeout(3000);
			Document html = conn.get();
			Elements elements = html.select("#contents > div.img_list > ul > li");
			for(Element elm : elements) {
				Map map = new HashMap();
				String title = elm.child(0).text(); // 제목
				String href = elm.child(1).attr("href"); // 주소
				String content = elm.child(1).attr("title");// 내용
				map.put("title", title);
				map.put("href", href.replace("/CSP/CsmMain.laf?csmSeq=", "/api/easylaw/detail?no="));
				map.put("no", href.replace("/CSP/CsmMain.laf?csmSeq=", ""));
				map.put("content", content);
				result.add(map);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Cacheable(value = "lawDetail", key = "#no")
	public Map detail(int no) {
		final String DETAIL_URL = "https://www.easylaw.go.kr/CSP/CsmMain.laf?csmSeq=";
		final String MAIN_URL = "https://www.easylaw.go.kr/CSP/CnpClsMain.laf?popMenu=ov&ccfNo=1&cciNo=1&cnpClsNo=1&csmSeq=";
		Map result = new HashMap();
		try {
			Connection conn = Jsoup.connect(DETAIL_URL + no).followRedirects(false).timeout(100000);
			Connection conn2 = Jsoup.connect(MAIN_URL + no).followRedirects(false).timeout(100000);
			Document html = conn.get();
			Document html2 = conn2.get();
			Element imageElm = html.selectFirst(".toon_img > img");
			Element summaryElm = html.selectFirst("div.toon_txt");
			Element contentElm = html2.selectFirst("#ovDiv > div");
			String image = imageElm.attr("src");
			String summary = summaryElm.text();
			String content = contentElm.html().replaceAll("<img src=\"/", "<img src=\"https://www.easylaw.go.kr/");
			result.put("image", "https://www.easylaw.go.kr"+ image);
			result.put("summary", summary);
			result.put("content", content);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
