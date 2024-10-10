<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="${pageContext.request.contextPath }/admin/category/update" method="post" enctype="multipart/form-data">
	 <input type="text"id="categoryid" name="categoryid" value="${cate.categoryid}" hidden="hidden"><br> 
	<label for="categoryname">Category Name:</label><br>
	 <input type="text"id="categoryname" name="categoryname" value="${cate.categoryname }"><br> 
	 <label for="images">Images:</label><br> 
				<c:choose>
					<c:when test="${cate.images != null && cate.images.length() >= 5 && cate.images.substring(0,5) != 'https'}">
						<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
					</c:when>
					<c:otherwise>
						<c:url value="${cate.images}" var="imgUrl"></c:url>
					</c:otherwise>
				</c:choose>
				<img id="imagess" style="max-height: 150px; max-width: 200px;" src="${imgUrl}" />	
	 <input type="file" onchange="chooseFile(this)" id="images"name="images" value="${cate.images}"><br>
	 <label for="status">Status:</label><br> 
	 <input type="radio" id="ston" name="status" value="1" ${cate.status==1?'checked':'' }>
	 <lable for="html">Đang hoạt động</lable><br>
	 <input type="radio" id="stoff" name="status" value="0" ${cate.status!=1?'checked':'' }>
	 <label for="css">Khoá</label><br>
     <br> <input type="submit" value="Update">
</form>