<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<a href="${pageContext.request.contextPath }/admin/category/add">Add Category</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>CategoryID</th>
		<th>CategoryName</th>
		<th>Status</th>
		<th>Action</th>
	</tr>

	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td>${STT.index + 1}</td>
			<td>
				<c:choose>
					<c:when test="${cate.images != null && cate.images.length() >= 5 && cate.images.substring(0,5) != 'https'}">
						<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
					</c:when>
					<c:otherwise>
						<c:url value="${cate.images}" var="imgUrl"></c:url>
					</c:otherwise>
				</c:choose>
				<img style="max-height: 150px; max-width: 200px;" src="${imgUrl}" />
			</td>

			<td>${cate.categoryid}</td>
			<td>${cate.categoryname}</td>

			<td>
				<%-- <c:choose>
					<c:when test="${cate.status == true}">
						Active
					</c:when>
					<c:otherwise>
						Inactive
					</c:otherwise>
				</c:choose> --%>
				<c:if test="${cate.status == 1 }">
					<span>Hoạt động</span>
				</c:if>
				
				<c:if test="${cate.status != 1 }">
					<span>Khoá</span>
				</c:if>
			</td>

			<td>
				<a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Sửa</a> |
				<a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table>