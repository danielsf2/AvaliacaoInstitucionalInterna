package tsi.too.aii.relatorios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.acesso.MetodosGerais;
import tsi.too.aii.classes.Avaliacao;
import tsi.too.aii.classes.ContabilizarAvaliacao;
import tsi.too.aii.classes.Curso;
import tsi.too.aii.classes.Pergunta;
import tsi.too.aii.classes.Segmentos;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por objetivo fornecer métodos que possibilitem a geração de relatórios durante a execução do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class Relatorios {
	
	private static final String DISCENTE = "Discente",
													DOCENTE = "Docente";
	private static final int ADICIONAR = 1;
	private static final double MEDIA_DEFAULT = 0.0;
	
	private static ManipulacaoArquivo manipulacaoArquivo;
	
	/**
	 * Cria um relatório para povoar uma base de dados que pesquisa segmentos participantes na pesquisa.
	 * 
	 * @return Segmentos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Segmentos criaBaseDadosRelatorioSegmento() throws FileNotFoundException, IOException {
		Pergunta avaliacao = new ManipulacaoArquivo().obterArquivo();
		Segmentos segmento = new Segmentos();
		for(Avaliacao av : avaliacao.getAvaliacaoPergunta().obterAvaliacoes()) {
			if(av.getTipoParticipante().equalsIgnoreCase(DISCENTE))
				segmento.setDiscente(segmento.getDiscente()+ADICIONAR);
			else if(av.getTipoParticipante().equalsIgnoreCase(DOCENTE))
				segmento.setDocente(segmento.getDocente()+ADICIONAR);
			else
				segmento.setTecnicoAdministrativo(segmento.getTecnicoAdministrativo()+ADICIONAR);
		}
		return segmento;
	}//criaBaseDadosRelatorioSegmento

	/**
	 * Cria uma base de dados para a criação do dataset, responsável por gerar o gráfico de barras horizontal. 
	 * 
	 * @param <code>DadosAvaliados</code>dadosAvaliados
	 * @return	<code>List</code><code>Cursos</code>
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @since 1.0
	 */
	public static List<Curso> criarBaseDadosRelatorioParticipante() throws FileNotFoundException, IOException{
		List<Curso>cursos = new ArrayList<>();
		List<String>campos = new ArrayList<>();
		manipulacaoArquivo = new ManipulacaoArquivo();
		Pergunta dadosAvaliados = manipulacaoArquivo.obterArquivo();
		
		//Obtém os cursos disponíveis do arquivo e cria uma lista com os mesmos.
		for (Avaliacao avaliacao : dadosAvaliados.getAvaliacaoPergunta().obterAvaliacoes()) {
			if(!campos.contains(avaliacao.getNomeCurso()))
				campos.add(avaliacao.getNomeCurso());
		}
		//Ordena a lista por nome em orndem decrescente
		campos.sort((String s1, String s2) -> {
				return -s1.compareToIgnoreCase(s2);
		});
		
		//Cria um array de cursos para realizar as operações
		for(String dado : campos)
			cursos.add(new Curso(dado, Constantes.INDEXADOR_ZERO));
		
		//Povoa uma lista de cursos com o nome e quantidade.
		for (Avaliacao avaliacao : dadosAvaliados.getAvaliacaoPergunta().obterAvaliacoes()) {
			for (Curso curso : cursos) {
				if(curso.getNomeCurso().equalsIgnoreCase(avaliacao.getNomeCurso()))
					curso.setQuantidadeVotos(curso.getQuantidadeVotos()+Constantes.INDEXADOR_UM);
			}
		}
		return cursos;
	}//criarBaseDados
	
	/**
	 * Cria uma base de dados para povoar os relatórios por curso, um {@link List} de {@link String} é passado como parâmetro
	 * com os cursos os quais devem ser obtidos da base de dados durante a pesquisa, o programa soma os valores e calcula
	 * uma média para os cursos passados como parâmetro.
	 * 
	 * @param cursos
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<ContabilizarAvaliacao> criarBaseDadosRelatorioCurso(List<String>cursos) throws FileNotFoundException, IOException{
		List<ContabilizarAvaliacao> lista = new ArrayList<>();
		for (String curso : cursos) {
			lista.addAll(new ManipulacaoArquivo().contabilizarAvaliacaoCurso(curso));
		}
		for (ContabilizarAvaliacao contabilizarAvaliacao : lista) {
			if(contabilizarAvaliacao.getPesoQuestao() == Constantes.INDEXADOR_ZERO && contabilizarAvaliacao.getQuantidade()==Constantes.INDEXADOR_ZERO)
				contabilizarAvaliacao.setMediaValor(MEDIA_DEFAULT);
			else {
				contabilizarAvaliacao.setMediaValor(((double)contabilizarAvaliacao.getPesoQuestao()/contabilizarAvaliacao.getQuantidade()));
				contabilizarAvaliacao.setMediaValor(MetodosGerais.arredondaValor(contabilizarAvaliacao.getMediaValor()));
			}
		}
		return lista;
	}//criarBaseDadosRelatorioCurso
	
}//class RelatorioParticipantesCurso 
