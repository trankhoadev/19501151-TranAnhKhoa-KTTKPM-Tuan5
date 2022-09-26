package com.example.spring_boot_jwt.controllers;

import com.example.spring_boot_jwt.models.NhanVien;
import com.example.spring_boot_jwt.services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nhan-vien")
public class NhanVienController {
    private final NhanVienService nhanVienService;

    @Autowired
    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }
    
	/* Get all data in table */
    @GetMapping
    public List<NhanVien> findAll() {
        return nhanVienService.findAll();
    }
    
	/* Get data by ID */
    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> findNhanVienById(@PathVariable(value = "id") long id) {
    	Optional<NhanVien> nv = 
    }
    
}
