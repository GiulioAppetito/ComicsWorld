package com.example.comics.model.fagioli;

import com.example.comics.model.Account;

public interface ReviewBean {

    String getComment();
    void setComment(String comment);

    int getRating();
    void setRating(int rating);

    Account getAccount();
    void setAccount(Account account);

}
