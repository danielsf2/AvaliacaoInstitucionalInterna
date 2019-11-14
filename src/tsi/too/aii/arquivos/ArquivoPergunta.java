package tsi.too.aii.arquivos;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.mos.arquivo.aleatorio.BinaryFile;
import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.classes.AvaliacaoPergunta;
import tsi.too.aii.classes.Pergunta;
import tsi.too.aii.classes.TamanhoRegistro;
import tsi.too.aii.interfaces.Constantes;

/**
 * Está classe extende {@link BinaryFile} e tem por objetivo fornecer métodos responsáveis por gerenciar a manipulação de 
 * arquivos no programa.
 * 
 * @author Daniel Soares Ferreira
 *	@version 1.0
 */
public class ArquivoPergunta extends BinaryFile {
	
	private static final int TAM_STRING_CLASSE_GERAL = 80,
												TAM_STRING_SUB_CLASSE = 80;
	
	private ArquivoAvaliacaoPergunta arqAvaliacaoPergunta; 

	public ArquivoPergunta() throws FileNotFoundException {
		super();
		arqAvaliacaoPergunta = new ArquivoAvaliacaoPergunta();
		arqAvaliacaoPergunta.openFile("arquivos/Avaliacoes/ArquivoDeAvaliacoes-"+ManipulacaoArquivo.nomeRegistro+".dat");
	}

	/**
	 * Lê um objeto do arquivo faz o cast para o objeto da classe e retorna o mesmo.
	 * @version 1.0
	 */
	@Override
	public Object readObject() throws IOException {
		Pergunta pergunta = new Pergunta();
		
		pergunta.setIdPergunta(randomAccessFile.readInt());
		pergunta.setClasseGeral(readString(TAM_STRING_CLASSE_GERAL));
		pergunta.setSubclasse(readString(TAM_STRING_SUB_CLASSE));
		
		TamanhoRegistro tamanhoRegistro = new TamanhoRegistro();
		try {
			tamanhoRegistro = ArquivoTamanhoRegistro.obteRegistro();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ManipulacaoArquivo.recordSizeAvaliacao = tamanhoRegistro.getTamanho();
		
		arqAvaliacaoPergunta.setFilePointer(pergunta.getIdPergunta());
		pergunta.setAvaliacaoPergunta((AvaliacaoPergunta)arqAvaliacaoPergunta.readObject());
		
		return pergunta;
	}//readObject

	/**
	 * Define o tamanho do objeto a ser persistido em disco.
	 * @version 1.0
	 */
	@Override
	public int recordSize() {
		return (((TAM_STRING_CLASSE_GERAL+TAM_STRING_CLASSE_GERAL)*Constantes.TAM_UNICODE)+Integer.BYTES);
	}

	@Override
	public void writeObject(Object obj) throws IOException {
		
		Pergunta pergunta = (Pergunta)obj;
		
		randomAccessFile.writeInt(pergunta.getIdPergunta());
		randomAccessFile.writeChars(setStringLength(pergunta.getClasseGeral(), TAM_STRING_CLASSE_GERAL));
		randomAccessFile.writeChars(setStringLength(pergunta.getSubclasse(), TAM_STRING_SUB_CLASSE));
		
	}//writeObject
	
}//ArquivoDadosAvaliados 
