package com.example.spring_boot_jwt.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "CHUYENBAY")
public
@Data
@EqualsAndHashCode(of = {"maCB"})
class ChuyenBay {
    @Id
    private String maCB;
    private String gaDi;
    private String gaDen;
    private String doDai;
    private String gioDi;
    private String gioDen;
    private String chiphi;
}
