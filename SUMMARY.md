# Tóm Tắt Dự Án LazyMovie3

## 📋 Thông Tin Tổng Quan

**Tên dự án**: LazyMovie3  
**Loại ứng dụng**: Web Application - Hệ thống quản lý và xem phim trực tuyến  
**Ngôn ngữ**: Java (Backend) + JSP/HTML/CSS/JavaScript (Frontend)  
**Cơ sở dữ liệu**: Microsoft SQL Server  
**Framework**: Java EE, Servlet API  

## 🛠️ Công Nghệ Sử Dụng (Technologies)

### Backend Technologies
| Công nghệ | Phiên bản | Mục đích |
|-----------|-----------|----------|
| Java | 8 | Ngôn ngữ lập trình chính |
| Java EE | - | Framework enterprise |
| Servlet API | 3.1 | Xử lý HTTP requests |
| JSP | - | Template engine |
| JSTL | - | Tag library cho JSP |

### Database & Persistence
| Công nghệ | Mục đích |
|-----------|----------|
| Microsoft SQL Server | Database chính |
| JDBC | Kết nối database |
| sqljdbc4.jar | Driver SQL Server |

### Frontend & UI
| Công nghệ | Mục đích |
|-----------|----------|
| HTML5 | Markup |
| CSS3 | Styling |
| JavaScript | Client-side logic |
| Font Awesome | Icons |
| Google Fonts | Web fonts |

### Tools & Server
| Công nghệ | Mục đích |
|-----------|----------|
| Apache Tomcat | Web server |
| NetBeans IDE | Development environment |
| Apache Ant | Build tool |
| Git | Version control |

## 📁 Cấu Trúc Dự Án & Trách Nhiệm (Project Structure & Responsibilities)

### 1. 🎛️ Controller Layer (`src/java/controller/`)

#### Authentication (Xác thực)
- **MainController.java** → Điều phối chính toàn hệ thống
- **LoginController.java** → Xử lý đăng nhập
- **LogoutController.java** → Xử lý đăng xuất  
- **RegisterController.java** → Đăng ký tài khoản

#### Movie Management (Quản lý phim)
- **MovieController.java** → Thêm phim mới
- **EditMovieController.java** → Chỉnh sửa phim
- **DeleteMovieController.java** → Xóa phim
- **ViewMovieVideoController.java** → Phát video

#### User Management (Quản lý người dùng)
- **UserController.java** → Quản lý thông tin user
- **DeleteUserController.java** → Xóa tài khoản

#### Search & Navigation (Tìm kiếm & điều hướng)
- **SearchController.java** → Tìm kiếm phim
- **ViewGenresController.java** → Xem theo thể loại
- **SetTypeController.java** → Thiết lập loại phim

### 2. 🗄️ Data Access Layer (`src/java/dao/`)

| File | Trách nhiệm |
|------|-------------|
| **MovieDAO.java** | CRUD operations cho phim |
| **UserDAO.java** | CRUD operations cho user |
| **GenreDAO.java** | Quản lý thể loại phim |
| **CountryDAO.java** | Quản lý quốc gia |

### 3. 📦 Data Transfer Objects (`src/java/dto/`)

| File | Thuộc tính chính |
|------|------------------|
| **MovieDTO.java** | movieID, title, description, releaseYear, rating, videoURL |
| **UserDTO.java** | userID, userName, fullName, email, password, role |
| **GenreDTO.java** | genreID, genreName |
| **CountryDTO.java** | countryID, countryName |

### 4. 🔧 Utility Layer (`src/java/web/utils/`)

- **DBUtils.java** → Quản lý kết nối database
  - Host: localhost:1433
  - Database: LazyMovie2
  - User: SA / Password: 12345

### 5. 🖥️ View Layer (`web/`)

#### Trang chính
- **index.jsp** → Trang chủ chính
- **home.jsp** → Trang chủ sau đăng nhập
- **login.jsp** → Trang đăng nhập
- **register.jsp** → Trang đăng ký

#### Quản lý phim
- **addMovie.jsp** → Thêm phim
- **editMovie.jsp** → Chỉnh sửa phim
- **phimchieurap.jsp** → Phim chiếu rạp
- **phimle.jsp** → Phim lẻ

#### Admin
- **admin1.jsp** → Trang quản trị chính
- **admin2.jsp** → Trang quản trị phụ

## 🎯 Chức Năng Chính (Main Features)

### 👤 Quản lý người dùng
- ✅ Đăng ký tài khoản mới
- ✅ Đăng nhập/đăng xuất
- ✅ Quản lý hồ sơ cá nhân
- ✅ Phân quyền user/admin

### 🎬 Quản lý phim
- ✅ Thêm phim mới (CRUD)
- ✅ Chỉnh sửa thông tin phim
- ✅ Xóa phim
- ✅ Phân loại theo thể loại/quốc gia
- ✅ Hệ thống rating/đánh giá

### 📺 Streaming & Xem phim
- ✅ Phát video trực tuyến (MP4)
- ✅ Giao diện xem phim
- ✅ Phân loại phim lẻ/phim chiếu rạp

### 🔍 Tìm kiếm & Lọc
- ✅ Tìm kiếm theo tên phim
- ✅ Lọc theo thể loại
- ✅ Lọc theo quốc gia
- ✅ Lọc theo năm phát hành

## 🗃️ Database Schema (Ước tính)

### Bảng chính
```sql
Movie: movieID, title, description, releaseYear, rating, videoURL, thumbnailURL
User: userID, userName, fullName, email, password, role, dateOfBirth
Genre: genreID, genreName  
Country: countryID, countryName
MovieType: movieTypeID, movieTypeName
```

### Quan hệ
- Movie ↔ Country (Many-to-One)
- Movie ↔ MovieType (Many-to-One)  
- Movie ↔ Genre (Many-to-Many)
- Movie ↔ User (creator relationship)

## 🏗️ Kiến Trúc (Architecture)

### Patterns được sử dụng
- **MVC (Model-View-Controller)** → Phân tách logic rõ ràng
- **DAO (Data Access Object)** → Trừu tượng hóa database operations
- **DTO (Data Transfer Object)** → Truyền dữ liệu giữa các tầng

### Luồng xử lý
```
Browser → Servlet Controller → DAO → Database
   ↑              ↓
   ←── JSP View ← DTO ←──────────────┘
```

## 🚀 Deployment & Environment

- **Web Server**: Apache Tomcat
- **Database**: SQL Server (localhost:1433)
- **Build**: Apache Ant
- **IDE**: NetBeans
- **Encoding**: UTF-8
- **Java**: Version 1.8

## 📝 Kết Luận

LazyMovie3 là một ứng dụng web hoàn chỉnh được xây dựng theo kiến trúc MVC truyền thống với Java EE. Dự án có cấu trúc rõ ràng, phân tách tốt các concerns và sử dụng các pattern phổ biến trong enterprise Java development. Giao diện được thiết kế thân thiện với người dùng Việt Nam và hỗ trợ đầy đủ các tính năng của một hệ thống streaming phim cơ bản.