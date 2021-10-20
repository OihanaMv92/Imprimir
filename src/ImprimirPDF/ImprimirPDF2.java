package ImprimirPDF;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ImprimirPDF2
{
	public static void main(String[] args)
	{
		// Se crea el documento
		Document documento = new Document();
		try
		{
			// Se crea el OutputStream para el fichero donde queremos dejar el pdf.
			FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");
			//Se asocia el documento al OutputStream y se indica que el espaciado entre
			//líneas será de 20. Esta llamada debe hacerse antes de abrir el documento
			PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
			//Se abre el documento.
			documento.open();
			documento.add(new Paragraph("Esto es el primer párrafo, normalito"));
			documento.add(new Paragraph("Este es el segundo y tiene una fuente rara",
					FontFactory.getFont("arial", // fuente
							22, // tamaño
							Font.ITALIC, // estilo
							BaseColor.ORANGE))); // color
			Image foto = Image.getInstance("pinguino.jpg");
			foto.scaleToFit(100, 100); // cambiar tamaño imagen
			foto.setAlignment(Chunk.ALIGN_MIDDLE); // donde quieres que aparezca
			documento.add(foto);
			PdfPTable tabla = new PdfPTable(3); // COLUMNAS
			for (int i = 0; i < 15; i++)
			{
				tabla.addCell("celda " + i);
			}
			documento.add(tabla); // añadimos tabla al documento
			documento.close();
			//Abrimos el archivo PDF recién creado
			try
			{
				File path = new File ("fichero.pdf");
				Desktop.getDesktop().open(path);
			}
			catch (IOException ex)
			{
				System.out.println("Se ha producido un error al abrir el archivo PDF");
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
}