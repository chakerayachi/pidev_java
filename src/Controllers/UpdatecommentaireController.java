/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static Controllers.AjoutercommentaireController.idsujet;
import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceCommentaireIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class UpdatecommentaireController implements Initializable {
    ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTextField inputcont;
private int idcom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CommentController.setrefresh(true);
    }    
    void setTextField(Commentaire c) {
        
    idcom=c.getIdcom();
    inputcont.setText(c.getContenu());
    
    }

    @FXML
    private void save(MouseEvent event) {
            String cont = inputcont.getText();

        if (cont.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
               Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Commentaire c=new Commentaire();
            c.setContenu(cont);
            c.setDate(dateFormat.format(date));
            c.setIdcom(idcom);
            c.setIduser(1);
             if(scom.modifier(c))
             {    
                   
                       //             /*int nbcom=s.getnbsujet(idtopic);
//                nbsujet++;
//                st.setnbsujet(idtopic, nbsujet);*/
Stage stage = (Stage) addbtn.getScene().getWindow();
stage.close();

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Done");
alert.setContentText("commentaire modifié avec succes!");
alert.show();
clean();
/*Notifications notificationbuilder;
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
//   CommentController.setrefresh(true);
             }}         
    }

    @FXML
    private void clean() {
        inputcont.setText(null);
    }
}
