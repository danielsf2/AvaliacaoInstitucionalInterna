package tsi.too.aii.acesso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tsi.too.aii.arquivos.ArquivoPergunta;
import tsi.too.aii.arquivos.ArquivoTexto;
import tsi.too.aii.classes.Avaliacao;
import tsi.too.aii.classes.AvaliacaoPergunta;
import tsi.too.aii.classes.ContabilizarAvaliacao;
import tsi.too.aii.classes.Pergunta;

import static tsi.too.aii.interfaces.Constantes.*;

/**
 * Está classe tem por objetivos fornecer métodos que possibilitem a importação de um arquivo.csv de uma base de dados externa
 * a referida classe tem como único método público o <code>importar</code> o qual fará uso de métodos internos a classe para
 * realizar sua atribuição.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class ImportacaoDados {

	static List<Pergunta> bufferDados = null;
	
	/**
	 * 
	 * O único método visível externamente a classe, recebera um caminho de arquivo, fara uso internamente dos métodos da classe
	 * e retornará um {@link List} do tipo {@link Pergunta} com todas as suas avaliações bem como suas respectivas notas e pesos.
	 * 
	 * @author Daniel Soares Ferreira
	 * 
	 * @param String caminhoArquivo
	 * 
	 * @return List
	 * 
	 * @throws FileNotFoundException
	 * @throws NullPointerException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 * @version 1.0
	 */
	public List<Pergunta> importar(String caminhoArquivo) throws FileNotFoundException, NullPointerException, IOException, ClassNotFoundException {
		List<Pergunta> dados = gerarListaDados(lerArquivo(caminhoArquivo));
		gerarConteudoAvaliacoes(dados, lerArquivo(caminhoArquivo));
		MetodosGerais.gerarIndicesArquivos(dados);
		gerarArquivoAcessoAleatorio(dados);
		bufferDados = dados;
		new ArquivoPergunta().closeFile();
		return dados;
	}//importar
	
	/**
	 * Recebe como parâmetro um {@link List} de objetos do tipo {@link Pergunta}, faz uso de métodos da classe {@link ManipulacaoArquivo}
	 * para refinar essa lista e criar um Arquivo de Acesso Aleatório.
	 * 
	 * @param Uma Lista de dadosAvaliados
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @version 1.0
	 */
	private void gerarArquivoAcessoAleatorio(List<Pergunta> dadosAvaliados) throws FileNotFoundException, IOException {
		ManipulacaoArquivo arquivo = new ManipulacaoArquivo();
		arquivo.gerarArquivoAleatorio(dadosAvaliados);
	}//gerarArquivoAcessoAleatorio

	/**
	 * 
	 * Faz uso da {@link List} gerada pelo metodo gerarListaDados o qual retorna uma {@link List} com todos os objetos do arquivos, sendo
	 * estes os campos objetos da pesquisa, adiciona a estes objetos {@link ContabilizarAvaliacao} com um List do tipo Avaliacao, os quais contêm todas
	 * as avaliacões para a referida questão, lê quantas avaliações o programa suportar, aceitando novas avaliações no arquivo texto
	 * trazendo escalabilidade ao programa. Retorna esta lista modifcada.
	 * 
	 * @param Um List do tipo DadosAvaliados
	 * @param String conteudoArquivo
	 * @version 1.0
	 */
	private void gerarConteudoAvaliacoes(List<Pergunta> listaDados, String conteudoArquivo) {
		String linhas[] = conteudoArquivo.split(SEPARADOR_LINHAS);
		
		int posAvaliacao = INDEXADOR_ZERO;
		
		for(int posAv = INDEXADOR_ZERO; posAv < listaDados.size(); posAv++) {
			AvaliacaoPergunta avaliacaoPergunta = new AvaliacaoPergunta();

			for(int pos = INDEXADOR_UM; pos < linhas.length; pos++) {
				String[] dados = linhas[pos].split(SEPARADOR_CAMPOS);
			
				Avaliacao avaliacao = new Avaliacao();
				
				avaliacao.setTipoParticipante(dados[INDEXADOR_ZERO]);
				avaliacao.setNomeCampus(dados[INDEXADOR_UM]);
				
				if(!dados[INDEXADOR_DOIS].equalsIgnoreCase(CAMPO_DISCENTE))
					avaliacao.setNomeCurso(dados[INDEXADOR_DOIS]);
				else
					avaliacao.setNomeCurso(VAZIO);
				
				avaliacao.setNota(dados[posAvaliacao + INDEXADOR]);
				avaliacao.setPesoNota(verificaPeso(dados[posAvaliacao + INDEXADOR]));
				
				avaliacaoPergunta.adicionarAvaliacao(avaliacao);
			}
			listaDados.get(posAv).setAvaliacaoPergunta(avaliacaoPergunta);
			posAvaliacao++;
		}
	}//gerarConteudoAvaliacoes
	
	/** 
	 * Recebe como parâmetro o cabeçalho do arquivo , fazendo uso do mesmo cria objetos referentes a cada campo do 
	 * cabeçalho, poderão ser passados N campos, fazendo desta um solução com boa escalabilidade, sendo adicionado um
	 * novo campo ao arquivo, sera criado um novo objeto referente, desde que o novo campo respeite a estrutua anterior, sem
	 * que haja necessidade de alterações no código.
	 * 
	 * @param String conteudo, com o conteudo da linha de cabeçalho.
	 * @return List
	 * @version 1.0
	 */
	private List<Pergunta> gerarListaDados(String conteudo) {
		String[] linha = conteudo.split(SEPARADOR_LINHAS);
	
		List<Pergunta> perguntas = new ArrayList<>();
		List<String> verificador = new ArrayList<>();

		String campo[] = linha[INDEXADOR_ZERO].split(SEPARADOR_CAMPOS);
	
		for (String dado : campo) {
			
			if(!verificador.contains(dado.trim())) {
				verificador.add(dado);
				
				if(dado.isEmpty() == false) {
					Pergunta pergunta = new Pergunta();
			
					dado = dado.replace(SEPARADOR_INDICE_TEMP, SEPARADOR_INDICE);
					
					pergunta = converteDadosPrimeiraLinha(dado);
					perguntas.add(pergunta);
				}
			}
		}
		return perguntas;
	}//gerarListaDados
	
	/**
	 * Recebe a nota da avaliação como parâmetro sendo, ótimo, bom, satisfatório, ruim ou péssimo, não avaliado ou
	 * inexistente são tratados como zero, trata esses valores e atribui pesos , retornando o referido peso respectivo.
	 * 
	 * @param String nota
	 * @return Integer 
	 * @version 1.0
	 */
	private Integer verificaPeso(String nota) {
		nota = nota.trim();
		if(nota.equalsIgnoreCase(PESO_OTIMO))
			return 5;
		
		if(nota.equalsIgnoreCase(PESO_BOM))
			return 4;
		
		if(nota.equalsIgnoreCase(PESO_SATISFATORIO))
			return 3;
		
		if(nota.equalsIgnoreCase(PESO_RUIM))
			return 2;
		
		if(nota.equalsIgnoreCase(PESO_PESSIMO))
			return 1;
		
		return 0;
	}// verificaPeso
	
	/**
	 * 
	 * Recebe como parâmetro a primeira linha do arquivo, a qual fara uso para gerar os tipos de avaliações desejadas.
	 * Com este cabecalho cria um objeto contendo um indice, uma classe gereal e uma subclasse caso exista
	 * poderão ser criados quantos objetos forem passados pelo arquivo.
	 * 
	 * @param String com os dados do cabeçalho do arquivo.
	 * @return DadosAvaliados, um objeto desta classe.
	 * @throws NumberFormatException
	 * @throws IndexOutOfBoundsException
	 * @version 1.0
	 */
	private Pergunta converteDadosPrimeiraLinha(String conteudo) throws NumberFormatException, IndexOutOfBoundsException{
		Pergunta pergunta = new Pergunta();

		Integer pos3 = conteudo.indexOf(SEPARADOR_INI_SUBCLASSE);
		Integer pos4 = conteudo.indexOf(SEPARADOR_FIM_SUBCLASSE);
		
		String dados[] = new String[INDEXADOR_DOIS];
		
		if(pos3 == ERRO_INDICE)
			dados[INDEXADOR_ZERO] = conteudo.substring(INDEXADOR_ZERO, conteudo.length());
		else
			dados[INDEXADOR_ZERO] = conteudo.substring(INDEXADOR_ZERO, pos3).trim();
		if(pos3 == ERRO_INDICE|| pos4 == ERRO_INDICE)
			dados[INDEXADOR_UM] = DEFAULT_VALOR_STRING;
		else
			dados[INDEXADOR_UM] = conteudo.substring(pos3+INDEXADOR_UM, pos4).trim();
		
		pergunta.setClasseGeral(dados[INDEXADOR_ZERO]);
		pergunta.setSubclasse(dados[INDEXADOR_UM]);
		
		return pergunta;
	}//converteDados
	
	/**
	 * Recebe o caminho de um arquivo e usa os componentes da classe ArquivoTexto para abrir o arquivo, 
	 * feito isso usa outro metodo da classe ArquivoTexto o qual le o conteudo do Arquivo e o armazena em uma String,
	 * retorna uma String com o conteúdo do Arquivo.
	 * 
	 * @param String com o caminho do Arquivo o qual deseja ser aberto.
	 * @return String com o Conteúdo do Arquivo.
	 * @throws IOException 
	 * @throws NullPointerException 
	 * @throws FileNotFoundException
	 * @version 1.0
	 */
	private String lerArquivo(String caminhoArquivo) throws NullPointerException, FileNotFoundException, IOException {
		ArquivoTexto texto = new ArquivoTexto();
		
		texto.abrir(caminhoArquivo);
		String conteudo = texto.ler();
		texto.fechar();
		
		return conteudo;
	}//lerArquivo
	
}//class ImportacaoDados 
