package com.example.comics.view1.beans;

import com.example.comics.model.fagioli.ReaderBean;

public class ReaderBean1 implements ReaderBean {
    String email;
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
