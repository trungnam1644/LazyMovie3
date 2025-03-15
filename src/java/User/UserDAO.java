package User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import web.utils.DBUtils;

public class UserDAO {
    public UserDTO login(String userName, String password) throws ClassNotFoundException {
        UserDTO user = null;
        String sql = "SELECT * FROM [User] ";
                sql +=  "WHERE UserName = ? AND Password = ?";

        try (Connection con = DBUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, userName);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserDTO();
                    user.setUserID(rs.getInt("UserID"));
                    user.setUserName(rs.getString("userName"));
                    user.setFullName(rs.getString("fullName"));
                    user.setEmail(rs.getString("email"));
                    user.setDateOfBirth((Date) rs.getObject("dateOfBirth"));
                    user.setPhone(rs.getString("phone"));
                    user.setGender(rs.getString("gender")); 
                    user.setRole(rs.getString("role"));
                    user.setTypeID(rs.getInt("typeID"));
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in login. Details: " + ex.getMessage());
            ex.printStackTrace();
        }
        return user;
    }
    public Integer createAccount(UserDTO user) throws ClassNotFoundException {
    

        String sql = "INSERT INTO [User] (UserName, FullName, Email, Phone, Password, DateOfBirth, Role , Gender) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, user.getUserName());
        ps.setString(2, user.getFullName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getPassword());
        ps.setDate(6, new java.sql.Date(user.getDateOfBirth().getTime())); 
        String gender = user.getGender();                     
        ps.setString(7, user.getRole());   
        ps.setString(8, gender);  
        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Lấy ID của tài khoản vừa tạo
                }
            }
        }
    } catch (SQLException ex) {
        System.out.println("Create Account error! " + ex.getMessage());
        ex.printStackTrace();
    }
    return null;
}
     public boolean updateUserTypeID(int userID, int typeID) throws ClassNotFoundException {
    String sql = "UPDATE [User] SET TypeID = ? WHERE UserID = ?";
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, typeID);
        ps.setInt(2, userID);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0; // Nếu có ít nhất 1 hàng được cập nhật, trả về true
    } catch (SQLException ex) {
        System.out.println("Update TypeID error! " + ex.getMessage());
        ex.printStackTrace();
    }
    return false;
}
//
}