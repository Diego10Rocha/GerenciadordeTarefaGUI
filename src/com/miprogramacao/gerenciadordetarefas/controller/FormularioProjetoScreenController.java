/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.ArgumentoInvalidoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;


/**
 *
 * @author User
 */

public class FormularioProjetoScreenController implements Initializable {
	
    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtDescricao;
    
    private MessageAlert msgAlert = new MessageAlert();
    
    private Projeto temp = new Projeto("temp");

    @FXML
    void addAlteracao(ActionEvent event) throws ArgumentoInvalidoException {
    	
    	String titulo = txtTitulo.getText();
    	String descricao = txtDescricao.getText();
    	
    	if(titulo == "" || descricao == "") {
    		
    		this.msgAlert.getMessageCampoEmBranco();
    		
    	}
    	
    	else {
    		
    		
        	temp.setTitulo(titulo);
        	temp.setDescricao(descricao);
        	
        	ProjetosScreenController.setProjetoSalvo(temp);
        	
        	this.msgAlert.getMessageProjetoSalvo();
        	
        	
    	}
    	
    	
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
 

    
    
    
    
    
    
    
    
    
}
