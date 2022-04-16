/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author admin
 */
public class frmLinhKien extends javax.swing.JFrame  {
    public Connection conn;
     public   PreparedStatement ps=null;
     public   ResultSet rs=null;
     DefaultTableModel dtm;
     boolean themmoi=false;
    /**
     * Creates new form frmLinhKien
     */
    public frmLinhKien() {
        initComponents();
        dtm=(DefaultTableModel) tblLinhKien.getModel();
        //KetNoiCSDL();
           hienThiDuLieu();
        HienThiCboLoaiLinhKien();
        HienThiCboNhaCC();
        DongTextBox();
        KochoPhepLuu();
    }
   
    public Connection KetNoiCSDL(){
        String user="sa";
        String pass="123456";
        try{
            conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLLinhKienPC_Laptop_java",user,pass);
            
            return conn;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    private void hienThiDuLieu(){
        try{
            
            dtm.setRowCount(0);
            conn=KetNoiCSDL();
           // String sql="select *from LinhKien where DaXoa=0";
           String sql="select MaLinhKien,TenLinhKien,XuatSu,GiaBan,BaoHanh,SoLuongTon,TenLoaiLinhKien,TenNCC\n" +
"from LinhKien a, NHACUNGCAP b, LoaiLinhKien c\n" +
"where a.MaLoaiLinhKien=c.MaLoaiLinhKien and a.MaNCC=b.MaNCC and a.DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("MaLinhKien"));
                vec.add(rs.getString("TenLinhKien"));
                vec.add(rs.getString("XuatSu"));
                vec.add(rs.getLong("GiaBan"));
                vec.add(rs.getString("BaoHanh"));
                vec.add(rs.getInt("SoLuongTon"));
                vec.add(rs.getString("TenLoaiLinhKien"));
                vec.add(rs.getString("TenNCC"));
                dtm.addRow(vec);
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboLoaiLinhKien(){
        try{
            conn=KetNoiCSDL();
            String sql="select * from LoaiLinhKien";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboLoaiLK.removeAllItems();
            while(rs.next()){
                cboLoaiLK.addItem(rs.getString("TenLoaiLinhKien"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboNhaCC(){
        try{
            conn=KetNoiCSDL();
            String sql="select MaNCC,TenNCC from NHACUNGCAP";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboNhaCungCap.removeAllItems();
            
            //ItemCboNhaCC dt = new ItemCboNhaCC(1 ,'123');
            int value;
            String display; 
            while(rs.next()){
                 value=rs.getInt("MaNCC");
                 display=rs.getString("TenNCC");
             // cboNhaCungCap.addItem(display);
              cboNhaCungCap.addItem(display);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void xoaDuLieuTextboxCbo(){
        txtBaoHanh.setText("");
        txtGiaBan.setText("");
        txtSoLuongTon.setText("");
        txtTenLK.setText("");
        txtSuatXu.setText("");
        cboLoaiLK.setToolTipText("");
        cboNhaCungCap.setToolTipText("");
    }
    public void DongTextBox(){
        
        txtBaoHanh.setEnabled(false);
        txtGiaBan.setEnabled(false);
        txtSoLuongTon.setEnabled(false);
        txtSuatXu.setEnabled(false);
        txtTenLK.setEnabled(false);
        cboLoaiLK.setEnabled(false);
        cboNhaCungCap.setEnabled(false);
    }
    public void MoTextBox(){
        
        txtBaoHanh.setEnabled(true);
        txtGiaBan.setEnabled(true);
        txtSoLuongTon.setEnabled(true);
        txtSuatXu.setEnabled(true);
        txtTenLK.setEnabled(true);
        cboLoaiLK.setEnabled(true);
        cboNhaCungCap.setEnabled(true);
    }
    private void KochoPhepLuu(){
        btnLuu.enable(false);
        btnKoLuu.enable(false);
        btnSua.enable(true);
        btnXoa.enable(true);
        btnThem.enable(true);
        
    }
    private void ChoPhepLuu(){
        btnLuu.enable(true);
        btnKoLuu.enable(true);
        btnSua.enable(false);
        btnXoa.enable(false);
        btnThem.enable(false);
    }
    
    private int LayMaNCCTheoTen(String TenNCC){
        int Ma=0;
        try{
            conn=KetNoiCSDL();
            String sql="select MaNCC from NHACUNGCAP where TenNCC=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenNCC);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MaNCC");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    private int LayMaLoaiLKTheoTen(String TenLK){
        int ma=0;
        try{
            conn=KetNoiCSDL();
            String sql="select MaLoaiLinhKien from LoaiLinhKien where TenLoaiLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenLK);
            rs=ps.executeQuery();
            while(rs.next()){
                 ma=rs.getInt("MaLoaiLinhKien");
                 return ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return ma;
    }
    public void TimKiemTheoTenLinhKien(String tenLK){
            
            try{
                dtm.setRowCount(0);
                conn=KetNoiCSDL();
                String sql="select MaLinhKien,TenLinhKien,XuatSu,GiaBan,BaoHanh,SoLuongTon,TenLoaiLinhKien,TenNCC\n" +
                "from LinhKien a, NHACUNGCAP b, LoaiLinhKien c\n" +
                "where a.MaLoaiLinhKien=c.MaLoaiLinhKien and a.MaNCC=b.MaNCC and a.DaXoa=0 and TenLinhKien like N'%"+tenLK+"%'";
                ps=conn.prepareStatement(sql);
                rs=ps.executeQuery();
                while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("MaLinhKien"));
                vec.add(rs.getString("TenLinhKien"));
                vec.add(rs.getString("XuatSu"));
                vec.add(rs.getLong("GiaBan"));
                vec.add(rs.getString("BaoHanh"));
                vec.add(rs.getInt("SoLuongTon"));
                vec.add(rs.getString("TenLoaiLinhKien"));
                vec.add(rs.getString("TenNCC"));
                dtm.addRow(vec);
            }
            }catch(Exception e){
                e.printStackTrace();
            }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtTenLK = new javax.swing.JTextField();
        txtSoLuongTon = new javax.swing.JTextField();
        txtSuatXu = new javax.swing.JTextField();
        txtBaoHanh = new javax.swing.JTextField();
        cboLoaiLK = new javax.swing.JComboBox<>();
        cboNhaCungCap = new javax.swing.JComboBox<>();
        btnThem = new java.awt.Button();
        btnLuu = new java.awt.Button();
        btnXoa = new java.awt.Button();
        btnKoLuu = new java.awt.Button();
        btnDong = new java.awt.Button();
        btnSua = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLinhKien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("form Linh Kiện");

        jLabel1.setText("Tìm Kiếm Nhanh:");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel3.setText("Nhà Cung Cấp");

        jLabel4.setText("Số Lượng Tồn");

        jLabel5.setText("Giá Bán");

        jLabel6.setText("Tên Linh Kiện");

        jLabel7.setText("Loại Linh Kiện");

        jLabel8.setText("Suất Xứ");

        jLabel9.setText("Bảo Hành");

        cboLoaiLK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiLK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiLKActionPerformed(evt);
            }
        });

        cboNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem.setLabel("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLuu.setLabel("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setLabel("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnKoLuu.setLabel("Không Lưu");
        btnKoLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKoLuuActionPerformed(evt);
            }
        });

        btnDong.setLabel("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        btnSua.setLabel("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tblLinhKien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Linh Kiện", "Tên Linh Kiện", "Suất Xứ", "Gía Bán", "Bảo Hành", "Số Lượng Tồn", "Loại LK", "Nhà Cung Cấp"
            }
        ));
        tblLinhKien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLinhKienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLinhKien);
        if (tblLinhKien.getColumnModel().getColumnCount() > 0) {
            tblLinhKien.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboLoaiLK, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnKoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 76, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoaiLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuongTon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDong, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKoLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboLoaiLKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiLKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiLKActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        xoaDuLieuTextboxCbo();
        MoTextBox();
        ChoPhepLuu();
        themmoi=true;
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
                themmoi=false;
                MoTextBox();
                ChoPhepLuu();
                int row=tblLinhKien.getSelectedRow();
                if(row ==-1){
                 JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu trong table để sửa");
                 return;
             }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row=tblLinhKien.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu trong table để Xóa");
                 return;
        }
        else{
            int maLK=Integer.parseInt(tblLinhKien.getValueAt(row, 0).toString());
            String tenLK=tblLinhKien.getValueAt(row, 1).toString();
            try{
                conn=KetNoiCSDL();
                String sql="update LinhKien set DaXoa=1 where MaLinhKien=?";
                ps=conn.prepareStatement(sql);
                ps.setInt(1, maLK);
               int result= JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa "+tenLK+" không?","Thông Báo",JOptionPane.YES_NO_OPTION);
               if(result==JOptionPane.YES_OPTION){
                   ps.executeUpdate();
                   JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                   hienThiDuLieu();
                   DongTextBox();
                   row=-1;
               }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        if(themmoi){
             if(txtTenLK.getText().equals("") || txtBaoHanh.getText().equals("")||
                txtGiaBan.getText().equals("")||txtSuatXu.getText().equals("")||
                     txtSoLuongTon.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đủ thông tin");
                 return;
             }
             
             String tenLK=txtTenLK.getText();
             String baoHanh=txtBaoHanh.getText();
             String suatXu=txtSuatXu.getText();
             try{
                 int slTon=Integer.parseInt(txtSoLuongTon.getText());
                 if(slTon<0)
                 {
                     JOptionPane.showMessageDialog(null, "Số lượng tồn lớn hơn 0");
                     return;
                 }
             }catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số ");
                 return;
             }
             
             try{
                long giaBan=Long.parseLong(txtGiaBan.getText());
                 if(giaBan<0)
                     JOptionPane.showMessageDialog(rootPane, "Giá Bán lớn hơn 0");
             }catch(Exception e){
                 JOptionPane.showMessageDialog(rootPane, "Gía bán phải là số");
                 return;
             }
             
             long giaBan=Long.parseLong(txtGiaBan.getText());
             int slTon=Integer.parseInt(txtSoLuongTon.getText());
             int MaNCC = LayMaNCCTheoTen(cboNhaCungCap.getSelectedItem().toString());
             int maLoaiLK=LayMaLoaiLKTheoTen(cboLoaiLK.getSelectedItem().toString());
             
             
             
             try{
                 conn=KetNoiCSDL();
                String sql="insert into LinhKien(TenLinhKien,XuatSu,GiaBan,BaoHanh,SoLuongTon,MaLoaiLinhKien,MaNCC,DaXoa) "
                        + "values(?,?,?,?,?,?,?,?)";
                ps=conn.prepareStatement(sql);
                ps.setString(1, tenLK);
                ps.setString(2, suatXu);
                ps.setLong(3, giaBan);
                ps.setString(4, baoHanh);
                ps.setInt(5, slTon);
                ps.setInt(6, maLoaiLK);
                ps.setInt(7, MaNCC);
                ps.setInt(8, 0);
                ps.executeUpdate();
                hienThiDuLieu();
                JOptionPane.showMessageDialog(rootPane, "Thêm Thành công");
                KochoPhepLuu();
                xoaDuLieuTextboxCbo();
                DongTextBox();
             }
             catch(Exception e){
                 e.printStackTrace();
             }
         }
         // sua du lieu
         else{
             int row=tblLinhKien.getSelectedRow();
             if(row ==-1){
                 JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu trong table để sửa");
                 return;
             }
             else{
                if(txtTenLK.getText().equals("") || txtBaoHanh.getText().equals("")||
                txtGiaBan.getText().equals("")||txtSuatXu.getText().equals("")||
                     txtSoLuongTon.getText().equals(""))
             {
                 JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đủ thông tin");
                 return;
             }
             
             String tenLK=txtTenLK.getText();
             String baoHanh=txtBaoHanh.getText();
             String suatXu=txtSuatXu.getText();
             try{
                 int slTon=Integer.parseInt(txtSoLuongTon.getText());
                 if(slTon<0)
                 {
                     JOptionPane.showMessageDialog(null, "Số lượng tồn lớn hơn 0");
                     return;
                 }
             }catch(Exception e)
             {
                 JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số ");
                 return;
             }
             
             try{
                long giaBan=Long.parseLong(txtGiaBan.getText());
                 if(giaBan<0)
                     JOptionPane.showMessageDialog(rootPane, "Giá Bán lớn hơn 0");
             }catch(Exception e){
                 JOptionPane.showMessageDialog(rootPane, "Gía bán phải là số");
                 return;
             }
             int maLK=Integer.parseInt(tblLinhKien.getValueAt(row, 0).toString());
             
             txtTenLK.setText(tblLinhKien.getValueAt(row, 1).toString());
             long giaBan=Long.parseLong(txtGiaBan.getText());
             int slTon=Integer.parseInt(txtSoLuongTon.getText());
             int MaNCC = LayMaNCCTheoTen(cboNhaCungCap.getSelectedItem().toString());
             int maLoaiLK=LayMaLoaiLKTheoTen(cboLoaiLK.getSelectedItem().toString()); 
             
                try{
                    conn=KetNoiCSDL();
                    String sql="update LinhKien set TenLinhKien=?,XuatSu=?,GiaBan=?,BaoHanh=?,SoLuongTon=?,MaLoaiLinhKien=?,MaNCC=? where MaLinhKien=?";
                    ps=conn.prepareStatement(sql);
                    ps.setString(1, tenLK);
                    ps.setString(2, suatXu);
                    ps.setLong(3, giaBan);
                    ps.setString(4, baoHanh);
                    ps.setInt(5, slTon);
                    ps.setInt(6, maLoaiLK);
                    ps.setInt(7, MaNCC);
                    ps.setInt(8, maLK);
                    ps.executeUpdate();
                    hienThiDuLieu();
                    JOptionPane.showMessageDialog(rootPane, "Sửa Thành công");
                    KochoPhepLuu();
                    xoaDuLieuTextboxCbo();
                    DongTextBox();
                }catch(Exception e){

                }
             }
         }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnKoLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKoLuuActionPerformed
        // TODO add your handling code here:
        xoaDuLieuTextboxCbo();
        DongTextBox();
        KochoPhepLuu();
        themmoi=false;
    }//GEN-LAST:event_btnKoLuuActionPerformed

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnDongActionPerformed

    private void tblLinhKienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLinhKienMouseClicked
        // TODO add your handling code here:
        int vitri=tblLinhKien.getSelectedRow();
        
        txtTenLK.setText(tblLinhKien.getValueAt(vitri, 1).toString());
        txtSuatXu.setText(tblLinhKien.getValueAt(vitri, 2).toString());
        txtGiaBan.setText(tblLinhKien.getValueAt(vitri, 3).toString());
        txtBaoHanh.setText(tblLinhKien.getValueAt(vitri, 4).toString());
        txtSoLuongTon.setText(tblLinhKien.getValueAt(vitri, 5).toString());
        cboLoaiLK.getModel().setSelectedItem(tblLinhKien.getValueAt(vitri, 6));
        cboNhaCungCap.getModel().setSelectedItem(tblLinhKien.getValueAt(vitri, 7));
    }//GEN-LAST:event_tblLinhKienMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        TimKiemTheoTenLinhKien(txtTimKiem.getText());
    }//GEN-LAST:event_txtTimKiemKeyPressed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLinhKien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLinhKien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnDong;
    private java.awt.Button btnKoLuu;
    private java.awt.Button btnLuu;
    private java.awt.Button btnSua;
    private java.awt.Button btnThem;
    private java.awt.Button btnXoa;
    private javax.swing.JComboBox<String> cboLoaiLK;
    private javax.swing.JComboBox<String> cboNhaCungCap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLinhKien;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtSuatXu;
    private javax.swing.JTextField txtTenLK;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
