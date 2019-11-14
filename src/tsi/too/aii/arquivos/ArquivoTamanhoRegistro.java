package tsi.too.aii.arquivos;

import java.io.FileNotFoundException;
import java.io.IOException;

import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.classes.TamanhoRegistro;

/**
 * Esta classe ter por objetivo fornecer métodos que possibilitem a gravação e recuperação de arquivos serializaveis
 * para o tipo de objeto {@link TamanhoRegistro}.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class ArquivoTamanhoRegistro {
	
	private static final String CAMINHO_RECORD_SIZE  = "arquivos/Registro de Tamanho/RecordSize-",
										 			 EXTENSAO_SER = ".ser";

	public static void gravarRegistro(TamanhoRegistro tamRegistro) throws FileNotFoundException, IOException {
		
		ArquivoObjeto arquivoObjeto = new ArquivoObjeto();
		arquivoObjeto.criarArquivo(CAMINHO_RECORD_SIZE+ManipulacaoArquivo.nomeRegistro+EXTENSAO_SER);
		arquivoObjeto.abrirArquivo(CAMINHO_RECORD_SIZE+ManipulacaoArquivo.nomeRegistro+EXTENSAO_SER);
		arquivoObjeto.escreverArquivo(tamRegistro);
		arquivoObjeto.fecharArquivo();
		
	}//gravarRegistro
	
	public static TamanhoRegistro obteRegistro() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		TamanhoRegistro tamanhoRegistro = new TamanhoRegistro();
		
		ArquivoObjeto arquivoObjeto = new ArquivoObjeto();
		arquivoObjeto.abrirArquivo(CAMINHO_RECORD_SIZE+ManipulacaoArquivo.nomeRegistro+EXTENSAO_SER);
		tamanhoRegistro = (TamanhoRegistro)arquivoObjeto.lerArquivo();
		
		return tamanhoRegistro;
	}//TamanhoRegistro 
	
}//class ArquivoTamanhoRegistro 
