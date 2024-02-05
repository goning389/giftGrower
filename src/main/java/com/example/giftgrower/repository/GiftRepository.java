package com.example.giftgrower.repository;


import com.example.giftgrower.model.Gift;
import com.example.giftgrower.model.SelectGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, String> {

    List<Gift> findAll();

}