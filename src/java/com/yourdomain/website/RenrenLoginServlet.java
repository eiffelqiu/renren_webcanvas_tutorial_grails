package com.yourdomain.website;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.utils.HttpURLUtils;
import com.yourdomain.website.config.AppConfig;
import com.yourdomain.website.dao.RenrenUserMappingDAO;
import com.yourdomain.website.dao.UserDAO;
import com.yourdomain.website.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用于处理用人人网帐号登录的Servlet，
 * 当从人人网的OAuth 2.0服务器跳转回来时，会到达这个Servlet，参数中会有code
 */
@SuppressWarnings("serial")
public class RenrenLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        String code = request.getParameter("code");
        if (code == null || code.length() == 0) {
            //缺乏有效参数，跳转到登录页去
            response.sendRedirect("/login");
            return;
        }
        //到人人网的OAuth 2.0的token endpoint用code换取access token
        String rrOAuthTokenEndpoint = "https://graph.renren.com/oauth/token";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", AppConfig.API_KEY);
        parameters.put("client_secret", AppConfig.APP_SECRET);
        parameters.put("redirect_uri", "http://www.yourdomain.com:8080/" + AppConfig.APP_NAME + "/rr_login");
        parameters.put("grant_type", "authorization_code");
        parameters.put("code", code);
        String tokenResult = HttpURLUtils.doPost(rrOAuthTokenEndpoint, parameters);
        JSONObject tokenJson = (JSONObject) JSONValue.parse(tokenResult);
        if (tokenJson != null) {
            String accessToken = (String) tokenJson.get("access_token");
			System.out.println("AccessToken: [" + accessToken + "]");
			request.getSession().setAttribute("accessToken", accessToken);
            Long expiresIn = (Long) tokenJson.get("expires_in");//距离过期时的时间段（秒数）
            long currentTime = System.currentTimeMillis() / 1000;
            long expiresTime = currentTime + expiresIn;//即将过期的时间点（秒数）
            request.getSession().setAttribute("expiresTime", expiresTime);
            //调用人人网API获得用户信息
            RenrenApiClient apiClient = new RenrenApiClient(accessToken, true);
            int rrUid = apiClient.getUserService().getLoggedInUser();
            JSONArray userInfo = apiClient.getUserService().getInfo(String.valueOf(rrUid), "name,headurl,tinyurl,read_user_photo,read_user_album");
            if (userInfo != null && userInfo.size() > 0) {
                JSONObject currentUser = (JSONObject) userInfo.get(0);

                if (currentUser != null) {

                    String name = (String) currentUser.get("name");
                    String headurl = (String) currentUser.get("headurl");
                    String tinyurl = (String) currentUser.get("tinyurl");

                    request.getSession().setAttribute("apiClient", apiClient);
                    // 获取第一个相册
//                    AlbumWebService albumService = new AlbumServiceImpl(apiClient.getRenrenApiInvoker());
//                    System.out.println("ouid " + rrUid);
//                    JSONArray albumInfo = albumService.getInfo(String.valueOf(rrUid), "name,url");
//                    if (albumInfo != null && albumInfo.size() > 0) {
//                        JSONObject currentAlbum = (JSONObject) albumInfo.get(0);
//                        String albumName = (String) currentAlbum.get("name");
//                        String url = (String) currentAlbum.get("url");
//                        Album album = new Album();
//                        album.setName(albumName);
//                        album.setUrl(url);
//                        request.getSession().setAttribute("album", album);
//                    }

                    //判断帐号关联表里有没有现成的关联
                    String username = RenrenUserMappingDAO.getInstance().getUsername(rrUid);
                    User user;
                    if (username == null) {
                        //在帐号关联表里没有记录，用户是第一次来；为这个用户创建一个User对象
                        User newUser = new User();
                        newUser.setName(name);
                        newUser.setHeadurl(headurl);
                        newUser.setTinyurl(tinyurl);
						newUser.setUid("" + currentUser.get("uid"));
						System.out.println("uid: [" + currentUser.get("uid") + "]");
                        //自动拼装一个username并随即生成一个password；实际实现时，这里应该保证
                        username = "renren-" + rrUid;
                        String password = UUID.randomUUID().toString();
                        newUser.setUsername(username);
                        newUser.setPassword(password);
                        //保存到用户表
                        UserDAO.getInstance().addUser(newUser);
                        //保存到帐号关联表
                        RenrenUserMappingDAO.getInstance().addMapping(rrUid, username);
                        user = newUser;
                    } else {
                        //用户不是第一次来了，已经在帐号关联表里有了
                        user = UserDAO.getInstance().getUser(username);
                    }
                    //将用户身份信息保存在会话里
                    request.getSession().setAttribute("user", user);
                    //已登录，跳转到个人主页
                    response.sendRedirect("/" + AppConfig.APP_NAME + "/event/list");
                    return;
                }
            }
        }
        response.sendRedirect( "/" + AppConfig.APP_NAME + "/login");
    }
}