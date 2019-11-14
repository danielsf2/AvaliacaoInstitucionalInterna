package tsi.too.aii.relatorios;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.jfree.chart.JFreeChart;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import tsi.too.aii.acesso.ManipulacaoArquivo;
import tsi.too.aii.interfaceGrafica.Alertas;
import tsi.too.aii.interfaceGrafica.CriacaoStage;
import tsi.too.aii.interfaces.Constantes;

/**
 * Esta classe tem por objetivo fornecer métodos que possibilitem a criacao de um arquivo do tipo PDF pelo usuário do programa.
 * 
 * @author Daniel Soares Ferreira
 *
 */
public class CriacaoPDF {
	public static final int TAMANHO_FONTE = 13,
											 ALTURA_IMG_GRAFICO = 400,
											 LARGURA_IMG_GRAFICO = 550;
	public static final String ESPACAMENTO = "\n\n",
										MSG_ARMAZENAR_ARQUIVO = "Arquivo armazanado em : ";
	public static final float PARAMETRO_IMAGE = 1.0f;

	/**
	 * Gera um Arquivo PDF de acordo com os parâmetros passados pelo usuário. Usa métodos privados da própria classe
	 * para executar suas funcionalidades.
	 * 
	 * @param tabela
	 * @param grafico
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void gerarPDF (JTable tabela, JFreeChart grafico) throws DocumentException, IOException {
		Document docPDF = new Document(PageSize.A4);
		String caminhoPdf = null;
		try {
			caminhoPdf = new CriacaoStage().startArmazenar(new Stage(), Constantes.TITULO_PROGRAMA);
		} catch (NullPointerException e) {
			return;
		}
		
		PdfWriter pdfWriter = PdfWriter.getInstance(docPDF, new FileOutputStream(caminhoPdf));
		docPDF.open();
		docPDF.add(criarTabelaPdf(tabela));

		docPDF.add(new Paragraph(ESPACAMENTO));
		docPDF.add(criaPdfGrafico(grafico, pdfWriter));
		
		docPDF.close();
		
		File pdf = new File(Constantes.CAMINHO_ARQUIVO_PDF+ManipulacaoArquivo.nomeRegistro+Constantes.EXTENSAO_PDF);
		Alertas.alerta(MSG_ARMAZENAR_ARQUIVO +caminhoPdf, AlertType.INFORMATION);
		Desktop.getDesktop().open(pdf);
	}//gerarPDF 
	
	/**
	 * Cria um {@link PdfPTable} o qual será usado para gerar um arquivo PDF comas as informações passadas pelo usuário da
	 * classe.
	 * 
	 * @param tabela
	 * @return PdfPTable com o conteudo preenchido
	 * @throws DocumentException
	 */
	private PdfPTable criarTabelaPdf(JTable tabela) throws DocumentException {
		Font fonteColuna = FontFactory.getFont(java.awt.Font.SANS_SERIF, TAMANHO_FONTE , Font.BOLD);
		
		PdfPTable pdfTable = new PdfPTable(tabela.getColumnCount());
		List<PdfPCell> colunas = new ArrayList<>();
		
		for(int pos = Constantes.INDEXADOR_ZERO; pos < tabela.getColumnCount(); pos++) {
			colunas.add(new PdfPCell(new Paragraph(tabela.getColumnName(pos), fonteColuna)));
			if(pos == Constantes.INDEXADOR_ZERO)
				colunas.get(pos).setHorizontalAlignment(Element.ALIGN_LEFT);
			else
				colunas.get(pos).setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		
		for(PdfPCell coluna : colunas)
			pdfTable.addCell(coluna);
		
		for(int linha = Constantes.INDEXADOR_ZERO; linha < tabela.getModel().getRowCount(); linha++) {
			for(int coluna = Constantes.INDEXADOR_ZERO; coluna < tabela.getColumnCount(); coluna++) {
				pdfTable.addCell(tabela.getModel().getValueAt(linha, coluna).toString());
				pdfTable.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
		}
		return pdfTable;
	}//criarTabelaPdf
	
	/**
	 * A finalidade deste método é transformar um objeto {@link JFreeChart} em um {@link Image} para que o mesmo possa ser
	 * armazenado em um arquivo PDF.
	 * 
	 * @param grafico
	 * @param pdfWriter
	 * @return Uma Image com o conteúdo do Gráfico
	 * @throws BadElementException
	 * @throws IOException
	 */
	private Image criaPdfGrafico(JFreeChart grafico, PdfWriter pdfWriter) throws BadElementException, IOException {
		BufferedImage bufferImage = grafico.createBufferedImage(LARGURA_IMG_GRAFICO, ALTURA_IMG_GRAFICO);
		Image image =Image.getInstance(pdfWriter, bufferImage, PARAMETRO_IMAGE);
		return image;
	}//criaPdfGrafico
	
}//class CriacaoPDF 
