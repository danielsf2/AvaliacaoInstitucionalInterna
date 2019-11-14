package tsi.too.aii.controller;

import static tsi.too.aii.interfaces.Constantes.DIM_GRAFICO_COLUNA;
import static tsi.too.aii.interfaces.Constantes.MSG_ERRO_BASE_DADOS;
import static tsi.too.aii.interfaces.Constantes.MSG_ERRO_GENERICO_AO_INICIALIZAR_GRAFICO;
import static tsi.too.aii.interfaces.Constantes.MSG_ERRO_INICIALIZAR_GRAFICO_VALORES;
import static tsi.too.aii.interfaces.Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS;
import static tsi.too.aii.interfaces.Constantes.TITULO_GRAFICO_COLUNA;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jfree.chart.plot.PlotOrientation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tsi.too.aii.classes.Segmentos;
import tsi.too.aii.interfaceGrafica.Alertas;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaceGrafica.GraficoColuna;
import tsi.too.aii.interfaces.Constantes;
import tsi.too.aii.relatorios.Datasets;
import tsi.too.aii.relatorios.Relatorios;

/**
 *  Classe controler de um {@link FXML} TabelaAvaliacaoSegmento, o qual é responsável por gerar graficamente uma tabela com 
 *  valores de segmentos e seus respectivos votos em relação a pesquisa objeto deste trabalho. Essa classe fornece implementação
 *  a essa parte visual.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class TabelaAvaliacaoSegmentoController extends Application{
	private static final String NOME_DISCENTE = "discente",
													NOME_DOCENTE = "docente",
													NOME_TECNICO = "tecnicoAdministrativo";
	
	@FXML private TableView<Segmentos> tabelaSegmento;
	@FXML private TableColumn<Segmentos, Integer> colDocente;
	@FXML private TableColumn<Segmentos, Integer> colDiscente;
	@FXML private TableColumn<Segmentos, Integer> colTecAdministrativo;
	@FXML public static Label baseDadosAtual;
	
	/**
	 * Preeche a tabela gerada pelo arquivo {@link FXML} com os respectivos valores.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void initialize() throws FileNotFoundException, IOException {
		colDiscente.setCellValueFactory(new PropertyValueFactory<>(NOME_DISCENTE));
		colDocente.setCellValueFactory(new PropertyValueFactory<>(NOME_DOCENTE));
		colTecAdministrativo.setCellValueFactory(new PropertyValueFactory<>(NOME_TECNICO));
		ObservableList<Segmentos> dados = FXCollections.observableArrayList(Relatorios.criaBaseDadosRelatorioSegmento());
		tabelaSegmento.setItems(dados);
	}

	/**
	 * Funcionalidade do {@link Button} Gerar Grafico, executa sua funcionalidade chamando seus respectivos métodos.
	 * 
	 * @param event
	 * @exception, {@link FileNotFoundException}, {@link IOException}, {@link NullPointerException}, {@link Exception}
	 */
    @FXML
    void acaoBotaoExibirGrafico(ActionEvent event) {
    	try {
    		new GraficoColuna().criarGrafico(Datasets.criaDatasetGraficoColuna(), PlotOrientation.VERTICAL,TITULO_GRAFICO_COLUNA , 
    				DIM_GRAFICO_COLUNA[Constantes.INDEXADOR_ZERO], DIM_GRAFICO_COLUNA[Constantes.INDEXADOR_UM]);
		} catch (FileNotFoundException e) {
			Alertas.alerta(MSG_ERRO_BASE_DADOS, AlertType.ERROR);//Erro ao importar.
		} catch (IOException e) {
			Alertas.alerta(MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);//Erro no arquivo.
		} catch (NullPointerException e) {
			Alertas.alerta(MSG_ERRO_INICIALIZAR_GRAFICO_VALORES, AlertType.ERROR);//Erro no initialize.
		} catch (Exception e) {
    		Alertas.alerta(MSG_ERRO_GENERICO_AO_INICIALIZAR_GRAFICO, AlertType.ERROR);//Alguma exceção não verificada.
		}
    }//acaoBotaoExibirGrafico

	@Override
	public void start(Stage stage) throws Exception {
		new CriacaoStage().start(stage, Constantes.CAMINHO_TABELA_AVALIACAO_SEGMENTO_FXML);
	}//start
	
}//TabelaCursoBacharelController 
