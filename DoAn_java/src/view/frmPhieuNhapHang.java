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
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class frmPhieuNhapHang extends javax.swing.JFrame {

    public Connection conn;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    DefaultTableModel tableModel_CT_PNH;

    public frmPhieuNhapHang() {
        initComponents();
        this.setLocationRelativeTo(null);
        tableModel_CT_PNH = (DefaultTableModel) table_CT.getModel();
        layTenNhanVien_LenCBB();
        layTenNhaCungCap_LenCBB();
        layTenLoaiLK();

        //Ẩn thông tin cột Mã chi tiết phiếu nhập hàng, Mã linh kiện, Loại linh kiện
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
        txtDiaChi_NV.setText("");
        txtSDT_NV.setText("");
        txtDiaChi_NCC.setText("");
        txtSDT_NCC.setText("");
    }

    public void lamMoiCTPhieuNhapHang() {
        txtTen_SP.setText("");
        txtXuatSu_SP.setText("");
        txtBaoHanh.setText("");
        txtGia_SP.setText("");
        txtSL.setText("");
        txtThanhTien.setText("");
    }

    private void layTenNhanVien_LenCBB() {
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

    private void layTenNhaCungCap_LenCBB() {
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

    private void layTenLoaiLK() {
        try {
            conn = ConnectToDataBase();
            String sql = "select TenLoaiLinhkien from LoaiLinhKien";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            Vector data = new Vector();
            while (rs.next()) {
                data.add(rs.getString("TenLoaiLinhkien"));
            }
            DefaultComboBoxModel conBoxModel = new DefaultComboBoxModel(data);
            cbbLoai_SP.setModel(conBoxModel);
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private double tinhTongTien() {
        int row = table_CT.getRowCount();
        double tongtien = 0;
        for (int i = 0; i < row; i++) {
            double ThanhTien = Double.parseDouble(tableModel_CT_PNH.getValueAt(i, 6).toString());
            tongtien += ThanhTien;
        }
        return tongtien;
    }

    private void them_PNH(int maNCC, int maNV, double tongTien) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String date1 = formatter.format(date);
        try {
            conn = ConnectToDataBase();
            String sql = "insert into PhieuNhapHang(MANV,MaNCC,NgayNhapHang,Tongtien,DaXoa)\n"
                    + "values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, maNV);
            ps.setInt(2, maNCC);
            ps.setString(3, date1);
            ps.setDouble(4, tongTien);
            ps.setInt(5, 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void them_CT_PNH(int ma_PNH, int Loai_LK, int maLK, String TenLinhKien, String xuatSu, String baoHanh, double giaBan, int soLuong, double thanhTien) {
        try {
            conn = ConnectToDataBase();
            String sql = "insert into CTPNH(MaPNH,MaLinhKien,LoaiLinhKien,TenLinhKien,XuatSu,BaoHanh,GiaBan,SoLuongNhap,ThanhTien)\n"
                    + "values(?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ma_PNH);
            ps.setInt(2, maLK);
            ps.setInt(3, Loai_LK);
            ps.setString(4, TenLinhKien);
            ps.setString(5, xuatSu);
            ps.setString(6, baoHanh);
            ps.setDouble(7, giaBan);
            ps.setInt(8, soLuong);
            ps.setDouble(9, thanhTien);
            ps.executeUpdate();
        } catch (Exception e) {
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
        jPanel3 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        txtThanhToan = new javax.swing.JButton();
        txtThemMoi = new javax.swing.JButton();
        txtXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
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
        cbbLoai_SP = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_CT = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 28)); // NOI18N
        jLabel1.setText(" PHIẾU NHẬP HÀNG");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(426, 426, 426))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
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
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtSDT_NV, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.Alignment.LEADING, 0, 215, Short.MAX_VALUE)
                    .addComponent(txtDiaChi_NV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDT_NCC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi_NCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiaChi_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtDiaChi_NV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng chính"));

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtThanhToan.setText("Thanh toán");
        txtThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhToanActionPerformed(evt);
            }
        });

        txtThemMoi.setText("Thêm mới");
        txtThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThemMoiActionPerformed(evt);
            }
        });

        txtXoa.setText("Xóa");
        txtXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhToan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết phiếu nhập hàng"));

        jLabel9.setText("Loại Sản Phẩm");

        jLabel10.setText("Tên Sản Phẩm");

        jLabel11.setText("Xuất Sứ");

        jLabel12.setText("Giá Bán");

        txtGia_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGia_SPMouseClicked(evt);
            }
        });

        jLabel13.setText("Số Lượng");

        txtSL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSLMouseClicked(evt);
            }
        });

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
                        .addGap(49, 49, 49)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                            .addComponent(txtGia_SP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBaoHanh, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtXuatSu_SP)
                            .addComponent(txtThanhTien)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbLoai_SP, 0, 142, Short.MAX_VALUE)
                            .addComponent(txtTen_SP))
                        .addGap(1, 1, 1)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbLoai_SP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(39, Short.MAX_VALUE))
        );

        table_CT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loại Sản Phẩm", "Tên Sản Phẩm", "Xuất Sứ", "Bảo Hành", "Giá Bán", "Số Lượng", "Thành Tiền"
            }
        ));
        table_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_CTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_CT);

        jLabel16.setText("Tổng Tiền Thanh Toán");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(36, 36, 36)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        lamMoiPhieuNhapHang();
        lamMoiCTPhieuNhapHang();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtGia_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGia_SPMouseClicked
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtGia_SPMouseClicked

    private void txtSLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSLMouseClicked
        txtThanhTien.setText("");
    }//GEN-LAST:event_txtSLMouseClicked

    private void table_CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_CTMouseClicked
        try {
            int row = table_CT.getSelectedRow();
            if (row >= 0) {
                cbbLoai_SP.getModel().setSelectedItem(tableModel_CT_PNH.getValueAt(row, 0));
                txtTen_SP.setText(table_CT.getValueAt(row, 1).toString());
                txtXuatSu_SP.setText(table_CT.getValueAt(row, 2).toString());
                txtBaoHanh.setText(table_CT.getValueAt(row, 3).toString());
                txtGia_SP.setText(table_CT.getValueAt(row, 4).toString());
                txtSL.setText(table_CT.getValueAt(row, 5).toString());
                double thanhTien = Double.parseDouble(tableModel_CT_PNH.getValueAt(row, 6).toString());
                Locale VN = new Locale("vi", "VN");
                Currency vnd = Currency.getInstance(VN);
                NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                txtThanhTien.setText(VNDFormat.format(thanhTien));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_table_CTMouseClicked

    private void txtThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThemMoiActionPerformed

        try {
            if (txtSL.getText().equals("") || txtGia_SP.getText().equals("") || txtXuatSu_SP.getText().trim().equals("")
                    || txtBaoHanh.getText().trim().equals("") || txtTen_SP.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đủ thông tin");
                return;
            }
            try {
                int slban = Integer.parseInt(txtSL.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                txtSL.setText("");
                txtSL.grabFocus();
                return;
            }
            try {
                long giaban = Long.parseLong(txtGia_SP.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gía bán phải là số");
                txtGia_SP.setText("");
                txtGia_SP.grabFocus();
                return;
            }

            int slban = Integer.parseInt(txtSL.getText());
            long giaban = Long.parseLong(txtGia_SP.getText());
            double thanhTien = slban * giaban;
            int row = table_CT.getRowCount();

            for (int i = 0; i < row; i++) {
                //kiểm tra nếu trùng tên thì tăng số lượng và cập nhật lại số lượng sản phẩm và số tiền của phiếu nhập hàng
                if (table_CT.getValueAt(i, 1).equals(txtTen_SP.getText())) {
                    int slHienTai = Integer.parseInt(tableModel_CT_PNH.getValueAt(i, 5).toString());
                    int slThem = Integer.parseInt(txtSL.getText());
                    int tongSL = slHienTai + slThem;
                    double thanhTienMoi = giaban * tongSL;
                    double giaBanMoi = Double.parseDouble(txtGia_SP.getText());

                    tableModel_CT_PNH.setValueAt(giaBanMoi + "", i, 4);
                    tableModel_CT_PNH.setValueAt(tongSL + "", i, 5);
                    tableModel_CT_PNH.setValueAt(thanhTienMoi + "", i, 6);
                    lamMoiCTPhieuNhapHang();

                    double tongTien = tinhTongTien();
                    Locale VN = new Locale("vi", "VN");
                    Currency dollars = Currency.getInstance(VN);
                    NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
                    txtTongTien.setText(VNDFormat.format(tongTien));
                    return;
                }
            }
            Vector<Object> vec = new Vector<>();

            vec.add(cbbLoai_SP.getSelectedItem().toString());
            vec.add(txtTen_SP.getText());
            vec.add(txtXuatSu_SP.getText());
            vec.add(txtBaoHanh.getText());
            vec.add(txtGia_SP.getText());
            vec.add(txtSL.getText());
            vec.add(thanhTien + "");

            tableModel_CT_PNH.addRow(vec);
            lamMoiCTPhieuNhapHang();
            tableModel_CT_PNH.fireTableDataChanged();

            double tongTien = tinhTongTien();
            Locale VN = new Locale("vi", "VN");
            Currency dollars = Currency.getInstance(VN);
            NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
            txtTongTien.setText(VNDFormat.format(tongTien));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtThemMoiActionPerformed

    private int LayMaNhanVienTheoTen(String TenNV) {
        int Ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select MANV from NHANVIEN where DaXoa=0 and HoTen=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, TenNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ma = rs.getInt("MANV");
                return Ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ma;
    }

    private int LayMaNCCTheoTen(String TenNCC) {
        int Ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select MaNCC from NhaCungCap where DaXoa=0 and TenNCC=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, TenNCC);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ma = rs.getInt("MaNCC");
                return Ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ma;
    }

    private int LayMaLinhKien() {
        int Ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select MaLinhKien from LinhKien where DaXoa=0";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ma = rs.getInt("MaLinhKien");
                return Ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ma;
    }

    private int LayMaLinhLoaiKienTheoTen(String loai_LK) {
        int Ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select a.MaLoaiLinhKien from LoaiLinhKien a, CTPNH b where a.MaLoaiLinhKien = b.LoaiLinhKien and TenLoaiLinhKien = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loai_LK);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ma = rs.getInt("MaLoaiLinhKien");
                return Ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ma;
    }

    private int layMa_PNH() {
        int ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select top 1 MaPNH from PhieuNhapHang order by MaPNH desc ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ma = rs.getInt("MaPNH");
                return ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }

    private int layMa_CT_PNH() {
        int ma = 0;
        try {
            conn = ConnectToDataBase();
            String sql = "select top 1 a.MaCTPNH from CTPNH a, PhieuNhapHang b where a.MaCTPNH = B.MaPNH order by MaCTPNH desc ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ma = rs.getInt("MaCTPNH");
                return ma;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ma;
    }

    private void txtXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXoaActionPerformed
        int row = table_CT.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn phiếu nhập hàng trong bảng để xóa");
            return;
        } else {
            tableModel_CT_PNH.removeRow(row);
            double tongTien = tinhTongTien();
            Locale VN = new Locale("vi", "VN");
            Currency dollars = Currency.getInstance(VN);
            NumberFormat VNDFormat = NumberFormat.getCurrencyInstance(VN);
            txtTongTien.setText(VNDFormat.format(tongTien));
            lamMoiCTPhieuNhapHang();
        }
    }//GEN-LAST:event_txtXoaActionPerformed

    private void txtThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhToanActionPerformed
        try {
            int rowCount = table_CT.getRowCount();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(rootPane, "Chưa thêm Phiếu nhập hàng ");
                return;
            } else {
                int maNV = LayMaNhanVienTheoTen(cbbNhanVien.getSelectedItem().toString());
                int maNCC = LayMaNCCTheoTen(cbbNhaCungCap.getSelectedItem().toString());
                int maLoai_LK = LayMaLinhLoaiKienTheoTen(cbbLoai_SP.getSelectedItem().toString());
                double tongTien = tinhTongTien();
                
                them_PNH(maNCC, maNV, tongTien);
                
                int maPNH = layMa_PNH();
                int maCT_PNH = layMa_CT_PNH();
                int maLK = LayMaLinhKien();
                
                for (int i = 0; i < rowCount; i++) {
                    String tenLK = tableModel_CT_PNH.getValueAt(i, 1).toString();
                    String xuatSu = tableModel_CT_PNH.getValueAt(i, 2).toString();
                    String baohanh = tableModel_CT_PNH.getValueAt(i, 3).toString();
                    double giaBan = Double.parseDouble(tableModel_CT_PNH.getValueAt(i, 4).toString());
                    int soLuong = Integer.parseInt(tableModel_CT_PNH.getValueAt(i, 5).toString());
                    double thanhTien = Double.parseDouble(tableModel_CT_PNH.getValueAt(i, 6).toString());
                    them_CT_PNH(maPNH, maLK, maLoai_LK, tenLK, xuatSu, baohanh, giaBan, soLuong, thanhTien);
                };
                
               tableModel_CT_PNH.setRowCount(0);
                lamMoiPhieuNhapHang();
                lamMoiCTPhieuNhapHang();
                txtTongTien.setText("");
                JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtThanhToanActionPerformed

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
            java.util.logging.Logger.getLogger(frmPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPhieuNhapHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPhieuNhapHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JComboBox<String> cbbLoai_SP;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_CT;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtDiaChi_NCC;
    private javax.swing.JTextField txtDiaChi_NV;
    private javax.swing.JTextField txtGia_SP;
    private javax.swing.JTextField txtSDT_NCC;
    private javax.swing.JTextField txtSDT_NV;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTen_SP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JButton txtThanhToan;
    private javax.swing.JButton txtThemMoi;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JButton txtXoa;
    private javax.swing.JTextField txtXuatSu_SP;
    // End of variables declaration//GEN-END:variables
}
