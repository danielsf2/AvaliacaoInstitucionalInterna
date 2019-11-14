/**
 * 
 */
package tsi.too.aii.interfaceGrafica;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import javafx.stage.Stage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe abstrata tem por objetivo fornecer métodos que facilitem a criação de graficos do tipo {@link JFreeChart}, 
 * foi criada com classe abtrata para também facilitar a criação de outras classes fornecendo atributos default as mesmas.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public abstract class Graficos{
	private static final double MARGEM_VERTICAL = -1.5,
														MARGEM_HORIZONTAL = -5.0,
														OFFSET = 20.0;

	/**
	 * Recebe como parâmetros um {@link DefaultCategoryDataset} que é o objeto responsável por povoar o gráfico o qual 
	 * será implementado posteriormente, um título geral do programa bem como um título lateral, os quais serão exibidos no
	 * gráfico, recebe um nome padrão de legenda e a orientação do gráfico. Faz uso destes parâmetros para criar um gráfico e
	 * exibi-lo na tela do usuário.
	 * 
	 * @param  DefaultCategoryDataset dataset
	 * @param  PlotOrientation orientacao
	 * @param  String titulo
	 * @param  int tamHor
	 * @param  int tamVer
	 * @version 1.0
	 */
	public void criarGrafico(DefaultCategoryDataset dataset, PlotOrientation orientacao, String titulo1, int tamHor,int tamVer ){
		ChartViewer cv = new ChartViewer(formatarGrafico(obterGrafico(dataset, titulo1, orientacao)));
		new CriacaoStage().start(new Stage(), cv, tamHor, tamVer);
	}//criarGrafico
	
	/**
	 * Recebe como parâmetro um objeto {@link DefaultCategoryDataset} que será usado como base para preenchimento do gráfico,
	 * uma {@link String} com o título do gráfico e outra com a legenda do gráfico. Cria um objeto {@link JFreeChart} com os respectivos
	 * parâmetros, chama o método responsável por formatar o objeto e retorna o mesmo.  
	 * 
	 * @param DefaultCategoryDataset dataset
	 * @param String titulo
	 * @param String legenda
	 * @return	JFreeChart grafico
	 * @version 1.0
	 */
	public abstract JFreeChart obterGrafico(DefaultCategoryDataset dataset, String tiutlo1,PlotOrientation orientacao);
	
	/**
	 * Recebe como parâmetro um gráfico com configursções default, formata esse gráfico através de um {@link CategoryPlot}
	 * e retorna o gráfico formatado.
	 * 
	 * @param JFreeChart
	 * @return JFreeChart
	 * @version 1.0
	 */
	public JFreeChart formatarGrafico(JFreeChart jf) {
		ItemLabelPosition pos = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE2, TextAnchor.CENTER);
		//pos.
		CategoryPlot plot = jf.getCategoryPlot();
		BarRenderer3D renderer = new BarRenderer3D();
		if(jf.getCategoryPlot().getOrientation() == PlotOrientation.HORIZONTAL) {
			renderer.setItemMargin(MARGEM_HORIZONTAL);
			renderer.setItemLabelAnchorOffset(OFFSET);
		}
		else
			renderer.setItemMargin(MARGEM_VERTICAL);
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.WHITE);
		plot.getRenderer().setSeriesPaint(Constantes.INDEXADOR_ZERO, Color.BLUE);
		plot.setDomainGridlinePaint(Color.DARK_GRAY);
		plot.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.getRenderer().setBasePositiveItemLabelPosition(pos);
		plot.getRenderer().setBaseItemLabelPaint(Color.BLUE);
		plot.getRenderer().setBaseItemLabelFont(Constantes.ARIAL_BOLD_13);
		plot.setOutlineVisible(false);
		plot.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plot.getRenderer().setBaseItemLabelsVisible(true);
		return jf;
	}//formatarGrafico
	
}//abstract class Graficos 
