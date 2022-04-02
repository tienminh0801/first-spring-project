package com.SpringBoot.dbsClone.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dong_san_pham")
public class Product {
    @Id
    @Column(name = "ten_san_pham", unique = true, nullable = false)
    String name;


    @ManyToOne
    @JoinColumn(name = "ten_san_pham_tang_kem")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Product productBonus;

    @Column(name = "loai_san_pham",nullable = false)
    String type;

    @Column(name = "khuyen_mai")
    String discount;

//    @Column(name = "ma_so_hang",nullable = false)
    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @NotNull(message = "hello validation")
    @ManyToOne
    @JoinColumn(name = "ma_so_hang")
    Factory factory;

    @Column(name = "gia_niem_yet",nullable = false)
    int price;

    @Column(name = "thoi_gian_bao_hanh",nullable = false)
    int guaranteeTime;

    @Column(name = "chat_lieu",nullable = false)
    String material;

    @Column(name = "thoi_gian_ra_mat",nullable = false)
    Date debutTime;

}
