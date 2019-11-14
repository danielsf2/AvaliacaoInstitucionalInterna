package tsi.too.aii.classes;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por finalidade, armazenar em tempo de execução a quantidade de votos de cada segmento.
 *  
 * @author Daniel Soares Ferreira
 *
 */
public class Segmentos {

	private Integer docente, discente, tecnicoAdministrativo;

	/**
	 * Construtor default da classe.
	 */
	public Segmentos() {
		discente = docente = tecnicoAdministrativo = Constantes.INDEXADOR_ZERO;
	}

	/**
	 * Construtor sobrecarregado, que permite a inicialização dos objetos externamente.
	 * 
	 * @param docente
	 * @param discente
	 * @param tecnicoAdministrativo
	 */
	public Segmentos(Integer docente, Integer discente, Integer tecnicoAdministrativo) {
		this.docente = docente;
		this.discente = discente;
		this.tecnicoAdministrativo = tecnicoAdministrativo;
	}

	/**
	 * Retorna a quantidade de Docentes.
	 * 
	 * @return Integer.
	 */
	public Integer getDocente() {
		return docente;
	}

	/**
	 * Altera a quantidade de docentes.
	 * 
	 * @param docente
	 */
	public void setDocente(Integer docente) {
		this.docente = docente;
	}

	/**
	 * Retorna a quantidade de discentes.
	 * 
	 * @return
	 */
	public Integer getDiscente() {
		return discente;
	}

	/**
	 * Altera a quantidade de discentes.
	 * 
	 *  @param discente
	 */
	public void setDiscente(Integer discente) {
		this.discente = discente;
	}

	/**
	 * Obem a quantidade de técnicos administrativos da educação.
	 */
	public Integer getTecnicoAdministrativo() {
		return tecnicoAdministrativo;
	}

	/**
	 * Altera a quantidade de técnicos administrativos da educação.
	 * @param tecnicoAdministrativo
	 */
	public void setTecnicoAdministrativo(Integer tecnicoAdministrativo) {
		this.tecnicoAdministrativo = tecnicoAdministrativo;
	}

	@Override
	public String toString() {
		return "Segmentos [docente=" + docente + ", discente=" + discente + ", tecnicoAdministrativo="
				+ tecnicoAdministrativo + "]";
	}
	
}//class Segmentos 
