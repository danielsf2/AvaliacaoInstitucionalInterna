package tsi.too.aii.arquivos;

import java.io.IOException;

import br.mos.arquivo.aleatorio.BinaryFile;
import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.classes.Avaliacao;
import tsi.too.aii.classes.AvaliacaoPergunta;
import tsi.too.aii.classes.TamanhoRegistro;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por objetivo fornecer métodos de escrita e acesso a um arquivo de acesso aleatório, resposável por tratar
 * objetos do tipo {@link AvaliacaoPergunta}.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class ArquivoAvaliacaoPergunta extends BinaryFile{
	
	private final int TAM_STRING_TIPO_PARTICIPANTE = 10,
									TAM_STRING_NOME_CAMPUS = 20,
									TAM_STRING_NOME_CURSO = 40,
									TAM_INT_ID_PERGUNTA = 4; 
	
	@Override
	public Object readObject() throws IOException{

		AvaliacaoPergunta avaliacaoPergunta = new AvaliacaoPergunta();
		avaliacaoPergunta.setIdPergunta(randomAccessFile.readInt());
		
		for (int pos = 0; pos < ManipulacaoArquivo.recordSizeAvaliacao; pos++) {
			
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setTipoParticipante(readString(TAM_STRING_TIPO_PARTICIPANTE));
			avaliacao.setNomeCampus(readString(TAM_STRING_NOME_CAMPUS));
			avaliacao.setNomeCurso(readString(TAM_STRING_NOME_CURSO));
			avaliacao.setPesoNota(randomAccessFile.readInt());
			
			avaliacaoPergunta.adicionarAvaliacao(avaliacao);
		}
		return avaliacaoPergunta;
	}//readObject

	@Override
	public int recordSize() {
		return TAM_INT_ID_PERGUNTA+((((TAM_STRING_NOME_CAMPUS+TAM_STRING_NOME_CURSO+
					  TAM_STRING_TIPO_PARTICIPANTE)*Constantes.TAM_UNICODE) + Integer.BYTES)* ManipulacaoArquivo.recordSizeAvaliacao);
	}//recordSize

	@Override
	public void writeObject(Object arg0) throws IOException {
		
		AvaliacaoPergunta avaliacaoPergunta = (AvaliacaoPergunta)arg0;
		randomAccessFile.writeInt(avaliacaoPergunta.getIdPergunta());

		ManipulacaoArquivo.recordSizeAvaliacao = avaliacaoPergunta.obterAvaliacoes().size();
		
		TamanhoRegistro tamRegistro = new TamanhoRegistro();
		tamRegistro.setTamanho(avaliacaoPergunta.obterAvaliacoes().size());
		tamRegistro.setNomeArquivo(randomAccessFile.getFD().toString());
		
		ArquivoTamanhoRegistro.gravarRegistro(tamRegistro);
		
		for (Avaliacao avaliacao : avaliacaoPergunta.obterAvaliacoes()) {
		
			randomAccessFile.writeChars(setStringLength(avaliacao.getTipoParticipante(),TAM_STRING_TIPO_PARTICIPANTE));
			randomAccessFile.writeChars(setStringLength(avaliacao.getNomeCampus(), TAM_STRING_NOME_CAMPUS));
			randomAccessFile.writeChars(setStringLength(avaliacao.getNomeCurso(), TAM_STRING_NOME_CURSO));
			randomAccessFile.writeInt(avaliacao.getPesoNota());
		}
	}//writeObject
	
}//class ArquivoAvaliacaoPergunta 
