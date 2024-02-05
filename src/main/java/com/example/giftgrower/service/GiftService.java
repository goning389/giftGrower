package com.example.giftgrower.service;

import com.example.giftgrower.model.Gift;
import com.example.giftgrower.model.SelectGift;
import com.example.giftgrower.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;

    public List<Gift> findAll() {
        return giftRepository.findAll();
    }

}