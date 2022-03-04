/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;

import static controller.CommentsController.idsujet;
import static controller.GestioncommentaireController.idsujet;
import entities.Commentaire;
import entities.Sujet;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class CommentController implements Initializable {
    ServiceCommentaireIMP scom=new ServiceCommentaireIMP();
     
    @FXML
    private AnchorPane AP;
        public static AnchorPane APStatic;

    @FXML
    private Label id;
        public static Label idStaitc;

    @FXML
    private JFXTextArea text;
    public static JFXTextArea textStatic;
public static boolean refresh=false;
    @FXML
    private Label labemnblike;
    public static Label labelnblike;
    @FXML
    private Label labelnbdislike;
     public static Label labelnbdisslike;
    @FXML
    private Label labelusername;
    public static Label labelusernamee;
    @FXML
    private Label labeldate;
        public static Label labeldatee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        textStatic = text;
        idStaitc = id;
        APStatic = AP;
        labelnbdisslike=labelnbdislike;
        labelnblike=labemnblike;
        labeldatee=labeldate;
        labelusernamee=labelusername;
        // TODO
    }    

    @FXML
    private void editAction(KeyEvent event) {
//        Date date = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Commentaire com=new Commentaire();
//        com=scom.getcommentbyid(Integer.parseInt(id.getText()));
//        
//        com.setIdcom(Integer.parseInt(id.getText()));
//        com.setContenu(text.getText());
//        com.setDate(dateFormat.format(date));
//        com.setIdsujet(GestioncommentaireController.idsujet);
//        //com.setIduser(1);
//        if(com.getIduser()!=GestiontopicController.iduser){
//        text.setEditable(false);
//        text.cancelEdit();
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Modifier");
//                alert.setHeaderText("vous pouver modifier seulement tes commentaires");
//                alert.setContentText(" Done!");
//                alert.show();
//        }
//        else
//        {if(scom.modifier(com))
//        {
//          Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Done");
//                alert.setContentText("commentaire modifié avec succes!");
//                alert.show();
//                 try {
//            Parent root = FXMLLoader.load(getClass().getResource("/GUI/comments.fxml"));
//            id.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//             }else
//             {
//                 System.err.println("System tray not supported!");
//             }
//            
//            
//        
//        }
        
    }

    @FXML
    private void delete(ActionEvent event) {
         Commentaire commentaire=new Commentaire();
        commentaire=scom.getcommentbyid(Integer.parseInt(id.getText()));
        if(commentaire.getIduser()==GestiontopicController.iduser){
                                 Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer ce commentaire  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceSujetIMP st = new ServiceSujetIMP();
            scom.supprimer(commentaire.getIdcom());
            int nbsujet=st.getnbcom(CommentsController.idsujet);
                nbsujet--;
                st.setnbcom(CommentsController.idsujet, nbsujet);
               //labelnbcom.setText(Integer.toString(st.getnbcom(idsujet)));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
       
        } else {
            alert2.close();
        }
            
            
            
        
        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modifier");
                alert.setHeaderText("vous pouver supprimer seulement tes commentaires");
                alert.setContentText(" Done!");
                alert.show();
        } try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/comments.fxml"));
            id.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void edit(MouseEvent event) {
       
        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/updatecommentaire.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestioncommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Commentaire com=new Commentaire();
        com=scom.getcommentbyid(Integer.parseInt(id.getText()));
        
        if(com.getIduser()==GestiontopicController.iduser){ 
                            UpdatecommentaireController updatecommentaireController = loader.getController();
                            updatecommentaireController.setTextField(com);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();    
                                   new animatefx.animation.ZoomIn(parent).play();

                           
  }
    else 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modifier");
                alert.setHeaderText("vous pouver modifier seulement tes commentaires");
                alert.setContentText(" Done!");
                alert.show();
    }
    
    }
    
 public static void setrefresh(boolean t)
 {
   refresh=t;
   
 }

    @FXML
    private void like(MouseEvent event) {
        Commentaire commentaire=new Commentaire();
        commentaire=scom.getcommentbyid(Integer.parseInt(id.getText()));
         labemnblike.setText(Integer.toString(scom.getnblike(commentaire.getIdcom())));
                                   labelnbdislike.setText(Integer.toString(scom.getnbdislike(commentaire.getIdcom())));  
        int check=scom.veriflikeuser(GestiontopicController.iduser,commentaire.getIdcom());
                               if(check==0){
                                   if(scom.verifdislikeuser(GestiontopicController.iduser,commentaire.getIdcom())==0)
                                    {
                                   scom.ajoutlike(GestiontopicController.iduser,commentaire.getIdcom() );
                               int nblike=scom.getnblike(commentaire.getIdcom());
                               nblike++;
                               scom.setlike(commentaire.getIdcom(), nblike);
                                   }
                                   else
                                   {
                                   scom.ajoutlike(GestiontopicController.iduser,commentaire.getIdcom() );
                               int nblike=scom.getnblike(commentaire.getIdcom());
                               nblike++;
                               scom.setlike(commentaire.getIdcom(), nblike);
                               int nbdislike=scom.getnbdislike(commentaire.getIdcom());
                               nbdislike--;
                               scom.setdislike(commentaire.getIdcom(), nbdislike);
                               //**********
                               scom.supprimerdislike(scom.verifdislikeuser(GestiontopicController.iduser,commentaire.getIdcom()));
                                    System.out.println(commentaire.getIdcom());
                                  
                                   }
                                  
                               }
                               else
                               {
                               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Like");
                alert.setHeaderText("vous avez deja liké ce commentaire");
                alert.setContentText(" Done!");
                alert.show();
                               }
                               
                              labemnblike.setText(Integer.toString(scom.getnblike(commentaire.getIdcom())));
                                   labelnbdislike.setText(Integer.toString(scom.getnbdislike(commentaire.getIdcom())));  
                }
    

    @FXML
    private void dislike(MouseEvent event) {
        Commentaire commentaire=new Commentaire();
        commentaire=scom.getcommentbyid(Integer.parseInt(id.getText()));
         labemnblike.setText(Integer.toString(scom.getnblike(commentaire.getIdcom())));
                                   labelnbdislike.setText(Integer.toString(scom.getnbdislike(commentaire.getIdcom())));  
if(scom.verifdislikeuser(GestiontopicController.iduser,commentaire.getIdcom())==0)
                               {if(scom.veriflikeuser(GestiontopicController.iduser,commentaire.getIdcom())==0){
                                scom.ajoutdislike(GestiontopicController.iduser,commentaire.getIdcom());
                               int nbdislike=scom.getnbdislike(commentaire.getIdcom());
                               nbdislike++;
                               scom.setdislike(commentaire.getIdcom(), nbdislike);
                               
                               }
                               else{
                               scom.ajoutdislike(GestiontopicController.iduser,commentaire.getIdcom());
                               int nbdislike=scom.getnbdislike(commentaire.getIdcom());
                               nbdislike++;
                               scom.setdislike(commentaire.getIdcom(), nbdislike);
                               int nblike=scom.getnblike(commentaire.getIdcom());
                               nblike--;
                               scom.setlike(commentaire.getIdcom(), nblike);
                               //***********
                               scom.supprimerlike(scom.veriflikeuser(GestiontopicController.iduser,commentaire.getIdcom()));
                               
                               }
                               }
                               else
                               {
                               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Like");
                alert.setHeaderText("vous avez deja disliké ce commentaire");
                alert.setContentText(" Done!");
                alert.show();
                               }labemnblike.setText(Integer.toString(scom.getnblike(commentaire.getIdcom())));
                                   labelnbdislike.setText(Integer.toString(scom.getnbdislike(commentaire.getIdcom())));
    }
    
   
}
