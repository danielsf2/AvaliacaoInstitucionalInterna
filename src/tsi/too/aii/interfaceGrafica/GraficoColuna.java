package tsi.too.aii.interfaceGrafica;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import tsi.too.aii.interfaces.Constantes;

/**
 * Está classe tem por objetivo oferecer métodos que facilitem a criação de um gráfico de colunas.
 * 
 * @author Daniel Soares Ferreira
 * @version 1.0
 */
public class GraficoColuna extends Graficos {

	@Override
	public JFreeChart obterGrafico(DefaultCategoryDataset dataset, String tiutlo1,PlotOrientation orientacao) {
		JFreeChart graficoColuna = ChartFactory.createBarChart3D(tiutlo1, Constantes.VAZIO, Constantes.VAZIO, dataset, orientacao, false, true, false);
		return graficoColuna;
	}//criarGraficoColunas
	
}//class GraficoColuna extends Graficos 