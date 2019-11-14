package tsi.too.aii.arquivos;

import java.io.FileNotFoundException;
import java.io.IOException;

import tsi.too.aii.classes.RegistroDeGravacao;

/**
 * Esta classe tem por objetivo fornecer métodos que forneçam implementações que possibilitem gravação e recuperação
 * de objetos serializaveis do tipo {@link RegistroDeGravacao}.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class ArquivoDescricao {

	private static final String arquivoDescricao = "arquivos/Registro de Descricao/RegistroDescricao.ser";
	private ArquivoObjeto arquivoObjeto = new ArquivoObjeto();
	
	/**
	 * Construtor default padrão da classe.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArquivoDescricao() throws FileNotFoundException, IOException{
		super();
	}
	
	/**
	 * Obtem o registro salvo no arquivo.
	 * 
	 * @return RegistroDeGravacao
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private RegistroDeGravacao obterRegistros() throws FileNotFoundException, IOException, ClassNotFoundException {
		try {
			arquivoObjeto.abrirArquivo(arquivoDescricao);
		} catch (FileNotFoundException e) {//caso ocorra a exeção fileNotFound será criado um arquivo
			arquivoObjeto.criarArquivo(arquivoDescricao);
			arquivoObjeto.abrirArquivo(arquivoDescricao);
		}
		return (RegistroDeGravacao)arquivoObjeto.lerArquivo();
	}//obterRegistros
	
	/**
	 * Insere um arquivo no registro retorna true em caso de sucesso caso contrário retorna false.
	 * 
	 * @param descricao
	 * @return boolean
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean inserirRegistro(String descricao) throws FileNotFoundException, ClassNotFoundException, IOException {
		RegistroDeGravacao descricoes = obterRegistros();
		
		if (descricoes == null) 
			descricoes = new RegistroDeGravacao();
	
		if(descricoes.inserirDescricao(descricao)==true) {
			arquivoObjeto.criarArquivo(arquivoDescricao);
			arquivoObjeto.escreverArquivo(descricoes);
			arquivoObjeto.fecharArquivo();
			return true;
		}
		else
			return false;
	}//inserirRegistro
	
}//class ArquivoDescricao 
