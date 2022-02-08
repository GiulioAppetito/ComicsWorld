package com.example.comics.model.fagioli.bundle;

import com.example.comics.model.fagioli.ReaderBean;

public class ReaderBundle implements ReaderBean {
    private String email;
    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
