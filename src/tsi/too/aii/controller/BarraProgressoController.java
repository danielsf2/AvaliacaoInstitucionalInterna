package tsi.too.aii.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Classe controladora do arquivo Fxml, BarraProgressoController.fxml, que foi responsável por gerar um recurso visual com uma
 * barra de progresso indeterminada, enquanto o programa carrega seus recursos, esta classe fornece implementação aos recursos
 * visuais do arquivo {@link FXMLLoader} referenciado.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class BarraProgressoController extends Application {
	
	@FXML private ProgressIndicator indicadorIndeterminado;
	private Stage stage;
	
	/**
	 * Inicializa a parte visual da classe.
	 * @throws IOException 
	 */
	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		new CriacaoStage().start(stage, Constantes.CAMINHO_BARRA_PROGRESSO_FXML, Modality.APPLICATION_MODAL);
	}//start
	
	/**
	 * Fecha a tela aberta pela classe.
	 */
	public void fechar() {
		this.stage.close();
	}

}//class BarraProgressoController 
