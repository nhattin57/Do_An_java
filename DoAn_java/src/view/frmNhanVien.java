/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Validation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class frmNhanVien extends javax.swing.JFrame {

    //khai bao bien toan cuc
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    DefaultTableModel tableModel;

    public frmNhanVien() {
        initComponents();
        layGioiTinh_LenCBB();
        layTenChucVu_LenCBB();
        setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) TableNhanVien.getModel();
        txtHoTen.grabFocus();
        hienThiDuLieu();
        TableNhanVien.getColumnModel().getColumn(0).setWidth(0);
        TableNhanVien.getColumnModel().getColumn(0).setMinWidth(0);
        TableNhanVien.getColumnModel().getColumn(0).setMaxWidth(0);
    }

    private Connection ConnectToServer() {
        String user = "sa";
        String pass = "123";
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-RCFES7K\\SQLEXPRESS:1433;databaseName=QLLinhKienPC_Laptop_java", user, pass);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void hienThiDuLieu() {
        try {
            conn = ConnectToServer();
            String sql = "select MaNV, HoTen, DiaCHi ,SDT ,NamSinh ,GioiTinh,b.TenChucVu ,TaiKhoan , MatKhau \n"
                    + "from NHANVIEN  a,CHUCVU b  \n"
                    + "where DaXoa = 0 and a.MaChucVu=b.MaChucVu";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nhan vien
            tableModel.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaNV"),
                    rs.getString("HoTen"),
                    rs.getString("DiaCHi"),
                    rs.getString("SDT"),
                    rs.getString("NamSinh"),
                    rs.getString("GioiTinh"),
                    rs.getString("TenChucVu"),
                    rs.getString("TaiKhoan"),
                    rs.getString("MatKhau"),};
                tableModel.addRow(row);
            }

            // cập nhật lại dữ liệu bên trong bảng nhan vien
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refresh() {
        //txtMaNV.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtNamSinh.setText("");
        cbbGioiTinh.addItem("");
        // txtMaChucVu.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        txtTimKiem.setText("");
        TableNhanVien.grabFocus();
    }

    private void layTenChucVu_LenCBB() {
        try {
            conn = ConnectToServer();
            String sql = "select TenChucVu from  CHUCVU";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("TenChucVu"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbMaCV.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void layGioiTinh_LenCBB() {
        try {
            conn = ConnectToServer();
            String sql = "select GioiTinh \n"
                    + "from NHANVIEN\n"
                    + "where DaXoa=0\n"
                    + "group by GioiTinh";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("GioiTinh"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbGioiTinh.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtNamSinh = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        cbbMaCV = new javax.swing.JComboBox();
        cbbGioiTinh = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableNhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng chính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

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

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N

        jLabel2.setText("Họ tên");

        jLabel3.setText("Địa chỉ");

        jLabel4.setText("SDT");

        jLabel5.setText("Năm sinh");

        jLabel6.setText("Giới tính");

        jLabel7.setText("Tên chức vụ");

        jLabel8.setText("Tài khoản");

        jLabel9.setText("Mật khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(42, 42, 42)
                        .addComponent(txtMatKhau))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(38, 38, 38)
                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(57, 57, 57)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(55, 55, 55)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(70, 70, 70)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(41, 41, 41)
                        .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbMaCV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbGioiTinh, 0, 300, Short.MAX_VALUE))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbbMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("Nhân Viên");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhân viên"));

        TableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ tên", "Địa chỉ", "SDT", "Năm sinh", "Giới tính", "Tên chức vụ", "Tài khoản", "Mật khẩu"
            }
        ));
        TableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableNhanVien);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        refresh();
    }//GEN-LAST:event_btnThemActionPerformed

    private void TableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableNhanVienMouseClicked
        try {
            int row = TableNhanVien.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn vào dòng cần hiển thị");
                return;
            } else {
               // txtMaNV.setText(TableNhanVien.getValueAt(row, 0).toString());
                txtHoTen.setText(TableNhanVien.getValueAt(row, 1).toString());
                txtDiaChi.setText(TableNhanVien.getValueAt(row, 2).toString());
                txtSDT.setText(TableNhanVien.getValueAt(row, 3).toString());
                txtNamSinh.setText(TableNhanVien.getValueAt(row, 4).toString());
                cbbGioiTinh.getModel().setSelectedItem(tableModel.getValueAt(row, 5));
                cbbMaCV.getModel().setSelectedItem(tableModel.getValueAt(row, 6));
                txtTaiKhoan.setText(TableNhanVien.getValueAt(row, 7).toString());
                txtMatKhau.setText(TableNhanVien.getValueAt(row, 8).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_TableNhanVienMouseClicked

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật nhân viên không ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            conn = ConnectToServer();
            int row =TableNhanVien.getSelectedRow();

            String sql = "update  NHANVIEN set HoTen = ?, DiaCHi = ?, SDT = ?, NamSinh = ?, GioiTinh = ?, MaChucVu = ?, TaiKhoan = ?,MatKhau = ?, DaXoa = ?"
                    + "     where MaNV = ? ";
            ps = conn.prepareStatement(sql);

            ps.setString(1, txtHoTen.getText());
            ps.setString(2, txtDiaChi.getText());
            ps.setString(3, txtSDT.getText());
            ps.setString(4, txtNamSinh.getText());
            ps.setInt((int)5, (int) tableModel.getValueAt(row, 5));
            ps.setString(6, cbbMaCV.toString());
            ps.setString(7, txtTaiKhoan.getText());
            ps.setString(8, txtMatKhau.getText());

            ps.setInt(9, 0);
            ps.setString(10,tableModel.getValueAt(row,0 ).toString());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Cập nhật nhân viên thành công");
            hienThiDuLieu();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            StringBuffer sb = new StringBuffer();

            Validation.check(txtHoTen, sb, "Tên nhân viên không được để trống");
            Validation.check(txtDiaChi, sb, "Địa chỉ không được để trống");
            Validation.check(txtNamSinh, sb, "Năm sinh không được để trống");
            //Validation.check(, sb, "Mã chức vụ không được để trống");
            Validation.check(txtTaiKhoan, sb, "Tài khoản không được để trống");
            Validation.check(txtMatKhau, sb, "Mật khẩu không được để trống");
            while (true) {
                if (txtSDT.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
                    txtSDT.grabFocus();
                    return;
                } else if (txtSDT.getText().trim().length() > 11 || txtSDT.getText().trim().length() < 10) {
                    JOptionPane.showMessageDialog(this, "SĐT chứa từ 10-11 số.");
                    txtSDT.grabFocus();
                    return;
                } else if (!txtSDT.getText().trim().matches("0[1-9]{1}\\d{8,9}")) {
                    JOptionPane.showMessageDialog(this, "SĐT chưa đúng định dạng.");
                    txtSDT.grabFocus();
                    return;
                } else {
                    break;
                }
            }

            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
                return;
            }

            conn = ConnectToServer();
            String sql = "insert into NHANVIEN(HoTen,DiaChi,SDT,NamSinh,GioiTinh,MaChucVu,TaiKhoan,MatKhau,DaXoa) values(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            ps.setString(1, txtHoTen.getText());
            ps.setString(2, txtDiaChi.getText());
            ps.setString(3, txtSDT.getText());
            ps.setString(4, txtNamSinh.getText());
            ps.setString(5, cbbGioiTinh.toString());
            // ps.setString(6, txtMaChucVu.getText());
            ps.setString(7, txtTaiKhoan.getText());
            ps.setString(8, txtMatKhau.getText());

            ps.setInt(9, 0);

            ps.executeUpdate();

            hienThiDuLieu();
            JOptionPane.showMessageDialog(this, "Lưu nhân viên thành công");
            refresh();
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = TableNhanVien.getSelectedRow();
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này không ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            int maLK = Integer.parseInt(TableNhanVien.getValueAt(row, 0).toString());
            conn = ConnectToServer();
            String sql = "update NHANVIEN set daxoa = 1 where MaNV = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maLK);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Xóa nhân viên thành công");
            hienThiDuLieu();
            refresh();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            //xóa trắng dữ liệu bên trong bảng nhân viên
            tableModel.setRowCount(0);
            conn = ConnectToServer();

            String sql = "select MaNV, HoTen, SDT ,DiaCHi ,NamSinh ,GioiTinh,b.TenChucVu ,TaiKhoan , MatKhau \n"
                    + "from NHANVIEN  a,CHUCVU b  ";
            if (txtTimKiem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhân viên ?");
            } else if (txtTimKiem.getText().length() > 0) {
                sql = sql + " where DaXoa = 0 and a.MaChucVu=b.MaChucVu and HoTen like '%" + txtTimKiem.getText() + "%'";

                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Tên nhân viên " + txtTimKiem.getText() + " không tồn tại trong danh sách!");
                    txtTimKiem.setText("");
                    txtTimKiem.grabFocus();
                    return;
                }
                while (rs.next()) {
                    String[] row = new String[]{
                        rs.getString("MaNV"),
                        rs.getString("HoTen"),
                        rs.getString("DiaCHi"),
                        rs.getString("SDT"),
                        rs.getString("NamSinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("TenChucVu"),
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),};
                    tableModel.addRow(row);
                }

                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Tìm thấy nhân viên có tên gần đúng là: " + txtTimKiem.getText());
                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableNhanVien;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cbbGioiTinh;
    private javax.swing.JComboBox cbbMaCV;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
