<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Video List</title>
</head>
<body>
	<a href="<c:url value='/admin/video/add'></c:url>">Add video</a>
	<table border="1">
		<tr>
			<th>STT</th>
			<th>Video ID</th>
			<th>Images</th>
			<th>Title</th>
			<th>Description</th>
			<th>Views</th>
			<th>Category</th>
			<th>Status</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${listvid}" var="vid" varStatus="STT">
			<tr>
				<td>${STT.index + 1}</td>
				<td>${vid.videoId}</td>

				<c:if test="${vid.poster.substring(0, 5) == 'https'}">
					<c:url value="${vid.poster}" var="imgUrl"></c:url>
				</c:if>

				<c:if test="${vid.poster.substring(0, 5) != 'https'}">
					<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
				</c:if>

				<td><img height="150" width="200" src="${imgUrl}" /></td>
				<td>${vid.title}</td>
				<td>${vid.description}</td>
				<td>${vid.views}</td>
				<td>${vid.category.categoryname}</td>
				<td>${vid.active}</td>
				<td><a
					href="<c:url value='/admin/video/edit?id=${vid.videoId}'/>">Sửa</a>
					| <a href="<c:url value='/admin/video/delete?id=${vid.videoId}'/>"
					onclick="return confirm('Bạn có chắc chắn muốn xóa video này?');">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>