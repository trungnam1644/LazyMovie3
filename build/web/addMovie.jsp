<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Phim Mới</title>
        <link rel="stylesheet" href="css/editMovie.css">
        <!-- Thêm CSS cho Select2 -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
    </head>
    <body>
        <div class="logo-lazymoive">
            <a href="home.jsp">
                <img src="img/logo.jpg" alt="Logo">
            </a>    
        </div>

        <div class="form-container">
            <h2>Thêm Phim Mới</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="addMovie">

                <div class="form-group">
                    <label for="title">Tên Phim:</label>
                    <input type="text" id="title" name="title" placeholder="Nhập tên phim" required>
                </div>

                <div class="form-group">
                    <label for="description">Mô Tả:</label>
                    <textarea id="description" name="description" placeholder="Nhập mô tả phim" required></textarea>
                </div>

                <div class="form-group">
                    <label for="releaseYear">Năm Phát Hành:</label>
                    <input type="number" id="releaseYear" name="releaseYear" min="1900" max="2100" required>
                </div>

                <div class="form-group">
                    <label for="country">Quốc Gia:</label>
                    <select id="country" name="country" required>
                        <option value="">Chọn quốc gia</option>
                        <!-- Duyệt qua danh sách các quốc gia -->
                        <c:forEach var="country" items="${countries}">
                            <option value="${country.countryID}">${country.countryName}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Chọn thể loại từ danh sách -->
                <div class="form-group">
                    <label for="genre">Thể Loại:</label>
                    <select id="genre" name="genre" multiple="multiple" required>
                        <option value="">Chọn thể loại</option>
                        <!-- Duyệt qua danh sách các thể loại -->
                        <c:forEach var="genre" items="${genres}">
                            <option value="${genre.genreID}">${genre.genreName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="movieType">Loại Phim:</label>
                    <select id="movieType" name="movieType" required>
                        <option value="">Chọn loại phim</option>
                        <c:forEach var="movieType" items="${movieTypes}">
                            <option value="${movieType.movieTypeID}">${movieType.movieTypeName}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="form-group">
                    <label for="rating">Đánh Giá:</label>
                    <input type="number" id="rating" name="rating" step="0.1" min="0" max="5" placeholder="Nhập điểm đánh giá (0-5)" required>
                </div>

                <div class="form-group">
                    <label for="videoUrl">URL Video:</label>
                    <input type="url" id="videoUrl" name="videoUrl" placeholder="Nhập URL video" required>
                </div>                

                <div class="form-group">
                    <label for="thumbnailUrl">URL Ảnh:</label>
                    <input type="url" id="thumbnailUrl" name="thumbnailUrl" placeholder="Nhập URL ảnh phim" required>
                </div>

                <div class="form-group">
                    <label for="username">Người Thêm:</label>
                    <input type="text" id="userName" name="userName" value="${User.userName}" readonly>
                </div>

                <div class="button-group">
                    <button type="submit">Thêm Phim</button>
                    <a href="admin1.jsp" class="cancel-button">Hủy</a>
                </div>
            </form>
        </div>

        <!-- Thêm JavaScript cho Select2 -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>

        <script>
            $(document).ready(function () {
                // Khởi tạo Select2 cho dropdown thể loại
                $('#genre').select2({
                    placeholder: "Chọn thể loại",
                    allowClear: true,
                    width: '100%',
                });
            });
        </script>
    </body>
</html>

<style>
    .button-group {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    .button-group button {
        width: 48%;
    }

    .back-button {
        display: block;
        width: fit-content;
        margin: 20px auto;
        padding: 10px 20px;
        background-color: #6c757d;
        color: white;
        text-align: center;
        text-decoration: none;
        border-radius: 5px;
    }

    .back-button:hover {
        background-color: #5a6268;
    }
</style>
