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
 * Classe controladora da tela de Tarefas
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
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
    
    
    
    /**
     * Evento que abre formul�rio de cria��o de tarefas
     * @param event
     * @throws IOException
     */
    @FXML
    void openFormularioTarefaScreen(ActionEvent event) throws IOException {
    	
    	screenManager.openNewScreen("FormularioTarefaScreen", "Cadastro Tarefas");
    	
    	setReferenciaFormularioTarefaController();

    }


    /**
     *  Metodo que pega a refer�ncia da tarefa selecionada para exclus�o
     */
    private void setReferenciaFormularioTarefaController() {

    	Object currentController = screenManager.getCurrenController();
    	
    	formularioTarefaController = (FormularioTarefaScreenController) currentController;

    	formularioTarefaController.addButtonsListener(this);

	}

    /**
     * Evento que abre formul�rio de edi��o de tarefas
     * @param event
     * @throws IOException
     */
    @FXML
    void openFormularioTarefaScreenEdit(ActionEvent event) throws IOException {
    	
    	if(tarefaSelecionada != null) {

        	screenManager.openNewScreen("FormularioTarefaScreenEdit", "Edi��o Tarefas");

        	setReferenciaFormularioTarefaControllerEdit();
    	}else {

			this.msgAlert.getMessageTarefaNaoSelecionada();
		}

    }

    /**
     * Metodo que pega a refer�ncia da tarefa selecionada para ser editada
     */
    private void setReferenciaFormularioTarefaControllerEdit() {

    	Object currentController = screenManager.getCurrenController();

    	formularioTarefaControllerEdit = (FormularioTarefaScreenEditController) currentController;

    	formularioTarefaControllerEdit.addButtonsListener(this);

	}

    /**
     * Evento para voltar a tela de projetos
     * @param event
     */
    @FXML
    void backToScreenProjetos(ActionEvent event) {
    	
    	Stage stage = (Stage) projetosBTN.getScene().getWindow();
    	
    	stage.close();
    }
    
	/**
	 * Evento para excluir uma tarefa
	 * @param event
	 */
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
    
    /**
     * Metodo que dado um projeto carrega suas tarefas na tela
     */
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
    
    /**
     * metodo que insere uma nova tarefa
     * @param newTarefa
     */
    public static void setTarefaSalva(Tarefa newTarefa) {
    	
    	projetoQueDetemTarefas.setTarefa(newTarefa);
    }
    
    /**
     * Retorna a tarefa selecionada pelo usu�rio
     * @return Tarefa
     */
    public static Tarefa getTarefaSelecionada() {
    	
    	return tarefaSelecionada;
    }
    
    /**
     * Metodo que verifica se uma tarefa da lista de concluidas foi selecionada
     */
    @FXML
    void listInViewConcluidas() {
    		
    	tarefaSelecionada = lvTarefasConcluidas.getSelectionModel().getSelectedItem();
    }
    

    /**
     * Metodo que verifica se uma tarefa da lista de em execu��o foi selecionada
     */
    @FXML
    void listInViewEmExecucao() {
    	
    	tarefaSelecionada = lvTarefasEmExecucao.getSelectionModel().getSelectedItem();
    }

    /**
     * Metodo que verifica se uma tarefa da lista de pendentes foi selecionada
     */
    @FXML
     void listInViewPendentes() {
    	 
    	tarefaSelecionada = lvTarefasPendentes.getSelectionModel().getSelectedItem();

    }


    /**
     * Metodo da interface Initializable
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	projetoQueDetemTarefas = ProjetosScreenController.getProjetoSelecionado();

    	formularioTarefaControllerEdit = new FormularioTarefaScreenEditController();

    	formularioTarefaController = new FormularioTarefaScreenController();
    	
        loadTarefas();
    }

	/**
	 * Metodo que verifica qual bot�o foi clicado
	 * @param arg0
	 */
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
