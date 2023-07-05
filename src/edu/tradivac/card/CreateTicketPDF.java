/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.card;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import edu.tradivac.bda.UserDAO;
import edu.tradivac.entities.Pack;
import java.sql.SQLException;
import edu.tradivac.entities.DetailsPack;
import edu.tradivac.entities.Tools;
import java.io.FileOutputStream;
import java.nio.file.Path;

/**
 *
 * @author itoga
 */
public class CreateTicketPDF {
    
    private Tools tools = new Tools();

    private static int ID = 1;
    private Pack pack;
    private UserDAO ticketDAO;
    private File file;
    
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/YY");
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public File getFile() {
        return file;
    }
    
    public UserDAO getTicketDAO() {
        return ticketDAO;
    }

    public void setTicketDAO(UserDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public CreateTicketPDF() {
    }

    public String getTicketName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm");
        String formattedDateTime = LocalTime.now().format(formatter);
        String idRequest = String.format("%02d", ID);
        String fileFormat = ".pdf";

        String ticketName = "Pack" + idRequest + "_" + formattedDateTime + fileFormat;
        return ticketName;
    }

    public void createTicketPDF(Pack pack) throws FileNotFoundException, DocumentException {
        this.pack = pack;
        Path ruta = Path.of("src\\edu\\tradivac\\tickets" + "\\" + getTicketName());

        Document document = new Document();
        FileOutputStream pdfFile = new FileOutputStream(ruta.toString());
        PdfWriter.getInstance(document, pdfFile);
        document.open();
        
        createLogoPDF(document);
        createTitlePDF(document);
        createInfoParagraph(document);
        createTables(document);
        file = ruta.toFile();
        document.close();
        ID++;
    }

    public void createLogoPDF(Document document) {
        try {
            Image imagen = Image.getInstance("src/edu/tradivac//img/log.jpg");
            imagen.scaleAbsolute(100, 100);
            imagen.setAlignment(Element.ALIGN_RIGHT);
            imagen.setAbsolutePosition(450, 730);
            document.add(imagen);
        } catch (DocumentException | IOException e) {
            tools.showAlertErr("image error: " + e.getMessage());
        }
    }

    public void createTitlePDF(Document document) {
        try {
            Paragraph titulo = new Paragraph("package ticket nº" + ID + "\n",
                    FontFactory.getFont("arial", 25, Font.BOLD, BaseColor.BLUE));
            Paragraph desc = new Paragraph(pack.getDescription() + "\n\n",
                    FontFactory.getFont("arial", 15, Font.BOLD, BaseColor.BLACK));
            document.add(titulo);
            document.add(desc);
        } catch (DocumentException e) {
            tools.showAlertErr("Error en titulo: " + e.getMessage());
        }
    }

    public void createInfoParagraph(Document document) {  
        try {
            Paragraph paragrapthInfo = new Paragraph();
            paragrapthInfo.add("\n\nNuméro de commande : " + ID + "\n");
            paragrapthInfo.add("Num. référence : REF-" + pack.getIdPack() + "\n");
            paragrapthInfo.add("Commande passée par: " + ticketDAO.nombreuser(pack) + "\n");
            paragrapthInfo.add("Date de commande: " + dateFormat.format(LocalDate.now()) + " a le " + timeFormat.format(LocalTime.now()) + "h\n");
            paragrapthInfo.add("Date de début du pack : " + dateFormat.format(pack.getstartDate()) + "\n");
            paragrapthInfo.add("Date de fin du pack : " + dateFormat.format(pack.getfinalDate()) + "\n");
            paragrapthInfo.add("PRIX TOTAL DE LA COMMANDE : " + pack.getprice() + "€\n\n\n");
            paragrapthInfo.setAlignment(Element.ALIGN_RIGHT);

            document.add(paragrapthInfo);
        } catch (DocumentException e) {
            tools.showAlertErr("Erreur d'informations sur le billet: " + e.getMessage());
        } catch (SQLException e){
            tools.showAlertErr("Error: " + e.getMessage());
        }
    }

    public void createTables(Document document) {
        try {
            PdfPTable table = new PdfPTable(6);
           // table.addCell("ACT ID");
            table.addCell("NOMBRE");
            table.addCell("Nº PLACES");
            table.addCell("LA DATE DE DÉBUT");
            table.addCell("DATE DE FIN");
            table.addCell("prix");

            for (DetailsPack act : pack.getListaactivities()) {
             //   table.addCell(String.valueOf(act.getIdActivity()));
                table.addCell(act.getNombreDetailsPack());
                table.addCell(String.valueOf(act.getNumPlaces()));
                table.addCell(String.valueOf(dateFormat.format(act.getStartDate())));
                table.addCell(String.valueOf(dateFormat.format(act.getFinalDate())));
                table.addCell(String.valueOf(act.getPrice()));
            }
            
            document.add(table);
        } catch (DocumentException e) {
            tools.showAlertErr("Error loading tables: " + e.getMessage());
        }
    }
}
