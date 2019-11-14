package tsi.too.aii.classes;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por finalidade fornecer métodos e variáveis que auxiliem na contabilização de cursos e votos que os mesmos
 * obtiveram durante as avaliações.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class Curso {

	private String nomeCurso;
	private Integer quantidadeVotos;
	
	/**
	 * Construtor default da classe.
	 */
	public Curso() {
		nomeCurso = Constantes.VAZIO;
		quantidadeVotos = Constantes.INDEXADOR_ZERO;
	}
	
	/**
	 * Construtor sobrecarregado da classe que fornece a possibilidade de inicializar objetos da classe com valores externos.
	 * 
	 * @param nomeCurso
	 * @param quantidadeVotos
	 */
	public Curso(String nomeCurso, Integer quantidadeVotos) {
		this.nomeCurso = nomeCurso;
		this.quantidadeVotos = quantidadeVotos;
	}

	/**
	 * Retorna o nome do curso.
	 * 
	 * @return String.
	 */
	public String getNomeCurso() {
		return nomeCurso;
	}

	/**
	 * Altera o nome do curso.
	 * 
	 * @param nomeCurso
	 */
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	/**
	 * Obtem a quantidade de votos do curso.
	 * 
	 * @return Integer
	 */
	public Integer getQuantidadeVotos() {
		return quantidadeVotos;
	}

	/**
	 * Altera a quantidade de votos do curso.
	 * 
	 * @param quantidadeVotos
	 */
	public void setQuantidadeVotos(Integer quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	@Override
	public String toString() {
		return "Curso [nomeCurso=" + nomeCurso + ", quantidadeVotos=" + quantidadeVotos + "]";
	}
	
}//class Curso 
