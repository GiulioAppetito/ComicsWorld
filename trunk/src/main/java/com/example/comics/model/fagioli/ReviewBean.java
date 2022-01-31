package com.example.comics.model.fagioli;

import com.example.comics.model.Account;

public interface ReviewBean {

    String getComment();
    void setComment(String comment);

    Account getAccount();
    void setAccount(Account account);

    int getRating();
    void setRating(int rating);


}
