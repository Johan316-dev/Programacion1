package com.example.model;

import com.itextpdf.kernel.pdf.PdfWriter;

import java.util.List;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.util.List;

public class ReporteHistorialPagos {

    public static void generarPDF(List<Pago> pagos, String rutaArchivo) {

        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Título
            document.add(new Paragraph("Historial de Pagos").setBold().setFontSize(16));

            // Tabla con 6 columnas
            float[] columnWidths = {100, 100, 120, 120, 100};
            Table table = new Table(columnWidths);

            // Encabezados
            table.addHeaderCell(new Cell().add(new Paragraph("Placa")));
            table.addHeaderCell(new Cell().add(new Paragraph("Tipo Vehículo")));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Ingreso")));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Salida")));
            table.addHeaderCell(new Cell().add(new Paragraph("Total Pagado")));
            //table.addHeaderCell(new Cell().add(new Paragraph("Con Membresía")));


            // Filas de datos
            for (Pago pago : pagos) {
                table.addCell(new Cell().add(new Paragraph(pago.getPlaca())));
                table.addCell(new Cell().add(new Paragraph(pago.getTipoVehiculo())));
                table.addCell(new Cell().add(new Paragraph(pago.getFechaIngreso().toString())));
                table.addCell(new Cell().add(new Paragraph(pago.getFechaSalida().toString())));
                table.addCell(new Cell().add(new Paragraph(String.format("$ %.2f", pago.getTotalPagado()))));
                //table.addCell(new Cell().add(new Paragraph(pago.isConMembresia() ? "Sí" : "No")));
            }

            document.add(table);
            document.close();

            System.out.println("Reporte PDF generado en: " + rutaArchivo);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
