package com.example.spring_boot_jwt.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "MAYBAY")
public @Data
@EqualsAndHashCode(of = {"maMB"})
class MayBay {
    @Id
    private String maMB;
    private String loai;
    private String tamBay;
    @ManyToMany(mappedBy = "dsMayBay", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<NhanVien> dsNhanVien;
}
