package tsi.too.aii.interfaceGrafica;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Está classe tem por objetivo fornecer um {@link Alert} ao usuário do programa para acompanhar o funcionamento do mesmo.
 * 
 * @author Daniel Soares Ferreira
 *	@version 1.0
 */
public class Alertas {

	/**
	 * Exibe um alerta do tipo <code>Alert.AlertType</code>.
	 * 
	 * @param mensagem
	 */
	public static Optional<ButtonType> alerta(String mensagem, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(Constantes.TITULO_PROGRAMA);
		alert.setHeaderText(mensagem);
		alert.initModality(Modality.APPLICATION_MODAL);
		((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(Constantes.IMAGEM_PROGRAMA));
		return alert.showAndWait();
	}//alertError
	
}//class Alertas 
