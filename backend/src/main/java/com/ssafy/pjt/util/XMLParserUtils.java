package com.ssafy.pjt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.XML;

public class XMLParserUtils {
	
	static public org.json.JSONObject convertXMLtoJSONObject(String url) throws MalformedURLException, IOException{
		Reader xmlSource = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		org.json.JSONObject xmlJSONObj = XML.toJSONObject(xmlSource);
		return xmlJSONObj;
	}
}
