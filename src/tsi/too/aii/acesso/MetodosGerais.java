package tsi.too.aii.acesso;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import javafx.application.Platform;
import javafx.scene.control.Label;
import tsi.too.aii.classes.Avaliacao;
import tsi.too.aii.classes.Pergunta;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Ofere métodos gerais necessários ao funcionamento do programa, esses métodos não são específicos a nenhuma classe.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class MetodosGerais {
	private static final int CRITERIO_ARREDONDAMENTO = 1,
												CRITERIO_ARREDONDAMENTO_ZERO = 0;
	private static final String NOME_LABEL_INICIAL = "Base de Dados : ";
	/**
	 * Recebe um {@link Double} com um valor literal, e faz uso da classe {@link BigDecimal} para fazer o arredondamento
	 * para o valor mais próximo para cima ou para baixo.
	 * 
	 * @param Double , com o valor literal.
	 * @return Double , com o valor arredondado para uma casa decimal.
	 * @version 1.0
	 */
	public static Double arredondaValor(Double valor) {
		BigDecimal bd = new BigDecimal(valor).setScale(CRITERIO_ARREDONDAMENTO, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}//arredondaValor
	
	/**
	 * Recebe um {@link Double} com um valor literal, e faz uso da classe {@link BigDecimal} para fazer o arredondamento
	 * para o valor mais próximo para cima ou para baixo.
	 * 
	 * @param Double , com o valor literal.
	 * @return Double , com o valor arredondado para uma casa decimal.
	 * @version 1.0
	 */
	public static Double arredondaValorInt(Double valor) {
		BigDecimal bd = new BigDecimal(valor).setScale(CRITERIO_ARREDONDAMENTO_ZERO, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}//arredondaValor
	
	/**
	 * Este método tem por finalidade gerar os índices para as bases de dados necessárias aos arquivos, um índice é gerado para
	 * um objeto do tipo {@link Pergunta} e o mesmo é relacionado ao objeto {@link Avaliacao} contido neste.
	 * 
	 * @param List do tipo Pergunta
	 */
	public static void gerarIndicesArquivos(List<Pergunta> perguntas) {
		for (int pos = Constantes.INDEXADOR_ZERO; pos < perguntas.size(); pos++) {
			perguntas.get(pos).setIdPergunta(pos);
			perguntas.get(pos).getAvaliacaoPergunta().setIdPergunta(perguntas.get(pos).getIdPergunta());
		}
	}//gerarIndicesArquivos
	
	/**
	 *	Verifica se o diretório relaionado a base de dados do programa está vazio, caso o diretório esteja vazio retorna true,
	 *caso não retorna false.
	 *
	 * @return boolean
	 */
	public static boolean verificaDiretorioBaseDadosVazio() {
		File file = new File(Constantes.DIRETORIO_BASE_DE_DADOS);
		if(file.listFiles().length == Constantes.INDEXADOR_ZERO)
			return true;
		else
			return false;
	}//verificaArquivoVazio
	
	/**
	 * O programa contém um {@link Label} onde está contido o nome da base de dados em execução no programa, este nome 
	 * é atualizado conforme a base de dados em execução se midifica, este método é responsável por fazer esta modificação.
	 */
	public static void alterarNomeBaseDados() {
		Platform.runLater(()->{
			StringBuilder dados = new StringBuilder();
			dados.append(NOME_LABEL_INICIAL);
			dados.append(ManipulacaoArquivo.nomeRegistro);
			dados.append(Constantes.EXTENSAO_DAT);
			CriacaoStage.labelNomeBaseDados.setText(dados.toString());
		});
	}//alterarNomeBaseDados
	
	/**
	 * Captura o nome do registro do arquivo dentro da {@link String} passada como parametro e retorna a mesma.
	 * 
	 * @param nomeArquivo
	 * @return nomeArquivo formatado
	 */
	public static String capturaSubstringNomeRegistro(String nomeArquivo) {
		String nomeRegistro = nomeArquivo.substring(nomeArquivo.lastIndexOf(Constantes.PARAMETRO_SUBSTRING), 
												   nomeArquivo.lastIndexOf(Constantes.EXTENSAO_DAT));
		nomeRegistro = nomeRegistro.substring(nomeRegistro.indexOf(Constantes.TRACO)+Constantes.INDEXADOR_UM);
		return nomeRegistro;
	}//capturaSubstringNomeRegistro
	
	/**
	 * Formata uma coluna de um {@link JTable}, altera a cor, posicionamento das colunas e fonte.
	 * 
	 * @param formatacao
	 * @param cor
	 * @return DefaultTableCellRenderer
	 */
	@SuppressWarnings("serial")
	public static DefaultTableCellRenderer formataColuna(DefaultTableCellRenderer formatacao, Color cor) {
		formatacao = new DefaultTableCellRenderer() {
			@Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		        c.setForeground(cor);
		        c.setFont(Constantes.ARIAL_BOLD_13);
		        return c;
			}
		};
		formatacao.setHorizontalAlignment(SwingConstants.CENTER);
		return formatacao;
	}//formataColunaPar
	
}//class MetodosGerais 
