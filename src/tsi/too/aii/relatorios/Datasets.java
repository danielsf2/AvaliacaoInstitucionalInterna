package tsi.too.aii.relatorios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import tsi.too.aii.classes.ContabilizarAvaliacao;
import tsi.too.aii.classes.Curso;
import tsi.too.aii.classes.Segmentos;

/**
 * Esta classe tem por objetivo gerar {@link DefaultCategoryDataset} para povoamento de objetos do tipo {@link JFreeChart}
 * que serão utilizados durante a execução do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class Datasets {
	
	private final static String DISCENTE = "Discente";
	private final static String DOCENTE = "Docente";
	private final static String TECNICO = "Técnico Administrativo da Educação";

	/**
	 * Refina a lista recebida como parâmetro extraindo a quantidade de discentes, docentes e técnicos administrativos
	 * participaram da pesquisa, adiciona o conteúdo e seus respectivos nomes a um objeto {@link DefaultCategoryDataset}
	 * e retona o objeto preenchido.
	 * 
	 * @param  DadosAvaliados
	 * @return  DefaultCategoryDataset 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @since 1.0
	 */
	public static DefaultCategoryDataset criaDatasetGraficoColuna() throws FileNotFoundException, IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		Segmentos segmento = Relatorios.criaBaseDadosRelatorioSegmento();
		
		ds.addValue(segmento.getDiscente(), DISCENTE, DISCENTE);
		ds.addValue(segmento.getDocente(), DOCENTE, DOCENTE);
		ds.addValue(segmento.getTecnicoAdministrativo(), TECNICO, TECNICO);
		return ds;
	}//criaDatasetGraficoColuna
	
	/**
	 * Gera um {@link DefaultCategoryDataset} com dados refinados a partir do banco de dados do programa, tal método tem
	 * por finalidade fornecer um dataset para a criação de um {@link BarChart}.
	 * 
	 * @return DefaultCategoryDataset povoado com os valores desejados
	 * @throws IOException
	 */
	public static DefaultCategoryDataset gerarDatasetGraficoBarra() throws IOException {
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		List<Curso> cursos = Relatorios.criarBaseDadosRelatorioParticipante();
		
		for (Curso curso : cursos) {
			ds.addValue(curso.getQuantidadeVotos(), curso.getNomeCurso(), curso.getNomeCurso());
		}
		return ds;
	}//gerarDataset
	
	/**
	 * Gera um {@link DefaultCategoryDataset} responsável por preencher um {@link LineChart} o qual é executado no programa.
	 * 
	 * @param cursos
	 * @return DefaultCategoryDataset 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static DefaultCategoryDataset gerarDatasetGraficoLinhaCursos(List<String>cursos) throws FileNotFoundException, IOException {
		
		List<ContabilizarAvaliacao> avaliacoes = Relatorios.criarBaseDadosRelatorioCurso(cursos);
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		
		for (ContabilizarAvaliacao ca : avaliacoes)
			ds.addValue(ca.getMediaValor(), ca.getCursoAvaliador(), ca.getQuestao());
	
		return ds;
	}//gerarDatasetGraficoLinhaBacharelado
	
	/**
	 * Versão sobrecarregada do método gerarDatasetGraficoLinhaCursos, fornece a possibilidade do usuário da classe, fornecer
	 * uma {@link String} ou uma lista de comprimento variável como parâmetro para a pesquisa.
	 * 
	 * @param cursos
	 * @return DefaultCategoryDataset 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static DefaultCategoryDataset gerarDatasetGraficoLinhaCursos(String ...cursos) throws FileNotFoundException, IOException {
		
		List<String>cursosLista = new ArrayList<>();
		for(String curso : cursos)
			cursosLista.add(curso);
		List<ContabilizarAvaliacao> avaliacoes = Relatorios.criarBaseDadosRelatorioCurso(cursosLista);
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		
		for (ContabilizarAvaliacao ca : avaliacoes)
			ds.addValue(ca.getMediaValor(), ca.getCursoAvaliador(), ca.getQuestao());
	
		return ds;
	}//gerarDatasetGraficoLinhaCurso
	
}//class Datasets 

