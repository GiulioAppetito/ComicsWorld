package com.example.comics.view1;

import com.example.comics.model.UserLogin;


public class HomeFactory {

    public static HomeControllerG getHomeControllerG(){

        HomeControllerG homeControllerG;
        String role = UserLogin.getInstance().getAccount().getRole();

        if(role.equals("reader")){
            homeControllerG = ReaderHomeControllerG.getInstance();
            homeControllerG.setLocation(ReaderHomeControllerG.class.getResource("readerhome.fxml"));
        }
        else{
            homeControllerG = AuthorHomeControllerG.getInstance();
            homeControllerG.setLocation(HomeControllerG.class.getResource("authorhome.fxml"));
        }

        return homeControllerG;

    }

}
