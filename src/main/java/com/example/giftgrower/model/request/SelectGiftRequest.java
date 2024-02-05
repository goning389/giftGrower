package com.example.giftgrower.model.request;

import lombok.Data;

@Data
public class SelectGiftRequest {
    private String userId;
    private String giftCd;
    private String giftNm;
}