/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
 * @author DELL
 */
public class formQLPhieuNhapHang extends javax.swing.JFrame {

    /**
     * Creates new form formQLPhieuNhapHang
     */
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    DefaultTableModel tableModel_PNH;
    DefaultTableModel tableModel_CT_PNH;

    public formQLPhieuNhapHang() {
        initComponents();
        this.setLocationRelativeTo(null);
        tableModel_PNH = (DefaultTableModel) tablePNH.getModel();
        tableModel_CT_PNH = (DefaultTableModel) table_CT_PNH.getModel();
        cbbNhanVien.grabFocus();
        layThongTinPNH();
        layTenNhanVien();
        layTenNhaCungCap();

        // Ẩn cột mã phiếu nhập hàng và hoạt động tốt đối với tôi ẩn một cột 0 và tôi vẫn có thể nhận được giá trị từ nó
        tablePNH.getColumnModel().getColumn(0).setWidth(0);
        tablePNH.getColumnModel().getColumn(0).setMinWidth(0);
        tablePNH.getColumnModel().getColumn(0).setMaxWidth(0);

    }

    private Connection ConnectToDataBase() {
        String user = "sa";
        String pass = "123456";
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-8I56L51:1433;databaseName=QLLinhKienPC_Laptop_java", user, pass);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void lamMoiPhieuNhapHang() {
        txtMa_PNH.setText("");
        txtDiaChi_NV.setText("");
        txtSDT_NV.setText("");
        txtDiaChi_NCC.setText("");
        txtSDT_NCC.setText("");
        txtTongTien.setText("");
    }

    private void layThongTinPNH() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.MaPNH, b.HoTen, c.TenNCC, a.NgayNhapHang, a.TongTien\n"
                    + "from PhieuNhapHang a, NHANVIEN b, NHACUNGCAP c\n"
                    + "where a.MaNCC = b.MANV and a.MaNCC = c.MaNCC and a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            tableModel_PNH.setRowCount(0);
            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaPNH"),
                    rs.getString("HoTen"),
                    rs.getString("TenNCC"),
                    rs.getString("NgayNhapHang"),
                    rs.getString("TongTien"),};
                tableModel_PNH.addRow(row);
            }
            tableModel_PNH.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối với cơ sở dữ liệu");
            e.printStackTrace();
        }
    }

    private void layThongTin_CT() {
        try {
            conn = ConnectToDataBase();
            String sql = "select c.TenLoaiLinhKien, b.TenLinhKien, a.XuatSu, a.BaoHanh, a.GiaBan, a.SoLuongNhap, a.ThanhTien\n"
                    + "from CTPNH a, LinhKien b, LoaiLinhKien c\n"
                    + "where a.MaCTPNH = b.MaLinhKien and a.LoaiLinhKien = c.MaLoaiLinhKien";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nha cung cấp
            tableModel_CT_PNH.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("TenloaiLinhKien"),
                    rs.getString("TenLinhKien"),
                    rs.getString("XuatSu"),
                    rs.getString("BaoHanh"),
                    rs.getString("GiaBan"),
                    rs.getString("SoLuongNhap"),
                    rs.getString("ThanhTien"),};
                tableModel_CT_PNH.addRow(row);
            }
            tableModel_CT_PNH.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void layThongTin_CT_PNH_TheoMa(int maPNH) {
        try {
            conn = ConnectToDataBase();
            String sql = "select c.TenLoaiLinhKien, b.TenLinhKien, a.XuatSu, a.BaoHanh, a.GiaBan, a.SoLuongNhap, a.ThanhTien\n"
                    + "from CTPNH a, LinhKien b, LoaiLinhKien c, PhieuNhapHang d\n"
                    + "where a.MaPNH = ? and a.MaLinhKien = b.MaLinhKien and a.LoaiLinhKien = c.MaLoaiLinhKien and a.MaPNH = d.MaPNH";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maPNH);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nha cung cấp
            tableModel_CT_PNH.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("TenloaiLinhKien"),
                    rs.getString("TenLinhKien"),
                    rs.getString("XuatSu"),
                    rs.getString("BaoHanh"),
                    rs.getString("GiaBan"),
                    rs.getString("SoLuongNhap"),
                    rs.getString("ThanhTien"),};
                tableModel_CT_PNH.addRow(row);
            }
            tableModel_CT_PNH.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void layTenNhanVien() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.HoTen from NHANVIEN a where a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("HoTen"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbNhanVien.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void layTenNhaCungCap() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.TenNCC from NhaCungCap a where a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("TenNCC"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbNhaCungCap.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void xoa_PNH(int maPNH) {
        try {
            conn = ConnectToDataBase();
            String sql = "update PhieuNhapHang set DaXoa=1 where maPNH=?";
            ps = conn.prepareCall(sql);
            ps.setInt(1, maPNH);
            ps.executeUpdate();

            ps.close();
            ConnectToDataBase();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void xoa_CT_PNH(int ma_CT_PNH) {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSDT_NV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi_NV = new javax.swing.JTextField();
        cbbNhaCungCap = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSDT_NCC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi_NCC = new javax.swing.JTextField();
        cbbNhanVien = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtMa_PNH = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePNH = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtLoai_SP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTen_SP = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtXuatSu_SP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGia_SP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtBaoHanh = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_CT_PNH = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel1.setText("QUẢN LÝ PHIẾU NHẬP HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(444, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(422, 422, 422))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin phiếu nhập hàng"));

        jLabel2.setText("Tên Nhân Viên");

        jLabel3.setText("Số Điện Thoại");

        jLabel4.setText("Địa Chỉ");

        cbbNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhaCungCapActionPerformed(evt);
            }
        });

        jLabel5.setText("Tên Nhà Cung Cấp");

        jLabel6.setText("Số Điện Thoại");

        jLabel7.setText("Địa Chỉ");

        cbbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhanVienActionPerformed(evt);
            }
        });

        jLabel16.setText("Mã Phiếu Nhập Hàng");

        txtMa_PNH.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi_NV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSDT_NV, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbNhanVien, 0, 182, Short.MAX_VALUE)
                    .addComponent(txtMa_PNH))
                .addGap(91, 91, 91)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT_NCC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, 0, 176, Short.MAX_VALUE)
                    .addComponent(txtDiaChi_NCC))
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChi_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtMa_PNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng chính"));

        btnCapNhat.setText("Cập Nhật");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        txtTimKiem.setText("Tìm kiếm ở đây !!!");
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng phiếu nhập hàng"));

        tablePNH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Nhập Hàng", "Tên Nhân Viên", "Tên Nhà Cung Cấp", "Ngày Lập Phiếu", "Tổng Tiền"
            }
        ));
        tablePNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePNHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePNH);
        if (tablePNH.getColumnModel().getColumnCount() > 0) {
            tablePNH.getColumnModel().getColumn(0).setMinWidth(0);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel8.setText("Tổng Tiền Thanh Toán");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết phiếu nhập hàng"));

        jLabel9.setText("Loại Sản Phẩm");

        jLabel10.setText("Tên Sản Phẩm");

        jLabel11.setText("Xuất Sứ");

        jLabel12.setText("Giá Bán");

        jLabel13.setText("Số Lượng");

        jLabel14.setText("Thành Tiền");

        jLabel15.setText("Bảo Hành");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSL, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGia_SP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtXuatSu_SP)
                            .addComponent(txtThanhTien)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoai_SP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtLoai_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTen_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtXuatSu_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng chi tiết phiếu nhập hàng"));

        table_CT_PNH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại Sản Phẩm", "Tên Loại Sản Phẩm", "Xuất Sứ", "Bảo Hành", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        table_CT_PNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_CT_PNHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_CT_PNH);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        txtTimKiem.setText("");
        txtTimKiem.grabFocus();
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiPhieuNhapHang();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    //Hiển thị thông tin từ bảng phiếu nhập hàng lên các text file
    private void tablePNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePNHMouseClicked
        try {
            int row = tablePNH.getSelectedRow();
            int maPNH = Integer.parseInt(tablePNH.getValueAt(row, 0).toString());
            if (row <= -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng nào trên bảng phiếu nhập hàng");
                return;
            } else {
                txtMa_PNH.setText(tablePNH.getValueAt(row, 0).toString());
                cbbNhanVien.setSelectedItem(tablePNH.getValueAt(row, 1).toString());
                cbbNhaCungCap.setSelectedItem(tablePNH.getValueAt(row, 2).toString());
                txtTongTien.setText(tablePNH.getValueAt(row, 4).toString());
                layThongTin_CT_PNH_TheoMa(maPNH);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tablePNHMouseClicked


    private void cbbNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhaCungCapActionPerformed
        try {
            String tenNhaCungCap = (String) cbbNhaCungCap.getSelectedItem();
            conn = ConnectToDataBase();
            String sql = "Select *from NhaCungCap where DaXoa = 0 and TenNCC = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenNhaCungCap);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                txtSDT_NCC.setText(rs.getString("SDT"));
                txtDiaChi_NCC.setText(rs.getString("DiaChi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbNhaCungCapActionPerformed

    private void cbbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhanVienActionPerformed
        try {
            String hoTen = (String) cbbNhanVien.getSelectedItem();
            conn = ConnectToDataBase();
            String sql = "Select *from NhanVien where DaXoa = 0 and HoTen = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, hoTen);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                txtSDT_NV.setText(rs.getString("SDT"));
                txtDiaChi_NV.setText(rs.getString("DiaChi"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbNhanVienActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            tableModel_PNH.setNumRows(0);
            conn = ConnectToDataBase();
            String sql = "select b.HoTen, c.TenNCC, a.NgayNhapHang, a.TongTien\n"
                    + "from PhieuNhapHang a, NHANVIEN b, NHACUNGCAP c";
            if (txtTimKiem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhà cung cấp");

            } else if (txtTimKiem.getText().length() > 0) {
                sql = sql + " where a.MaNCC = b.MANV and a.MaNCC = c.MaNCC and a.DaXoa = 0 and c.TenNCC like '%" + txtTimKiem.getText() + "%'";
                ps = conn.prepareCall(sql);
                rs = ps.executeQuery();

                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Tên nhà cung cấp " + txtTimKiem.getText() + " không tồn tại trong danh sách!!!");
                    return;
                }
                while (rs.next()) {
                    String[] row = new String[]{
                        rs.getString("TenNCC"),
                        rs.getString("HoTen"),
                        rs.getString("NgayNhapHang"),
                        rs.getString("TongTien"),};
                    tableModel_PNH.addRow(row);
                }
                tableModel_PNH.fireTableDataChanged();
                lamMoiPhieuNhapHang();
                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void table_CT_PNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_CT_PNHMouseClicked
        try {
            int row = table_CT_PNH.getSelectedRow();
            if (row <= -1) {
                JOptionPane.showMessageDialog(this, "Bạn chưa lựa chon dòng trên bảng chi tiết phiếu nhập hàng");
                return;
            } else {
                txtLoai_SP.setText(table_CT_PNH.getValueAt(row, 0).toString());
                txtTen_SP.setText(table_CT_PNH.getValueAt(row, 1).toString());
                txtXuatSu_SP.setText(table_CT_PNH.getValueAt(row, 2).toString());
                txtBaoHanh.setText(table_CT_PNH.getValueAt(row, 3).toString());
                txtGia_SP.setText(table_CT_PNH.getValueAt(row, 4).toString());
                txtSL.setText(table_CT_PNH.getValueAt(row, 5).toString());
                txtThanhTien.setText(table_CT_PNH.getValueAt(row, 6).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_table_CT_PNHMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

        int selectedRow_PNH = tablePNH.getSelectedRow();
        int maPNH = Integer.parseInt(tableModel_PNH.getValueAt(selectedRow_PNH, 0).toString());

        xoa_PNH(maPNH);
        JOptionPane.showMessageDialog(this, "xoa thanh cong");
        layThongTinPNH();
    }//GEN-LAST:event_btnXoaActionPerformed

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
            java.util.logging.Logger.getLogger(formQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formQLPhieuNhapHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbNhaCungCap;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablePNH;
    private javax.swing.JTable table_CT_PNH;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtDiaChi_NCC;
    private javax.swing.JTextField txtDiaChi_NV;
    private javax.swing.JTextField txtGia_SP;
    private javax.swing.JTextField txtLoai_SP;
    private javax.swing.JTextField txtMa_PNH;
    private javax.swing.JTextField txtSDT_NCC;
    private javax.swing.JTextField txtSDT_NV;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTen_SP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtXuatSu_SP;
    // End of variables declaration//GEN-END:variables
}
