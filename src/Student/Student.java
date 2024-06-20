/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Student {
    private String mssv, hoten, lop, nganh, khoas, khoa, email, sdt, quequan;
    private String gioitinh;
    
    public Student(){
        this.mssv = new String();
        this.hoten = new String();
        this.lop = new String();
        this.nganh = new String();
        this.khoas = new String();
        this.khoa = new String();
        this.email = new String();
        this.sdt = new String();
        this.quequan = new String();
        this.gioitinh = "Nam";
    }
    
    public Student(String mssv,String hoten, String lop, String nganh, String khoas, String khoa, String email, String sdt, String quequan, boolean gioitinh ) {
        this.mssv = mssv;
        this.hoten = hoten;
        this.lop = lop;
        this.nganh = nganh;
        this.khoas = khoas;
        this.khoa = khoa;
        this.email = email;
        this.sdt = sdt;
        this.quequan = quequan;
        if(gioitinh)
                this.gioitinh= new String("Nam");
        else 
                this.gioitinh= new String("Nữ");
        
    }

    public String getMSSV() {
        return mssv;
    }

    public void setMSSV(String mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoten;
    }

    public void setHoTen(String hoten) {
        this.hoten = hoten;
    }
    
    public String getKhoas() {
        return khoas;
    }

    public void setKhoas(String khoas) {
        this.khoas = khoas;
    }
    
    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }
    
    public String getSDT() {
        return sdt;
    }

    public void setSDT(String sdt) {
        this.sdt = sdt;
    }
    
    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
    
    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getQueQuan() {
        return quequan;
    }

    public void setQueQuan(String quequan) {
        this.quequan = quequan;
    }
    
    public String getGioiTinh() {
        return gioitinh;
    }

    public void setGioiTinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    

    @Override
    public String toString() {
        return this.hoten;
    }
    
    public boolean save(Student student) {
        String sql = "INSERT INTO qlsv.student(mssv, hoten, lop, nganh, khoas, khoa, email, sdt, quequan, gioitinh)"
                + " VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, student.getMSSV());
            ps.setString(2, student.getHoTen());
            ps.setString(3, student.getLop());
            ps.setString(4, student.getNganh());
            ps.setString(5, student.getKhoas());
            ps.setString(6, student.getKhoa());
            ps.setString(7, student.getEmail());
            ps.setString(8, student.getSDT());
            ps.setString(9, student.getQueQuan());
            ps.setString(10, student.getGioiTinh());
            ps.execute();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean update(Student student) {
        String sql = "UPDATE qlsv.student SET hoten = ? WHERE (mssv = ? );";
        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, student.getMSSV());
            ps.setString(2, student.getHoTen());
            ps.execute();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean delete(Student student) {
        String sql = "DELETE FROM qlsv.student WHERE mssv =? ";

        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, student.getMSSV());
            ps.execute();
            return ps.executeUpdate() < 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    Connection con = ConnectDB.getJDBCConnection();
    PreparedStatement pst;
    public void getStudentTable(JTable table, String searchValua){
        String sql = new String("select * from qlsv.student where concat(mssv, hoten)like ?");
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+searchValua+"%");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row ;
            while (rs.next()){
                row = new Object[4];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(5);
                row[3] = rs.getString(4);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void getFullStudentTable(JTable table, String searchValua){
        String sql = new String("select * from qlsv.student where concat(mssv, hoten)like ?");
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+searchValua+"%");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row ;
            while (rs.next()){
                row = new Object[10];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getStudent(JTable table, String searchValua){
        String sql = new String("select * from qlsv.student where concat(mssv, hoten)like ?");
        String[] row = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+searchValua+"%");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()){
                row = new String[10];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
     
   
}
