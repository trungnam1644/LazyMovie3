<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh Sửa Phim</title>
        <link rel="stylesheet" href="css/editMovie.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="logo-lazymoive">
            <a href="home.jsp">
                <img src="img/logo.jpg" alt="Logo">
            </a>    
        </div>

        <div class="form-container">
            <h2>Chỉnh Sửa Phim</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="excuteeditMovie">
                <input type="hidden" name="movieID" value="${movie.movieID}">
                <div class="form-group">
                    <label for="movieID">ID Phim:</label>
                    <input type="text" id="movieID" name="movieID" value="${movie.movieID}" readonly>
                </div>

                <div class="form-group">
                    <label for="title">Tên Phim:</label>
                    <input type="text" id="title" name="title" value="${movie.title}" required>
                </div>

                <div class="form-group">
                    <label for="description">Mô Tả:</label>
                    <textarea id="description" name="description" required>${movie.description}</textarea>
                </div>

                <div class="form-group">
                    <label for="releaseYear">Năm Phát Hành:</label>
                    <input type="number" id="releaseYear" name="releaseYear" value="${movie.releaseYear}" min="1900" max="2100" required>
                </div>

                <div class="form-group">
                    <label for="country">Quốc Gia:</label>
                    <select id="country" name="countryID" required>
                        <c:forEach var="country" items="${countries}">
                            <option value="${country.countryID}" ${movie.countryID == country.countryID ? 'selected="selected"' : ''}>
                                ${country.countryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="genre">Thể Loại:</label>
                    <select id="genre" name="genres" multiple="multiple" required>
                        <c:forEach var="genre" items="${genres}">
                            <option value="${genre.genreID}" ${movieGenreIDs.contains(genre.genreID) ? 'selected="selected"' : ''}>
                                ${genre.genreName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="movieType">Loại Phim:</label>
                    <select id="movieType" name="movieTypeID" required>
                        <c:forEach var="type" items="${movieTypes}">
                            <option value="${type.movieTypeID}" ${movie.movieTypeID == type.movieTypeID ? 'selected="selected"' : ''}>
                                ${type.movieTypeName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="rating">Đánh Giá:</label>
                    <input type="number" id="rating" name="rating" step="0.1" min="0" max="5" value="${movie.rating}" required>
                </div>

                <div class="form-group">
                    <label for="videoUrl">URL Video:</label>
                    <input type="url" id="videoUrl" name="videoURL" value="${movie.videoURL}" required>
                </div>


                <div class="form-group">
                    <label for="thumbnailUrl">URL Ảnh:</label>
                    <input type="url" id="thumbnailUrl" name="thumbnailURL" value="${movie.thumbnailURL}" required>
                </div>

                <div class="form-group">
                    <label for="username">Người Chỉnh Sửa:</label>
                    <input type="text" id="userName" name="userName" value="${User.userName}" readonly>
                </div>

                <div class="button-group">
                    <button type="submit">Lưu Thay Đổi</button>
                    <a href="admin1.jsp" class="cancel-button">Hủy</a>
                </div>
            </form>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#genre').select2({
                    placeholder: "Chọn thể loại",
                    allowClear: true,
                    width: '100%',
                });
            });
        </script>
    </body>
</html>

