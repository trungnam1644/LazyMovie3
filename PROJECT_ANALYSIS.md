# LazyMovie3 - Phân Tích Dự Án

## Tóm Tắt Dự Án (Summary)

LazyMovie3 là một ứng dụng web quản lý và xem phim trực tuyến được phát triển bằng Java. Đây là một nền tảng web cho phép người dùng:

- **Xem phim trực tuyến**: Hỗ trợ streaming video với giao diện thân thiện
- **Quản lý tài khoản người dùng**: Đăng ký, đăng nhập, quản lý hồ sơ cá nhân
- **Tìm kiếm và duyệt phim**: Tìm kiếm theo tên, thể loại, quốc gia
- **Quản lý phim (Admin)**: Thêm, sửa, xóa phim cho quản trị viên
- **Phân loại phim**: Quản lý theo thể loại, quốc gia, năm phát hành
- **Hệ thống đánh giá**: Cho phép đánh giá và xếp hạng phim

## Công Nghệ Sử Dụng (Technologies)

### Backend Technologies
- **Java 8**: Ngôn ngữ lập trình chính
- **Java EE (Enterprise Edition)**: Framework cho ứng dụng web enterprise
- **Servlet API 3.1**: Xử lý HTTP requests/responses
- **JSP (JavaServer Pages)**: Template engine cho frontend
- **JSTL (JSP Standard Tag Library)**: Thư viện tag chuẩn cho JSP

### Database & Persistence
- **Microsoft SQL Server**: Hệ quản trị cơ sở dữ liệu chính
- **JDBC**: Java Database Connectivity cho kết nối database
- **sqljdbc4.jar**: Driver cho SQL Server

### Web Server & Deployment
- **Apache Tomcat**: Web container và servlet engine
- **Java EE Web Application (WAR)**: Định dạng deployment

### Frontend Technologies
- **HTML5**: Markup language
- **CSS3**: Styling và responsive design
- **JavaScript**: Client-side scripting
- **Font Awesome**: Icon library
- **Google Fonts**: Web fonts

### Development Tools
- **NetBeans IDE**: Môi trường phát triển chính
- **Apache Ant**: Build automation tool
- **Git**: Version control system

### Architecture Patterns
- **MVC (Model-View-Controller)**: Kiến trúc phân lớp
- **DAO (Data Access Object)**: Pattern truy cập dữ liệu
- **DTO (Data Transfer Object)**: Pattern truyền dữ liệu

## Trách Nhiệm Các Thành Phần (Responsibilities)

### 1. Controller Layer (`src/java/controller/`)

#### Authentication Controllers
- **MainController.java**: Điều phối chính, xử lý routing cho toàn bộ ứng dụng
- **LoginController.java**: Xử lý đăng nhập người dùng
- **LogoutController.java**: Xử lý đăng xuất
- **RegisterController.java**: Xử lý đăng ký tài khoản mới

#### Movie Management Controllers
- **MovieController.java**: Thêm phim mới vào hệ thống
- **EditMovieController.java**: Chỉnh sửa thông tin phim
- **excuteEditController.java**: Thực thi việc cập nhật phim
- **DeleteMovieController.java**: Xóa phim khỏi hệ thống
- **ViewMovieVideoController.java**: Hiển thị video phim

#### User Management Controllers
- **UserController.java**: Quản lý thông tin người dùng
- **DeleteUserController.java**: Xóa tài khoản người dùng

#### Search & Navigation Controllers
- **SearchController.java**: Tìm kiếm phim theo các tiêu chí
- **ViewGenresController.java**: Hiển thị phim theo thể loại
- **CreatePageController.java**: Tạo các trang mới
- **SetTypeController.java**: Thiết lập loại phim

### 2. Data Access Layer (`src/java/dao/`)

#### Core DAO Classes
- **MovieDAO.java**: Truy cập dữ liệu phim
  - Thêm, sửa, xóa, truy vấn phim
  - Quản lý thông tin chi tiết phim
- **UserDAO.java**: Truy cập dữ liệu người dùng
  - Xác thực đăng nhập
  - Quản lý thông tin tài khoản
- **GenreDAO.java**: Truy cập dữ liệu thể loại phim
- **CountryDAO.java**: Truy cập dữ liệu quốc gia

### 3. Data Transfer Objects (`src/java/dto/`)

#### Model Classes
- **MovieDTO.java**: Đối tượng dữ liệu phim
  - movieID, title, description, releaseYear
  - rating, videoURL, thumbnailURL
  - countryID, movieTypeID, genres, actors
- **UserDTO.java**: Đối tượng dữ liệu người dùng
  - userID, userName, fullName, email
  - password, dateOfBirth, role, gender
- **GenreDTO.java**: Đối tượng dữ liệu thể loại
- **CountryDTO.java**: Đối tượng dữ liệu quốc gia
- **MovieTypeDTO.java**: Đối tượng dữ liệu loại phim

### 4. Utility Layer (`src/java/web/utils/`)

- **DBUtils.java**: Tiện ích kết nối cơ sở dữ liệu
  - Quản lý connection pool
  - Cấu hình database connection
  - Database: LazyMovie2, User: SA, Password: 12345

### 5. View Layer (`web/`)

#### Main Pages
- **index.jsp**: Trang chủ chính
- **home.jsp**: Trang chủ sau đăng nhập
- **homeview.jsp**: Chế độ xem khác của trang chủ
- **login.jsp**: Trang đăng nhập
- **register.jsp**: Trang đăng ký

#### Movie Management Pages
- **addMovie.jsp**: Thêm phim mới
- **editMovie.jsp**: Chỉnh sửa phim
- **phimchieurap.jsp**: Phim chiếu rạp
- **phimle.jsp**: Phim lẻ

#### Admin Pages
- **admin1.jsp**: Trang quản trị chính
- **admin2.jsp**: Trang quản trị phụ

#### User Management Pages
- **profile.jsp**: Trang hồ sơ cá nhân
- **packagesUser.jsp**: Gói dịch vụ người dùng

### 6. Static Resources

#### CSS Files (`web/css/`)
- **home.css**: Styling cho trang chủ
- **login.css**: Styling cho trang đăng nhập
- **admin.css**: Styling cho trang quản trị
- **welcome.css**: Styling cho trang chào mừng
- **editMovie.css**: Styling cho trang chỉnh sửa phim
- **homeview.css**: Styling cho chế độ xem khác

#### Images (`web/img/`)
- Logo và hình ảnh giao diện

### 7. Configuration Files

#### Web Configuration
- **web.xml**: Cấu hình servlet mapping và filter
  - Mapping các controller với URL patterns
  - Cấu hình encoding filter (UTF-8)
  - Session timeout (30 phút)
  - MIME type cho video MP4

#### Build Configuration
- **build.xml**: Ant build script
- **project.properties**: Cấu hình NetBeans project
  - Java 8 target
  - Tomcat server
  - JSTL libraries
  - SQL Server JDBC driver

## Database Schema (Suy đoán từ code)

### Main Tables
- **Movie**: Bảng chính chứa thông tin phim
- **User**: Bảng người dùng
- **Genre**: Bảng thể loại phim
- **Country**: Bảng quốc gia
- **MovieType**: Bảng loại phim

### Key Relationships
- Movie -> Country (Many-to-One)
- Movie -> MovieType (Many-to-One)
- Movie -> Genre (Many-to-Many, likely through junction table)
- Movie -> User (creator relationship)

## Tính Năng Chính

1. **Quản lý người dùng**: Đăng ký, đăng nhập, phân quyền
2. **Quản lý phim**: CRUD operations cho phim
3. **Streaming video**: Phát video trực tuyến
4. **Tìm kiếm và lọc**: Theo tên, thể loại, quốc gia
5. **Giao diện responsive**: Tương thích đa thiết bị
6. **Hệ thống đánh giá**: Rating cho phim
7. **Phân loại nội dung**: Theo thể loại và loại phim

## Deployment & Environment

- **Server**: Apache Tomcat
- **Database**: Microsoft SQL Server (localhost:1433)
- **Build Tool**: Apache Ant
- **IDE**: NetBeans
- **Encoding**: UTF-8
- **Java Version**: 1.8