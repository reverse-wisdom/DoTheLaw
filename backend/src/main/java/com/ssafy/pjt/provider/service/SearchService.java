package com.ssafy.pjt.provider.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.ssafy.pjt.core.repository.mapper.SearchMapper;
import com.ssafy.pjt.util.XMLParserUtils;

@Service
public class SearchService {

	@Autowired
	private SearchMapper mapper;

	@Value("${custom.law-search-url}")
	private String lawSearchUrl;

	public org.json.JSONObject searchByWord(String searchWord) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		mapper.hit(searchWord);
		return XMLParserUtils.convertXMLtoJSONObject(lawSearchUrl + URLEncoder.encode(searchWord,"UTF-8"));
	}
}
