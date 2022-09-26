package com.example.spring_boot_jwt.services;

import com.example.spring_boot_jwt.models.NhanVien;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NhanVienService {
    public List<NhanVien> findAll();
    public NhanVien findNhanVienById(Long id);
    public NhanVien getDiemDenChuyenBay(String diemDen);
}
