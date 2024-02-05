package com.example.giftgrower.model;

import lombok.Data;

import javax.persistence.*;

/*@Getter
@Setter*/
@Data
@Entity
@Table(name = "gift")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gift_cd")
    private String giftCd;

    @Column(name="gift_nm")
    private String giftNm;

    @Column(name="memo")
    private String memo;

}