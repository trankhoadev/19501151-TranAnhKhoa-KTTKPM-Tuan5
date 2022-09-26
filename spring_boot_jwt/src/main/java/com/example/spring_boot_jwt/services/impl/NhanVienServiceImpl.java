package com.example.spring_boot_jwt.services.impl;

import com.example.spring_boot_jwt.models.NhanVien;
import com.example.spring_boot_jwt.repositories.NhanVienRepository;
import com.example.spring_boot_jwt.services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    @Autowired
    public NhanVienServiceImpl(NhanVienRepository nhanVienRepository) {
        this.nhanVienRepository = nhanVienRepository;
    }
    @Override
    public List<NhanVien> findAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien findNhanVienByMaNV(Long id) {
        return nhanVienRepository.findById(id).orElse(null);
    }
}
