package com.example.comics.model.fagioli;

import java.time.LocalDate;

public interface DiscountCodeBean {
    String getCode();
    void setCode(String code);

    void setPercentage(Float percentage);
    Float getPercentage();

    LocalDate getExpiringDate();
    void setExpiringDate(LocalDate expiringDate);

    int getLimitDays();
    void setLimitDays(int limitDays);


}
