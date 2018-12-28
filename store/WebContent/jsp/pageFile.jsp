<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <div style="text-align:center">
            共${page.totalPageNum} 页/第${page.currentPageNum } 页
         <!--    ProductServlet?method=findProductByIdWithPage&cid=1 -->
    <a href="${pageContext.request.contextPath}/${page.url}&num=1">首页</a>
    <a href="${pageContext.request.contextPath}/${page.url}&num=${page.prePageNum}">上一页</a>
    <a href="${pageContext.request.contextPath}/${page.url}&num=${page.nextPageNum}">下一页</a>
     <a href="${pageContext.request.contextPath}/${page.url}&num=${page.totalPageNum}">末页</a>
   
   </div>

