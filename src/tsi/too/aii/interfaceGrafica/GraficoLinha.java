package tsi.too.aii.interfaceGrafica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.stage.Stage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por objetivo oferecer métodos que possibilitem e orientem a criação de um Grafico de Linhas.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class GraficoLinha{
	
	/**
	 * Recebe vários parâmetros que possibilitam a criação de um grafico de linhas pelos métodos internos a classe.
	 * 
	 * @param DefaultCategoryDataset
	 * @param PlotOrientation
	 * @param String titulo
	 * @param Integer tamHor
	 * @param Integer tamVer
	 * @param List cursos
	 * @version 1.0
	 */
	public void criarGrafico(DefaultCategoryDataset dataset, PlotOrientation orientacao, String titulo1, int tamHor,int tamVer, 
			List<String> cursos ){
		ChartViewer cv = new ChartViewer(formatarGrafico(obterGrafico(dataset, titulo1, orientacao), cursos));
		new CriacaoStage().start(new Stage(), cv, tamHor, tamVer);
	}//criarGrafico
	
	/**
	 * Recebe um gráfico default criado com a classe {@link JFreeChart} formata o mesmo e retorna o gráfico formatado.
	 * 
	 * @param JFreeChart
	 * @param String cursos
	 * @return {@link JFreeChart}
	 * @version 1.0
	 */
	public JFreeChart formatarGrafico(JFreeChart jf, List<String>cursos) {
		CategoryPlot plot = jf.getCategoryPlot();
		
		for(int pos = Constantes.INDEXADOR_ZERO; pos < cursos.size(); pos++)
			plot.getRenderer().setSeriesStroke(pos, new BasicStroke(Constantes.INDEXADOR));
		
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeMinorGridlinesVisible(false);
		plot.setRangeMinorGridlinePaint(Color.DARK_GRAY);
		plot.setRangeGridlinePaint(Color.DARK_GRAY);
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		return jf;
		
	}//formatarGrafico

	/**
	 * Obtem um grafico de linhas usando a classe {@link JFreeChart}.
	 * 
	 * @param DefaultCategoryDataset dataset
	 * @param String titulo
	 * @param PlotOrientation
	 * @return {@link JFreeChart}
	 */
	public JFreeChart obterGrafico(DefaultCategoryDataset dataset, String tiutlo1, PlotOrientation orientacao) {
		JFreeChart jf = ChartFactory.createLineChart(tiutlo1, Constantes.VAZIO, Constantes.VAZIO, dataset, orientacao, true, true, false);
		return jf;
	}//obterGrafico

}//class GraficoLinha 
