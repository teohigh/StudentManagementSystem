/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

import static java.lang.Integer.parseInt;
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
public class HP {
    private String maHP, tenHP, mota;
    private int soTC;
    
    public HP(){
        this.maHP = new String();
        this.tenHP = new String();
        this.mota = new String();
        this.soTC = 0;
    }
    
    public HP(String maHP, String tenHP, String soTC, String mota){
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.soTC = parseInt(soTC);
        this.mota = mota;
    }
    
    public String getMaHP(){
        return maHP;
    }
    
    public void setMaHP(String maHP){
        this.maHP = maHP;
    }
    
    public String getTenHP(){
        return tenHP;
    }
    
    public void setTenHP(String tenHP){
        this.tenHP = tenHP;
    }
    
    public int getSoTC(){
        return soTC;
    }
    
    public void setSoTC(int soTC){
        this.soTC = soTC;
    }
    
    public String getMoTa(){
        return mota;
    }
    
    public void setMoTa(String mota){
        this.mota = mota;
    }
    
    public boolean save(HP hp) {
        String sql = "INSERT INTO qlsv.hocphan(maHP, tenHP, soTC, mota) VALUE(?, ?, ?, ?);";
        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, hp.getMaHP());
            ps.setString(2, hp.getTenHP());
            ps.setInt(3, hp.getSoTC());
            ps.setString(4, hp.getMoTa());
            ps.execute();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean delete(HP hp) {
        String sql = "DELETE FROM qlsv.hocphan WHERE maHP =? ";

        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, hp.getMaHP());
            ps.execute();
            return ps.executeUpdate() < 0;
        } catch (Exception e) {
            return false;
        }
    }
       
    Connection con = ConnectDB.getJDBCConnection();
    PreparedStatement pst;
    public void getHPTable(JTable table, String searchValua){
        String sql = new String("select * from qlsv.hocphan where concat(maHP, tenHP)like ?");
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+searchValua+"%");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row ;
            while (rs.next()){
                row = new Object[3];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public String[] getHP(JTable table, String searchValua){
        String sql = new String("select * from qlsv.hocphan where concat(maHP, tenHP)like ?");
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}
