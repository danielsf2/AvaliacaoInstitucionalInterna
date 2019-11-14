package tsi.too.aii.classes;

import java.util.ArrayList;
import java.util.List;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe é um objeto de uma classe principal pergunta, tendo por finalidade armazenar uma identificação das perguntas
 * fornecidas por participantes da pesquisa, bem como um {@link List} com todas as respostas das referidas perguntas.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class AvaliacaoPergunta {

	private Integer idPergunta;
	private List<Avaliacao> avaliacoes;
	
	/**
	 * Construtor default da classe.
	 */
	public AvaliacaoPergunta() {
		idPergunta = Constantes.INDEXADOR_ZERO;
		avaliacoes = new ArrayList<>();
	}

	/**
	 * Retorna o id da AvaliacaoPergunta.
	 * 
	 * @return Integer
	 */
	public Integer getIdPergunta() {
		return idPergunta;
	}

	/**
	 * Altera o id da Avaliação Pergunta.
	 * 
	 * @param idPergunta
	 */
	public void setIdPergunta(Integer idPergunta) {
		this.idPergunta = idPergunta;
	}
	
	/**
	 * Adiciona um objeto {@link Avaliacao} à {@link List} de avaliações presentes nesta classe.
	 * 
	 * @param avaliacao
	 */
	public void adicionarAvaliacao(Avaliacao avaliacao) {
		avaliacoes.add(avaliacao);
	}
	
	/**
	 * Retorna um {@link List} de {@link Avaliacao} armazenada no objeto.
	 * 
	 * @return List
	 */
	public List<Avaliacao> obterAvaliacoes(){
		return avaliacoes;
	}

	@Override
	public String toString() {
		return "AvaliacaoPergunta [idPergunta=" + idPergunta + ", avaliacoes=" + avaliacoes.size() + "]";
	}
	
}//class AvaliacaoPergunta 
