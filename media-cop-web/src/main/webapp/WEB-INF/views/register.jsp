<%--
  Created by IntelliJ IDEA.
  User: divya
  Date: 20/10/16
  Time: 10:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Media Cop</title>
</head>
<body>

<h1><centre>Register Hashtag</centre></h1>

<form method="post" action="addHashtag">
    Name   :  <input type="text" name="name"/> <br/><br/>
    Rank  :  <input type="text" name="rank"/> <br/><br/>
    Priority :  <input type="text" name="priority"/> <br/><br/>
    Country :  <input type="text" name="country"/> <br/><br/>
    <input type="submit" value="Register"/>
</form>

</body>
</html>
