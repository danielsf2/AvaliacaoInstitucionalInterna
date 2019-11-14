package tsi.too.aii.interfaceGrafica;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * Esta classe tem por objetivo facilitar a criação de uma janela para abertura de arquivos do tipo {@link FileChooser}.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class JanelaFileChooser {
	public static final String PASTA_PROGRAMA = "arquivos",
												   SEPARADOR = ".",
												   PASTA_PDF = "PDFs",
												   EXTENSAO_PDF = "*.pdf",
												   PDF_FILE = "PDF files";

	/**
	 * Recebe como parametro um {@link Window} que servirá de referência para o posicionamento da janela e uma {@link String}
	 * com o título da mesma, retorna uma {@link String} com o caminho do arquivo selecionado ao clicar.
	 * 
	 * @param Window janelaPai
	 * @param String titulo
	 * @return String caminho do arquivo
	 * @version 1.0
	 */
	public static String abrirArquivo(Window janelaPai, String titulo, String diretorioInicial, String tipoFile, String files) throws NullPointerException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(titulo);
		
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter(tipoFile, files));
		fileChooser.setInitialDirectory(new File(SEPARADOR + File.separator +PASTA_PROGRAMA+File.separator+ diretorioInicial));
		
		String pasta = fileChooser.showOpenDialog(janelaPai).getPath();
		
		return pasta;
	}//abrirArquivo
	
	/**
	 * Abre uma janela do tipo {@link FileChooser}, o qual fornecerá ao usuário do programa um local default onde o mesmo
	 * poderá salvar seu arquivo PDF.
	 * 
	 * @param janelaPai
	 * @param titulo
	 * @return String com o caminho do arquivo
	 */
	public static String armazenarArquivo(Window janelaPai, String titulo) {
		  FileChooser fileChooser = new FileChooser();
		    
		  // Define qual é o diretório default de gravação.
		  fileChooser.getExtensionFilters().addAll(new ExtensionFilter(PDF_FILE, EXTENSAO_PDF));
		  fileChooser.setInitialDirectory(new File(SEPARADOR + File.separator + PASTA_PROGRAMA + File.separator + PASTA_PDF));
		  
		  // Exibe o diálogo.
		  File opcao = fileChooser.showSaveDialog(janelaPai);
		
		  return opcao.toString();
		}//armazenarArquivo 
	
}//class JanelaFileChooser 
