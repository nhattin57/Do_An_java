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
public class formQLHD extends javax.swing.JFrame {
    public Connection conn;
     public   PreparedStatement ps=null;
     public   ResultSet rs=null;
     DefaultTableModel dtmHD;
     DefaultTableModel dtmCTHD;
    /**
     * Creates new form frmCTHD
     */
    public formQLHD() {
        initComponents();
        HienThiCboNhanVien();
        HienThiCboKhachHang();
        HienThiCboTenLinhKien();
        dtmCTHD=(DefaultTableModel) tblCTHD.getModel();
        dtmHD=(DefaultTableModel) tblHoaDon.getModel();
        hienThiTableHoaDon();
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
            conn=KetNoiCSDL();
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
            conn=KetNoiCSDL();
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
            String sql="select MaLinhKien from LinhKien where TenLinhKien=?";
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
    
    private void hienThiTableHoaDon(){
        try {
            dtmHD.setRowCount(0);
            conn=KetNoiCSDL();
            String sql="select MaHoaDon,b.Hoten,c.HoTen,NgayXuatHoaDon,Tongtien\n" +
                        "from HOADON a, KHACHHANG b, NHANVIEN c\n" +
                        "where a.DaXoa=0 and a.MaKhachHang=b.MaKhachHang and a.MANV=c.MANV";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("MaHoaDon"));
                vec.add(rs.getString("Hoten"));
                vec.add(rs.getString("HoTen"));
                vec.add(rs.getDate("NgayXuatHoaDon"));
                vec.add(rs.getLong("Tongtien"));
                dtmHD.addRow(vec);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void hienThitableCTHDTheoMaHD(int maHD){
        try {
            conn=KetNoiCSDL();
            dtmCTHD.setRowCount(0);
            String sql="select TenLinhKien,GiaBan,SoLuong,ThanhTien from CTHD where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getString("TenLinhKien"));
                vec.add(rs.getLong("GiaBan"));
                vec.add(rs.getInt("SoLuong"));
                vec.add(rs.getLong("ThanhTien"));
                dtmCTHD.addRow(vec);
            }
        } catch (Exception e) {
        }
    }
    private void timHoaDonTheoTenKhachHang(String tenKH){
        try {
            conn=KetNoiCSDL();
            dtmHD.setRowCount(0);
            String sql="select MaHoaDon,b.Hoten,c.HoTen,NgayXuatHoaDon,Tongtien\n" +
                        "from HOADON a, KHACHHANG b, NHANVIEN c\n" +
                        "where a.DaXoa=0 and a.MaKhachHang=b.MaKhachHang and a.MANV=c.MANV and b.Hoten like N'%"+tenKH+"%'";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Vector<Object> vec=new Vector<>();
                vec.add(rs.getInt("MaHoaDon"));
                vec.add(rs.getString("Hoten"));
                vec.add(rs.getString("HoTen"));
                vec.add(rs.getDate("NgayXuatHoaDon"));
                vec.add(rs.getLong("TongTien"));
                dtmHD.addRow(vec);
            }
        } catch (Exception e) {
        }
    }
    private void xoaHDTheoMaHD(int maHD){
        try {
            conn=KetNoiCSDL();
            String sql="update HOADON set DaXoa=1 where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void xoaCTHD(int maHD, int maLK){
        try {
            conn=KetNoiCSDL();
            String sql="delete from CTHD where MaHoaDon=? and MaLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            ps.setInt(2, maLK);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private long layTongTienSauKhiXoaHoaDon(int maHD){
        long tongtien=0;
        try {
            conn=KetNoiCSDL();
            String sql="select ThanhTien from CTHD where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs=ps.executeQuery();
            while(rs.next()){
                long thanhtien=rs.getLong("ThanhTien");
                tongtien+=thanhtien;
            }
            return tongtien;
        } catch (Exception e) {
        }
        return tongtien;
    }
    private void capNhatTongTienHoaDonSauKhiXoaCTHD(int maHD, long tongTien){
        try {
            conn=KetNoiCSDL();
            String sql="update HOADON set Tongtien=? where MaHoaDon=?";
             ps=conn.prepareStatement(sql);
             ps.setLong(1, tongTien);
             ps.setInt(2, maHD);
             ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    void dongTextBox(){
        txtGiaBan.setText("");
        txtMaHD.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
        txtTongTien.setText("");
    }
    void dongTextBoxCTHD(){
        txtGiaBan.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
    }
    private void capNhatHoaDon(int maHD, int maKH, int maNV){
        try {
            conn=KetNoiCSDL();
            String sql="update HOADON set MaKhachHang=?, MANV=? where MaHoaDon=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, maKH);
            ps.setInt(2, maNV);
            ps.setInt(3, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void capNhatCTHD(int maHD, int maLK, long giaBan, int soLuong, long thanhTien){
        try {
            conn=KetNoiCSDL();
            String sql="update CTHD set GiaBan=?,SoLuong=?, ThanhTien=? where MaHoaDon=? and MaLinhKien=?";
            ps=conn.prepareStatement(sql);
            ps.setLong(1, giaBan);
            ps.setInt(2, soLuong);
            ps.setLong(3, thanhTien);
            ps.setInt(4, maHD);
            ps.setInt(5, maLK);
            ps.executeUpdate();
        } catch (Exception e) {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTHD = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        cboTenNV = new javax.swing.JComboBox<>();
        cboTenKH = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTenLK = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("form Quản Lý Hóa Đơn");

        jLabel1.setText("Tìm Kiếm Nhanh:");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Khách Hàng", "Nhân Viên", "Ngày Xuất HD", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblHoaDon.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblHoaDon.getColumnModel().getColumn(2).setPreferredWidth(120);
        }

        tblCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Linh Kiện", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        tblCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTHD);

        jLabel2.setText("Mã Hóa Đơn");

        jLabel3.setText("Nhân Viên");

        jLabel4.setText("Khách Hàng");

        txtMaHD.setEditable(false);

        txtTongTien.setEditable(false);

        jLabel5.setText("Tổng Tiền");

        jLabel6.setText("jLabel6");

        jLabel7.setText("Tên Linh Kiện");

        jLabel8.setText("Gía Bán");

        jLabel9.setText("Số Lượng");

        txtSoLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuongMouseClicked(evt);
            }
        });

        txtGiaBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGiaBanMouseClicked(evt);
            }
        });

        jLabel10.setText("Thành Tiền");

        txtThanhTien.setEditable(false);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(348, 348, 348)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cboTenLK, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel6)))
                                            .addComponent(jLabel8))
                                        .addGap(36, 36, 36)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9)
                                            .addComponent(txtSoLuong)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
                                .addGap(174, 174, 174))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(1, 1, 1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(cboTenKH, 0, 119, Short.MAX_VALUE)
                                    .addComponent(txtTongTien)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(111, 111, 111)
                                .addComponent(jLabel4))
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cboTenLK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel8)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        dongTextBoxCTHD();
        int row=tblHoaDon.getSelectedRow();
        int maHD=Integer.parseInt(tblHoaDon.getValueAt(row, 0).toString());
        String tenkh=tblHoaDon.getValueAt(row, 1).toString();
        String tenNV=tblHoaDon.getValueAt(row, 2).toString();
        long tongTien=Long.parseLong(tblHoaDon.getValueAt(row, 4).toString());
                Locale VN = new Locale("vi", "VN");
                Currency vnd = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtTongTien.setText(VNDFormat.format(tongTien));
        txtMaHD.setText(tblHoaDon.getValueAt(row, 0).toString());
        cboTenKH.getModel().setSelectedItem(tenkh);
        cboTenNV.getModel().setSelectedItem(tenNV);
            hienThitableCTHDTheoMaHD(maHD);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        timHoaDonTheoTenKhachHang(txtTimKiem.getText());
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void tblCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHDMouseClicked
        // TODO add your handling code here:
        int row=tblCTHD.getSelectedRow();
        cboTenLK.getModel().setSelectedItem(tblCTHD.getValueAt(row, 0));
        txtGiaBan.setText(tblCTHD.getValueAt(row, 1).toString());
        txtSoLuong.setText(tblCTHD.getValueAt(row, 2).toString());
        long thanhtien=Long.parseLong(tblCTHD.getValueAt(row, 3).toString());
                Locale VN = new Locale("vi", "VN");
                Currency vnd = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtThanhTien.setText(VNDFormat.format(thanhtien));
    }//GEN-LAST:event_tblCTHDMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try{
        int selectedRowHD=tblHoaDon.getSelectedRow();
        int selectedrowCTHD=tblCTHD.getSelectedRow();
        int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
        int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn dữ liệu để xóa");
            return;
        }
        int selectedRowHD=tblHoaDon.getSelectedRow();
        int selectedrowCTHD=tblCTHD.getSelectedRow();
        int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
        int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
        
        if(selectedRowHD!=-1 &&selectedrowCTHD!=-1){
            
           int result= JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa chi tiết hóa đơn này không?",
                   "Thông báo", JOptionPane.YES_NO_OPTION);
           if(result==JOptionPane.YES_OPTION){
               
               xoaCTHD(maHD, maLK);
               
               long tongtiensauKhiXoa=layTongTienSauKhiXoaHoaDon(maHD);
               capNhatTongTienHoaDonSauKhiXoaCTHD(maHD, tongtiensauKhiXoa);
               hienThiTableHoaDon();
               hienThitableCTHDTheoMaHD(maHD);
               dongTextBox();
               JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
               return;
           }
        }
        else if(selectedRowHD!=-1){
            int result=JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xóa luôn hóa đơn "+maHD+" không?"
                    , "Xóa Hóa Đơn"
                    , JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.YES_OPTION){
                xoaHDTheoMaHD(maHD);
                hienThiTableHoaDon();
                dongTextBox();
                
                return;
            }
        }
        
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try{
            int selectedRowHD=tblHoaDon.getSelectedRow();
            int selectedrowCTHD=tblCTHD.getSelectedRow();
            int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
            int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
            int maNV=LayMaNhanVienTheoTen(cboTenNV.getSelectedItem().toString());
            int maKH=LayMaKhachHangTheoTen(cboTenKH.getSelectedItem().toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dữ liệu và thay đổi các giá trị ở textbox"
                    + "hoặc combobox để lưu !");
            return;
        }
        
            
            
            int selectedRowHD=tblHoaDon.getSelectedRow();
            int selectedrowCTHD=tblCTHD.getSelectedRow();
            int maHD=Integer.parseInt(tblHoaDon.getValueAt(selectedRowHD, 0).toString());
            int maLK=LayMaLinhKienTheoTen(cboTenLK.getSelectedItem().toString());
            int maNV=LayMaNhanVienTheoTen(cboTenNV.getSelectedItem().toString());
            int maKH=LayMaKhachHangTheoTen(cboTenKH.getSelectedItem().toString());
            
            if(selectedRowHD != -1 && selectedrowCTHD != -1){
                    try {
                            int soLuong=Integer.parseInt(txtSoLuong.getText());
                    } catch (Exception e) {
                            JOptionPane.showMessageDialog(rootPane, "Số lượng phải là số");
                            return;
                    }

                    try {
                        long giaBan=Long.parseLong(txtGiaBan.getText());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, "Giá bán phải là số");
                        return;
                    }
                int soLuong=Integer.parseInt(txtSoLuong.getText());
                Long giaBan=Long.parseLong(txtGiaBan.getText());
                long thanhTien=soLuong*giaBan;
                
                capNhatCTHD(maHD, maLK, giaBan, soLuong, thanhTien);
                Long tongtien=layTongTienSauKhiXoaHoaDon(maHD);
                capNhatTongTienHoaDonSauKhiXoaCTHD(maHD, tongtien);
                hienThiTableHoaDon();
                hienThitableCTHDTheoMaHD(maHD);
                dongTextBoxCTHD();
                txtTongTien.setText("");
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công");
                return;
            }
            else{
                capNhatHoaDon(maHD, maKH, maNV);
                hienThiTableHoaDon();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật hóa đơn thành công khách hàng và nhân viên cho hóa đơn "+maHD+" thành công");
            }
            
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtSoLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongMouseClicked
        // TODO add your handling code here:
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtSoLuongMouseClicked

    private void txtGiaBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGiaBanMouseClicked
        // TODO add your handling code here:
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtGiaBanMouseClicked

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
            java.util.logging.Logger.getLogger(formQLHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formQLHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formQLHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formQLHD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formQLHD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTenKH;
    private javax.swing.JComboBox<String> cboTenLK;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTHD;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
