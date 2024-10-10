<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form action="<c:url value='/admin/video/insert'></c:url>" method="post"
	enctype="multipart/form-data">
	<!-- Video ID Field -->
	<label for="videoId">Video ID:</label><br> <input type="text"
		id="videoId" name="videoId" required><br> <br>
	<!-- Title Field -->
	<label for="title">Title:</label><br> <input type="text"
		id="title" name="title" required><br> <br>

	<!-- Description Field -->
	<label for="description">Description:</label><br>
	<textarea id="description" name="description" rows="4" cols="50"
		required></textarea>
	<br> <br>

	<!-- Views Field -->
	<label for="views">Views:</label><br> <input type="number"
		id="views" name="views" min="0" value="0" required><br> <br>

	<!-- Category ID Field (Dropdown) -->
	<label for="categoryId">Category:</label><br> <select
		id="categoryid" name="categoryid" required>
		<option value="">-- Select Category --</option>
		<c:forEach items="${listcate}" var="category">
			<option value="${cate.categoryId}">${cate.categoryname}</option>
		</c:forEach>
	</select><br>
	<br>



	<!-- Image Upload Field -->
	<label for="poster">Images:</label><br> <img height="150"
		width="200" src="" id="posters" /> <input type="file"
		onchange="chooseFile(this)" id="poster" name="poster"><br>
	<br>

	<!-- Status Field -->
	<p>Status:</p>
	<input type="radio" id="ston" name="active" value="1" checked>
	<label for="ston">Đang hoạt động</label><br> <input type="radio"
		id="stoff" name="active" value="0"> <label for="stoff">Khóa</label><br>
	<br>

	<!-- Submit Button -->
	<input type="submit" value="insert">
</form>
</body>
</html>