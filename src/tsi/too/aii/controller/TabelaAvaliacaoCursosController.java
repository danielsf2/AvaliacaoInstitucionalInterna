package tsi.too.aii.controller;

import static tsi.too.aii.interfaces.Constantes.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jfree.chart.plot.PlotOrientation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tsi.too.aii.classes.Curso;
import tsi.too.aii.interfaceGrafica.Alertas;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaceGrafica.GraficoBarra;
import tsi.too.aii.interfaces.Constantes;
import tsi.too.aii.relatorios.Datasets;
import tsi.too.aii.relatorios.Relatorios;

/**
 * Classe controler de um {@link FXML} TabelaAvaliacaoCursos, o qual é responsável por gerar graficamente uma tabela com 
 * valores de cursos e seus respectivos votos em relação a pesquisa objeto deste trabalho. Essa classe fornece implementação
 * a essa parte visual.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class TabelaAvaliacaoCursosController extends Application {
	
	private static final String NOME_COL_CURSO = "nomeCurso",
													 NOME_COL_QUANTIDADE_VOTOS = "quantidadeVotos";

	@FXML private TableColumn<Curso, Integer> colAvaliacoes;
	@FXML private TableView<Curso> tabela;
	@FXML private TableColumn<Curso, String> colCursos;
	@FXML public Label baseDadosAtual;
	
	/**
	 * Preeche a tabela gerada pelo arquivo {@link FXML} com os respectivos valores.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@FXML
	public void initialize() throws FileNotFoundException, IOException {
		colCursos.setCellValueFactory(new PropertyValueFactory<>(NOME_COL_CURSO));
		colAvaliacoes.setCellValueFactory(new PropertyValueFactory<>(NOME_COL_QUANTIDADE_VOTOS));
		
		ObservableList<Curso> cursos = FXCollections.observableArrayList(Relatorios.criarBaseDadosRelatorioParticipante());
		tabela.autosize();
		tabela.setItems(cursos);
	}//initialize
	
	/**
	 * Funcionalidade do {@link Button} Gerar Grafico, executa sua funcionalidade chamando seus respectivos métodos.
	 * 
	 * @param event
	 * @exception, {@link FileNotFoundException}, {@link IOException}, {@link NullPointerException}, {@link Exception}
	 */
	@FXML
    void acaoBotaoExibirGrafico(ActionEvent event) {
	  try {
			new GraficoBarra().criarGrafico(Datasets.gerarDatasetGraficoBarra(), PlotOrientation.HORIZONTAL,
					TITULO_GRAFICO_BARRA , DIM_GRAFICO_BARRA[INDEXADOR_ZERO], DIM_GRAFICO_BARRA[INDEXADOR_UM]);
		} catch (FileNotFoundException e) {
			Alertas.alerta(MSG_ERRO_BASE_DADOS, AlertType.ERROR);//Erro ao importar.
		}catch(NullPointerException e) {
			Alertas.alerta(MSG_ERRO_INICIALIZAR_GRAFICO_VALORES, AlertType.ERROR);//Erro no initialize.
		}catch (IOException e) {
			Alertas.alerta(MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);//Erro no arquivo.
		}catch (Exception e) {
			Alertas.alerta(MSG_ERRO_GENERICO_AO_INICIALIZAR_GRAFICO, AlertType.ERROR);//Alguma exceção não verificada.
		}
    }//acaoBotaoExibirGrafico
	
	@Override
	public void start(Stage stage) throws Exception {//Tabela de Avaliação por Curso
		new CriacaoStage().start(stage, Constantes.CAMINHO_TABELA_AVALIACAO_CURSO_FXML);
	}//start

}//TabelaAvaliacaoCursosController 
