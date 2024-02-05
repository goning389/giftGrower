package com.example.giftgrower.model;

import lombok.Data;

import javax.persistence.*;

/*@Getter
@Setter*/
@Data
@Entity
@Table(name = "selectgift")
public class SelectGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String userId;

    @Column(name="gift_cd")
    private String giftCd;

    @Column(name="gift_nm")
    private String giftNm;

}