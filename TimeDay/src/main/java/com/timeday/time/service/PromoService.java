package com.timeday.time.service;

import com.timeday.time.service.model.PromoModel;

public interface PromoService {
    PromoModel getPromoByItemId(Integer itemId);
}
