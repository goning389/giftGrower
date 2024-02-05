package com.example.giftgrower.repository;


import com.example.giftgrower.model.Gift;
import com.example.giftgrower.model.SelectGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectGiftRepository extends JpaRepository<SelectGift, String> {

    /* 현재 키우고 있는 선물 정보 */
    SelectGift findByUserId(String userId);

}