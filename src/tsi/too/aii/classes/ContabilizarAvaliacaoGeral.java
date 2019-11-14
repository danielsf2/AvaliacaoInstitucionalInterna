package tsi.too.aii.classes;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por finalidade fornecer métodos e variáveis que possibilitem a realização de cálculos relacionados a avaliação
 * geral dos relatórios.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class ContabilizarAvaliacaoGeral {

	private String questao, media;

	/**
	 * Construtor default da classe.
	 */
	public ContabilizarAvaliacaoGeral() {
		questao = media = Constantes.VAZIO;
	}

	/**
	 * Construtor sobrecarregado da classe, fornece possibilidade de inicializar objetos da classe com valores definidos externamente.
	 * 
	 * @param questao
	 * @param media
	 */
	public ContabilizarAvaliacaoGeral(String questao, String media) {
		this.questao = questao;
		this.media = media;
	}

	/**
	 * Obtem o nome da questão.
	 * 
	 * @return String
	 */
	public String getQuestao() {
		return questao;
	}

	/**
	 * Altera o nome da questão.
	 * 
	 * @param questao
	 */
	public void setQuestao(String questao) {
		this.questao = questao;
	}

	/**
	 * Obtem a média da questão.
	 * 
	 * @return String
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * Altera a média da questão.
	 * 
	 * @param media
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "ContabilizarAvaliacaoGeral [questao=" + questao + ", media=" + media + "]";
	}
	
}//class ContabilizarAvaliacaoGeral 
