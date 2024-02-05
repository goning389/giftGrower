package com.example.giftgrower.service;

import com.example.giftgrower.model.Gift;
import com.example.giftgrower.model.SelectGift;
import com.example.giftgrower.repository.GiftRepository;
import com.example.giftgrower.repository.SelectGiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SelectGiftService {
    private final SelectGiftRepository selectGiftRepository;

    /* 현재 키우고 있는 선물 정보 */
    public SelectGift findByUserId(String userId) {
        return selectGiftRepository.findByUserId(userId);
    }

}