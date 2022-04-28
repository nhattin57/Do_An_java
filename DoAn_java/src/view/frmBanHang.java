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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;  
/**
 *
 * @author admin
 */
public class frmBanHang extends javax.swing.JFrame {
     public Connection conn;
     public   PreparedStatement ps=null;
     public   ResultSet rs=null;
     DefaultTableModel dtm;
    /**
     * Creates new form frmHoaDon
     */
    public frmBanHang() {
        initComponents();
        setLocationRelativeTo(null);
        KetNoiCSDL();
        dtm=(DefaultTableModel)tblHoaDon.getModel();
        HienThiCboTenLinhKien();
        HienThiCboNhanVien();
        HienThiCboKhachHang();
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
    public void HienThiCboTenLinhKien(){
        try{
            conn=KetNoiCSDL();
            String sql="select TenLinhKien from LinhKien where DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenLK.removeAllItems();
            while(rs.next()){
                cboTenLK.addItem(rs.getString("TenLinhKien"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboNhanVien(){
        try{
           // conn=KetNoiCSDL();
            String sql="select HoTen from NHANVIEN where DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenNV.removeAllItems();
            while(rs.next()){
                cboTenNV.addItem(rs.getString("HoTen"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void HienThiCboKhachHang(){
        try{
            //conn=KetNoiCSDL();
            String sql="select HoTen from KHACHHANG where DaXoa=0";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            cboTenKH.removeAllItems();
            while(rs.next()){
                cboTenKH.addItem(rs.getString("HoTen"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //---------------------------------------------------------------------
    private int LayMaKhachHangTheoTen(String TenKH){
        int Ma=0;
        try{
            //conn=KetNoiCSDL();
            String sql="select MaKhachHang from KHACHHANG where DaXoa=0 and Hoten=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenKH);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MaKhachHang");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private int LayMaNhanVienTheoTen(String TenNV){
        int Ma=0;
        try{
            //conn=KetNoiCSDL();
            String sql="select MANV from NHANVIEN where DaXoa=0 and HoTen=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenNV);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MANV");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    private int LayMaLinhKienTheoTen(String TenLinhKien){
        int Ma=0;
        try{
            conn=KetNoiCSDL();
            String sql="select MaLinhKien from LinhKien where DaXoa=0 and TenLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, TenLinhKien);
            rs=ps.executeQuery();
            while(rs.next()){
                 Ma=rs.getInt("MaLinhKien");
                 return Ma;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
            // xong gán nó cho thằng mã này
        return Ma;
    }
    
    void hienThiThongTinSauKhiChonSanPham(String tenLK){
        try{
            conn=KetNoiCSDL();
            String sql="select MaLinhKien,GiaBan from LinhKien where TenLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1, tenLK);
            rs=ps.executeQuery();
            while(rs.next()){
                txtGiaBan.setText(rs.getString("GiaBan"));
                txtMaLK.setText(rs.getString("MaLinhKien"));
                return;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cboTenKH = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboTenNV = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboTenLK = new javax.swing.JComboBox<>();
        txtGiaBan = new javax.swing.JTextField();
        txtMaLK = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("form Bán Hàng");

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Linh Kiện", "Mã Linh Kiện", "Gía Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        jLabel1.setText("Tên Khách Hàng");

        cboTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenKHActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Nhân Viên");

        jLabel4.setText("Tên Linh Kiện");

        jLabel5.setText("Gía Bán");

        jLabel6.setText("Mã Linh Kiện");

        jLabel7.setText("Số Lượng");

        cboTenLK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTenLKItemStateChanged(evt);
            }
        });
        cboTenLK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTenLKMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cboTenLKMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cboTenLKMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboTenLKMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboTenLKMouseReleased(evt);
            }
        });
        cboTenLK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenLKActionPerformed(evt);
            }
        });
        cboTenLK.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboTenLKPropertyChange(evt);
            }
        });
        cboTenLK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboTenLKKeyPressed(evt);
            }
        });

        txtGiaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGiaBanMouseClicked(evt);
            }
        });

        txtMaLK.setEditable(false);

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
        });

        jLabel3.setText("Thành Tiền");

        txtThanhTien.setEditable(false);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel8.setText("Tổng Tiền: ");

        txtTongTien.setEditable(false);

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnInHoaDon.setText("In Hóa Đơn");

        btnXoaSP.setText("Xóa Sản Phẩm");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnXoaSP)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(94, 94, 94))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cboTenKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(45, 45, 45)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(cboTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(45, 45, 45)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiaBan)
                                    .addComponent(cboTenNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel2))
                                        .addGap(0, 60, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtMaLK, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(btnInHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTenKHActionPerformed

    private void cboTenLKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenLKActionPerformed
        // TODO add your handling code here:
       // cboTenLK.setSelectedItem(cboTenLK.getSelectedIndex());
       //hienThiThongTinSauKhiChonSanPham(cboTenLK.getItemAt(cboTenLK.getSelectedIndex()));
       // HienThiCboTenLinhKien();
    }//GEN-LAST:event_cboTenLKActionPerformed

    private void cboTenLKMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenLKMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTenLKMouseEntered

    private void cboTenLKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenLKMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTenLKMouseClicked

    private void cboTenLKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTenLKItemStateChanged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cboTenLKItemStateChanged

    private void cboTenLKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenLKMousePressed
        // TODO add your handling code here:
            
    }//GEN-LAST:event_cboTenLKMousePressed

    private void cboTenLKMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenLKMouseExited
        // TODO add your handling code here:
     
    }//GEN-LAST:event_cboTenLKMouseExited

    private void cboTenLKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTenLKMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTenLKMouseReleased

    private void cboTenLKPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboTenLKPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTenLKPropertyChange

    private void cboTenLKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboTenLKKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTenLKKeyPressed

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(txtSoLuong.getText().equals("") ||txtGiaBan.getText().equals("")||
                txtMaLK.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đủ thông tin");
            return;
        }
        try{
            int slban=Integer.parseInt(txtSoLuong.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Số lượng phải là số");
            return;
        }
        try{
            long giaban=Long.parseLong(txtGiaBan.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Gía bán phải là số");
            return;
        }
        
        int slban=Integer.parseInt(txtSoLuong.getText());
        long giaban=Long.parseLong(txtGiaBan.getText());
        long thanhTien=slban*giaban;
        int row=tblHoaDon.getRowCount();
        
        for (int i = 0; i < row; i++) {
            
            if(tblHoaDon.getValueAt(i, 1).equals(txtMaLK.getText())){
                int slHienTai=Integer.parseInt(tblHoaDon.getValueAt(i, 3).toString());
                int slThem=Integer.parseInt(txtSoLuong.getText());
                
                int tongSL=slHienTai+slThem;
                long thanhTienMoi=giaban*tongSL;
                long giaBanMoi=Long.parseLong(txtGiaBan.getText());
               
                tblHoaDon.setValueAt(giaBanMoi+"", i, 2);
                tblHoaDon.setValueAt(tongSL+"", i, 3);
                tblHoaDon.setValueAt(thanhTienMoi+"", i, 4);
                
                txtGiaBan.setText("");
                txtSoLuong.setText("");
                txtMaLK.setText("");
                txtThanhTien.setText("");
                
                long tongTien=tinhTongTien();
                Locale VN = new Locale("vi", "VN");
                Currency dollars = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtTongTien.setText(VNDFormat.format(tongTien));
                return;
                
            }
            
        }
                Vector<Object> vec=new Vector<>();
                vec.add(cboTenLK.getSelectedItem().toString());
                vec.add(txtMaLK.getText());
                vec.add(txtGiaBan.getText());
                vec.add(txtSoLuong.getText());
                vec.add(thanhTien+"");
                dtm.addRow(vec);
                
                txtGiaBan.setText("");
                txtSoLuong.setText("");
                txtMaLK.setText("");
                txtThanhTien.setText("");
                
                long tongTien=tinhTongTien();
                Locale VN = new Locale("vi", "VN");
                Currency dollars = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtTongTien.setText(VNDFormat.format(tongTien));
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int row=tblHoaDon.getSelectedRow();
        txtGiaBan.setText(tblHoaDon.getValueAt(row, 2).toString());
        txtMaLK.setText(tblHoaDon.getValueAt(row, 1).toString());
        txtSoLuong.setText(tblHoaDon.getValueAt(row, 3).toString());
        txtThanhTien.setText(tblHoaDon.getValueAt(row, 4).toString());
        cboTenLK.getModel().setSelectedItem(tblHoaDon.getValueAt(row, 0).toString());
        
         
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtGiaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaBanMouseClicked
        // TODO add your handling code here:
       hienThiThongTinSauKhiChonSanPham(cboTenLK.getItemAt(cboTenLK.getSelectedIndex()));
       txtThanhTien.setText("");
    }//GEN-LAST:event_txtGiaBanMouseClicked

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        int row=tblHoaDon.getSelectedRow();
        if(row==-1){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn hàng trong bảng để xóa");
            return;
        }
        else{
            dtm.removeRow(row);
            long tongTien=tinhTongTien();
                Locale VN = new Locale("vi", "VN");
                Currency dollars = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtTongTien.setText(VNDFormat.format(tongTien));
        }
    }//GEN-LAST:event_btnXoaSPActionPerformed
    
    private void themHoaDon(int maKH, int maNV, long tongTien){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
        Date date = new Date();  
        String date1=formatter.format(date).toString();
        try{
            conn=KetNoiCSDL();
            String sql="insert into HOADON(MANV,MaKhachHang,NgayXuatHoaDon,Tongtien,DaXoa)\n" +
                        "values(?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maNV);
            ps.setInt(2, maKH);
            ps.setString(3, date1);
            ps.setLong(4, tongTien);
            ps.setInt(5, 0);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private int layMaHoaDon(){
        int ma=0;
        try{
            conn=KetNoiCSDL();
            String sql="select top 1 MaHoaDon from HOADON order by MaHoaDon desc";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ma=rs.getInt("MaHoaDon");
                return ma;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ma;
    }
    
    private void themCTHD(int maHD, int maLK,String tenLK,long giaBan,int soLuong,long thanhTien){
         try{
             conn=KetNoiCSDL();
            String sql="insert into CTHD(MaHoaDon,MaLinhKien,TenLinhKien,GiaBan,SoLuong,ThanhTien)\n" +
                        "values(?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.setInt(2, maLK);
            ps.setString(3, tenLK);
            ps.setLong(4, giaBan);
            ps.setInt(5, soLuong);
            ps.setLong(6, thanhTien);
            ps.executeUpdate();
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        int rowCount=tblHoaDon.getRowCount();
        if(rowCount==-1){
            JOptionPane.showMessageDialog(rootPane, "Chưa thêm sản phẩm ");
            return;
        }
        else{
            int maNV=LayMaNhanVienTheoTen(cboTenNV.getSelectedItem().toString());
            int maKH=LayMaKhachHangTheoTen(cboTenKH.getSelectedItem().toString());
            long tongTien=tinhTongTien();
            themHoaDon(maKH, maNV, tongTien);
            int maHD=layMaHoaDon();
            for (int i = 0; i < rowCount; i++) {
                String tenLK=tblHoaDon.getValueAt(i, 0).toString();
                int maLK=Integer.parseInt(tblHoaDon.getValueAt(i, 1).toString());
                long giaBan=Long.parseLong(tblHoaDon.getValueAt(i, 2).toString());
                int soLuong=Integer.parseInt(tblHoaDon.getValueAt(i, 3).toString());
                long thanhTien=Long.parseLong(tblHoaDon.getValueAt(i, 4).toString());
                themCTHD(maHD, maLK, tenLK, giaBan, soLuong, thanhTien);
            }
            dtm.setRowCount(0);
            txtGiaBan.setText("");
            txtMaLK.setText("");
            txtSoLuong.setText("");
            txtThanhTien.setText("");
            txtTongTien.setText("");
            JOptionPane.showMessageDialog(rootPane, "Thanh toán thành công ");
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed
    
    private long tinhTongTien(){
         int row=tblHoaDon.getRowCount();
         long tongtien=0;
         for (int i = 0; i < row; i++) {
            long ThanhTien=Long.parseLong(tblHoaDon.getValueAt(i, 4).toString());
            tongtien+=ThanhTien;
        }
         return tongtien;
    }
   
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
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cboTenKH;
    private javax.swing.JComboBox<String> cboTenLK;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaLK;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
