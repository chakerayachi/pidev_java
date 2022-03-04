/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import entities.Commentaire;
import entities.Sujet;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class CommentsController implements Initializable {
public static int idsujet;
     ServiceCommentaireIMP scom=new ServiceCommentaireIMP();
          ServiceSujetIMP ssuj=new ServiceSujetIMP();
    @FXML
    private StackPane AP;
    @FXML
    private JFXTextField searchComBox;
    @FXML
    private FlowPane FP;
    @FXML
    private Label inputtitre;
    @FXML
    private Label inputdescript;
    @FXML
    private Label inputdate;
    @FXML
    private  Label labelnbcom;
public static Label labelcom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Sujet sujet=ssuj.getsujetbyid(idsujet);
        inputtitre.setText(sujet.getTitresujet());
        inputdescript.setText(sujet.getContenu());
        inputdate.setText(sujet.getDate());
        labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));
        initializecomments();
        labelcom=labelnbcom;
    }    

  

    

    @FXML
    private void searchEvent(KeyEvent event) {
        if ((searchComBox.getText().trim().equals(""))) {
            initializecomments();
        } else {

            FP.getChildren().clear();
            System.out.println(searchComBox.getText().trim());
                    List<Commentaire> list = new ArrayList<Commentaire>();   
                    list=scom.affichercommentairebysujetlikee(idsujet,searchComBox.getText().trim());
        int count = scom.affichercommentairebysujetlikee(idsujet,searchComBox.getText().trim()).size();
        System.out.println(count);
        System.out.println(idsujet);
        for (int i = 0; i < count; i++) {
            try {
                         Utilisateur user=scom.getuserbyid(list.get(i).getIduser());
                Parent commentFXML = FXMLLoader.load(getClass().getResource("/GUI/comment.fxml"));
                CommentController.textStatic.setText(list.get(i).getContenu());
               CommentController.idStaitc.setText(list.get(i).getIdcom()+ "");
               CommentController.labelnbdisslike.setText(Integer.toString(list.get(i).getNbdislike()));
               CommentController.labelnblike.setText(Integer.toString(list.get(i).getNblike()));
               CommentController.labelusernamee.setText(user.getLogin());
               CommentController.labeldatee.setText(scom.affichercommentairebysujet(idsujet).get(i).getDate());
 
               System.out.println(list.get(i).getNblike());
       labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));

                FP.getChildren().add(commentFXML);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
            
        }
    }
    public  void initializecomments()
    {
        ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

        FP.getChildren().clear();
        
        int count = scom.affichercommentairebysujet(idsujet).size();
        System.out.println(count);
        System.out.println(idsujet);
        for (int i = 0; i < count; i++) {
            try {
                Utilisateur user=scom.getuserbyid(scom.affichercommentairebysujet(idsujet).get(i).getIduser());
                Parent commentFXML = FXMLLoader.load(getClass().getResource("/GUI/comment.fxml"));
                CommentController.textStatic.setText(scom.affichercommentairebysujet(idsujet).get(i).getContenu());
               CommentController.idStaitc.setText(scom.affichercommentairebysujet(idsujet).get(i).getIdcom()+ "");
               CommentController.labelnbdisslike.setText(Integer.toString(scom.affichercommentairebysujet(idsujet).get(i).getNbdislike()));
               CommentController.labelnblike.setText(Integer.toString(scom.affichercommentairebysujet(idsujet).get(i).getNblike()));
               CommentController.labelusernamee.setText(user.getLogin());
               CommentController.labeldatee.setText(scom.affichercommentairebysujet(idsujet).get(i).getDate());
 
               System.out.println(scom.affichercommentairebysujet(idsujet).get(i).getNblike());
       labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));

                FP.getChildren().add(commentFXML);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
@FXML
    public  void  refresh()
    {
        initializecomments();
       labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));
       System.gc();
    }

    @FXML
    private void back(MouseEvent event) {
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/gestionsujet.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                 new animatefx.animation.ZoomIn(page1).play();

            } catch (IOException ex) {
                Logger.getLogger(GestionsujetController.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }

    @FXML
    private void AddcommentAction(MouseEvent event) {
         try {
        AjoutercommentaireController.idsujet=idsujet;
        Parent parent = FXMLLoader.load(getClass().getResource("/GUI/ajoutercommentaire.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.showAndWait();

        initializecomments();
        labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));
         System.gc();
    } catch (IOException ex) {
        Logger.getLogger(CommentsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    @FXML
    private void refreshpage(MouseEvent event) {
        initializecomments();
       labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));
       System.gc();
    }

    @FXML
    private void AddNoteAction(ActionEvent event) {
    }

    
   
  
}
