package tsi.too.aii.classes;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe referencia uma Avaliação, que é lida da lista. A classe fornece métodos que possibilitem a manipulação de seus
 * elementos.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class Avaliacao{ 
	private String nomeCampus, tipoParticipante, nomeCurso, nota;
	private Integer pesoNota;//4bytes
	
	/**
	 * Construdos default da classe.
	 */
	public Avaliacao() {
		nomeCampus = tipoParticipante = nomeCurso = nota = Constantes.VAZIO;
		pesoNota =  Constantes.INDEXADOR_ZERO;
	}

	/**
	 * Retorna o nome do Campus
	 * 
	 * @return String.
	 */
	public String getNomeCampus() {
		return nomeCampus;
	}

	/**
	 * Altera o nome do Campus.
	 * 
	 * @param nomeCampus
	 */
	public void setNomeCampus(String nomeCampus) {
		this.nomeCampus = nomeCampus;
	}

	/**
	 * Retorna o tipo do Participante.
	 * 
	 * @return String
	 */
	public String getTipoParticipante() {
		return tipoParticipante;
	}

	/**
	 * Altera a descrição do tipo de participante.
	 * 
	 * @param tipoParticipante
	 */
	public void setTipoParticipante(String tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}

	/**
	 *Retorna o nome do Curso. 
	 *
	 * @return String
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
	 * Retorna a nota.
	 * 
	 * @return String
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * Altera a nota dada pelo participante da pesquisa.
	 * 
	 * @param nota
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}

	/**
	 * Retorna o peso da nota do participante da pesquisa.
	 * 
	 * @return Integer
	 */
	public Integer getPesoNota() {
		return pesoNota;
	}

	/**
	 * Altera o peso do participante da pesquisa.
	 * 
	 * @param pesoNota
	 */
	public void setPesoNota(Integer pesoNota) {
		this.pesoNota = pesoNota;
	}

	@Override
	public String toString() {
		return "Avaliacao \n Nome do Campus = " + nomeCampus + ", tipoParticipante=" + tipoParticipante + ", nomeCurso="
				+ nomeCurso + ", nota=" + nota + ", pesoNota=" + pesoNota + "\n";
	}
	
}//class Avaliacao 
