/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static Controllers.AjoutersujetController.idtopic;
import entities.Commentaire;
import entities.Sujet;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;
import static utils.BadWords.chackwords;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class AjoutercommentaireController implements Initializable {
    ServiceCommentaireIMP scom=new ServiceCommentaireIMP();
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTextField inputcont;
public static int idsujet;

int attention=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(MouseEvent event) {
          ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

         Utilisateur user=scom.getuserbyid(GestiontopicController.iduser);
         String cont = inputcont.getText();

        if (cont.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
              try {
                  if(chackwords(cont).equals("false")){
                      
                      Date date = new Date();
                      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                      Commentaire c=new Commentaire();
                      c.setContenu(cont);
                      c.setDate(dateFormat.format(date));
                      c.setIdsujet(idsujet);
                      c.setIduser(user.getId());
                      if(scom.ajout(c))
                      {    ServiceSujetIMP s = new ServiceSujetIMP();
                      int nbcom=s.getnbcom(idsujet);
                      nbcom++;
                      s.setnbcom(idsujet, nbcom);
                      Stage stage = (Stage) addbtn.getScene().getWindow();
                      stage.close();
                      
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Done");
                      alert.setContentText("commentaire ajouté avec succes!");
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
                      
                      }else
                      {
                          System.err.println("System tray not supported!");
                      }
                      
                  }
                  else
                  {
                      attention++;
                      clean();
                      Alert alert = new Alert(Alert.AlertType.WARNING);
                      alert.setTitle("Worning !! ");
                      alert.setContentText("vous ne pouvez pas ajouter ce commentaire avec ces mots ! ");
                      alert.show();
                      
                      if(attention>2)
                      {
                          System.out.println(attention);
                          Mail.envoyer(user);
                      }
                      
                  }     } catch (IOException ex) {
                  Logger.getLogger(AjoutercommentaireController.class.getName()).log(Level.SEVERE, null, ex);
              }
    
        }
    }

    @FXML
    private void clean() {
        inputcont.setText(null);
    }
    
}
