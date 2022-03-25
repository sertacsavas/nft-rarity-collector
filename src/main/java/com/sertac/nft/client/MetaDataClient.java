package com.sertac.nft.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sertac.nft.model.Asset;

@Service
public class MetaDataClient {

	public Asset getMetaData(String apiUrl) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<Asset> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Asset.class);

		return response.getBody();
	}
}
