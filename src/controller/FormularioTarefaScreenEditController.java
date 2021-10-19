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
 *
 * @author Diego Cerqueira e Joanderson Santos
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


    public void salvarEditTarefa() throws ObjetoInexistenteException {
    	
    	boolean isCampoAnyEmpty  = verificarCampoAnyEmpty();
    	
    	if(isCampoAnyEmpty) {
    		
    		this.msgAlert.getMessageCampoEmBranco();
    	}
    	
    	else {
    		
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
    
   
   public void closeScreen() {
    	
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
    	
    	stage.close();

    }
  
    private void cleanInfoTarefa() {
		
    	txtTitulo.setText("");
    	txtDescricao.setText("");
    	txtValidade.getEditor().setText("");
		
	}

	private boolean verificarCampoAnyEmpty() {
    	
    	boolean isCampoAnyEmpty = false;
    	
    	if( txtTitulo.getText() == "" ||
    	    txtDescricao.getText() == "" ||
    	    txtValidade.getEditor().getText() == "") {
    		
    		isCampoAnyEmpty = true;
    	}
    		
		return isCampoAnyEmpty;
	}
	
	public Status getStatusSelecionado() {
		
		Status statusSelecionado;
		
		RadioButton radio = (RadioButton) group.getSelectedToggle();
		
		if(radio.getText().equals("Pendente")) {
			
			
			statusSelecionado = Status.PENDENTE;
		}
		
		else if(radio.getText().equals("Conclu�da")) {
			
			
			statusSelecionado = Status.CONCLUIDA;
		}
		
		else {
			
			statusSelecionado = Status.EM_EXECUCAO;
		}
		
		return statusSelecionado;
		
	}
	
	

	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		tarefaSelecionada = TarefasScreenController.getTarefaSelecionada();
		
		txtTitulo.setText(tarefaSelecionada.getTitulo());
    	txtDescricao.setText(tarefaSelecionada.getDescricao());
    	txtValidade.getEditor().setText(tarefaSelecionada.getValidade());
        
    }    
    
 
	public void addButtonsListener(EventHandler<ActionEvent> listener){
		   
		btnAddNovaTarefaEdit.setOnAction(listener);
    	btnVoltar.setOnAction(listener);
    }


	public TextField getTxtTitulo() {
		return txtTitulo;
	}


	public void setTxtTitulo(TextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}


	public TextArea getTxtDescricao() {
		return txtDescricao;
	}


	public void setTxtDescricao(TextArea txtDescricao) {
		this.txtDescricao = txtDescricao;
	}


	public Button getBtnVoltar() {
		return btnVoltar;
	}


	public void setBtnVoltar(Button btnVoltar) {
		this.btnVoltar = btnVoltar;
	}


	public DatePicker getTxtValidade() {
		return txtValidade;
	}


	public void setTxtValidade(DatePicker txtValidade) {
		this.txtValidade = txtValidade;
	}


	
	public ToggleGroup getGroup() {
		return group;
	}


	public void setGroup(ToggleGroup group) {
		this.group = group;
	}


	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}


	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}


	public Button getBtnAddNovaTarefaEdit() {
		return btnAddNovaTarefaEdit;
	}


	public void setBtnAddNovaTarefaEdit(Button btnAddNovaTarefaEdit) {
		this.btnAddNovaTarefaEdit = btnAddNovaTarefaEdit;
	}

}
