package tsi.too.aii.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.acesso.MetodosGerais;
import tsi.too.aii.classes.ContabilizarAvaliacao;
import tsi.too.aii.classes.ContabilizarAvaliacaoGeral;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;
import tsi.too.aii.relatorios.Relatorios;

/**
 * Classe controladora do arquivo {@link FXML} TabelaAvaliacaoCursos.fxml, a qual é responsável por fornecer uma interface
 * gráfica que permita a exibição de uma tabela contedo uma avaliação geral da pesquisa nos cursos, esta classe tem por
 * objetivo dar funcionalidade a esta interface gráfica.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class TabelaGeralAvaliacaoCursosController extends Application{

	private static final String NOME_COL_QUESTAO = "questao",
													 NOME_COL_MEDIA = "media",
													 CRITERIO_PARADA = ".0",
													 CONCEITO_GERAL = "Conceito médio Geral";
	private static final double MEDIA_DEFAULT = 0.0;
	
	@FXML private TableColumn<ContabilizarAvaliacaoGeral, String> colAssuntosAvaliados;
	@FXML private TableColumn<ContabilizarAvaliacaoGeral , String> colAvaliacoes;
	@FXML private TableView<ContabilizarAvaliacaoGeral> tabela;
	@FXML public static Label baseDadosAtual;
	
	/**
	 * Método responsável por inicializar a interface gráfica com seus respectivos valores.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void initialize() throws FileNotFoundException, IOException {
		colAssuntosAvaliados.setCellValueFactory(new PropertyValueFactory<>(NOME_COL_QUESTAO));
		colAvaliacoes.setCellValueFactory(new PropertyValueFactory<>(NOME_COL_MEDIA));

		ObservableList<ContabilizarAvaliacaoGeral> dados = FXCollections.observableArrayList(criaListaRelatorioGeral());
		
		tabela.setEditable(true);
		tabela.setItems(dados);
	}//initialize
	
	/**
	 * Cria um relatório geral através de toda a base de dados, o qual fornece uma média geral a respeito dos assuntos avaliados
	 * na pesquisa, levando em consideração todos os cursos avaliadores bem como todos os segmentos.
	 * 
	 * @return List com o conteúdo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private List<ContabilizarAvaliacaoGeral> criaListaRelatorioGeral() throws FileNotFoundException, IOException{
		List<String> questoes = new ArrayList<>();
		List<String> cursos = ManipulacaoArquivo.geraListaGeralCursos();
		List<ContabilizarAvaliacao> avaliacoes = Relatorios.criarBaseDadosRelatorioCurso(cursos);
		List<ContabilizarAvaliacao>avaliacaoGeral = new ArrayList<>();
		
		//Alterar aqui.
		avaliacoes.sort((ContabilizarAvaliacao o1, ContabilizarAvaliacao o2) -> {
				return o1.getQuestao().compareToIgnoreCase(o2.getQuestao());
		});
		
		for(ContabilizarAvaliacao curso: avaliacoes) {
			if(!questoes.contains(curso.getQuestao()))
				questoes.add(curso.getQuestao());
		}
		
		for(String questao : questoes)
			avaliacaoGeral.add(new ContabilizarAvaliacao(questao, Constantes.VAZIO, Constantes.INDEXADOR_ZERO, Constantes.INDEXADOR_ZERO,
					Constantes.INDEXADOR_ZERO, MEDIA_DEFAULT));
		
		for(ContabilizarAvaliacao avaliacao : avaliacoes) {
			for(ContabilizarAvaliacao avGeral : avaliacaoGeral) {
				if(avGeral.getQuestao().equalsIgnoreCase(avaliacao.getQuestao()))
					avGeral.setMediaValor(avGeral.getMediaValor()+avaliacao.getMediaValor());
			}
		}
		
		Double somaGeral = MEDIA_DEFAULT;
		
		List<ContabilizarAvaliacaoGeral> avaliacoesGerais = new ArrayList<>();
		
		//Percorre o array somando os respectivos valores.
		for(ContabilizarAvaliacao avaliacao : avaliacaoGeral) {
			ContabilizarAvaliacaoGeral avg = new ContabilizarAvaliacaoGeral();
			avg.setQuestao(avaliacao.getQuestao());
			avg.setMedia(MetodosGerais.arredondaValor((avaliacao.getMediaValor()/cursos.size())).toString());
			somaGeral += Double.parseDouble(avg.getMedia());
			avaliacoesGerais.add(avg);
		}
		
		//Atualiza a média geral.
		avaliacoesGerais.add(new ContabilizarAvaliacaoGeral(CONCEITO_GERAL,MetodosGerais.
				arredondaValorInt((somaGeral)/questoes.size()).toString().replaceAll(CRITERIO_PARADA, Constantes.VAZIO)));
		
		return avaliacoesGerais;
	}//criaListaRelatorioGeral
	
	@Override
	public void start(Stage stage) throws Exception {
		new CriacaoStage().start(stage, Constantes.CAMINHO_TABELA_GERAL_FXML);
	}//start
	
}//class TabelaGeralAvaliacaoCursosController 
