<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Phim Mới</title>
        <link rel="stylesheet" href="css/register.css">
        
    </head>
    <body>
        <div class="logo-lazymoive">
            <a href="index.jsp">
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
                    <input type="text" id="country" name="country" placeholder="Nhập quốc gia" required>
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
                    <label for="trailerUrl">URL Trailer:</label>
                    <input type="url" id="trailerUrl" name="trailerUrl" placeholder="Nhập URL trailer" required>
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