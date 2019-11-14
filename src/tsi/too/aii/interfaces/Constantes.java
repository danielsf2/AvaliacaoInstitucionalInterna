package tsi.too.aii.interfaces;

import java.awt.Color;
import java.awt.Font;


/**
 * Esta interface tem por objetivo fornecer constantes que são utilizadas durante o funcionamento do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public interface Constantes {

	public static final String TITULO_PROGRAMA = "Avaliação Institucional Interna",
													MSG_DADOS_IMPORTADOS_SUCESSO = "Importação Bem Sucedida!",
													ALERTA_BASE_DADOS_VAZIA = "Base de Dados Vazia, Importe um Arquivo!",
													CAMINHO_BARRA_PROGRESSO_FXML = "../Fxml/BarraProgresso.fxml",
													DIRETORIO_BASE_DE_DADOS = "./arquivos/Base de Dados/",
													IMAGEM_PROGRAMA = "/tsi/too/aii/imagens/icone3.png",
													TITULO_GRAFICO_BARRA= "Gráfico de Relatório por Curso.",
													TITULO_GRAFICO_COLUNA= "Quantidade de participações por Segmento",
													TITULO_GRAFICO_BACHAREAL= "Conceito médio da avaliação dos discentes dos cursos superiores de Bacharelado.",
													TITULO_GRAFICO_LICENCIATURA="Conceito médio da avaliação dos discentes dos cursos superiores de Licenciatura.",
													TITULO_GRAFICO_TECNOLOGIA="Conceito médio da avaliação dos discentes dos cursos Técnicos e Tecnólogos",
													CAMINHO_ARQUIVO_CSS = "../css/FormatacaoTabelas.css",
													CAMINHO_ARQUIVO_CSS_GERAIS = "../css/FormatacoesGerais.css",
													CAMINHO_TABELA_GERAL_FXML = "../Fxml/TabelaGeralAvaliacaoCursos.fxml",
													CAMINHO_TABELA_AVALIACAO_CURSO_FXML = "../Fxml/TabelaAvaliacaoCursos.fxml",
													CAMINHO_TABELA_AVALIACAO_SEGMENTO_FXML = "../Fxml/TabelaAvaliacaoSegmento.fxml",
													CAMINHO_TABELA_CURSOS = "../Fxml/TabelaCursos.fxml",
													CAMINHO_TELA_PESQUISA_REGISTRO = "../Fxml/TelaPesquisaRegistro.fxml",
													CAMINHO_TELA_PROGRAMA = "../Fxml/TelaPrograma.fxml",
													CAMINHO_BASE_DADOS = "./arquivos/Base de Dados/",
													TELA_CONSULTA_BASE_DADOS = "Base de Dados",
													EXTENSAO_DAT_FILES = "DAT files",
													EXTENSAO_DAT_FILE_CHOOSER = "*.dat",
													EXTENSAO_DAT = ".dat",
													CAMINHO_ARQUIVO_BASE_DADOS = "arquivos/Base de Dados/Base de dados-",
													CURSOS_LICENCIATURA="Licenciatura",
												 	CURSOS_BACHARELADO="Bacharelado",
												 	CURSOS_TECNICOS ="Técnico",
												 	CURSOS_TECNOLOGOS="Tecnologia",
												 	VAZIO = "",
												 	PONTO = ".",
												 	TRACO = "-",
												 	PARAMETRO_SUBSTRING = "dados-",
												 	SEPARADOR_CAMPOS = ";",
													SEPARADOR_INDICE_TEMP = " - ",
													SEPARADOR_LINHAS = "\n",
													SEPARADOR_INDICE = ". ",
													SEPARADOR_INDICE_CARACTERE = ".",
													SEPARADOR_CLASSE = " ",
													SEPARADOR_INI_SUBCLASSE = "[",
													SEPARADOR_FIM_SUBCLASSE = "]",
													DEFAULT_VALOR_STRING = "",
													CAMPO_DISCENTE = "discente",
													PESO_OTIMO = "ótimo",
													 PESO_BOM = "bom",
													 PESO_SATISFATORIO = "satisfatório",
													 PESO_RUIM = "ruim",
													 PESO_PESSIMO = "péssimo",
												 	CAMINHO_ARQUIVO_AVALIACOES = "arquivos/Avaliacoes/ArquivoDeAvaliacoes-",
												 	MSG_ERRO_BASE_DADOS = "Erro na Base de Dados!",
												 	MSG_ARQUIVO_NAO_ENCONTRADO = "Arquivo não Encontrado!",
												 	MSG_NULL_POINTER = "O Sistema não Pode Inicializar alguns Dados!",
												 	MSG_CLASS_NOT_FOUND = "Algumas classes não foram Encontradas!",
												 	MSG_ERRO_INICIALIZAR_GRAFICO_VALORES = "Erro ao Inicializar o Gráfico!",
												 	MSG_ERRO_MANIPULACAO_ARQUIVOS = "Erro na Manipulação da Base de Dados!",
												 	MSG_ERRO_GENERICO_AO_INICIALIZAR_GRAFICO = "Erro ao Gerar o Gráfico",
												 	MSG_ERRO_GENERICO = "Erro na execução do Programa!",
												 	CAMINHO_ARQUIVO_PDF = "arquivos/PDFs/",
												 	EXTENSAO_PDF = ".pdf";
	public static int DIM_GRAFICO_BARRA[] = {1000,600}, DIM_GRAFICO_COLUNA[] = {650,550}, DIM_GRAFICO_LINHA[] = {1100,550},
								   ERRO_INDICE = -1,
								   INDEXADOR = 3,
								   INDEXADOR_ZERO = 0,
								   INDEXADOR_UM = 1,
								   INDEXADOR_DOIS = 2,
								   TAM_UNICODE = 2,
								   TAM_PRIMEIRA_COLUNA = 325,
								   DIM_TABELA_CURSOS[] = {1290,285};
	public static final Color COR_RGB_AZUL = new Color(30, 144, 255),
												 COR_RGB_VERDE = new Color(0, 100, 0);
	public static final Font ARIAL_BOLD_13 = new Font("arial", Font.BOLD, 13);
	
}//interface Constantes 
