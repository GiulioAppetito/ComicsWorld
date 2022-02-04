package com.example.comics.controller;

import com.example.comics.controller.boundaries.BuyComicBoundary;
import com.example.comics.controller.boundaries.BuyComicsAuthorBoundary;
import com.example.comics.model.DiscountCode;
import com.example.comics.model.UserLogin;
import com.example.comics.model.dao.DiscountCodeDAO;
import com.example.comics.model.exceptions.DiscountCodeException;
import com.example.comics.model.exceptions.InvalidPaymentException;
import com.example.comics.model.fagioli.AccountBean;
import com.example.comics.model.fagioli.SeriesBean;
import com.example.comics.model.fagioli.bundle.AccountBundle;

import java.time.LocalDate;

public class BuyComicController {

    public void buyComic(SeriesBean seriesBean, String code) throws InvalidPaymentException, DiscountCodeException {
        DiscountCode discountCode = null;
        //controllo del codice se è valido,se inserito
        if(code != "" || code != null){
            discountCode = UserLogin.getInstance().getReader().getDiscountCodeByCode(code);
            if(discountCode == null){
                throw new DiscountCodeException("You don't own this discount code!");
            }
            if(discountCode.getExpiringDate().isBefore(LocalDate.now())){
                throw new DiscountCodeException("Your code is expired!");
            }
        }


        //nel caso vada tutto a buon fine ...

        //chiamo altra boundary per la carta
        AccountBean accountBean = new AccountBundle();
        accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
        accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());

        BuyComicBoundary buyComicBoundary = new BuyComicBoundary();
        buyComicBoundary.convalidPayment(accountBean, seriesBean, discountCode);

    }
    
    public void completedPayment(SeriesBean seriesBean, DiscountCode discountCode){
        //mail all'autore
        BuyComicsAuthorBoundary buyComicsAuthorBoundary = new BuyComicsAuthorBoundary();
        buyComicsAuthorBoundary.sendEmailForSoldChapter(seriesBean);

        //cancella discount code
        UserLogin.getInstance().getReader().removeDiscountCode(discountCode);
        DiscountCodeDAO discountCodeDAO = new DiscountCodeDAO();
        discountCodeDAO.deleteDiscountCode(UserLogin.getInstance().getReader(),discountCode);

        //mostri il pagamento avvenuto

        System.out.println("Payment went well");
    }


    public void failedPayment(SeriesBean seriesBean) {
        System.out.println("Payment of "+seriesBean.getTitle()+" went wrong");
    }

    public void buyComic(SeriesBean seriesBean) {

        //chiamo altra boundary per la carta
        AccountBean accountBean = new AccountBundle();
        accountBean.setFirstName(UserLogin.getInstance().getAccount().getFirstName());
        accountBean.setLastName(UserLogin.getInstance().getAccount().getLastName());

        BuyComicBoundary buyComicBoundary = new BuyComicBoundary();
        buyComicBoundary.convalidPayment(accountBean, seriesBean);

    }

    public void completedPayment(SeriesBean seriesBean) {
        //mail all'autore
        BuyComicsAuthorBoundary buyComicsAuthorBoundary = new BuyComicsAuthorBoundary();
        buyComicsAuthorBoundary.sendEmailForSoldChapter(seriesBean);

        //mostri il pagamento avvenuto
        System.out.println("Payment went well");
    }
}
