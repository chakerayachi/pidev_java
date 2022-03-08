 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import entities.Reservation;
import entities.Ticket;
import entities.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author sabri belhaj amor
 */
public class ServiceTicketIMP {
     Connection cnxx;

    public ServiceTicketIMP() {
        this.cnxx =MyDB.getInstance().getConnection();
    }
    
    
//---Create tickets--
public void add_ticket(Ticket t){
        String Request = "INSERT INTO ticket (prix,id_evenement,type) VALUES(?,?,?)";
        PreparedStatement pst;
        try {
                pst = cnxx.prepareStatement(Request);
                pst.setInt(1, t.getPrix());
                pst.setInt(2,5);
                pst.setString(3, t.getType());
                pst.executeUpdate();
                
                      System.out.println("Ajout de ticket effectué avec succés");
        } catch (SQLException ex) {
                   System.err.println(ex.getMessage());    
               }
    }

//--Modify tickets--
    public void modify(Ticket t) {
        try {
            //Statement stm = cnxx.createStatement();
            String req = "update ticket set prix = ? , type = ? where id= ?";
            PreparedStatement ps=cnxx.prepareStatement(req);
            ps.setInt(1,t.getPrix());
           // ps.setString(2,t.getType());
            ps.setInt(3,t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {            
            System.out.println(ex.getMessage());
        }
    }
    //--delete tickets--
    public void delete(int id) {
        try {
            String req = "DELETE FROM ticket WHERE id = ?" ;
          PreparedStatement ps = cnxx.prepareStatement(req);
          System.out.println(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
      }
    }
    
    //--display tickets--
    public List<Ticket> afficher() {
     ArrayList<Ticket>  list = new ArrayList();
       try {
                  String req ="Select * FROM ticket";
             Statement st = cnxx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){
               
                       Ticket t = new Ticket();
                         t.setId(rs.getInt(1));
                         t.setPrix(rs.getInt(2));
                         t.setId_evenement(rs.getInt(3)); 
                         t.setType(rs.getString(4));
                         
                         list.add(t);

             }             
   
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                            System.out.println("Error on selecting ticket");

        }   
     return list;    
    }
    
    public List<Ticket> get_tickets_by_event_id(int id_evenement){ 
        List<Ticket> ticket_list = new ArrayList(); 
         String Request = "SELECT * FROM ticket tk where tk.id_evenement="+id_evenement;
         PreparedStatement  pst;
         try {
            pst = cnxx.prepareStatement(Request);
            ResultSet rs = pst.executeQuery(Request);
            while (rs.next()) {
              Ticket v = new Ticket();
                       Ticket t = new Ticket();
                         t.setId(rs.getInt(1));
                         t.setPrix(rs.getInt(2));
                         t.setId_evenement(rs.getInt(3)); 
                         t.setType(rs.getString(4));
                ticket_list.add(t);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return ticket_list;
    }
   
    
}
