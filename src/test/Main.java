/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.stripe.Stripe;
import entities.Carte;
import entities.Reservation;
import entities.Utilisateur;
import java.sql.Date;
import services.ServiceRéservationIMP;
import services.ServiceStripeIMP;

/**
 *
 * @author chaker
 */
public class Main {
    public static void main(String[] args) { 
        Stripe.apiKey="sk_test_51KW6yrADzWkQoAVyoXU2QxKdXriWn9eO6XH8SCttqQlpC7ZVzsd3DQ5zwq9LYr5wOgEJYQJ6KIZmLQXLCmBz6pqG00xeuLIrsT";
        //Date date_deb =Date.valueOf("2021-10-12");
        //Date date_fin =Date.valueOf("2021-10-13");*/
        //ServiceRéservationIMP service=new ServiceRéservationIMP();
        //System.out.println(service.check_to_update("2022-01-27"));
        //service.add_reservation_hotel(reservation_hotel);*/
        ServiceRéservationIMP service=new ServiceRéservationIMP();
        System.out.println(service.get_reservation_details_by_id(10028,"ticket"));
        //Utilisateur user=new Utilisateur(2,"alaa","","alaa","","alaazarrouk7@gmail.com",56353474,100000,"","","","");
        /*ServiceStripeIMP stripe=new ServiceStripeIMP(); 
        Carte carte=new Carte("3566002020360505",6,2034,232); 
        System.out.println(stripe.payment(user, 1000, carte));*/
        //stripe.payment(user, 1000, card_id)

    }
    
}
