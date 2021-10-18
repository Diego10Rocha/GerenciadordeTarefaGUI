/*******************************************************************************
Autores: Joanderson Santos e Diego Cerqueira
Componente Curricular: Algoritmos II
Concluido em: 18/10/2021
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/

package model;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageAlert {
	
	private Alert alert;
	
	public void getMessageCampoEmBranco() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor preenche os campos primeiro");
		
		alert.show();
		
	}
	
	public void getMessageTarefasNaoConcluidas() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("As tarefas devem estar conclu�das para que um projeto seja exclu�do");
		
		alert.show();
		
	}
	
	public void getMessageProjetoSalvo() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto Salvo com Sucesso!");
		
		alert.show();
	}
	
	public void getMessageProjetoEditado() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto Editado com Sucesso!");
		
		alert.show();
	}
	
	public void getMessageProjetoNaoSelecionada() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor selecione um projeto primeiro!");
		
		alert.show();
	}
	
	public void getMessageProjetoExcluida() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Projeto exclu�do com Sucesso!");
		
		alert.show();
	}

	public void getMessageTarefaSalva() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa salva com Sucesso!");
		
		alert.show();
		
	}

	public void getMessageTarefaEditada() {
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa editada com Sucesso!");
		
		alert.show();
		
	}

	public void getMessageTarefaNaoSelecionada() {
		
		alert = new Alert(AlertType.WARNING);
		alert.setContentText("Por favor selecione uma Tarefa!");
		
		alert.show();
		
	}

	public void getMessageTarefaExcluida() {
	
		
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Tarefa exclu�da com Sucesso!");
		
		alert.show();
	}
		
	
}
