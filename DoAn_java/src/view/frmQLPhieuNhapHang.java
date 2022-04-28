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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class frmQLPhieuNhapHang extends javax.swing.JFrame {

    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    DefaultTableModel tableModel;
    DefaultTableModel tableModelCT;

    /**
     * Creates new form frmQLPhieuNhapHang
     */
    public frmQLPhieuNhapHang() {
        initComponents();
        setLocationRelativeTo(null);
        tableModel = (DefaultTableModel) tblPhieuNhapHang.getModel();
        tableModelCT = (DefaultTableModel) tblCTPhieuNhapHang.getModel();
        txtMaPhieuNhapHang.grabFocus();
        hienThiDuLieuPhieuNhapHang();
        hienThiNhaCungCap();
        hienThiNhanVien();
        hienThiTenLoaiLK();
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

    public void refreshThongTinChung() {
        txtMaPhieuNhapHang.setText("");
        txtPhoneNhaCC.setText("");
        txtPhoneNhanVien.setText("");
        txtTimKiem.setText("");
        txtTongTien.setText("");
        txtDiaChi.setText("");
        txtDiaChiNV.setText("");
        txtNgayLapPhieu.setText("");
        txtMaPhieuNhapHang.grabFocus();
    }

    public void refreshThongTinCT() {
        txtMaCT.setText("");
        txtTenLK.setText("");
        txtXuatSu.setText("");
        txtGiaBan.setText("");
        txtSL.setText("");
        txtThanhTien.setText("");
        txtMaCT.grabFocus();
    }

    private void hienThiDuLieuPhieuNhapHang() {
        try {
            conn = ConnectToDataBase();
            String sql = "select MaPNH,c.TenNCC,b.HoTen,NgayNhapHang,Tongtien \n"
                    + "from PhieuNhapHang a,NHANVIEN b,NHACUNGCAP c\n"
                    + "where a.MaNCC=c.MaNCC and a.MANV=b.MANV and a.DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nha cung cấp
            tableModel.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaPNH"),
                    rs.getString("HoTen"),
                    rs.getString("TenNCC"),
                    rs.getString("NgayNhapHang"),
                    rs.getString("Tongtien")
                };
                tableModel.addRow(row);
            }

            // cập nhật lại dữ liệu bên trong bảng nhà cung cấp
            tableModel.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void hienThiDuLieuCTPhieuNhapHang() {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.MaCTPNH,b.TenLoaiLinhKien,a.TenLinhKien, a.XuatSu, a.GiaBan, a.SoLuongNhap, a.ThanhTien\n"
                    + "from CTPNH a, LoaiLinhKien b, PhieuNhapHang c, LinhKien d\n"
                    + "where a.MaPNH = c.MaPNH and a.MaLinhKien = d.MaLinhKien and d.MaLinhKien = b.MaLoaiLinhKien";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nha cung cấp
            tableModelCT.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaCTPNH"),
                    rs.getString("TenloaiLinhKien"),
                    rs.getString("TenLinhKien"),
                    rs.getString("XuatSu"),
                    rs.getString("GiaBan"),
                    rs.getString("SoLuongNhap"),
                    rs.getString("ThanhTien"),};
                tableModelCT.addRow(row);
            }

            // cập nhật lại dữ liệu bên trong bảng nhà cung cấp
            tableModelCT.fireTableDataChanged();
            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void hienthiCTtheoMaPNH(int maPNH) {
        try {
            conn = ConnectToDataBase();
            String sql = "select a.MaCTPNH,b.TenLoaiLinhKien,a.TenLinhKien, a.XuatSu, a.GiaBan, a.SoLuongNhap, a.ThanhTien\n"
                    + "from CTPNH a, LoaiLinhKien b, PhieuNhapHang c, LinhKien d\n"
                    + "where a.MaPNH = ? and a.MaLinhKien = d.MaLinhKien and d.MaLinhKien = b.MaLoaiLinhKien";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maPNH);
            rs = ps.executeQuery();

            //cập nhập lại giá trị bên trong bảng nha cung cấp
            tableModelCT.setRowCount(0);

            while (rs.next()) {
                String[] row = new String[]{
                    rs.getString("MaCTPNH"),
                    rs.getString("TenloaiLinhKien"),
                    rs.getString("TenLinhKien"),
                    rs.getString("XuatSu"),
                    rs.getString("GiaBan"),
                    rs.getString("SoLuongNhap"),
                    rs.getString("ThanhTien"),};
                tableModelCT.addRow(row);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void hienThiNhaCungCap() {
        try {

            conn = ConnectToDataBase();
            String sql = "select TenNCC from NhaCungCap where DaXoa = 0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Vector data = new Vector();
            while (rs.next()) {

                data.add(rs.getString("TenNCC"));
            }
            DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(data);
            cbbTenNCC.setModel(cmbModel);

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void hienThiNhanVien() {
        try {
            conn = ConnectToDataBase();
            String sql = "select HoTen from NhanVien ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("HoTen"));
            }
            DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(data);
            cbbNhanVien.setModel(cmbModel);

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void hienThiTenLoaiLK() {
        try {

            conn = ConnectToDataBase();
            String sql = "select TenLoaiLinhKien from LoaiLinhKien ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Vector data = new Vector();
            while (rs.next()) {

                data.add(rs.getString("TenLoaiLinhKien"));
            }
            DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(data);
            cbbLoaiLK.setModel(cmbModel);

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaPhieuNhapHang = new javax.swing.JTextField();
        txtPhoneNhanVien = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        cbbTenNCC = new javax.swing.JComboBox<>();
        cbbNhanVien = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtPhoneNhaCC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChiNV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNgayLapPhieu = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhieuNhapHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTPhieuNhapHang = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMaCT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbbLoaiLK = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtTenLK = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtXuatSu = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtSL = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý phiếu nhập hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(495, 495, 495)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chung"));

        jLabel2.setText("Mã phiếu nhập hàng");

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Tên nhà cung cấp");

        jLabel6.setText("Địa chỉ");

        jLabel7.setText("Tổng tiền thanh toán");

        txtMaPhieuNhapHang.setEditable(false);

        cbbTenNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenNCCActionPerformed(evt);
            }
        });

        cbbNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhanVienActionPerformed(evt);
            }
        });

        jLabel8.setText("Số điện thoại");

        jLabel9.setText("Địa chỉ");

        jLabel10.setText("Ngày lập phiếu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhoneNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(cbbNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChiNV)
                            .addComponent(txtNgayLapPhieu))))
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChi)
                            .addComponent(cbbTenNCC, 0, 172, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongTien)
                            .addComponent(txtPhoneNhaCC, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaPhieuNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbTenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(txtPhoneNhaCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhoneNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtDiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addContainerGap())
                            .addComponent(txtNgayLapPhieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng chính"));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem)
                        .addGap(210, 210, 210))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách phiếu nhập hàng"));

        tblPhieuNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập Hàng", "Tên nhân viên", "Tên nhà cung cấp", "Ngày lập phiếu", "Tổng tiền thanh toán"
            }
        ));
        tblPhieuNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuNhapHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPhieuNhapHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1222, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách chi tiết phiếu nhập hàng"));

        tblCTPhieuNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CT phiếu nhập hàng", "Tên loại linh kiên", "Tên linh kiên", "Xuất sứ", "Giá bán", "Số lượng nhập", "Thành tiền"
            }
        ));
        tblCTPhieuNhapHang.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblCTPhieuNhapHangAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblCTPhieuNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTPhieuNhapHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTPhieuNhapHang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(309, 309, 309))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết phiếu nhập hàng"));

        jLabel11.setText("Mã chi tiết phiếu nhập hàng");

        txtMaCT.setEditable(false);

        jLabel12.setText("Tên loại linh kiện");

        jLabel13.setText("Tên linh kiện");

        jLabel14.setText("Xuất sứ");

        jLabel15.setText("Giá bán");

        jLabel16.setText("Số lượng nhập");

        jLabel17.setText("Thành tiền");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaCT)
                    .addComponent(cbbLoaiLK, 0, 186, Short.MAX_VALUE)
                    .addComponent(txtThanhTien))
                .addGap(85, 85, 85)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenLK, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(txtXuatSu))
                .addGap(94, 94, 94)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(64, 64, 64)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiaBan)
                    .addComponent(txtSL, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txtTenLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(cbbLoaiLK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtXuatSu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhanVienActionPerformed
        try {
            String HoTen = (String) cbbNhanVien.getSelectedItem();
            conn = ConnectToDataBase();
            String sql = "select *from NhanVien where HoTen = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, HoTen);
            rs = ps.executeQuery();
            Vector data = new Vector();
            while (rs.next()) {
                txtPhoneNhanVien.setText(rs.getString("SDT"));
                txtDiaChiNV.setText(rs.getString("DiaCHi"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbNhanVienActionPerformed

    private void cbbTenNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenNCCActionPerformed
        try {

            String tenNhaCC = (String) cbbTenNCC.getSelectedItem();
            conn = ConnectToDataBase();
            String sql = "select *from NhaCungCap where TenNCC = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tenNhaCC);
            rs = ps.executeQuery();
            Vector data = new Vector();
            while (rs.next()) {
                txtPhoneNhaCC.setText(rs.getString("SDT"));
                txtDiaChi.setText(rs.getString("DiaCHi"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cbbTenNCCActionPerformed

    private void tblPhieuNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuNhapHangMouseClicked

        int row = tblPhieuNhapHang.getSelectedRow();
        int maPNH = Integer.parseInt(tblPhieuNhapHang.getValueAt(row, 0).toString());
        try {
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn vào cột nào");
            } else {
                txtMaPhieuNhapHang.setText(tblPhieuNhapHang.getValueAt(row, 0).toString());
                cbbNhanVien.setSelectedItem(tblPhieuNhapHang.getValueAt(row, 1).toString());
                cbbTenNCC.setSelectedItem(tblPhieuNhapHang.getValueAt(row, 2).toString());
                txtNgayLapPhieu.setText(tblPhieuNhapHang.getValueAt(row, 3).toString());
                txtTongTien.setText(tblPhieuNhapHang.getValueAt(row, 4).toString());
                hienthiCTtheoMaPNH(maPNH);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblPhieuNhapHangMouseClicked

    private void tblCTPhieuNhapHangAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblCTPhieuNhapHangAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCTPhieuNhapHangAncestorAdded

    private void tblCTPhieuNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTPhieuNhapHangMouseClicked
        int row = tblCTPhieuNhapHang.getSelectedRow();
        try {
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa lựa chọn vào cột nào");
            } else {
                txtMaCT.setText(tblCTPhieuNhapHang.getValueAt(row, 0).toString());
                cbbLoaiLK.setSelectedItem(tblCTPhieuNhapHang.getValueAt(row, 1).toString());
                txtTenLK.setText(tblCTPhieuNhapHang.getValueAt(row, 2).toString());
                txtXuatSu.setText(tblCTPhieuNhapHang.getValueAt(row, 3).toString());
                txtGiaBan.setText(tblCTPhieuNhapHang.getValueAt(row, 4).toString());
                txtSL.setText(tblCTPhieuNhapHang.getValueAt(row, 5).toString());
                txtThanhTien.setText(tblCTPhieuNhapHang.getValueAt(row, 6).toString());
                hienThiDuLieuCTPhieuNhapHang();
         
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblCTPhieuNhapHangMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        try {
            //xóa trắng dữ liệu bên trong bảng nhà cung cấp
            tableModel.setRowCount(0);
            conn = ConnectToDataBase();

            String sql = "select a.MaPNH, c.HoTen, b.TenNCC, a.NgayNhapHang, a.TongTien \n"
                    + "from PhieuNhapHang a, NHACUNGCAP b, NHANVIEN c";
            if (txtTimKiem.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhà cung cấp");
            } else if (txtTimKiem.getText().length() > 0) {
                sql = sql + "where a.DaXoa=0 and a.MaNCC=b.MaNCC and a.MANV=c.MANV and b.TenNCC like '%" + txtTimKiem.getText() + "%'";

                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Tên nhà cung cấp " + txtTimKiem.getText() + " không tồn tại trong danh sách!");
                    return;
                }
                while (rs.next()) {
                    String[] row = new String[]{
                        rs.getString("MaPNH"),
                        rs.getString("HoTen"),
                        rs.getString("TenNCC"),
                        rs.getString("NgayNhapHang"),
                        rs.getString("TongTien"),};
                    tableModel.addRow(row);
                }

                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Tìm thấy nhà cung cấp có tên gần đúng là: " + txtTimKiem.getText());
                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(frmQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQLPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLPhieuNhapHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbLoaiLK;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private javax.swing.JComboBox<String> cbbTenNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCTPhieuNhapHang;
    private javax.swing.JTable tblPhieuNhapHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDiaChiNV;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaCT;
    private javax.swing.JTextField txtMaPhieuNhapHang;
    private javax.swing.JTextField txtNgayLapPhieu;
    private javax.swing.JTextField txtPhoneNhaCC;
    private javax.swing.JTextField txtPhoneNhanVien;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenLK;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtXuatSu;
    // End of variables declaration//GEN-END:variables

}
