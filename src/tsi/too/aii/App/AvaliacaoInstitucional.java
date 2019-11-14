package tsi.too.aii.App;
	
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tsi.too.aii.interfaceGrafica.Alertas;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Classe principal da aplicação. Esta classe inicializa a aplicação.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class AvaliacaoInstitucional extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			new CriacaoStage().start(primaryStage);
		} catch(Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
	}//start
	
	/**
	 * Inicializa a aplicação na classe principal.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		System.exit(0);//programas com interface gráfica precisam disso para finalizar
	}
}//class AvaliacaoInstitucional 
