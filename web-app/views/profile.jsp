<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Renren Website Demo -- Profile</title>
</head>
<body>
<img src="${user.tinyurl}"/> <a href="/logout">退出</a> <br/>
你好，${user.name}，欢迎使用本网站。 <br/>
相册 <p/>
<img src="${album.url}"/>
</body>
</html>