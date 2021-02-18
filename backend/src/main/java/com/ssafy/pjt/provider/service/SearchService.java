package com.ssafy.pjt.provider.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.xml.sax.SAXException;

import com.ssafy.pjt.core.repository.mapper.SearchMapper;
import com.ssafy.pjt.util.XMLParserUtils;
import com.ssafy.pjt.web.dto.SearchDictDTO;

@Service
public class SearchService {

	@Autowired
	private SearchMapper mapper;
	@Autowired
	private SearchService self;

	@Value("${custom.law-search-url}")
	private String lawSearchUrl;
	@Value("${custom.law-service-url}")
	private String lawServiceUrl;

	public org.json.JSONObject searchByWordAndcountHit(String searchWord)
			throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		mapper.hit(searchWord);
		return self.searchByWord(searchWord);
	}

	@Cacheable(value = "cacheWord", key = "#searchWord")
	public org.json.JSONObject searchByWord(String searchWord)
			throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {
		return XMLParserUtils.convertXMLtoJSONObject(lawSearchUrl + URLEncoder.encode(searchWord, "UTF-8"));
	}

	@Cacheable(value = "cacheCode", key = "#code")
	public org.json.JSONObject searchByCode(String code)
			throws MalformedURLException, UnsupportedEncodingException, IOException {
		return XMLParserUtils.convertXMLtoJSONObject(lawServiceUrl + URLEncoder.encode(code, "UTF-8"));
	}

	public Map top() throws IOException {
		Map<String, List> result = new HashMap();
		result.put("items", mapper.selectHitTop());
		return result;
	}

	public List<SearchDictDTO> getDictAll() {
		return mapper.selectDictAll();
	}
}
