package edu.tradivac.gui;

import edu.tradivac.services.ReclamationCrud;
import edu.tradivac.entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class AJouterReclamationFXMLController implements Initializable {

    @FXML
    private TextField tfobjet;
    @FXML
    private TextArea tfdescription;
    
    private int nbReclamations = 0;
    private ReclamationCrud rec = new ReclamationCrud();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private String generateTicket(String object, String contenu) {
    String ticketFilePath = "./ticket.pdf"; // Specify the file path for the generated ticket

    try {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set the font for the text
        PDType1Font font = PDType1Font.HELVETICA_BOLD;
        float fontSize = 12;
        contentStream.setFont(font, fontSize);

        // Set the position and dimensions of the table
        float startX = 50;
        float startY = page.getMediaBox().getHeight() - 50;
        float rowHeight = 20;

        // Generate a random number between 100 and 1000
        Random random = new Random();
        int randomNumber = random.nextInt(901) + 100; // Generates a random number from 0 to 900 and adds 100

        // Define the content information
        String[] contentInfo = {
           
            "oBJECT: " + object,
            "cONTENU: " + contenu,
   "NumeroRECLKAMTION: #"  + randomNumber        };

        // Draw the content information line by line
        float currentY = startY;
        for (String info : contentInfo) {
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, currentY);
            contentStream.showText(info);
            contentStream.endText();

            currentY -= rowHeight;
        }

        contentStream.close();
        document.save(ticketFilePath);
        document.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return ticketFilePath;
}
private void sendEmailWithTicket(String email, String contunu ,String object) throws MessagingException {
    // Sender's email address and password
    final String senderEmail = "abrarpidev@gmail.com";
    final String senderPassword = "ayjnsqdxtrjagwpg"; // Replace with your actual password

    // SMTP server configuration
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.put("mail.smtp.auth", "true"); // Enable authentication
    properties.put("mail.smtp.port", "465");

    // Create a session with the SMTP server
    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    });

    // Create a message
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(senderEmail));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
    message.setSubject("Tradiavac: Confirmation de reclamtion");
    message.setText("Objet de reclamation " + object);

    // Create the ticket attachment
    MimeBodyPart ticketPart = new MimeBodyPart();
    DataSource source = new FileDataSource(generateTicket(contunu,object));
    ticketPart.setDataHandler(new DataHandler(source));
    ticketPart.setFileName("ticket.pdf"); // Modify the file name as needed

    // Create the multipart message with the ticket attachment
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(ticketPart);

    // Set the content of the message as the multipart
    message.setContent(multipart);
       System.out.println(message);
    // Send the message
    Transport.send(message);
    System.out.println("Ticket email sent to: " + email);
}
    @FXML
    private void addEntity(ActionEvent event) throws MessagingException{
        int nbRecByUser = rec.getReclamationCountByUserIdAndDate(4);
        if (tfdescription.getText().isEmpty() || tfobjet.getText().isEmpty()) {
            System.out.println(tfdescription.getText());
            System.out.println(tfobjet.getText());
            Alert a = new Alert(Alert.AlertType.ERROR, "OBJET ou DESCRIPTION invalide(s)", ButtonType.OK);
            a.showAndWait();
            System.out.println("Réclamation ajoutée ! Nombre total des réclamations : " + nbRecByUser);
        } else {

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Reclamation r = new Reclamation(3, tfdescription.getText(), tfobjet.getText(), timestamp);
            rec.addReclamation(r);
            sendEmailWithTicket("mariem.bensalem@esprit.tn", r.getObjet(), r.getDescription());
            Alert a = new Alert(Alert.AlertType.INFORMATION, "RECLAMATION added!", ButtonType.OK);
            a.showAndWait();
            
            

            

        }
        

    }

    @FXML
    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheInterfaceReclamation.fxml"));
            Parent root = loader.load();
            tfdescription.getScene().setRoot(root);
    }
}
