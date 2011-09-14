package com.yourdomain.website.service;

import org.json.simple.JSONArray;

public interface AlbumWebService {

    public JSONArray getInfo(String userIds, String fields);

}
