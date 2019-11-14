package tsi.too.aii.arquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import tsi.too.aii.interfaces.Constantes;

/**
 * Está Classe tem por objetivo fornecer métodos que possibilitem a leitura de um arquivo em disco.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class ArquivoTexto {

	private BufferedReader inputBuffer;
	private FileInputStream fileInputStream;
	private Scanner scanner;
	
	/**
	 * Abre um arquivo texto para leitura de dados, caso o arquivo nao exista será criado.
	 * 
	 * @param String caminhoArquivo
	 * @exception FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	public void abrir(String caminhoArquivo) throws FileNotFoundException, UnsupportedEncodingException {
		
		fileInputStream = new FileInputStream(caminhoArquivo);
		//inputBuffer = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
		scanner = new Scanner(fileInputStream);
		
	}//abrir
	
	/**
	 * 
	 * Lê o conteúdo do arquivo enquanto houver uma nova linha. Armazena o conteúdo em um objeto da classe
	 * StringBuilder e retorna um objeto String com o conteúdo do Arquivo.
	 * 
	 * @return String com o conteúdo do Arquivo.
	 * @throws IOException 
	 */
	public String ler() throws IOException {
		StringBuilder conteudo = new StringBuilder();
		while(scanner.hasNextLine()) {
			conteudo.append(scanner.hasNextLine());
			conteudo.append(Constantes.SEPARADOR_LINHAS);
		}
		return conteudo.toString();
	}//ler
	
	/**
	 * Fecha os arquivos de entrada de dados. Caso os arquivos a serem fechados não estejam abertos, dispara
	 * NullPointerException.
	 * 
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public void fechar() throws NullPointerException, IOException {
		fileInputStream.close();
		inputBuffer.close();
		//scanner.close();
	}//fechar
	
}//class ArquivoTexto 
