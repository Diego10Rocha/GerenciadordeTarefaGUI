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

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import message.MessageAlert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import model.Tarefa;


/**
 * Classe controladora do formul�rio de cria��o de tarefa
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */

public class FormularioTarefaScreenController implements Initializable {
	
	@FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtDescricao;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker txtValidade;
 

    @FXML
    private Button btnAddNovaTarefa;
    
    private MessageAlert msgAlert = new MessageAlert();
    
    /**
     * Metodo para salvar uma nova tarefa
     */
    public void salvarNovaTarefa() {
    	
    	boolean isCampoAnyEmpty  = verificarCampoAnyEmpty();
    	
    	if(isCampoAnyEmpty) {
    		
    		this.msgAlert.getMessageCampoEmBranco();
    		
    	} else {
    		
    		Tarefa newTarefa;
    		
    		String titleTarefa = txtTitulo.getText();
    		String descriptionTarefa = txtDescricao.getText();
    		String dateTarefa = txtValidade.getEditor().getText();
    		
    		newTarefa = new Tarefa(titleTarefa, descriptionTarefa, dateTarefa);
    		
    		TarefasScreenController.setTarefaSalva(newTarefa);
    		
    		cleanInfoTarefa();
    		
    		this.msgAlert.getMessageTarefaSalva();
    		
    	}
    }
  
    /**
     * Metodo para limpar os campos do formul�rio
     */
    private void cleanInfoTarefa() {
		
    	txtTitulo.setText("");
    	txtDescricao.setText("");
    	txtValidade.getEditor().setText("");
		
	}
    
    /**
     * Metodo que fecha a tela do formul�rio e volta para a listagem de tarefas
     */
    void closeScreen() {
    	
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
    	
    	stage.close();

    }

	/**
	 * Verifica se os campos est�o vazios
	 * @return boolean
	 */
	private boolean verificarCampoAnyEmpty() {
    	
    	boolean isCampoAnyEmpty = false;
    	
    	if( txtTitulo.getText() == "" ||
    	    txtDescricao.getText() == "" ||
    	    txtValidade.getEditor().getText() == "") {
    		
    		isCampoAnyEmpty = true;
    	}
    		
		return isCampoAnyEmpty;
	}
	
	/**
	 * Metodo para "ouvir" as a��es dos bot�es
	 * @param listener
	 */
	public void addButtonsListener(EventHandler<ActionEvent> listener){
	 	   
    	btnAddNovaTarefa.setOnAction(listener);
    	btnVoltar.setOnAction(listener);
    }

	/**
	 * Metodo da interface Initializable
	 */
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

	/**
	 * Retorna o titulo da tarefa
	 * @return String
	 */
	public String getTxtTitulo() {
		return txtTitulo.getText();
	}

	/**
	 * Insere um titulo a tarefa
	 * @param txtTitulo
	 */
	public void setTxtTitulo(TextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}

	/**
	 * Metodo que retorna a descri��o da tarefa
	 * @return String
	 */
	public String getTxtDescricao() {
		return txtDescricao.getText();
	}

	/**
	 * Metodo que insere a descri��o da tarefa
	 * @param txtDescricao
	 */
	public void setTxtDescricao(TextArea txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	/**
	 * Pega o bot�o de voltar
	 * @return Button
	 */
	public Button getBtnVoltar() {
		return btnVoltar;
	}

	/**
	 * Insere o bot�o de voltar
	 * @param btnVoltar
	 */
	public void setBtnVoltar(Button btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	/**
	 * retorna a validade da tarefa
	 * @return DatePicker
	 */
	public DatePicker getTxtValidade() {
		return txtValidade;
	}

	/**
	 * Insere uma validade para a tarefa
	 * @param txtValidade
	 */
	public void setTxtValidade(DatePicker txtValidade) {
		this.txtValidade = txtValidade;
	}

	/**
	 * Retorna o bot�o de salvar a tarefa
	 * @return Button
	 */
	public Button getBtnAddNovaTarefa() {
		return btnAddNovaTarefa;
	}

	/**
	 * insere o bot�o de salvar tarefa
	 * @param btnAddNovaTarefa
	 */
	public void setBtnAddNovaTarefa(Button btnAddNovaTarefa) {
		this.btnAddNovaTarefa = btnAddNovaTarefa;
	}    
    
}
