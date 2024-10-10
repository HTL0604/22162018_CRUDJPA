<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
  <label for="fname">Category name:</label><br>
  <input type="text" id="categoryname" name="categoryname"><br>
  <label for="lname">Link images:</label><br>
  <input type="text" id="images" name="images">
  <label for="lname">Upload File:</label><br>
  <input type="file" id="images1" name="images1">
  
  
  
  <label for="lname">Status:</label><br>
  <input type="radio" id="statuson" name="status" value="1">
  <label for="css">Hoat dong</label><br>
  <input type="radio" id="statusoff" name="status" value="0">
  <label for="javascript">Ngung</label>
   <input type="submit" value="Insert">
</form>