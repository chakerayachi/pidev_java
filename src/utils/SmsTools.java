/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entities.Utilisateur;

/**
 *
 * @author chaker
 */
public class SmsTools {

    public static void sendSms( Utilisateur user , String code) {
        String ACCOUNT_SID = "ACf450dc61a39a8fefd258a20befa9b3c2";
        String AUTH_TOKEN = "c5b26515681bc9fedf0814e619613765";
        
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                // to phone number
                new com.twilio.type.PhoneNumber("+216"+user.getNum_tel()),
                new com.twilio.type.PhoneNumber("+19108125880"),
                " Votre code de reinitialisation du not de passe est : "+code)
            .create();

        System.out.println(message.getSid());
        

    }

}
