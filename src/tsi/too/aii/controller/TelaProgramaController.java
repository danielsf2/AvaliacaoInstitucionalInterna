package tsi.too.aii.controller;

import static tsi.too.aii.acesso.ManipulacaoArquivo.geraListaCursosBacharelado;
import static tsi.too.aii.acesso.ManipulacaoArquivo.geraListaCursosLicenciatura;
import static tsi.too.aii.acesso.ManipulacaoArquivo.geraListaCursosTecnicosTecnologos;
import static tsi.too.aii.interfaces.Constantes.DIM_GRAFICO_LINHA;
import static tsi.too.aii.interfaces.Constantes.TITULO_GRAFICO_BACHAREAL;
import static tsi.too.aii.interfaces.Constantes.TITULO_GRAFICO_LICENCIATURA;
import static tsi.too.aii.interfaces.Constantes.TITULO_GRAFICO_TECNOLOGIA;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTable;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.acesso.MetodosGerais;
import tsi.too.aii.interfaceGrafica.Alertas;
import tsi.too.aii.interfaceGrafica.GraficoLinha;
import tsi.too.aii.interfaces.Constantes;
import tsi.too.aii.relatorios.Datasets;

/**
 * Classe controladora do arquivo {@link FXML}, TelaPrograma.fxml, a qual é reponsável por fornecer a interface gráfica de
 * iteração com o usuário ao progama, esta classe da funcionalidade a parte visual do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class TelaProgramaController {
	
	/**
	 * Trata o evento de clicar no botão de importar do programa.
	 * 
	 * @param event
	 * 
	 * @exception, {@link FileNotFoundException}, {@link NullPointerException}, {@link ClassNotFoundException}, {@link IOException},
	 * {@link Exception}.
	 */
	 @FXML 
	 void acaobtnImportar(ActionEvent event){
		try {
			new TelaPesquisaRegistroController().start(new Stage());
		} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ARQUIVO_NAO_ENCONTRADO, AlertType.ERROR);//arquivo nao esta na base de dados
		} catch (NullPointerException e) {
			Alertas.alerta(Constantes.MSG_NULL_POINTER, AlertType.WARNING);//erro initialize
		} catch (ClassNotFoundException e) {
			Alertas.alerta(Constantes.MSG_CLASS_NOT_FOUND, AlertType.WARNING);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);//erro critico
		} catch(Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaobtnImportar

	 /**
	  * Trata o evento do botao exibir um relatório por curso participante da pesquisa.
	  * 
	  * @param event
	  * @exception, {@link Exception}
	  */
    @FXML
    void acaoBtnRelatorioParticipante(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new TabelaAvaliacaoCursosController().start(new Stage());
		} catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioParticipante

    /**
     * Trata o evento de exibir um relatório por segmentos participantes da pesquisa.
     * 
     * @param event
     * @exception, {@link Exception}.
     */
    @FXML
    void acaoBtnRelatorioSegmento(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new TabelaAvaliacaoSegmentoController().start(new Stage());
		} catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioSegmentoNormal

    /**
     * Trata o evento de clicar em um {@link Button} para exibir um relatorio de tabelas de cursos de bacharelado.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception}
     */
    @FXML
    void acaoBtnRelatorioComumBacharelado(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new TabelaCursosController().gerarTabelaCursos(geraListaCursosBacharelado());
		} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
		}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioComumBacharelado
    
    /**
     * Trata o evento de gerar um {@link JFreeChart} grafico , com um relatorio de cursos de Bacharelado.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception}
     */
    @FXML
    void acaoBtnRelatorioGraficoBacharelado(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new GraficoLinha().criarGrafico(Datasets.gerarDatasetGraficoLinhaCursos(geraListaCursosBacharelado()), 
					PlotOrientation.VERTICAL, TITULO_GRAFICO_BACHAREAL, DIM_GRAFICO_LINHA[Constantes.INDEXADOR_ZERO],
					DIM_GRAFICO_LINHA[Constantes.INDEXADOR_UM], geraListaCursosBacharelado());
    	} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
		}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioGraficoBacharelado
    
    /**
     * Trata o evento de clicar em um {@link Button} e gera um relatório em um {@link JTable} por cursos de Licenciatura.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception} 
     */
    @FXML
    void acaoBtnRelatorioComumLicenciatura(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new TabelaCursosController().gerarTabelaCursos(geraListaCursosLicenciatura());
    	} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
		}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioComumLicenciatura

    /**
     * Trata o evento de clicar em um {@link Button}, o qual tem a funcionalidade de gerar um relatório em um {@link LineChart}
     * de cursos de Licenciatura.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception}
     */
    @FXML
    void acaoBtnRelatorioGraficoLicenciatura(ActionEvent event) {
    	try {
			new GraficoLinha().criarGrafico(Datasets.gerarDatasetGraficoLinhaCursos(geraListaCursosLicenciatura()), PlotOrientation.VERTICAL,
					TITULO_GRAFICO_LICENCIATURA, DIM_GRAFICO_LINHA[Constantes.INDEXADOR_ZERO], 
					DIM_GRAFICO_LINHA[Constantes.INDEXADOR_UM], geraListaCursosLicenciatura());
    	} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
		}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioGraficoLicenciatura

    /**
     * Trata o evento de clicar em um {@link Button} e gera um relatório usando um {@link JTable}, contendo a quantidade de 
     * avaliações obtidas pelos cursos técnicos e tecnólogos.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception}
     */
    @FXML
    void acaoBtnRelatorioComumTecnologia(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new TabelaCursosController().gerarTabelaCursos(geraListaCursosTecnicosTecnologos());
    	} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
		}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioComumTecnologia

    /**
     * Trata o vento de clicar em um {@link Button}, o qual tem a funcionalidade de gerar um {@link LineChart}, contendo a 
     * quantidade de avaliações realizadas por cursos técnicos e tecnólogos.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception}
     */
    @FXML
    void acaoBtnRelatorioGraficoTecnologia(ActionEvent event) {
    	try {
			new GraficoLinha().criarGrafico(Datasets.gerarDatasetGraficoLinhaCursos(geraListaCursosTecnicosTecnologos()), 
					PlotOrientation.VERTICAL, TITULO_GRAFICO_TECNOLOGIA, DIM_GRAFICO_LINHA[Constantes.INDEXADOR_ZERO], 
					DIM_GRAFICO_LINHA[Constantes.INDEXADOR_UM], geraListaCursosTecnicosTecnologos());
    	} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
		} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
		}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioGraficoTecnologia
    

    /**
     * Trata o evento de clicar em um {@link Button} o qual é responsável por gerar um relatório geral de avaliações por todos
     * os cursos e segmentos da pesquisa em um {@link TableView}.
     * 
     * @param event
     * @exception Exception
     */
    @FXML
    void acaoBtnRelatorioGeral(ActionEvent event) {
    	if(MetodosGerais.verificaDiretorioBaseDadosVazio() == true) {
    		Alertas.alerta(Constantes.ALERTA_BASE_DADOS_VAZIA, AlertType.WARNING);
    		return;
    	}
    	try {
			new TabelaGeralAvaliacaoCursosController().start(new Stage());
		} catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
		}
    }//acaoBtnRelatorioGeral
    
    /**
     * Trata o evento do {@link Button} de carregar a base um base de dados ja presente no programa.
     * 
     * @param event
     * @exception, {@link FileNotFoundException}, {@link IOException}, {@link Exception}
     * 
     */
    @FXML
    void acaoBotaoCarregarAlterarBaseDados(ActionEvent event) {
    	try {
			new ManipulacaoArquivo().carregarBaseDeDadosExistente();
			Alertas.alerta(Constantes.MSG_DADOS_IMPORTADOS_SUCESSO, AlertType.INFORMATION);
    	}catch (NullPointerException e) {
			return;
    	} catch (FileNotFoundException e) {
			Alertas.alerta(Constantes.MSG_ERRO_BASE_DADOS, AlertType.ERROR);
    	} catch (IOException e) {
			Alertas.alerta(Constantes.MSG_ERRO_MANIPULACAO_ARQUIVOS, AlertType.ERROR);
    	}catch (Exception e) {
			Alertas.alerta(Constantes.MSG_ERRO_GENERICO, AlertType.ERROR);
    	}
    }//acaoBotaoCarregarAlterarBaseDados
    
}//class TelaProgramaController 
