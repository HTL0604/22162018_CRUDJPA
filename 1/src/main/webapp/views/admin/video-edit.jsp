<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>Edit Video</title>
    <script>
        function chooseFile(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('posters').src = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</head>
<body>
    <h2>Edit Video</h2>
    <form action="<c:url value='/admin/video/update'/>" method="post" enctype="multipart/form-data">
        <!-- Hidden Field for Video ID -->
        <input type="hidden" name="videoid" value="${vid.videoId}" />

        <!-- Title Field -->
        <label for="title">Title:</label><br> 
        <input type="text" id="title" name="title" value="${vid.title}" required><br><br>

        <!-- Description Field -->
        <label for="description">Description:</label><br> 
        <textarea id="description" name="description" rows="4" cols="50" required>${vid.description}</textarea><br><br>

        <!-- Views Field -->
        <label for="views">Views:</label><br> 
        <input type="number" id="views" name="views" min="0" value="${vid.views}" required><br><br>

        <!-- Category ID Field (Dropdown) -->
        <label for="categoryId">Category:</label><br> 
        <select id="categoryId" name="categoryId" required>
            <option value="" disabled>Select a category</option>
            <c:forEach items="${categoryList}" var="category">
                <option value="${category.id}" 
                    <c:if test="${category.id == vid.category.id}">selected</c:if>>
                    ${category.name}
                </option>
            </c:forEach>
        </select><br><br>

        <!-- Image Upload Field -->
        <label for="poster">Images:</label><br> 
        <img height="150" width="200" src="<c:url value='/image?fname=${vid.poster}'/>" id="posters" /> 
        <input type="file" onchange="chooseFile(this)" id="poster" name="poster"><br><br>

        <!-- Status Field -->
        <p>Status:</p>
        <input type="radio" id="ston" name="active" value="1" 
            <c:if test="${vid.active == 1}">checked</c:if>> 
        <label for="ston">Đang hoạt động</label><br> 
        <input type="radio" id="stoff" name="active" value="0" 
            <c:if test="${vid.active == 0}">checked</c:if>> 
        <label for="stoff">Khóa</label><br><br>

        <!-- Submit Button -->
        <input type="submit" value="Update">
    </form>
</body>
</html>