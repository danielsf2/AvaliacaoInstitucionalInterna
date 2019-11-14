package tsi.too.aii.classes;

import tsi.too.aii.interfaces.Constantes;

/**
 * Classe responsavel por referenciar as perguntas relacionadas as avaliações, a classe fornece variáveis e métodos que
 * possibilitam a manipulação de seus elementos.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class Pergunta{

	private Integer idPergunta;//8bytes
	private String classeGeral, subclasse;//80caracteres. 320bytes
	private AvaliacaoPergunta avaliacaoPergunta;
	
	/**
	 * Construtor default da classe.
	 */
	public Pergunta() {
		classeGeral = subclasse = Constantes.VAZIO;
		idPergunta = Constantes.INDEXADOR_ZERO;
		avaliacaoPergunta = new AvaliacaoPergunta();
	}

	/**
	 * Retorna o id que identifica a pergunta.
	 */
	public Integer getIdPergunta() {
		return idPergunta;
	}

	/**
	 * Altera o id da pergunta.
	 */
	public void setIdPergunta(Integer idPergunta) {
		this.idPergunta = idPergunta;
	}

	/**
	 * Retorna o nome da classe geral da pergunta.
	 * 
	 * @return String.
	 */
	public String getClasseGeral() {
		return classeGeral;
	}

	/**
	 * Altera o nome da classe geral da pergunta.
	 */
	public void setClasseGeral(String classeGeral) {
		this.classeGeral = classeGeral;
	}

	/**
	 * Retorna o nome da subClasse da pergunta que identifica a pergunta.
	 * 
	 * @return String
	 */
	public String getSubclasse() {
		return subclasse;
	}

	/**
	 * Altera o nome da subClasse da pergunta que identifica a pergunta.
	 * 
	 * @param subclasse
	 */
	public void setSubclasse(String subclasse) {
		this.subclasse = subclasse;
	}

	/**
	 * Retorna um objeto {@link Avaliacao} o qual armazena as respostas das perguntas.
	 * 
	 * @return AvaliacaoPergunta.
	 */
	public AvaliacaoPergunta getAvaliacaoPergunta() {
		return avaliacaoPergunta;
	}

	/**
	 * Altera a avaliação das perguntas.
	 * 
	 * @param avaliacaoPergunta.
	 */
	public void setAvaliacaoPergunta(AvaliacaoPergunta avaliacaoPergunta) {
		this.avaliacaoPergunta = avaliacaoPergunta;
	}

	@Override
	public String toString() {
		return "Pergunta [idPergunta=" + idPergunta + ", classeGeral=" + classeGeral
				+ ", subclasse=" + subclasse + ", avaliacaoPergunta=" + avaliacaoPergunta + "]";
	}

}//class Avaliacao 
