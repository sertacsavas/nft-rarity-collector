package com.sertac.nft.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sertac.nft.service.AssetService;

@RestController
@RequestMapping(path = "/api/asset")
public class AssetController {

	@Autowired
	AssetService assetService;

	@GetMapping("/collectAssets")
	public void collectAssets(@RequestParam(name = "collectionId") Long collectionId)
			throws InterruptedException, ExecutionException {
		assetService.collectAssets(collectionId);
	}

	@GetMapping("/collectAll")
	public void collectAll() {
		assetService.collectAll();
	}

}
