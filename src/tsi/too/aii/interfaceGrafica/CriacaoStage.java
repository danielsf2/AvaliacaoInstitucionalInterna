package tsi.too.aii.interfaceGrafica;

import java.io.IOException;

import org.jfree.chart.fx.ChartViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por objetivos fornecer métodos que possibilitem a criação de {@link Stage} ou palcos que serão inicializados
 * na execução de funções do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class CriacaoStage extends Application {
	
	private static final String NOME_BASE_DADOS_DEFAULT = "Base de Dados não Carregada.",
													ID_NOME_BASE_DADOS ="idLabelNomeBaseDados" ;

	public static Label labelNomeBaseDados;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource(Constantes.CAMINHO_TELA_PROGRAMA));
		labelNomeBaseDados = new Label(NOME_BASE_DADOS_DEFAULT);
		labelNomeBaseDados.setId(ID_NOME_BASE_DADOS);
		pane.getChildren().addAll(labelNomeBaseDados);
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource(Constantes.CAMINHO_ARQUIVO_CSS_GERAIS).toExternalForm());
		primaryStage.setTitle(Constantes.TITULO_PROGRAMA);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(Constantes.IMAGEM_PROGRAMA)));
		primaryStage.show();
	}//start
	
	
	/**
	 * Cria um palco para a incializacao de uma caixa de dialogo {@link FileChooser}, a qual é responsável por fornecer ao usuário
	 * uma interface gráfica que o permita escolher um arquivo para carregamento ou gravação no programa.
	 * 
	 * @param stage
	 * @param tituloFileChooser
	 * @param nomeDiretorio
	 * @param tipoArquivos
	 * @param extensao
	 * @return String com o caminho do arquivo selecionado
	 */
	public String start(Stage stage, String tituloFileChooser, String nomeDiretorio, String tipoArquivos, String extensao) {
		AnchorPane root =  new AnchorPane();
		Scene scene = new Scene(root);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		String nomeArquivo = JanelaFileChooser.abrirArquivo(root.getScene().getWindow(), tituloFileChooser, nomeDiretorio,
				tipoArquivos,extensao);
		return nomeArquivo;
	}//start
	
	/**
	 * Cria um palco para a incializacao de uma caixa de dialogo {@link FileChooser}, a qual é responsável por fornecer ao usuário
	 * uma interface gráfica que o permita escolher um arquivo para carregamento ou gravação no programa.
	 * 
	 * @param stage
	 * @param tituloFileChooser
	 * @return String com o caminho do arquivo selecionado
	 */
	public String startArmazenar(Stage stage, String tituloFileChooser) {
		AnchorPane root =  new AnchorPane();
		Scene scene = new Scene(root);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		String nomeArquivo = JanelaFileChooser.armazenarArquivo(root.getScene().getWindow(), tituloFileChooser);
		return nomeArquivo;
	}//start
	
	/**
	 * Cria um {@link Stage} o qual é responsável por cria um palco para a aplicação é uma versão sobrecarregada do método
	 * original possuindo algumas funcionalidades diferentes a principal é que o método show usando, não impede que o 
	 * programa prossiga com sua execução.
	 * 
	 * @param stage
	 * @param caminhoFxml
	 * @param modality
	 * @throws IOException
	 */
	public void start(Stage stage, String caminhoFxml, Modality modality) throws IOException {
		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource(caminhoFxml));
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource(Constantes.CAMINHO_ARQUIVO_CSS).toExternalForm());
		stage.setTitle(Constantes.TITULO_PROGRAMA);
		stage.setResizable(false);
		stage.initModality(modality);
		stage.setScene(scene);
		stage.getIcons().add(new Image(getClass().getResourceAsStream(Constantes.IMAGEM_PROGRAMA)));
		stage.show();
	}//void start
	
	/**
	 * Versão sobrecarregada do método original fornecendo implementações adicionais que possibilitem a passagem de 
	 * parâmetro de um arquivo {@link FXMLLoader}.
	 * 
	 * @param stage
	 * @param caminhoFxml
	 * @throws IOException
	 */
	public void start(Stage stage, String caminhoFxml) throws IOException {
		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource(caminhoFxml));
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource(Constantes.CAMINHO_ARQUIVO_CSS).toExternalForm());
		stage.setTitle(Constantes.TITULO_PROGRAMA);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.getIcons().add(new Image(getClass().getResourceAsStream(Constantes.IMAGEM_PROGRAMA)));
		stage.showAndWait();
	}//void start
	
	/**
	 * Versão sobrecarregada do método original, tendo como finalidade fornecer um palco para a inicialização de gráficos
	 * recebendo como parametro um {@link Chart} o qual será exibido conforme sua funcionalidade.
	 * 
	 * @param stage
	 * @param grafico
	 * @param tamHor
	 * @param tamVer
	 */
	public void start(Stage stage, ChartViewer grafico, int tamHor, int tamVer) {
		stage.setScene(new Scene(grafico));
		stage.setTitle(Constantes.TITULO_PROGRAMA);
		stage.setWidth(tamHor);
		stage.setHeight(tamVer);
		stage.getIcons().add(new Image(getClass().getResourceAsStream(Constantes.IMAGEM_PROGRAMA)));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}//void start

}//class CriacaoStage 
