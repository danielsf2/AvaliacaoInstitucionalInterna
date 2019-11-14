package tsi.too.aii.arquivos;

import java.io.*;

/**
 * Classe responsável por implementar métodos que permitam a gravação(serialização) e a recuperação(desserialização) de 
 * objetos.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class ArquivoObjeto {
	private ObjectInputStream objectInputStream; // Arquivo para desserialização de objetos.
	private ObjectOutputStream objectOutputStream; // Arquivo para serialização de objetos.
	
	/** 
	 * Abre um arquivo binário. O objeto ObjectInputStream é usado para fazermos a desserialização 
	 * do objeto em disco. O objeto FileInputStream é usado para lermos os bytes do objeto 
	 * armazenado em arquivo. O arquivo aberto é usado somente para leitura. 
	 */
	public void abrirArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
		/*
		 * Este método usa o encadeamento de objetos de fluxos de entrada de dados para criar um 
		 * arquivo em disco e usá-lo para ler os bytes de um objeto armazenado no arquivo.  
		 */
		objectInputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
	} 

	/**
	 * Cria um arquivo binário em disco. O objeto ObjectOutputStream é usado para fazermos a 
	 * serialização do objeto em disco. O objeto FileOutputStream é usado para escrevermos os 
	 * bytes do objeto em um arquivo em disco.  O arquivo criado é usado somente para escrita.
	 */
	public void criarArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
		/*
		 * Este método usa o encadeamento de objetos de fluxos de saída de dados para criar um 
		 * arquivo em disco e usá-lo para escrever os bytes de um objeto no arquivo.  
		 */
		objectOutputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
	} 

	/**
	 * Escreve o objeto em disco.
	 */
	public void escreverArquivo(Object objeto) throws IOException {
		objectOutputStream.writeObject(objeto);
	} 

	/**
	 * Lê (recupera) e retorna um objeto do arquivo. Quando não existir mais objetos e o fim de arquivo for 
	 * alcançado retorna null.
	 */ 
	public Object lerArquivo() throws IOException, ClassNotFoundException 
	{ 
		try { return objectInputStream.readObject();
		} catch (EOFException e) { 
			// Trata a exceção de fim de arquivo (EOFException).
			return null;
		}
	} 

	public void fecharArquivo() throws IOException {
		if (objectInputStream != null) objectInputStream.close();
		if (objectOutputStream != null) objectOutputStream.close();
	} 
} // classe ArquivoObjeto
