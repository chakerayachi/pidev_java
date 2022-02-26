/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class AdminDashboardFXMLController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableView_user;
    @FXML
    private ImageView image;
    @FXML
    private TableColumn<Utilisateur, Integer> nom;
    @FXML
    private TableColumn<Utilisateur, Integer> prenom;
    @FXML
    private TableColumn<Utilisateur, String> adresse;
    @FXML
    private TableColumn<Utilisateur, String> email;
    @FXML
    private TableColumn<Utilisateur, Integer> phone;
    @FXML
    private TableColumn<Utilisateur, Integer> cin;
    @FXML
    private JFXTextField nomTexte;
    @FXML
    private JFXTextField prenomTexte;
    @FXML
    private JFXTextField emailTexte;
    @FXML
    private JFXTextField numTexte;
    @FXML
    private JFXTextField cinTexte;
    @FXML
    private JFXButton btn_image;

    /**
     * Initializes the controller class.
     */
    ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
    Utilisateur userConn = Utilisateur.user_connecter;
    private List<String> listfiles;
    String mewImagePath = userConn.getImage();
    @FXML
    private TableColumn<Utilisateur, String> etatTexte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listfiles = new ArrayList<>();
        listfiles.add("*.png");
        listfiles.add("*.jpg");

        afficheTableView();
        String path = "Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Image/" + mewImagePath;
        image.setImage(new Image("file:/" + path, 193, 200, false, false));
        nomTexte.setText(userConn.getNom());
        prenomTexte.setText(userConn.getPrenom());
        emailTexte.setText(userConn.getEmail());
        numTexte.setText(String.valueOf(userConn.getNum_tel()));
        cinTexte.setText("" + userConn.getCin());

    }

    private void afficheTableView() {
        List users = su.afficherClientList();
        users.addAll(su.afficherAgencierList());
        ObservableList list = FXCollections.observableArrayList(users);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        etatTexte.setCellValueFactory(new PropertyValueFactory<>("etat"));

        tableView_user.setItems(list);

    }

    @FXML
    private void updateUser(ActionEvent event) {

        Utilisateur user = new Utilisateur();

        user.setLogin(userConn.getLogin());
        user.setPassword(userConn.getPassword());
        user.setAdresse(userConn.getAdresse());
        user.setRole(userConn.getRole());
        user.setDescription(userConn.getDescription());
        user.setEtat(userConn.getEtat());
        user.setAccount_date(userConn.getAccount_date());
        user.setNom(nomTexte.getText());
        user.setPrenom(prenomTexte.getText());
        user.setEmail(emailTexte.getText());
        user.setNum_tel(Integer.parseInt(numTexte.getText()));
        user.setCin(Integer.parseInt(cinTexte.getText()));
        user.setImage(mewImagePath);
        su.modifierUtilisateur(user, userConn.getId());
        String path = "Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Image/" + mewImagePath;
        image.setImage(new Image("file:/" + path, 193, 200, false, false));

    }

    @FXML
    private void FileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", listfiles));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            mewImagePath = f.getName();
        }
    }

    @FXML
    private void changerEtat(ActionEvent event) {
        Utilisateur user = tableView_user.getSelectionModel().getSelectedItem();
        if (user == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Warning");
            al.setContentText("Selctionner un ustilisateur");
            al.setHeaderText(null);
            al.show();
        } else if (user.getEtat().equals("active")) {
            su.DescativerUser(user.getId());

        } else if (user.getEtat().equals("desactive")) {
            su.ActiverUser(user.getId());

        }
        afficheTableView();

    }

}
