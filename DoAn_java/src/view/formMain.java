/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author thanh
 */
public class formMain extends javax.swing.JFrame {
    private FormThongKe mThongKe;
    private frmLinhKien mLinhKien;
    /**
     * Creates new form formMain
     */
    public formMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator14 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        tplMainBoard = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jmenuLinhKien = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        menu = new javax.swing.JMenu();
        menuThongKe = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenuBar1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(400, 45));

        jMenu1.setBorder(null);
        jMenu1.setText("H??? Th???ng");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem1.setText("????ng Xu???t");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK));
        menuExit.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        menuExit.setText("Tho??t");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setBorder(null);
        jMenu2.setText("Qu???n L??");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem3.setText("Nh??n Vi??n");
        jMenu2.add(jMenuItem3);
        jMenu2.add(jSeparator2);

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem4.setText("Kh??ch H??ng");
        jMenu2.add(jMenuItem4);
        jMenu2.add(jSeparator4);

        jmenuLinhKien.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jmenuLinhKien.setText("Linh Ki???n");
        jmenuLinhKien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmenuLinhKienActionPerformed(evt);
            }
        });
        jMenu2.add(jmenuLinhKien);
        jMenu2.add(jSeparator5);

        jMenuItem8.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem8.setText("Lo???i Linh Ki???n");
        jMenu2.add(jMenuItem8);
        jMenu2.add(jSeparator6);

        jMenuItem9.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem9.setText("B??n H??ng");
        jMenu2.add(jMenuItem9);
        jMenu2.add(jSeparator9);

        jMenuItem10.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem10.setText("Qu???n L?? H??a ????n");
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator7);

        jMenuItem11.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem11.setText("Nh???p H??ng");
        jMenu2.add(jMenuItem11);
        jMenu2.add(jSeparator8);

        jMenuItem12.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem12.setText("Qu???n L?? Nh???p H??ng");
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        menu.setBorder(null);
        menu.setText("Th???ng K??");
        menu.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        menuThongKe.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        menuThongKe.setText("Th???ng K??");
        menuThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuThongKeActionPerformed(evt);
            }
        });
        menu.add(menuThongKe);

        jMenuBar1.add(menu);

        jMenu3.setBorder(null);
        jMenu3.setText("Tr??? Gi??p");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem5.setText("Gi???i Thi???u");
        jMenu3.add(jMenuItem5);
        jMenu3.add(jSeparator3);

        jMenuItem6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jMenuItem6.setText("N???i Dung");
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tplMainBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 942, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tplMainBoard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuThongKeActionPerformed
        if (mThongKe == null) {
            mThongKe = new FormThongKe();
            tplMainBoard.addTab("Th???ng K??", mThongKe);
        }
        tplMainBoard.setSelectedComponent(mThongKe);
    }//GEN-LAST:event_menuThongKeActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void jmenuLinhKienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmenuLinhKienActionPerformed
        if (mLinhKien == null) {
            mLinhKien = new frmLinhKien();
            tplMainBoard.addTab("Linh Ki???n", mLinhKien);
        }
        tplMainBoard.setSelectedComponent(mLinhKien);
    }//GEN-LAST:event_jmenuLinhKienActionPerformed

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
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem jmenuLinhKien;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuThongKe;
    private javax.swing.JTabbedPane tplMainBoard;
    // End of variables declaration//GEN-END:variables
}
