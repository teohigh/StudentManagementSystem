/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ConnectDB {
        public static Connection getJDBCConnection(){
            String url="jdbc:mysql://localhost:3306/qlsv";
            String user="root";
            String password="sa2008";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try {
                    return DriverManager.getConnection(url, user, password);
                } catch (SQLException ex) {
                    Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = getJDBCConnection();
        if(conn!=null)
            System.out.println("Kết nối My SQL thành công!");
        else
            System.out.println("Kết nối thất bại!");
    }    
        
}
