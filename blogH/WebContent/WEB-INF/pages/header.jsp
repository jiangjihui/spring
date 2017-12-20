<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="blog-masthead">
  <div class="container">
    <nav class="blog-nav">
      <a class="blog-nav-item active" href="#">主页</a>
      <a class="blog-nav-item" href="go/addnote">发布</a>
      <c:if test="${customer == null }"><a class="blog-nav-item" href="login">登录</a></c:if>
      <c:if test="${customer != null }">
      	<a class="blog-nav-item" href="customerinfo">个人信息</a>
      	<a class="blog-nav-item" href="logout">退出</a>
      	<a class="blog-nav-item navbar-right">用户：${customer.loginname }</a>
      </c:if>
      <form class="navbar-form navbar-right" action="search">
       	<input type="text" class="form-control" placeholder="Search..." name="condition" value="${condition }">
       	<button type="submit" class="btn btn-default">搜索</button>
      </form>
    </nav>
  </div>
</div>