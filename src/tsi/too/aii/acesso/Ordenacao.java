package tsi.too.aii.acesso;

import static tsi.too.aii.interfaces.Constantes.INDEXADOR_ZERO;

import java.util.List;

import tsi.too.aii.classes.AvaliacaoPergunta;
import tsi.too.aii.classes.ContabilizarAvaliacao;

/**
 * Esta classe tem como finalidade fornecer métodos de ordenação que serão usados durante a execução do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class Ordenacao {

	/**
	 * Ordena um {@link List} de {@link String} crescentemente, ou seja pela ordem natural de seus elementos.
	 * 
	 * @param cursos
	 */
	public static void ordenaListaCursosNome(List<String>cursos) {
		//Ordena os cursos por nome.
		cursos.sort((String o1, String o2)-> {
			return o1.compareToIgnoreCase(o2);
		});
	}//ordenaListaCursos
	
	/**
	 * Utiliza dois critérios de ordenação para ordenar um {@link List} de objetos do tipo {@link ContabilizarAvaliacao}, ordena
	 * primeiramente pelo curso avaliador, e quando o argumento se repetir ordena também pelo nome da questão.
	 * 
	 * @param avaliacoes
	 */
	public static void ordenaAvaliacoesCursoIndice(List<ContabilizarAvaliacao>avaliacoes) {
		avaliacoes.sort((ContabilizarAvaliacao o1, ContabilizarAvaliacao o2) -> {
			Integer comparador = o1.getCursoAvaliador().compareToIgnoreCase(o2.getCursoAvaliador());//Ordena por curso.
				if(comparador == INDEXADOR_ZERO)
					comparador = o1.getQuestao().compareTo(o2.getQuestao());//Ordena por indice.
			return comparador;
		});
	}//ordenaAvaliacoes
	
	/**
	 * Ordena um {@link List} de {@link AvaliacaoPergunta} pelo Identificador do objeto.
	 * 
	 * @param perguntas
	 */
	public static void ordenaAvaliacoesIdPergunta(List<AvaliacaoPergunta>perguntas) {
		perguntas.sort((AvaliacaoPergunta o1, AvaliacaoPergunta o2) -> {
				return o1.getIdPergunta().compareTo(o2.getIdPergunta());
		});
	}//ordenaAvaliacoes
	
}//class Ordenacao 
