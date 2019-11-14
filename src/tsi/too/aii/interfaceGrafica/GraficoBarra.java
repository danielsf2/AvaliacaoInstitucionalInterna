package tsi.too.aii.interfaceGrafica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Esta classe tem por objetivo fornecer métodos que possibilitem e facilitem a criação de um grafico de barras.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class GraficoBarra extends Graficos {

	@Override
	public JFreeChart obterGrafico(DefaultCategoryDataset dataset, String tiutlo1,PlotOrientation orientacao) {
		JFreeChart graficoBarra = ChartFactory.createBarChart3D(tiutlo1,"","", dataset, orientacao, false, true, false);
		return graficoBarra;
	}//criarGraficoColunas

}//class GraficoBarra
