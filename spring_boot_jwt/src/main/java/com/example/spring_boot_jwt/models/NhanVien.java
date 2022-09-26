package com.example.spring_boot_jwt.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NHANVIEN")
public @Data
@EqualsAndHashCode(of = {"maNV"})
class NhanVien {
    @Id
    private String maNV;
    private String ten;
    private String luong;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "CHUNGNHAN", joinColumns = {@JoinColumn(name = "maNV")},
            inverseJoinColumns = {@JoinColumn(name = "maMB")})
    private Set<ChuyenBay> dsMayBay = new HashSet<>();
}
