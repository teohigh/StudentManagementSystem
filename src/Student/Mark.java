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
public class Mark {
    private String mssv, maHP, diemGK, diemCK, diemTB;
    
    public Mark(){
        this.mssv = new String();
        this.maHP = new String();
        this.diemGK = new String();
        this.diemCK = new String();
        this.diemTB = new String();
    }
    
    public Mark(String mssv, String maHP, String diemGK, String diemCK, String diemTB){
        this.mssv = mssv;
        this.maHP = maHP;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemTB = diemTB;
    }
    
    public String getMSSV(){
        return mssv;
    }
    
    public void setMSSV(String mssv){
        this.mssv=mssv;
    }
    
    public String getMaHP(){
        return maHP;
    }
    
    public void setMaHP(String maHP){
        this.maHP=maHP;
    }
    
    public String getDiemGK(){
        return diemGK;
    }
    
    public void setDiemGK(String diemGK){
        this.diemGK=diemGK;
    }
    
    public String getDiemCK(){
        return diemCK;
    }
    
    public void setDiemCK(String diemCK){
        this.diemCK=diemCK;
    }
    
    public String getDiemTB(){
        return diemTB;
    }
    
    public void setDiemTB(String diemTB){
        this.diemTB=diemTB;
    }
    
    public boolean save(Mark m) {
        String sql = "INSERT INTO qlsv.marks(mssv, maHP, diemGK, diemCK, diemTB) VALUE(?, ?, ?, ?, ?);";
        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, m.getMSSV());
            ps.setString(2, m.getMaHP());
            ps.setString(3, m.getDiemGK());
            ps.setString(4, m.getDiemCK());
            ps.setString(5, m.getDiemTB());
            ps.execute();
            return ps.executeUpdate() != 0;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean update(Mark m){
//        String sql = "UPDATE qlsv.marks SET diemGK = ?, diemCK = ?, diemTB = ? WHERE mssv = ? AND maHP = ?";
//        try (
//                Connection conn = ConnectDB.getJDBCConnection(); 
//                PreparedStatement ps = conn.prepareCall(sql);
//            ) {
//            ps.setString(1, m.getMSSV());
//            ps.setString(2, m.getMaHP());
//            ps.setString(3, m.getDiemGK());
//            ps.setString(4, m.getDiemCK());
//            ps.setString(5, m.getDiemTB());
//            ps.execute();
//            return ps.executeUpdate() > 0;
//        } catch (Exception e) {
//            return false;
//        }
        String sql1 = "DELETE FROM qlsv.marks WHERE mssv =? AND maHP = ?";
        String sql2 = "INSERT INTO qlsv.marks(mssv, maHP, diemGK, diemCK, diemTB) VALUE(?, ?, ?, ?, ?);";
        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
        ){
            ps1.setString(1, m.getMSSV());
            ps1.setString(2, m.getMaHP());
            
            ps2.setString(1, m.getMSSV());
            ps2.setString(2, m.getMaHP());
            ps2.setString(3, m.getDiemGK());
            ps2.setString(4, m.getDiemCK());
            ps2.setString(5, m.getDiemTB());
            
            ps1.executeUpdate();
            ps2.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    
    
    public boolean delete(Mark m) {
        String sql = "DELETE FROM qlsv.marks WHERE mssv =? AND maHP = ?";

        try (
                Connection conn = ConnectDB.getJDBCConnection(); 
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, m.getMSSV());
            ps.setString(2, m.getMaHP());
            ps.execute();
            return ps.executeUpdate() != 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    Connection con = ConnectDB.getJDBCConnection();
    PreparedStatement pst;
    public void getMarkTable(JTable table, String searchValua){
//        String sql = new String("SELECT qlsv.mark.mssv, qlsv.mark.maHP, qlsv.mark.diemGK, qlsv.mark.diemCK, qlsv.mark.diemTB " +
//                            " FROM qlsv.mark " +
//                            " INNER JOIN qlsv.student ON qlsv.student.mssv=qlsv.mark.mssv " +
//                            " AND qlsv.student.mssv=?;");
        String sql = new String("select * from qlsv.marks where concat(mssv, maHP)like ?");
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+searchValua+"%");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row ;
            while (rs.next()){
                row = new Object[4];
                row[0] = rs.getString(2);
                row[1] = rs.getString(3);
                row[2] = rs.getString(4);
                row[3] = rs.getString(5);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public String[] getMark(JTable table, String mssv, String maHP){
                String sql = new String("select * from qlsv.marks where concat(mssv, maHP)like ?");
        String[] row = null ;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,"%"+mssv+"%"+maHP+"%");
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()){
                row = new String[2];
                row[0] = rs.getString(3);
                row[1] = rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
}
