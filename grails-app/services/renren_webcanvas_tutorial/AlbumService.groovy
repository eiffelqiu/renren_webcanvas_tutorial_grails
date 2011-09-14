package renren_webcanvas_tutorial

import com.yourdomain.website.model.Album
import com.yourdomain.website.service.AlbumWebService
import com.yourdomain.website.service.impl.AlbumServiceImpl
import org.json.simple.JSONArray
import org.json.simple.JSONObject

class AlbumService {

    static transactional = true

    def fetchAlbum(apiClient) {
        AlbumWebService alservice = new AlbumServiceImpl(apiClient.getRenrenApiInvoker());
        int rrUid = apiClient.getUserService().getLoggedInUser();
        JSONArray albumInfo = alservice.getInfo(String.valueOf(rrUid), "name,url");
        if (albumInfo != null && albumInfo.size() > 0) {
            JSONObject currentAlbum = (JSONObject) albumInfo.get(0);
            String albumName = (String) currentAlbum.get("name");
            String url = (String) currentAlbum.get("url");
            Album album = new Album();
            album.setName(albumName);
            album.setUrl(url);
            return album;
        }
    }
}
