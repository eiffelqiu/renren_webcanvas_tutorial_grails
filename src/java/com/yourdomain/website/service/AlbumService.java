package com.yourdomain.website.service;

import org.json.simple.JSONArray;

public interface AlbumService {

    public JSONArray getInfo(String userIds, String fields);

}
