package tsi.too.aii.controller;

import static tsi.too.aii.interfaces.Constantes.*;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import com.itextpdf.text.DocumentException;
import com.sun.xml.internal.ws.api.Component;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tsi.too.aii.acesso.MetodosGerais;
import tsi.too.aii.acesso.Ordenacao;
import tsi.too.aii.classes.ContabilizarAvaliacao;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaceGrafica.GraficoLinha;
import tsi.too.aii.interfaces.Constantes;
import tsi.too.aii.relatorios.CriacaoPDF;
import tsi.too.aii.relatorios.Datasets;
import tsi.too.aii.relatorios.Relatorios;

/**
 * Classe controladora do arquivo {@link FXML} TabelaCursos.fxml, a qual é responsável por fornecer um componente gráfico
 * para a aplicação onde estão contidos gráficos, botões e tabelas, esta classe é responsável por fornecer implementação a esta
 * parte gráfica.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class TabelaCursosController extends Application{

	private static final String CABECALHO_QUESTOES = "Assuntos Avaliados",
													 TITULO_CONCEITO="Conceito Médio por Curso",
													 VALOR_REPLACE = ".0";
	private static final int POS_INICIAL = 0,
												ADICAO = 1,
												DECREMENTO = -1;
	private static final double MEDIA_DEFAULT = 0.0;
	private static String TITULO_GRAFICO="";
	private static JTable tabelaExibicao;
	private static List<String>listaCursos;
	
	@FXML private BorderPane borderPane;
	@FXML public static Label baseDadosAtual;

	/**
	 * Inicializa o conteúdo das tabelas, bem usa usa o {@link Component} {@link SwingNode}, o qual foi usado para encapsular
	 * um {@link JTable} necessário para preencher o formulário requerido pela aplicação.
	 */
	public void initialize() {
		borderPane.setVisible(true);
		SwingNode sn = new SwingNode();
		sn.setContent(new JScrollPane(tabelaExibicao));
		borderPane.setCenter(sn);
	}//initialize

	/**
	 * Executa a funcionalidade do {@link Button} Exibir Gráfico presente na interface gráfica.
	 * 
	 * @param event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@FXML
	void acaoBotaoExibirGrafico(ActionEvent event) throws FileNotFoundException, IOException {
		listaCursos.remove(CABECALHO_QUESTOES);
		if(listaCursos.contains(CURSOS_BACHARELADO))
			TITULO_GRAFICO = TITULO_GRAFICO_BACHAREAL;
		else if (listaCursos.contains(CURSOS_LICENCIATURA))
			TITULO_GRAFICO = Constantes.TITULO_GRAFICO_LICENCIATURA;
		else
			TITULO_GRAFICO = Constantes.TITULO_GRAFICO_TECNOLOGIA;
		new GraficoLinha().criarGrafico(Datasets.gerarDatasetGraficoLinhaCursos(listaCursos), 
			PlotOrientation.VERTICAL, TITULO_GRAFICO, DIM_GRAFICO_LINHA[Constantes.INDEXADOR_ZERO], DIM_GRAFICO_LINHA[Constantes.INDEXADOR_UM], listaCursos);
	}//acaoBotaoExibirGrafico
	
	/**
	 * Executa a funcionalidade do {@link Button} Gerar PDF presente na interface gráfica.
	 * 
	 * @param event
	 * @throws DocumentException
	 * @throws IOException
	 */
	@FXML
	 void acaoBotaoGerarPDF(ActionEvent event) throws DocumentException, IOException {
		listaCursos.remove(CABECALHO_QUESTOES);
		if(listaCursos.contains(CURSOS_BACHARELADO))
			TITULO_GRAFICO = TITULO_GRAFICO_BACHAREAL;
		else if (listaCursos.contains(CURSOS_LICENCIATURA))
			TITULO_GRAFICO = Constantes.TITULO_GRAFICO_LICENCIATURA;
		else
			TITULO_GRAFICO = Constantes.TITULO_GRAFICO_TECNOLOGIA;
		JFreeChart grafico = new GraficoLinha().formatarGrafico(new GraficoLinha().obterGrafico(Datasets.gerarDatasetGraficoLinhaCursos(listaCursos),
				TITULO_GRAFICO, PlotOrientation.VERTICAL), listaCursos);
		new CriacaoPDF().gerarPDF(tabelaExibicao, grafico);
	}//acaoBotaoGerarPDF
	
	/**
	 * Cria um {@link JTable} através de uma matriz de {@link String} passada como parâmetro bem como um {@link List} tamém
	 * de {@link String} que será utilizado como título, formata a referida tabela e a devolve.
	 * 
	 * @param conteudoTabela
	 * @param titulo
	 * @return
	 */
	private JTable criarTabela(String[][]conteudoTabela, List<String>titulo) {
		JTable tabela = new JTable(conteudoTabela, titulo.toArray());
		formatarTabela(tabela);
		return tabela;
	}//criarTabela
	    
	/**
	 * Formata o {@link JTable} passado como parâmetro, alterando fontes, posicionamento de colulas, cores e tamahos.
	 * 
	 * @param tabela
	 * @return Tabela com o conteúdo formatado.
	 */
	private JTable formatarTabela(JTable tabela) {
		//tamanho da tabela
		tabela.setPreferredScrollableViewportSize(new Dimension(DIM_TABELA_CURSOS[INDEXADOR_ZERO],DIM_TABELA_CURSOS[INDEXADOR_UM]));
				
		//Deixa as colunas pares e cor verde, formatando e alterando suas fontes.
		DefaultTableCellRenderer formatacaoColunaPar = new DefaultTableCellRenderer();
		formatacaoColunaPar = MetodosGerais.formataColuna(formatacaoColunaPar, COR_RGB_VERDE);
		
		//Deixa as colunas ímpares em cor azul, formatando e alterando suas fontes.
		DefaultTableCellRenderer formatacaoColunaImpar = new DefaultTableCellRenderer();
		formatacaoColunaImpar = MetodosGerais.formataColuna(formatacaoColunaImpar, COR_RGB_AZUL);
		
		tabela.getTableHeader().setFont(ARIAL_BOLD_13);

		//Centraliza a tabela, não alinhando as perguntas/questões.
		for(int pos=INDEXADOR_UM; pos < tabela.getColumnCount();pos++) {
			if(pos%INDEXADOR_DOIS == INDEXADOR_ZERO)
				tabela.getColumnModel().getColumn(pos).setCellRenderer(formatacaoColunaPar);
			else
				tabela.getColumnModel().getColumn(pos).setCellRenderer(formatacaoColunaImpar);
		}
		
		tabela.getColumnModel().getColumn(POS_INICIAL).setPreferredWidth(TAM_PRIMEIRA_COLUNA);

		return tabela;
	}//formatarTabela
	
	/**
	 * Recebe uma base de dados como parâmetro, neste caso foi usado um {@link ArrayList}, percorre esse array, tranformando
	 * seu conteúdo em uma matriz que servirá para popular a tabela, tabem referencia as devidas operações matemáticas que
	 * serão utilizadas na mesma.
	 * 
	 * @param cursos
	 * @return Tabela preenchida
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private JTable preecnherTabelaCursos(ArrayList<String>cursos) throws FileNotFoundException, IOException {
		Ordenacao.ordenaListaCursosNome(cursos);
		List<ContabilizarAvaliacao> avaliacoes = Relatorios.criarBaseDadosRelatorioCurso(cursos);
		cursos.add(POS_INICIAL,CABECALHO_QUESTOES);		
		Ordenacao.ordenaAvaliacoesCursoIndice(avaliacoes);
		
		int tam = avaliacoes.size();//Utiliza a variável, pois o tamanho do array diminui conforme é decrementado.
		Double mediaGeral = MEDIA_DEFAULT;
		String matriz[][] = new String[avaliacoes.size()/(cursos.size()+DECREMENTO)+ADICAO][cursos.size()];
		for(int col = INDEXADOR_ZERO; col < cursos.size(); col++) {
			mediaGeral = MEDIA_DEFAULT;
			for(int lin = INDEXADOR_ZERO; lin < tam/(cursos.size()+DECREMENTO)+ADICAO; lin++) {
				if(col == POS_INICIAL) {
					matriz[lin][col] = avaliacoes.get(lin).getQuestao().trim();//Preenche a primeira coluna do gráfico.
				}
				if(lin == (tam/(cursos.size()+DECREMENTO)) && col == POS_INICIAL) {
					matriz[lin][col] = TITULO_CONCEITO;//Insere manualmente o último campo da primeira coluna
				}
				if(col > INDEXADOR_ZERO && lin <  tam/(cursos.size()+DECREMENTO)) {
					matriz[lin][col] = avaliacoes.get(POS_INICIAL).getMediaValor().toString();//Preenche as demais colunas.
					mediaGeral += avaliacoes.get(POS_INICIAL).getMediaValor();
					avaliacoes.remove(POS_INICIAL);//Exclui a posição que ja foi consumida pelo arquivo.
				}
				if(lin == tam/(cursos.size()+DECREMENTO) && col > POS_INICIAL) {
					mediaGeral = mediaGeral/(tam/(cursos.size()+DECREMENTO));
					matriz[lin][col] = Double.toString(MetodosGerais.arredondaValorInt(mediaGeral)).replaceAll(VALOR_REPLACE,VAZIO);
				}
			}
		}
		listaCursos = (List<String>) cursos.clone();
		return criarTabela(matriz, cursos);
	}//preecnherTabelaCursos

	/**
	 * Gera um {@link JTable} com os cursos passados como parâmetro, e exibe seu conteúdo em um {@link Stage}.
	 * 
	 * @param cursos
	 * @throws Exception
	 */
	public void gerarTabelaCursos(List<String>cursos) throws Exception {
		tabelaExibicao = preecnherTabelaCursos((ArrayList<String>) cursos);
		start(new Stage());
	}//gerarTabelaCursos
	
	@Override
	public void start(Stage stage) throws Exception {
		new CriacaoStage().start(stage, Constantes.CAMINHO_TABELA_CURSOS);
	}//start
	
}//class TabelaCursoBacharel 
