/*******************************************************************************
Autor: Diego Cerqueira e Joanderson Santos
Componente Curricular: MI Programa��o
Concluido em: 18/10/2021
Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum
trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo
de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte
do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.
******************************************************************************************/

package model;

import date.MyDate;

/**
 * model de tarefa
 * @author Diego Cerqueira e Joanderson Santos
 *
 */
public class Tarefa {
	
	private String titulo;
	private String descricao;
	private String validade;
	private Status status;
	
	/**
	 * Construtor vazio de tarefa
	 */
	public Tarefa() {
		
	}
			
	/**
	 * Contrutor de tarefa
	 * @param titulo
	 * @param descricao
	 * @param validade
	 */
	public Tarefa(String titulo, String descricao, String validade) {
		
		this.titulo = titulo;
		this.descricao = descricao;
		this.validade = validade;
		this.status = Status.PENDENTE;
	}
	
	/**
	 * Contrutor de tarefa
	 * @param titulo
	 * @param validade
	 */
	public Tarefa(String titulo, String validade) {
		
		this.titulo = titulo;
		this.descricao = "";
		this.validade = validade;
		this.status = Status.PENDENTE;
	}
	
	/**
	 * retorna o titulo da tarefa
	 * @return String
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * insere o titulo da tarefa
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**retorna a descri��o da tarefa
	 * @return String
	 */
	public String getDescricao() {
		return descricao;
	}
	/**insere a descri��o da tarefa
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**retorna a data de validade da tarefa
	 * @return String
	 */
	public String getValidade() {
		return validade;
	}
	/**insere uma validade a tarefa
	 * @param validade
	 */
	public void setValidade(String validade) {
		this.validade = validade;
	}
	/**retorna o status da tarefa
	 * @return Status
	 */
	public Status getStatus() {
		return status;
	}
	/**insere um status a tarefa
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * compara duas tarefas
	 */
	public boolean equals(Object obj) {
        
        if (obj instanceof Tarefa){

            Tarefa tarefaASerComparada = (Tarefa) obj;
            
            return (this.titulo == tarefaASerComparada.getTitulo() &&
            		this.descricao == tarefaASerComparada.getDescricao() &&           		
    				this.validade == tarefaASerComparada.getValidade() &&
    				this.status == tarefaASerComparada.getStatus());

        }
        
        return false;
	}

	/**verifica se uma tarefa est� atrasada
	 * @return boolean
	 */
	public boolean getIsAtrasada() {
		
		Status statusConcluida = Status.CONCLUIDA;
		
		boolean isAtrasada = false;
		boolean isTarefaConcluida = (this.status == statusConcluida);
		
		if(!isTarefaConcluida) {
			
			MyDate date = new MyDate();
			
			String currentDate = date.getCurrentDate();
			String dateTarefa = this.validade;
			
			
			int valComp = date.compareTo(currentDate, dateTarefa);//M�todo similar ao CompareTo da classe String
			
			if(valComp >= 1) isAtrasada = true; //Data atual > Data tarefa
		}	
		return isAtrasada;
	}

	/**
	 * metodo pra converter um objeto tarefa para uma string
	 */
	@Override
	public String toString() {
		
		boolean isTarefaAtrasada = this.getIsAtrasada();
		
		String marcacaoAtrasada = "";
		

		if(isTarefaAtrasada)  marcacaoAtrasada = " [Atrasada!]";
		
		return "T�tulo: "    +  this.getTitulo()  + "\n" +
			   "Descri��o: " + this.getDescricao() + "\n" +
			   "Validade: " + this.getValidade() + 
			   marcacaoAtrasada;

	}
	
}
