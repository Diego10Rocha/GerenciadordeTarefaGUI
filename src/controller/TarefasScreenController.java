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

import Exceptions.ObjetoInexistenteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import message.MessageAlert;
import model.Projeto;
import model.Tarefa;
import screenManager.ScreenManager;

/**
 *
 * @author Diego Cerqueira e Joanderson Santos
 */

public class TarefasScreenController implements Initializable, EventHandler<ActionEvent> {
	
	@FXML
    private ListView<Tarefa> lvTarefasPendentes;

    @FXML
    private ListView<Tarefa> lvTarefasEmExecucao;
    
    @FXML
    private ListView<Tarefa> lvTarefasConcluidas;
    
    private static ObservableList<Tarefa> obsTarefasPendentes;
    private static ObservableList<Tarefa> obsTarefasConcluidas;
    private static ObservableList<Tarefa> obsTarefasEmExecucao;
    private static Tarefa tarefaSelecionada;

    @FXML
    private Button addNovaTarefaBTN;
    
    @FXML
    private Button projetosBTN;
    
    @FXML
    private Button editarTarefaBTN;

    @FXML
    private Button excluirTarefaBTN;

    @FXML
    private Button attTarefasBTN;

    private ScreenManager screenManager = new ScreenManager();


    private static Projeto projetoQueDetemTarefas;
    
    private FormularioTarefaScreenController formularioTarefaController;
    private FormularioTarefaScreenEditController formularioTarefaControllerEdit;
    
    private MessageAlert msgAlert = new MessageAlert();
    
    
    
    @FXML
    void openFormularioTarefaScreen(ActionEvent event) throws IOException {
    	
    	screenManager.openNewScreen("FormularioTarefaScreen", "Cadastro Tarefas");
    	
    	setReferenciaFormularioTarefaController();

    }


    private void setReferenciaFormularioTarefaController() {

    	Object currentController = screenManager.getCurrenController();
    	
    	formularioTarefaController = (FormularioTarefaScreenController) currentController;

    	formularioTarefaController.addButtonsListener(this);

	}

    @FXML
    void openFormularioTarefaScreenEdit(ActionEvent event) throws IOException {
    	
    	if(tarefaSelecionada != null) {

        	screenManager.openNewScreen("FormularioTarefaScreenEdit", "Edi��o Tarefas");

        	setReferenciaFormularioTarefaControllerEdit();
    	}

    	else {

			this.msgAlert.getMessageTarefaNaoSelecionada();
		}

    }

    private void setReferenciaFormularioTarefaControllerEdit() {

    	Object currentController = screenManager.getCurrenController();

    	formularioTarefaControllerEdit = (FormularioTarefaScreenEditController) currentController;

    	formularioTarefaControllerEdit.addButtonsListener(this);

	}



    @FXML
    void backToScreenProjetos(ActionEvent event) {
    	
    	Stage stage = (Stage) projetosBTN.getScene().getWindow();
    	
    	stage.close();
    }
    


	@FXML
    void excluirTarefa(ActionEvent event) {
    	
    	if(tarefaSelecionada != null) {
    		
    		projetoQueDetemTarefas.getTarefas().remove(tarefaSelecionada);
    		
    		loadTarefas();
    		
    		this.msgAlert.getMessageTarefaExcluida();
    	}
    	
    	else {
			
			this.msgAlert.getMessageTarefaNaoSelecionada();
		}

    }
    
    
    public void loadTarefas() {
    	
    	
    	List<Tarefa> tarefasPendentes  = projetoQueDetemTarefas.getTarefasPendentes();
    	List<Tarefa> tarefasEmExecucao  = projetoQueDetemTarefas.getTarefasEmExecucao();
    	List<Tarefa> tarefasConcluidas  = projetoQueDetemTarefas.getTarefasConcluidas();
    	
    	
    	obsTarefasPendentes = FXCollections.observableArrayList(tarefasPendentes);
    	obsTarefasEmExecucao = FXCollections.observableArrayList(tarefasEmExecucao);
    	obsTarefasConcluidas = FXCollections.observableArrayList(tarefasConcluidas);
    	
    	lvTarefasPendentes.setItems(obsTarefasPendentes);
    	lvTarefasEmExecucao.setItems(obsTarefasEmExecucao);
    	lvTarefasConcluidas.setItems(obsTarefasConcluidas);
    }
    
    public static void setTarefaSalva(Tarefa newTarefa) {
    	
    	projetoQueDetemTarefas.setTarefa(newTarefa);
    }
    
    
    public static Tarefa getTarefaSelecionada() {
    	
    	return tarefaSelecionada;
   
    }
    
    
    @FXML
    void listInViewConcluidas() {
    		
    	tarefaSelecionada = lvTarefasConcluidas.getSelectionModel().getSelectedItem();
    }
    

    @FXML
    void listInViewEmExecucao() {
    	
    	tarefaSelecionada = lvTarefasEmExecucao.getSelectionModel().getSelectedItem();
    }

    @FXML
     void listInViewPendentes() {
    	 
    	tarefaSelecionada = lvTarefasPendentes.getSelectionModel().getSelectedItem();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	projetoQueDetemTarefas = ProjetosScreenController.getProjetoSelecionado();

    	formularioTarefaControllerEdit = new FormularioTarefaScreenEditController();

    	formularioTarefaController = new FormularioTarefaScreenController();
    	
        loadTarefas();
    }

	@Override
	public void handle(ActionEvent arg0) {

		if (arg0.getSource() == formularioTarefaController.getBtnAddNovaTarefa()) {

			formularioTarefaController.salvarNovaTarefa();

			loadTarefas();

		} else if (arg0.getSource() == formularioTarefaController.getBtnVoltar()) {

			formularioTarefaController.closeScreen();

		} else if (arg0.getSource() == formularioTarefaControllerEdit.getBtnAddNovaTarefaEdit()) {

			try {

				formularioTarefaControllerEdit.salvarEditTarefa();

				loadTarefas();

			} catch (ObjetoInexistenteException e) {

				e.printStackTrace();
			}

		} else if (arg0.getSource() == formularioTarefaControllerEdit.getBtnVoltar()) {


			formularioTarefaControllerEdit.closeScreen();

		}
	}
}
