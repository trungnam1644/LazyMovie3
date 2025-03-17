<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Phim Lẻ</title>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>
    <div class="container">
        <h1>Phim Lẻ</h1>
        <div class="movie-list">
            <c:forEach var="movie" items="${requestScope.phimleList}">
                <div class="movie-item">
                    <img src="${movie.thumbnailURL}" alt="${movie.title}">
                    <h3>${movie.title}</h3>
                    <p>${movie.description}</p>
                    <p>Năm phát hành: ${movie.releaseYear}</p>
                    <p>Đánh giá: ${movie.rating}/10</p>
                    <a href="MovieDetailController?movieID=${movie.movieID}">Xem chi tiết</a>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
