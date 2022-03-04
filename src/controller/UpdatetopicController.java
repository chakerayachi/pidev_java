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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceCommentaireIMP;
import services.ServiceTopicIMP;
import static utils.BadWords.chackwords;
import utils.Mail;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class UpdatetopicController implements Initializable {
                 ServiceTopicIMP st = new ServiceTopicIMP();
int attention=0;
    private int idtopic;
    @FXML
    private JFXTextField inputtitre;
    @FXML
    private TextArea inputdesc;
    @FXML
    private JFXButton addbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     void setTextField(Topic t) {

        idtopic=t.getIdtopic();
        inputtitre.setText(t.getTitretopic());
        inputdesc.setText(t.getDescription());
        

    }

    @FXML
    private void save(MouseEvent event) {
          ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

         Utilisateur user=scom.getuserbyid(GestiontopicController.iduser);
         String titre = inputtitre.getText();
        String description = inputdesc.getText();

        if (titre.isEmpty() || description.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else { try {
            if(chackwords(titre).equals("false")&&chackwords(description).equals("false"))
            {if(st.verifexisttopic(titre,description)==0)
            {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Topic t=new Topic();
                t.setTitretopic(titre);
                t.setDescription(description);
                t.setDate(dateFormat.format(date));
                t.setIdtopic(idtopic);
                t.setIduser(1);
                if(st.modifier(t))
                {Stage stage = (Stage) addbtn.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("topic modifie avec succes!");
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("topic existe deja!");
                alert.show();
            }}
            else
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
            }    } catch (IOException ex) {
                 Logger.getLogger(UpdatetopicController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }}

    @FXML
    private void clean() {
        inputtitre.setText(null);
        inputdesc.setText(null);
    }
}
