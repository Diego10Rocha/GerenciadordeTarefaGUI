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

import Exceptions.ObjetoInexistenteException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import message.MessageAlert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import model.Status;
import model.Tarefa;


/**
 * Classe controladora da tela de edi��o de tarefas
 * @author Diego Cerqueira e Joanderson Santos
 * @since 2021
 */

public class FormularioTarefaScreenEditController implements Initializable {
	
	@FXML
    private TextField txtTitulo;

    @FXML
    private TextArea txtDescricao;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker txtValidade;
    
    @FXML
    private Button btnAddNovaTarefaEdit;

    @FXML
    private ToggleGroup group;
   
    private MessageAlert msgAlert = new MessageAlert();
    
    private Tarefa tarefaSelecionada;


    /**
     * Metodo para salvar as altera��es em uma tarefa
     * @throws ObjetoInexistenteException
     */
    public void salvarEditTarefa() throws ObjetoInexistenteException {
    	
    	boolean isCampoAnyEmpty  = verificarCampoAnyEmpty();
    	
    	if(isCampoAnyEmpty) {
    		
    		this.msgAlert.getMessageCampoEmBranco();
    	} else {
    		
			String titleTarefa = txtTitulo.getText();
    		String descriptionTarefa = txtDescricao.getText();
    		String dateTarefa = txtValidade.getEditor().getText();
    		Status statusSelecionado = getStatusSelecionado();
    		
    		tarefaSelecionada.setTitulo(titleTarefa);
    		tarefaSelecionada.setDescricao(descriptionTarefa);
    		tarefaSelecionada.setValidade(dateTarefa);
    		tarefaSelecionada.setStatus(statusSelecionado);
    		  		
    		cleanInfoTarefa();
    		
    		this.msgAlert.getMessageTarefaEditada();
    		
    	}
    }
    
    /**
     * Metodo para fechar a tela de edi��o de tarefas e voltar a tela com a listagem das tarefas
 	 */
    public void closeScreen() {
    	
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
    	
    	stage.close();

    }
  
    /**
     * Metodo que limpa os campos do formul�rio de edi��o de tarefa
     */
    private void cleanInfoTarefa() {
		
    	txtTitulo.setText("");
    	txtDescricao.setText("");
    	txtValidade.getEditor().setText("");
		
	}

	/**
	 * Metodo que verifica se os campos do formul�rio est�o vazios
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
	 * Retorna qual o status selecionado no formul�rio
	 * @return Status
	 */
	public Status getStatusSelecionado() {
		
		Status statusSelecionado;
		
		RadioButton radio = (RadioButton) group.getSelectedToggle();
		
		if(radio.getText().equals("Pendente")) {
			
			
			statusSelecionado = Status.PENDENTE;
		} else if(radio.getText().equals("Conclu�da")) {
			
			
			statusSelecionado = Status.CONCLUIDA;
		} else {
			
			statusSelecionado = Status.EM_EXECUCAO;
		}
		
		return statusSelecionado;
		
	}
	
	/**
	 * Metodo da interface Initializable
	 * @param url
	 * @param rb
	 */
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		tarefaSelecionada = TarefasScreenController.getTarefaSelecionada();
		
		txtTitulo.setText(tarefaSelecionada.getTitulo());
    	txtDescricao.setText(tarefaSelecionada.getDescricao());
    	txtValidade.getEditor().setText(tarefaSelecionada.getValidade());
        
    }    
    
	/**
	 * Metodo para "ouvir" a a��o de um bot�o
	 * @param listener
	 */
	public void addButtonsListener(EventHandler<ActionEvent> listener){
		   
		btnAddNovaTarefaEdit.setOnAction(listener);
    	btnVoltar.setOnAction(listener);
    }

	/**
	 * Retorna o titulo da tarefa editada
	 * @return TextField
	 */
	public TextField getTxtTitulo() {
		return txtTitulo;
	}

	/**
	 * Metodo que insere um novo titulo a tarefa
	 * @param txtTitulo
	 */
	public void setTxtTitulo(TextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}

	/**
	 * Metodo que retorna a nova descri��o da tarefa
	 * @return TextArea
	 */
	public TextArea getTxtDescricao() {
		return txtDescricao;
	}

	/**
	 * Metodo que insere uma nova descri��o na tarefa
	 * @param txtDescricao
	 */
	public void setTxtDescricao(TextArea txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	/**
	 * Retorna o bot�o de voltar
	 * @return Button
	 */
	public Button getBtnVoltar() {
		return btnVoltar;
	}

	/**
	 * insere o bot�o de voltar
	 * @param btnVoltar
	 */
	public void setBtnVoltar(Button btnVoltar) {
		this.btnVoltar = btnVoltar;
	}

	/**
	 * Retorna uma data
	 * @return DatePicker
	 */
	public DatePicker getTxtValidade() {
		return txtValidade;
	}

	/**
	 * Metodo de inserir uma data de validade
	 * @param txtValidade
	 */
	public void setTxtValidade(DatePicker txtValidade) {
		this.txtValidade = txtValidade;
	}

	/**
	 * @return ToggleGroup
	 */
	public ToggleGroup getGroup() {
		return group;
	}

	/**
	 * @param group
	 */
	public void setGroup(ToggleGroup group) {
		this.group = group;
	}

	/**
	 * Retorna a tarefa selecionada
	 * @return Tarefa
	 */
	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	/**
	 * Metodo que insere uma tarefa selecionada
	 * @param tarefaSelecionada
	 */
	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

	/**
	 * Retorna o bot�o de editar tarefa
	 * @return Button
	 */
	public Button getBtnAddNovaTarefaEdit() {
		return btnAddNovaTarefaEdit;
	}

	/**
	 * Insere um bot�o de edi��o de tarefa
	 * @param btnAddNovaTarefaEdit
	 */
	public void setBtnAddNovaTarefaEdit(Button btnAddNovaTarefaEdit) {
		this.btnAddNovaTarefaEdit = btnAddNovaTarefaEdit;
	}

}
