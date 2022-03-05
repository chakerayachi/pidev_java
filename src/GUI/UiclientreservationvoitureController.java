/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.DetailsVoitureController.voiture;
import entities.Voiture;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.ServiceAvisIMP;
import services.ServiceImageIMP;
import services.ServicesVoitureIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class UiclientreservationvoitureController implements Initializable {

    @FXML
    private FlowPane FP;
//public static Voiture v;
    @FXML
    private ChoiceBox<String> choicategorie;
    private String[] reccategorie ={"familial","luxe","sport","4x4"};
    @FXML
    private ChoiceBox<String> choicapacite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        ServiceImageIMP sm = new ServiceImageIMP();
        ServicesVoitureIMP sv = new ServicesVoitureIMP();
        ServiceAvisIMP sa = new ServiceAvisIMP();
        FP.getChildren().clear();

        int count = sm.afficher().size();
        System.out.println(count);
        // System.out.println(idsujet);
        for (int i = 0; i < count; i++) {

            try {
                Parent voitureFXML = FXMLLoader.load(getClass().getResource("/GUI/reservationvoiture.fxml"));

                int id = sv.afficher().get(i).getId();
                System.out.println(id);
                Voiture v = sv.affichervoiturebyud(id);
                String bath = sm.afficher().get(i).getImg_blob();
                ReservationvoitureController.image.setImage(new Image("file:" + bath));
                ReservationvoitureController.marque.setText(v.getMarque());
                ReservationvoitureController.model.setText(v.getmodel());
                ReservationvoitureController.couleur.setText(v.getCouleur());
                ReservationvoitureController.review.setRating(sa.get_moy_avis(id));
                ReservationvoitureController.review.setUpdateOnHover(false);
                ReservationvoitureController.capacite.setText(String.valueOf(v.getCapacite()));
                ReservationvoitureController.deta.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        try {
                            DetailsVoitureController dc = new DetailsVoitureController();
                            dc.voiture = v;

                            Parent root = FXMLLoader.load(getClass().getResource("../GUI/DetailsVoiture.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(UiclientreservationvoitureController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                ReservationvoitureController.reservation.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        try {
                            DetailsVoitureController dk = new DetailsVoitureController();
                            dk.voiture = v;
                            int id_voiture = voiture.getId();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("avis.fxml"));
                            Parent root = loader.load();
                            AvisController controller = loader.getController();
                            controller.setid_voiture(id_voiture);

                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(UiclientreservationvoitureController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });

                System.out.println(v.getMarque());

                FP.getChildren().add(voitureFXML);
            } catch (IOException ex) {
                Logger.getLogger(UiclientreservationvoitureController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

   /* public void setData() {
        int id = voiture.getId();
    }*/

}
