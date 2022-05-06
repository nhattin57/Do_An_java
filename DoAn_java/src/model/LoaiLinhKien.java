/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class LoaiLinhKien {
    private int MaLoaiLinhKien ;
    private String TenLoaiLinhKien ;

    public LoaiLinhKien(int MaLoaiLinhKien, String TenLoaiLinhKien) {
        this.MaLoaiLinhKien = MaLoaiLinhKien;
        this.TenLoaiLinhKien = TenLoaiLinhKien;
    }

    public int getMaLoaiLinhKien() {
        return MaLoaiLinhKien;
    }

    public void setMaLoaiLinhKien(int MaLoaiLinhKien) {
        this.MaLoaiLinhKien = MaLoaiLinhKien;
    }

    public String getTenLoaiLinhKien() {
        return TenLoaiLinhKien;
    }

    public void setTenLoaiLinhKien(String TenLoaiLinhKien) {
        this.TenLoaiLinhKien = TenLoaiLinhKien;
    }
    
    
}
