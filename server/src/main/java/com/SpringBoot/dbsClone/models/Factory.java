package com.SpringBoot.dbsClone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hang_san_xuat")
public class Factory {
    @Id
    @Column(name = "ma_so_hang")
    String idFactor;
    @Column(name = "ten_hang")
    String name;
    @Column(name = "xuat_xu")
    String origin;

//    @OneToMany(mappedBy = "factorId")
//    Set<Product> products ;
}
