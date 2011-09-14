package com.yourdomain.website.service.impl;

import com.renren.api.client.RenrenApiInvoker;
import com.renren.api.client.services.impl.AbstractService;
import com.yourdomain.website.service.AlbumWebService;
import org.json.simple.JSONArray;

import java.util.TreeMap;

public class AlbumServiceImpl extends AbstractService implements AlbumWebService {

    public AlbumServiceImpl(RenrenApiInvoker renrenApiInvoker) {
        super(renrenApiInvoker);
    }

    public JSONArray getInfo(String userIds, String fields) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("method", "photos.getAlbums");
        params.put("uid", userIds);
        params.put("fields", fields);
        return this.getResultJSONArray(params);
    }
}
