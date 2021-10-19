/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programa��o
Concluido em: 18/10/2021
Declaro que este c�digo foi elaborado por Diego Cerqueira e Joanderson Santos em dupla e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.ArgumentoInvalidoException;
import Exceptions.ObjetoInexistenteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import message.MessageAlert;
import model.Projeto;
import model.User;
import screenManager.ScreenManager;

/**
 * Classe controladora da p�gina de projetos
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */

public class ProjetosScreenController implements Initializable, EventHandler<ActionEvent> {
	
	@FXML
    private ListView<Projeto> lvProjetos;
	
    @FXML
    private Button novoProjetoBTN;
    
    @FXML
    private Button openTarefasBTN;
    
    @FXML
    private Button atualizarProjetosBTN;

    @FXML
    private Button excluirProjetoBTN;

    @FXML
    private Button editarProjetoBTN;
    
    @FXML
    private MenuButton menuBTN;

    @FXML
    private MenuItem menuItemNome;

    @FXML
    private MenuItem menuItemDescricao;
    
    private ObservableList<Projeto> obsProjetos;
    
    private ScreenManager screenManager = new ScreenManager();
    
    private static User user = new User();
    private static Projeto projetoSelecionado;
    
    private MessageAlert msgAlert = new MessageAlert();
    
    private FormularioProjetoScreenController formularioProjetoController;
    private FormularioProjetoScreenEditController formularioProjetoControllerEdit;
    
 
    /**
     * Metodo que carrega os projetos na tela
     */
    public void loadProjetos() {
    	
    	List<Projeto> projetosCadastrados = user.getProjetos();
    	
    	obsProjetos = FXCollections.observableArrayList(projetosCadastrados);
    
    	lvProjetos.setItems(obsProjetos);
    }

    /**
     * Metodo que abre o formul�rio  de cadastro de projetos
     * @param event
     * @throws IOException
     * @throws ArgumentoInvalidoException
     */
    @FXML
    void openFormularioProjetoScreen(ActionEvent event) throws IOException, ArgumentoInvalidoException {
    	
    	screenManager.openNewScreen("FormularioProjetoScreen", "Cadastro Projetos");
    	
    	setReferenciaFormularioProjetoController();
    	 	
    }
    	  
    
    /**
     * Metodo que pega a refer�ncia de um projeto a ser excluido
     */
    private void setReferenciaFormularioProjetoController() {
    	
    	Object currentController = screenManager.getCurrenController();
    	
    	formularioProjetoController = (FormularioProjetoScreenController) currentController;
    	
    	formularioProjetoController.addButtonsListener(this);
	}

	/**
	 * Metodo que abre o formul�rio de edi��o de projetos
	 * @param event
	 * @throws IOException
	 */
	@FXML
    void openFormularioProjetoScreenEdit(ActionEvent event) throws IOException {
    	
    	projetoSelecionado = lvProjetos.getSelectionModel().getSelectedItem();
    	
    	if(projetoSelecionado == null) {
    		
    		this.msgAlert.getMessageProjetoNaoSelecionada();
    		
    	}else {
        	
    		screenManager.openNewScreen("FormularioProjetoScreenEdit", "Edi��o Projetos");
    		
    		setReferenciaFormularioProjetoControllerEdit();
    		
    	}
    }

    /**
     * Metodo que pega a refer�ncia do projeto selecionado para editar
     */
    private void setReferenciaFormularioProjetoControllerEdit() {
    	
    	Object currentController = screenManager.getCurrenController();
    	
    	formularioProjetoControllerEdit = (FormularioProjetoScreenEditController) currentController;
    	
    	formularioProjetoControllerEdit.addButtonsListener(this);
		
	}

	/**
	 * Evento para excluir um projeto
	 * @param event
	 */
	@FXML
    void excluirProjeto(ActionEvent event) {
    	
    	Projeto projetoAlvo = lvProjetos.getSelectionModel().getSelectedItem();
    	
    	
    	if(projetoAlvo == null) {
    		
    		this.msgAlert.getMessageProjetoNaoSelecionada();
    		
    	} else {
    		
    		boolean isProjetoExcluido = user.excluirProjeto(projetoAlvo);
    		
    		if(isProjetoExcluido) {
    			
    			loadProjetos();
    			
    			this.msgAlert.getMessageProjetoExcluida();;
    			
    		} else {
    			
    			this.msgAlert.getMessageTarefasNaoConcluidas();
    		}
    	}
    }
    
    /**
     * Evento para abrir as tarefas relacionadas a um projeto selecionado
     * @param event
     * @throws IOException
     */
    @FXML
    void openTarefas(ActionEvent event) throws IOException {
    	
    	projetoSelecionado = lvProjetos.getSelectionModel().getSelectedItem();
    	
    	
    	boolean isProjetoSelecionado = projetoSelecionado != null;
    	
		if(isProjetoSelecionado) {

	    	screenManager.openNewScreen("TarefasScreen", "Tarefas");
			
		} else {
			
			this.msgAlert.getMessageProjetoNaoSelecionada();
		}
    }
    
    /**
     * Metodo que retorna qual projeto foi selecionado
     * @return Projeto
     */
    public static Projeto getProjetoSelecionado() {
    	
    	return projetoSelecionado;
    }
    
    /**
     * Metodo que insere um novo projeto
     * @param projeto
     * @throws ArgumentoInvalidoException
     */
    public static void setNewProjeto(Projeto projeto) throws ArgumentoInvalidoException {
    	
    	user.setProjeto(projeto);
    	
    }
    
    /**
     * Metodo da interface Initializable
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 
        loadProjetos();
    }
    
	/**
	 * Retorna o usu�rio da aplica��o
	 * @return User
	 */
	public static User getUser() {
		
		return user;
	}


	/**
	 * Metodo que verifica qual bot�o foi clicado
	 * @param arg0
	 */
	@Override
	public void handle(ActionEvent arg0) {
		
		if(arg0.getSource() == formularioProjetoController.getBntSalvar()) {
			
			try {
				
				formularioProjetoController.addNewProjeto();
				
				loadProjetos();
				
			} catch (ArgumentoInvalidoException e) {
				
				e.printStackTrace();
			}
			
		}else if(arg0.getSource() == formularioProjetoController.getBtnVoltar()) {
			
			formularioProjetoController.closeScreen();
			
		}
		
		else if(arg0.getSource() == formularioProjetoControllerEdit.getBtnSalvar()) {
			
			try {
				
				formularioProjetoControllerEdit.addProjetoEditado();
				
				loadProjetos();
				
			} catch (ArgumentoInvalidoException | ObjetoInexistenteException e) {
				
				e.printStackTrace();
			}
			
		}else if(arg0.getSource() == formularioProjetoControllerEdit.getBtnVoltar()) {
			
			formularioProjetoControllerEdit.closeScreen();
			
		}
	}
}
