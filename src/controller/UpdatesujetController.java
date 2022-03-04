/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Sujet;
import entities.Topic;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceSujetIMP;
import services.ServiceTopicIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class UpdatesujetController implements Initializable {
    private int idsujet;
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

    @FXML
    private void save(MouseEvent event) {
        
        String titre = inputtitre.getText();
        String description = inputdesc.getText();

        if (titre.isEmpty() || description.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
               Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Sujet s=new Sujet();
            s.setTitresujet(titre);
            s.setContenu(description);
            s.setDate(dateFormat.format(date));
            s.setIdsujet(idsujet);
            s.setIduser(1);
             ServiceSujetIMP sp = new ServiceSujetIMP();
             if(sp.modifier(s))
             {Stage stage = (Stage) addbtn.getScene().getWindow();
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("sujet modifie avec succes!");
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
    }

    @FXML
    private void clean() {
        inputtitre.setText(null);
        inputdesc.setText(null);
    }
     void setTextField(Sujet s) {

        idsujet=s.getIdsujet();
        inputtitre.setText(s.getTitresujet());
        inputdesc.setText(s.getContenu());
        

    }
}
