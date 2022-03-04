/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Topic;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.ServiceCommentaireIMP;
import services.ServiceTopicIMP;
import static utils.BadWords.chackwords;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class AjoutertopicController  implements Initializable {
        ServiceCommentaireIMP scom=new ServiceCommentaireIMP();
                ServiceTopicIMP st = new ServiceTopicIMP();

    @FXML
    private JFXTextField inputtitre;
    @FXML
    private TextArea inputdesc;
    @FXML
    private JFXButton addbtn;
    
    int attention=0;
    //private GestiontopicController gestiontopic;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) {
        
          
        Utilisateur user=scom.getuserbyid(GestiontopicController.iduser);
        System.out.println(user.getId());

        try {
            String titre = inputtitre.getText();
            String description = inputdesc.getText();
            
            if (titre.isEmpty() || description.isEmpty() ) {
                
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All DATA");
                alert.showAndWait();
                
            } 
            
            else {
                if(chackwords(titre).equals("false")&&chackwords(description).equals("false"))
                {System.out.println(titre);
                    if(st.verifexisttopic(titre,description)==0){
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Topic t=new Topic();
                t.setTitretopic(titre);
                t.setDescription(description);
                t.setDate(dateFormat.format(date));
                t.setIduser(user.getId());
                if(st.ajout(t))
                {  Stage stage = (Stage) addbtn.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("topic ajouté avec succes!");
                alert.show();
                clean();
               /* Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                           .title("Alert").text("topic ajouté avec succes!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event)
                                {
                                    System.out.println("clicked on");
                                }
                            });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();*/
                //gestiontopic.refreshTable(event);
                }else
                {
                    System.err.println("System tray not supported!");
                }
                    }else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("topic existe deja!");
                alert.show();
                }
                }else
                {
                    attention++;
                 clean();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Worning !! ");
                alert.setContentText("vous ne pouvez pas ajouter ce topic avec ces mots ! ");
                alert.show();
                
                if(attention>2)
                {
                    System.out.println(attention);
                 Mail.envoyer(user);
                }
               
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(AjoutertopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void clean() {
        inputtitre.setText(null);
        inputdesc.setText(null);
    }
    
}
