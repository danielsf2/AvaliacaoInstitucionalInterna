package tsi.too.aii.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tsi.too.aii.acesso.ImportacaoDados;
import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.acesso.MetodosGerais;
import tsi.too.aii.arquivos.ArquivoDescricao;
import tsi.too.aii.interfaceGrafica.Alertas;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe fornece uma tela para que o usuário forneça a descrição de uma pesquisa que será importada externamente.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class TelaPesquisaRegistroController extends Application {

	private static final String MSG_CONFIRMACAO_SOBRRESCREVER = "Já Existe um Arquivo contendo esta Descrição.\nDeseja sobrescrever"
													+ " os dados?",
													MSG_OPERACAO_CANCELADA = "Operação Cancelada",
													TITULO_FILECHOOSER = "Abrir Arquivo",
													NOME_DIRETORIO = "Pesquisas SPA",
													TIPO_ARQUIVOS = "CSV Files",
													EXTENSAO = "*.csv",
													ERRO_REGISTRO = "Nome do Registro não pode Ficar em Branco!";
	
    @FXML private TextField texteAreaDescricao;

    /**
     * Trata o evento de clicar no botão Ok que aparece na interface gráfica, este evento possui o mesmo tratamento de clicar
     * na tecla "enter" do teclado. Dispara os eventos relacionados aos mesmos.
     * 
     * @param event
     * @throws IOException
     * @throws NullPointerException
     * @throws ClassNotFoundException
     */
    @FXML
    void acaoBtnOk(ActionEvent event) throws IOException, NullPointerException, ClassNotFoundException {
		ArquivoDescricao descricao = new ArquivoDescricao();
		String dados = texteAreaDescricao.getText();
		Optional<ButtonType> op = null;//referencia o botao que o usuário poderá clicar
		
		if(dados.trim().isEmpty() == true || dados == null) {
			Alertas.alerta(ERRO_REGISTRO, AlertType.WARNING);
			return;
		}
		
		if(descricao.inserirRegistro(dados) == false) {
			op = Alertas.alerta(MSG_CONFIRMACAO_SOBRRESCREVER, AlertType.CONFIRMATION);//confirma com o usuário

			if(op.get() == ButtonType.CANCEL) {
				Alertas.alerta(MSG_OPERACAO_CANCELADA, AlertType.WARNING);
				return;
			}	
		}
		String nomeArquivo=null;
		try {
			nomeArquivo = new CriacaoStage().start(new Stage(), TITULO_FILECHOOSER, NOME_DIRETORIO, TIPO_ARQUIVOS, EXTENSAO);	
		} catch (NullPointerException e) {
			return;
		}
		fecharTelaPesquisaRegistro(texteAreaDescricao);//fecha a tela de entrada de dados após a importação.
		ManipulacaoArquivo.nomeRegistro = dados;
		new ImportacaoDados().importar(nomeArquivo);
		Alertas.alerta("Dados Importados com Sucesso!", AlertType.INFORMATION);
		MetodosGerais.alterarNomeBaseDados();
    }//acaoBtnOk

    /**
     * Obtem o {@link Stage} relacionado ao {@link TextField} e fecha o mesmo assim que é chamado.
     * 
     * @param texteAreaDescricao
     */
    public void fecharTelaPesquisaRegistro(TextField texteAreaDescricao) {
    	((Stage)texteAreaDescricao.getScene().getWindow()).close();
    }//fecharTelaPesquisaRegistro
    
	@Override
	public void start(Stage stage)throws IOException, NullPointerException, ClassNotFoundException, Exception {
		new CriacaoStage().start(stage, Constantes.CAMINHO_TELA_PESQUISA_REGISTRO);
	}//start

}//TelaPesquisaRegistroController
