
package com.tpFinal.generadorPDF;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Factura;


import java.io.IOException;
import java.util.List;



public class PDFGenerator{
    private static Image headerImage;
    private static Alumno alumno;
    private static String nombreArchivo;
    public PDFGenerator()
    {
    }

    public static void generarFacturasPDF(Alumno alumno, String nombreArchivo) throws IOException {
        PdfWriter writer = new PdfWriter(nombreArchivo);
        PdfDocument pdf = new PdfDocument(writer);

        // Inicializar headerImage
        headerImage = new Image(ImageDataFactory.create("src/assets/UTN.jpeg"));
        headerImage.setWidth(150);
        headerImage.setMarginTop(30);

        //  eventos de encabezado y pie de página
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderEventHandler());
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());

        try (Document document = new Document(pdf)) {
            document.setMargins(60, 20, 120, 20); // Ajustar márgenes


            // TITULO PDF
            document.add(new Paragraph("Factura de "+alumno.getNombre()+" "+alumno.getApellido()).setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));



            //  tabla para el detalle del cliente
            //  tabla horizontal para el detalle del cliente
            Table detalleClienteTable = new Table(3)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setWidth( pdf.getDefaultPageSize().getWidth() - 120); // Ajustar posición y tamaño de la tabla
            // Agregar filas a la tabla con la información del cliente
            detalleClienteTable.addCell(new Paragraph("Legajo:"));
            detalleClienteTable.addCell(new Paragraph("Alumno"));
            detalleClienteTable.addCell(new Paragraph("Email:"));
            detalleClienteTable.addCell(new Paragraph(alumno.getLegajo()));
            detalleClienteTable.addCell(new Paragraph(alumno.getNombre()+" "+alumno.getApellido()));
            detalleClienteTable.addCell(new Paragraph(alumno.getEmail()));

            document.add(detalleClienteTable);
        document.add(new Paragraph("\n"));
            for (Factura factura : alumno.getListFacturas()) {
                document.add(new Paragraph("Código: " + factura.getIdFactura()));
                document.add(new Paragraph("Curso: " + factura.getCurso().name()));
                document.add(new Paragraph("Precio: $" + factura.getCurso().getPrecio()));
                document.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------------------------"));
            }

            // Calcular y agregar precio total
            double precioTotal = alumno.getListFacturas().stream().mapToDouble(Factura::getValor).sum();
            document.add(new Paragraph("Precio Total: $" + precioTotal).setBold().setFontSize(14).setTextAlignment(TextAlignment.RIGHT));

        }

    }


    static class HeaderEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            Rectangle pageSize = page.getPageSize();
            //  tamaño de la página y ajuste la posición de la imagen
            Rectangle rect = ((PdfPage) page).getPageSize();
            float x = rect.getLeft() + 20;  // Ajustar la posición x
            float y = rect.getTop() - 70;   // Ajustar la posición y

            try {
                Image headerImage = new Image(ImageDataFactory.create("src/assets/UTN.jpeg"));
                headerImage.setFixedPosition(x, y);
                headerImage.setWidth(150);
                new Canvas(canvas, pdfDoc, rect).add(headerImage);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    static class FooterEventHandler implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfDocument pdfDoc = docEvent.getDocument();
            PdfPage page = docEvent.getPage();
            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

            //  tamaño de la página y ajuste la posición del pie de página
            Rectangle rect = page.getPageSize();
            float x = rect.getLeft() + 10;  // Ajustar la posición x
            float y = rect.getBottom() + 20;  // Ajustar la posición y

            //  contenido para el pie de página
            Paragraph footer = new Paragraph("Dirección: Calle Buque Pesquero Dorrego N° 281, Mar del Plata, Argentina | Teléfono: (0223) 480-1220 | Email: \n" +
                    "informes@mdp.utn.edu.ar")
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER);

            Link instagramLink = new Link("Intagram: @utnmardelplata", PdfAction.createURI("http://www.instagram.com/utnmardelplata"));
            Link paginaWeb = new Link("Sitio Web: mdp.utn.edu.ar ", PdfAction.createURI("https://mdp.utn.edu.ar/"));



            //  párrafo del pie de página con el enlace y el ícono de Instagram
            Paragraph footer2 = null; // Agregar ícono de Instagram
            footer2 = new Paragraph()
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(50)
                    .add("\n")
                    .add(instagramLink)
                    .add("\n")
                    .add(paginaWeb);



            Paragraph pageNumber = new Paragraph("Página " + pdfDoc.getPageNumber(page))
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER);

            //   pie de página al canvas
            new Canvas(canvas, pdfDoc, rect).showTextAligned(footer, x + rect.getWidth() / 2, y + 40, TextAlignment.CENTER);
            new Canvas(canvas, pdfDoc, rect).showTextAligned(footer2, x + rect.getWidth() / 2, y + 15, TextAlignment.CENTER);
            new Canvas(canvas, pdfDoc, rect).showTextAligned(pageNumber, x + rect.getWidth() / 2, y, TextAlignment.CENTER);
        }


    }
}

