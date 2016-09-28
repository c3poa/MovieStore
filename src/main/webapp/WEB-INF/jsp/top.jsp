<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="/mavenproject1/" />
<title>Online Movie Store</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="resources/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
  <div id="inner">
    <div id="header">
      <h1><img src="resources/images/logo.gif" width="519" height="63" alt="Online Movie Store" /></h1>
      <div id="nav"> <a href="http://www.free-css.com/">HOME</a> | <a href="cart">view cart</a> | <a href="http://www.free-css.com/">help</a> </div>
      <!-- end nav -->
      <a href="http://www.free-css.com/"><img src="resources/images/header_1.jpg" width="744" height="145" alt="Harry Potter cd" /></a> <a href="http://www.free-css.com/"><img src="resources/images/header_2.jpg" width="745" height="48" alt="" /></a> </div>
    <!-- end header -->
    <dl id="browse">
      <dt>Full Category Lists</dt>
      <c:forEach items="${categories}" var="category">
          <dd><a href="./${category.id}">${category.name}</a></dd>
      </c:forEach>
      <dt>Search Your Favourite Movie</dt>
      <dd class="searchform">
        <form action="http://www.free-css.com/" method="get">
          <div>
            <select name="cat">
              <option value="-" selected="selected">CATEGORIES</option>
              <option value="-">------------</option>
            </select>
          </div>
          <div>
            <input name="q" type="text" value="DVD TITLE" class="text" />
          </div>
          <div class="softright">
            <input type="image" src="resources/images/btn_search.gif" />
          </div>
        </form>
      </dd>
    </dl>
    <div id="body">
      <div class="inner">