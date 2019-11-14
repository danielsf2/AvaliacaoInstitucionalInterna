package tsi.too.aii.acesso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import javafx.stage.Stage;
import tsi.too.aii.arquivos.ArquivoAvaliacaoPergunta;
import tsi.too.aii.arquivos.ArquivoPergunta;
import tsi.too.aii.classes.Avaliacao;
import tsi.too.aii.classes.AvaliacaoPergunta;
import tsi.too.aii.classes.ContabilizarAvaliacao;
import tsi.too.aii.classes.Pergunta;
import tsi.too.aii.controller.BarraProgressoController;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;
/**
 * Esta classe tem por objetivo fornecer métodos que forneçam acesso a base de dados do programa.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class ManipulacaoArquivo{

	private static ArquivoPergunta arquivoPerguntas;
	public static Pergunta pergunta;
	public static Integer recordSizeAvaliacao;
	public static String nomeRegistro;
	private static boolean controle = false;
	
	/**
	 * Construtor da classe. Responsável pela inicialização padrão dos objetos da classe bem como abrir os arquivos.
	 * 
	 * @throws FileNotFoundException
	 * @version 1.0
	 */
	public ManipulacaoArquivo(){
		super();
	}//ManipulacaoArquivo
	
	/**
	 * Abre um arquivo na base de dados conforme a necessidade do programa.
	 * 
	 * @throws FileNotFoundException
	 */
	public void abreArquivo() throws FileNotFoundException {
		arquivoPerguntas = new ArquivoPergunta();
		arquivoPerguntas.openFile(Constantes.CAMINHO_ARQUIVO_BASE_DADOS+nomeRegistro+Constantes.EXTENSAO_DAT);
	}//abreArquivo
	
	/**
	 * Gera um {@link List} com todos os cursos do Arquivo.
	 * 
	 * @return List com o conteudo desejado.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> geraListaGeralCursos() throws FileNotFoundException, IOException{
		List<String>cursos = new ArrayList<>();
		for (Avaliacao avaliacao : new ManipulacaoArquivo().obterArquivo().getAvaliacaoPergunta().obterAvaliacoes()) {
			if(!cursos.contains(avaliacao.getNomeCurso()))
					cursos.add(avaliacao.getNomeCurso());
		}
		return cursos;
	}//geraListaCursosLicenciatura
	/**
	 * Pesquisa na base de dados e retorna um {@link List}com  os nomes dos cursos de Licenciatura presentes no arquivo.
	 * 
	 * @return List com todos os cursos de Licenciatura.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @version 1.0
	 */
	public static List<String> geraListaCursosLicenciatura() throws FileNotFoundException, IOException{
		List<String>cursos = new ArrayList<>();
		for (Avaliacao avaliacao : new ManipulacaoArquivo().obterArquivo().getAvaliacaoPergunta().obterAvaliacoes()) {
			if(avaliacao.getNomeCurso().contains(Constantes.CURSOS_LICENCIATURA) && !cursos.contains(avaliacao.getNomeCurso()))
					cursos.add(avaliacao.getNomeCurso());
		}
		return cursos;
	}//geraListaCursosLicenciatura
	
	/**
	 * Pesquisa na base de dados e retorna um {@link List} com todos os cursos técnicos e tecnólogos presentes na base de dados.
	 * 
	 * @return List com todos os cursos Técnicos e Tecnólogos.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @version 1.0
	 */
	public static List<String> geraListaCursosTecnicosTecnologos() throws FileNotFoundException, IOException{
		List<String>cursos = new ArrayList<>();
		for (Avaliacao avaliacao : new ManipulacaoArquivo().obterArquivo().getAvaliacaoPergunta().obterAvaliacoes()) {
			if(avaliacao.getNomeCurso().contains(Constantes.CURSOS_TECNICOS) ||avaliacao.getNomeCurso().contains(Constantes.CURSOS_TECNOLOGOS)) {
				if(!cursos.contains(avaliacao.getNomeCurso()))
					cursos.add(avaliacao.getNomeCurso());
			}
		}
		return cursos;
	}//geraListaCursosTecnicosTecnologos
	
	/**
	 * Pesquisa na base de dados e retona um {@link List} com todos os cursos Bacharel.
	 * 
	 * @return List com todos os cursos Bacharel.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @see 1.0
	 */
	public static List<String> geraListaCursosBacharelado() throws FileNotFoundException, IOException{
		List<String>cursos = new ArrayList<>();
		for (Avaliacao avaliacao : new ManipulacaoArquivo().obterArquivo().getAvaliacaoPergunta().obterAvaliacoes()) {
			if(avaliacao.getNomeCurso().contains(Constantes.CURSOS_BACHARELADO) && !cursos.contains(avaliacao.getNomeCurso()))
					cursos.add(avaliacao.getNomeCurso());
		}
		return cursos;
	}//geraListaCursosBacharelado
	
	/**
	 * Gera um {@link List} contendo todos os tópicos das perguntas armazenadas em disco.
	 * 
	 * @return List com todas as perguntas armazenadas.
	 * @throws IOException
	 * @since 1.0
	 */
	private List<String> geraListaPerguntas() throws IOException{
		//Gera uma lista com todas as classes base de perguntas disponíveis no arquivo
		if(ImportacaoDados.bufferDados == null)
			ImportacaoDados.bufferDados = importarDadosArquivoBinario();
		List<String>dados = new ArrayList<>();
		for (Pergunta dadosAvaliados : ImportacaoDados.bufferDados) {
				if(!dados.contains(dadosAvaliados.getClasseGeral()))
					dados.add(dadosAvaliados.getClasseGeral());
		}
		return dados;
	}//geraListaPerguntas
	
	/**
	 * Recebe como parâmetro uma {@link String} com o nome do curso, pesquisa esse curso na base de dados e retorna
	 * um {@link List} com o conjunto de dados refinado para pesquisa. Em caso de erro dispara uma exceção.
	 * 
	 * @param String nome do curso que será usado como parâmetro.
	 * @return List com todas as avaliações referentes ao curso passado como parâmetro.
	 * @throws IOException
	 * @version 1.0
	 */
	public List<ContabilizarAvaliacao> contabilizarAvaliacaoCurso(String curso) throws IOException{
		List<ContabilizarAvaliacao> avaliacoes = new ArrayList<>();

		//Cria um buffer durante a importação do Arquivo para tentar evitar problemas de lentidao, durante a execução do programa.
		if(ImportacaoDados.bufferDados == null)
			ImportacaoDados.bufferDados = importarDadosArquivoBinario();
		
		//Gera uma lista com todas as classes base de perguntas disponíveis no arquivo
		List<String>dados = geraListaPerguntas();
		
		//Cria uma lista com todas as perguntas
		for (String string : dados) avaliacoes.add(new ContabilizarAvaliacao(string,Constantes.VAZIO, Constantes.INDEXADOR_ZERO, 
				Constantes.INDEXADOR_ZERO, Constantes.INDEXADOR_ZERO, (double)Constantes.INDEXADOR_ZERO));
		
		//Preenche os campos para refinamento 
		for (Pergunta dadosAvaliados : ImportacaoDados.bufferDados) {
			//Percorre o arquivo verificando os campos
			for (ContabilizarAvaliacao contAvaliacao : avaliacoes) {
				//Verifica o Curso desejado pelo usuario
				if(dadosAvaliados.getClasseGeral().equalsIgnoreCase(contAvaliacao.getQuestao())) {
					//Percorre a lista de avaliações para gerar os dados.
					for (Avaliacao av : dadosAvaliados.getAvaliacaoPergunta().obterAvaliacoes()) {
						
						if(av.getNomeCurso().contains(curso)) {
							//contAvaliacao.setIndiceQuestao(dadosAvaliados.getIndiceClasse());
							contAvaliacao.setCursoAvaliador(av.getNomeCurso());
							contAvaliacao.setPesoQuestao(contAvaliacao.getPesoQuestao()+av.getPesoNota());
							contAvaliacao.setQuantidade(contAvaliacao.getQuantidade()+Constantes.INDEXADOR_UM);
						}
					}
				}
			}
		}
		return avaliacoes;
	}//contabilizarAvaliacaoCurso
	
	/**
	 * Recebe como parâmetro uma lista com o conjunto de dados que devem ser persistidos no arquivo, em caso de erro]
	 * dispara uma exceção.
	 * 
	 * @param <code>List</code> Dados Avaliados, com todos os elementos a serem persistidos. 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @version 1.0
	 */
	public void gerarArquivoAleatorio(List<Pergunta> perguntas) throws FileNotFoundException, IOException {
		abreArquivo();
		List<AvaliacaoPergunta> avaliacoesPerguntas = new ArrayList<>();
		//Cria um arquivo do tipo AvaliacaoPergunta
		for(Pergunta pergunta : perguntas)
			avaliacoesPerguntas.add(pergunta.getAvaliacaoPergunta());

		//Ordena a lista de Avaliacoes de Acordo com o Indice para Gravação. Pois será usado como Indice do arquivo.
		Ordenacao.ordenaAvaliacoesIdPergunta(avaliacoesPerguntas);
		
		//Cria um objeto ArquivoAvaliacaoPergunta para abertura do arquivo e gravação.
		ArquivoAvaliacaoPergunta arqAvaliacaoPergunta = new ArquivoAvaliacaoPergunta();
		arqAvaliacaoPergunta.openFile(Constantes.CAMINHO_ARQUIVO_AVALIACOES+nomeRegistro+Constantes.EXTENSAO_DAT);
		
		
		//Grava um arquivo com as repostas das perguntas ordenado pelo índice.
		for (AvaliacaoPergunta avaliacaoPergunta : avaliacoesPerguntas)
			arqAvaliacaoPergunta.writeObject(avaliacaoPergunta);

		arqAvaliacaoPergunta.closeFile();
		
		//Grava o arquivo de perguntas.
		for (Pergunta pergunta : perguntas) 
			arquivoPerguntas.writeObject(pergunta);
		
		arquivoPerguntas.closeFile();
	}//gerarArquivoAleatorio
	
	
	/**
	 * Abre um componente {@link JFileChooser} o qual oferece ao usuário as opções de arquivos que estão carregadas na base de
	 * dados do sistema, trata os eventos de captura de nome do arquivo e realiza a importação dos dados.
	 * 
	 * @throws IOException
	 */
	public void carregarBaseDeDadosExistente() throws IOException {
		controle = true;
		String arq = new CriacaoStage().start(new Stage(), Constantes.TITULO_PROGRAMA, Constantes.TELA_CONSULTA_BASE_DADOS,
				Constantes.EXTENSAO_DAT_FILES, Constantes.EXTENSAO_DAT_FILE_CHOOSER);
		nomeRegistro = MetodosGerais.capturaSubstringNomeRegistro(arq);
		ImportacaoDados.bufferDados = importarDadosArquivoBinario();
		MetodosGerais.alterarNomeBaseDados();
	}//carregarBaseDeDadosExistente
	
	/**
	 * Não é necessário que o usuário escolha um arquivo caso a base de dados possua apenas um arquivo, portanto o programa,
	 * usa este método para carregar a base de dados quando exista apenas um arquivo, este método também é usado, para
	 * carregar uma base de dados defaul caso a mesma exista.
	 */
	public void carregaBaseDadosUmArquivo() {
		File file = new File(Constantes.CAMINHO_BASE_DADOS);
		if(file.listFiles().length >= Constantes.INDEXADOR_UM && ImportacaoDados.bufferDados==null && controle == false) {
			String arq[] = file.list();
			nomeRegistro = arq[Constantes.INDEXADOR_ZERO].substring(arq[Constantes.INDEXADOR_ZERO].indexOf(Constantes.TRACO)+ 
										   Constantes.INDEXADOR_UM, arq[Constantes.INDEXADOR_ZERO].indexOf(Constantes.PONTO));
			MetodosGerais.alterarNomeBaseDados();
		}
	}//carregaBaseDadosUmArquivo
	
	/**
	 * Retona o elemento do arquivo correspondente ao Indice, caso este índice seja inválido, menor que zero ou maior que a 
	 * quantidade de dados no arquivo retorna <code>null</code>. Faz uso do método da classe {@link ArquivoPergunta}
	 * para posicionar o ponteiro na posicao inicial do arquivo e retornar o mesmo.
	 * 
	 * @param Integer, com a posicao do objeto desejado.
	 * @return DadosAvaliados , com o conteúdo do primeiro elemento do arquivo.
	 * @throws IOException
	 * @version 1.0
	 */
	public Pergunta obterArquivo(Integer posArquivo) throws IOException {
		if(ImportacaoDados.bufferDados != null) {
			return ImportacaoDados.bufferDados.get(Constantes.INDEXADOR_ZERO);
		}
		else {
			carregaBaseDadosUmArquivo();
			abreArquivo();
			if(posArquivo < Constantes.INDEXADOR_ZERO || posArquivo >= arquivoPerguntas.recordQuantity())
				return null;
			arquivoPerguntas.setFilePointer(posArquivo);
			return(Pergunta)arquivoPerguntas.readObject();
		}
	}//obterArquivo
	
	/**
	 * Retona o primeiro elemento do arquivo, fazendo uso do método da classe {@link ArquivoPergunta}
	 * para posicionar o ponteiro na posicao inicial do arquivo e retornar o mesmo.
	 * 
	 * @return DadosAvaliados , com o conteúdo do primeiro elemento do arquivo.
	 * @throws IOException
	 * @version 1.0
	 */
	public Pergunta obterArquivo() throws IOException {
		if(ImportacaoDados.bufferDados != null)
			return ImportacaoDados.bufferDados.get(Constantes.INDEXADOR_ZERO);
		else {
			carregaBaseDadosUmArquivo();
			abreArquivo();
			arquivoPerguntas.setFilePointer(Constantes.INDEXADOR_ZERO);
			return(Pergunta)arquivoPerguntas.readObject();
		}
	}//obterArquivo
	
	/**
	 * Importa todos os dados existentes na base de dados e enche um Buffer com os mesmos.
	 * 
	 * @return List com todos os valores armazenados.
	 * @throws IOException
	 * @version 1.0
	 */
	private List<Pergunta> importarDadosArquivoBinario() throws IOException{
		carregaBaseDadosUmArquivo();
		abreArquivo();
		BarraProgressoController barraProgresso = new BarraProgressoController();
		barraProgresso.start(new Stage());
		List<Pergunta>pergunta = new ArrayList<>();
		long tamanhoLista = arquivoPerguntas.recordQuantity();
		for (int pos = Constantes.INDEXADOR_ZERO; pos < tamanhoLista; pos++) {
			arquivoPerguntas.setFilePointer(pos);
			//Trecho lento do programa.
			pergunta.add((Pergunta)arquivoPerguntas.readObject());
		}
		barraProgresso.fechar();
		//MetodosGerais.alterarNomeBaseDados();
		return pergunta;
	}//importarDadosArquivoBinario
	
}//class ManipulacaoArquivo 
