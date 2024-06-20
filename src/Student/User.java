/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class User {
    private String email, password;
    
    public User(){
        this.email = new String();
        this.password = new String();
    }
    
    public User(String email, String password){
        this.email=email;
        this.password=password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    
    public String getPassWord(){
        return password;
    }
    
    public void setPassWord(String password){
        this.password=password;
    }
    
    public void SignUp(User user) {
        String sql = "INSERT INTO qlsv.user(email, password) VALUE(?, ?);";
        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassWord());
            ps.execute();
//            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            
        }
    }
    
    Connection con = ConnectDB.getJDBCConnection();
    PreparedStatement pst;
    public String getPassword(String searchValua){
        String sql = new String("select * from qlsv.user where email = ?");
        String password = new String() ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, searchValua);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                password = rs.getString(2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }
        
    public boolean EmailTonTai(String email){
        String sql = new String("select * from qlsv.user where email = ?");
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
