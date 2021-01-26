package com.ssafy.pjt.provider.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class RSSservice {
	public List parsingNews() throws SAXException, IOException, ParserConfigurationException {
		List<Map<String, String>> result = new ArrayList<>();
		String url = "http://www.evernews.co.kr/rss/S1N8.xml";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(url);
		doc.getDocumentElement().normalize();
//		System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("item");
//		System.out.println("파싱할 리스트 수 : " + nList.getLength());
		for (int i = 0; i < nList.getLength(); i++) {
			NodeList childs = nList.item(i).getChildNodes();
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < childs.getLength(); j++) {
				Node node = childs.item(j);
				if (node.getNodeName().equals("title")) {
					map.put("title", node.getTextContent());
				} else if (node.getNodeName().equals("link")) {
					map.put("link", node.getTextContent());
				}
			}

			result.add(map);
		}
		return result;
	}

//	public static void main(String[] args) {
//		try {
//			new RSSservice().parsingNews();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
