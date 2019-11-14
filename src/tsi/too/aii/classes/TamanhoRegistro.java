package tsi.too.aii.classes;

import java.io.Serializable;

import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por finalidade fornecer um objeto que permita a manipulação de um objeto com as descrições do tamanho
 * e nome do registro de tamanho do arquivo de avaliações.
 * 
 * @author Daniel Soares Ferreira
 *
 */
@SuppressWarnings("serial")
public class TamanhoRegistro implements Serializable{

	private Integer tamanho;
	private String nomeArquivo;

	/**
	 * Construtor default da classe.
	 */
	public TamanhoRegistro() {
		tamanho = Constantes.INDEXADOR_ZERO;
		setNomeArquivo(Constantes.VAZIO);
	}

	/**
	 * Construtor sobrecarregado da classe, responsável por fornecer uma inicialização dos objetos com valores externos.
	 * 
	 * @param tamanho
	 * @param nomeArquivo
	 */
	public TamanhoRegistro(Integer tamanho, String nomeArquivo) {
		this.tamanho = tamanho;
		this.setNomeArquivo(nomeArquivo);
	}

	/**
	 * Obtem o tamaho de um registro.
	 * 
	 * @return Integer.
	 */
	public Integer getTamanho() {
		return tamanho;
	}

	/**
	 * Altera o tamanho de um registro.
	 * 
	 * @param tamanho
	 */
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * Obtem o nome do Arquivo.
	 * 
	 * @return String
	 */
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	/**
	 * Altera o nome do Arquivo.
	 * 
	 * @param nomeArquivo
	 */
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	@Override
	public String toString() {
		return "TamanhoRegistro [tamanho=" + tamanho + ", nomeArquivo=" + nomeArquivo + "]";
	}
	
}//class TamanhoRegistro 
