package com.example.comics.view1;

import com.example.comics.model.UserLogin;

public class HomeFactory {

    public HomeControllerG getHomeControllerG(){

        HomeControllerG homeControllerG;
        String role = UserLogin.getInstance().getAccount().getRole();

        if(role.equals("reader")){
            homeControllerG = ReaderHomeControllerG.getInstance();
        }
        else{
            homeControllerG = AuthorHomeControllerG.getInstance();
        }

        return homeControllerG;

    }

}
