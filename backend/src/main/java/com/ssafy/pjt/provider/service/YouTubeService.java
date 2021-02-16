package com.ssafy.pjt.provider.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.ssafy.pjt.core.repository.mapper.YouTubeMapper;
import com.ssafy.pjt.web.dto.YouTubeDTO;

@Service
public class YouTubeService {

	@Autowired
	YouTubeMapper mapper;

	private static final String apiKey = "AIzaSyBZByBbTnWIMt7nHQL-VPmY9-8a-O-XVqg";
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 10;

	private static YouTube youtube;
	
	@Cacheable("youtubeCache")
	public List<YouTubeDTO> get() throws SQLException, IOException {
		List<YouTubeDTO> result = null;
		result = mapper.getTodayYouTube();
		if(result != null && result.size() > 0) {
			return result;
		}
		result = new ArrayList<YouTubeDTO>();
		try {
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
				@Override
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("youtube-cmdline-search-sample").build();

			String queryTerm = "한문철 TV";

			YouTube.Search.List search = youtube.search().list("id,snippet");

			search.setKey(apiKey);
			search.setQ(queryTerm);

			search.setType("video");

			search.setFields(
					"items(id/kind,id/videoId,snippet/title,snippet/publishedAt,snippet/description,snippet/channelId,snippet/thumbnails/high/url)");
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			SearchListResponse searchResponse = search.execute();

			List<SearchResult> searchResultList = searchResponse.getItems();

			if (searchResultList != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss.s'Z'");

				for (SearchResult item : searchResultList) {
					ResourceId rId = item.getId();

					if (rId.getKind().equals("youtube#video")) {
						YouTubeDTO dto = new YouTubeDTO();
						Thumbnail thumbnail = (Thumbnail) item.getSnippet().getThumbnails().getHigh();
						dto.setId(rId.getVideoId());
						dto.setTitle(item.getSnippet().getTitle());
						dto.setDescription(item.getSnippet().getDescription());
						dto.setThumbnail(thumbnail.getUrl());
						dto.setPublishedAt(sdf.parse(item.getSnippet().getPublishedAt().toString()));
						dto.setChannelId(item.getSnippet().getChannelId());
						System.out.println(dto);
						result.add(dto);
					}
				}
			}
		} catch (GoogleJsonResponseException e) {
			System.err.println(
					"There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
		} catch (IOException e) {
			System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		try {
			if(result.size() > 0)
				for(YouTubeDTO data : result) {
					mapper.setTodayYouTube(data);
				}
		}catch(Exception e) {
			System.err.println("유튜브 영상 삽입 중 오류가 발생하였습니다 : " + e.getCause() + " : " + e.getMessage());
		}
		return result;
	}

}