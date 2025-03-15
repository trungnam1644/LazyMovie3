/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;



import java.sql.Date;



/**
 *
 * @author Asus
 */
public class UserDTO {
    private int UserID;
    private String userName;
    private String fullName;    
    private String email;
    private String phone;
    private String password;
    private Date dateOfBirth; // Có thể dùng LocalDate nếu cần
    private String role;
    private String gender;
    private int typeID;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public UserDTO(int UserID,String userName, String fullName, String email, String phone, String password, Date dateOfBirth, String role, String gender) {
        this.UserID = UserID;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.gender = gender;
        
    }
    public UserDTO(String userName, String fullName, String email, String phone, String password, Date dateOfBirth, String role, String gender, int typeID) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.gender = gender;
        this.typeID = typeID;
    }
    
    public UserDTO(String userName, String fullName, String email, String phone, String password, Date dateOfBirth, String role, String gender ) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.gender = gender;
        
    }

    UserDTO() {
      
    }

    

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

     public void setFullName(String fullName) {
        this.fullName = fullName;
    }
     
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getFullName() {
        return fullName;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
       
   

  
}