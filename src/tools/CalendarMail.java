/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.*;

public class CalendarMail {

    
    public static void main(String[] args) throws Exception {

        Properties config = new Properties();
        config.put("mail.smtp.host", "mail.coopertradicao.com.br");
        config.put("mail.smtp.port", "587");
        config.put("mail.smtp.auth", "true");

        javax.mail.Authenticator autenticacao = new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication("luiz.barcellos", "Lu!z12345");
            }
        };

        Session session = Session.getDefaultInstance(config, autenticacao);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(
                "contabilidade@coopertradicao.com.br"));
        mimeMessage.addRecipients(Message.RecipientType.TO,
                "luiz.barcellos@coopertradicao.com.br");
        mimeMessage.addRecipients(Message.RecipientType.TO,
                "barcellos.luizfernando@gmail.com");

        StringBuffer sb = new StringBuffer();
        sb.append("BEGIN:VCALENDAR\n");
        sb.append("PRODID:-//Coopertradição//Fechamento Contábil//PT\n");
        sb.append("VERSION:2.0\n");
        sb.append("METHOD:REQUEST\n");
        sb.append("BEGIN:VEVENT\n");
        sb.append("UID:reuniao023-minhaempresa.com.br\n");
        sb.append("ORGANIZER;CN=\"Contabilidade\":mailto: contabilidade@coopertradicao.com.br\n");
        sb.append("DTSTART:20180415T203000\n");
        sb.append("DTEND:20180415T210000\n");
        sb.append("LOCATION:Sala de reunião 3 - Térreo\n");
        sb.append("STATUS:CONFIRMED\n");
        sb.append("CATEGORIES:APPOINTMENT\n");
        sb.append("SUMMARY:Reunião gerencial\n");
        sb.append("DESCRIPTION:Elaboração de nova \\nestratégia comercial \n");
        sb.append(" para o próximo trimestre\n");
        sb.append("END:VEVENT\n");
        sb.append("END:VCALENDAR");

        ByteArrayDataSource ds = new ByteArrayDataSource(sb.toString().
                getBytes("UTF-8"),
                "text/calendar;method=REQUEST;charset=\"UTF-8\"");
        DataHandler dh = new DataHandler(ds);

        Multipart multipart = new MimeMultipart();
        MimeBodyPart mimepart = new MimeBodyPart();
        mimepart.setDataHandler(dh);
        multipart.addBodyPart(mimepart);
        mimeMessage.setContent(multipart);

        Transport.send(mimeMessage);
    }
}
