package tsi.too.aii.classes;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por objetivo fornecer variáveis e métodos responsáveis por auxiliar e armazenadar dados referentes aos
 * cálculos realizados durante o programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class ContabilizarAvaliacao {

	private String questao,cursoAvaliador;
	private Integer pesoQuestao,quantidade;
	private Double mediaValor;
	
	/**
	 * Construtor default da classe.
	 */
	public ContabilizarAvaliacao() {
		questao= cursoAvaliador = Constantes.VAZIO;
	}

	/**
	 * Construtor sobrecarregado da classe que permite a inicialização das variáveis da classe de acordo com valores repassados.
	 * 
	 * @param questao
	 * @param cursoAvaliador
	 * @param pesoQuestao
	 * @param quantidade
	 * @param indiceQuestao
	 * @param mediaValor
	 */
	public ContabilizarAvaliacao(String questao, String cursoAvaliador, Integer pesoQuestao, Integer quantidade,
			Integer indiceQuestao, Double mediaValor) {
		this.questao = questao;
		this.cursoAvaliador = cursoAvaliador;
		this.pesoQuestao = pesoQuestao;
		this.quantidade = quantidade;
		this.mediaValor = mediaValor;
	}

	/**
	 * Retorna a questão.
	 * 
	 * @return String
	 */
	public String getQuestao() {
		return questao;
	}

	/**
	 * Altera a questão.
	 * 
	 * @param questao
	 */
	public void setQuestao(String questao) {
		this.questao = questao;
	}

	/**
	 * Obtem o nome do curso avaliador.
	 * 
	 * @return String.
	 */
	public String getCursoAvaliador() {
		return cursoAvaliador;
	}

	/**
	 * Altera o nome do curso avaliador.
	 * 
	 * @param cursoAvaliador
	 */
	public void setCursoAvaliador(String cursoAvaliador) {
		this.cursoAvaliador = cursoAvaliador;
	}

	/**
	 * Retorna o peso da questão.
	 * 
	 * @return Integer.
	 */
	public Integer getPesoQuestao() {
		return pesoQuestao;
	}

	/**
	 * Altera o peso da questão.
	 * 
	 * @param pesoQuestao
	 */
	public void setPesoQuestao(Integer pesoQuestao) {
		this.pesoQuestao = pesoQuestao;
	}

	/**
	 * Retorna a quantidade de elementos.
	 * 
	 * @return Integer.
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * Altera a quantidade de elementos.
	 * 
	 * @param quantidade
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Retorna a media de valores.
	 * 
	 * @return Double.
	 */
	public Double getMediaValor() {
		return mediaValor;
	}

	/**
	 * Altera a média de valores.
	 * 
	 * @param mediaValor
	 */
	public void setMediaValor(Double mediaValor) {
		this.mediaValor = mediaValor;
	}

	@Override
	public String toString() {
		return "ContabilizarAvaliacao [questao=" + questao + ", cursoAvaliador=" + cursoAvaliador + ", pesoQuestao="
				+ pesoQuestao + ", quantidade=" + quantidade + ", mediaValor="
				+ mediaValor + "]";
	}
	
}//class ContabilizarAvaliacao 
