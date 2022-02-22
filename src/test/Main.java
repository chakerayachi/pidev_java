/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Reservation;
import java.sql.Date;
import services.ServiceRéservationIMP;

/**
 *
 * @author chaker
 */
public class Main {
    public static void main(String[] args) { 
        /*Date date_deb =Date.valueOf("2021-10-12");
        Date date_fin =Date.valueOf("2021-10-13");
        Reservation_hotel reservation_hotel=new Reservation_hotel(500f,1,date_deb,date_fin,1); 
        ServiceRéservationIMP service=new ServiceRéservationIMP(); 
        service.add_reservation_hotel(reservation_hotel);*/
        ServiceRéservationIMP service=new ServiceRéservationIMP();
        System.out.println(service.get_all_reservations());
         
    }
    
}
