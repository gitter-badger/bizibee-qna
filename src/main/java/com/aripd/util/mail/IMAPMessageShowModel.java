package com.aripd.util.mail;

import java.io.IOException;
import java.util.Date;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import org.apache.log4j.Logger;

public class IMAPMessageShowModel {

    static final Logger LOG = Logger.getLogger(IMAPMessageShowModel.class.getName());

    private Date receivedDate;
    private Date sentDate;
    private boolean expunged;
    private String subject;
    private int messageNumber;
    private String from;
    private String recipientsTo;
    private String recipientsCc;
    private String contentType;
    private String content;

    public IMAPMessageShowModel() {
    }

    public IMAPMessageShowModel(Message message) throws MessagingException, IOException {
        Object msgContent = message.getContent();
        Address[] fromAddresses = message.getFrom();
        
        //Flags flags = message.getFlags();
        //flags.contains(Flags.Flag.SEEN);

        this.expunged = message.isExpunged();
        this.from = fromAddresses[0].toString();// InternetAddress.toString(message.getFrom());
        this.messageNumber = message.getMessageNumber();
        this.receivedDate = message.getReceivedDate();
        this.sentDate = message.getSentDate();
        this.subject = message.getSubject();
        this.recipientsTo = parseAddresses(message.getRecipients(Message.RecipientType.TO));// InternetAddress.toString(message.getRecipients(Message.RecipientType.TO))
        this.recipientsCc = parseAddresses(message.getRecipients(Message.RecipientType.CC));// InternetAddress.toString(message.getRecipients(Message.RecipientType.CC))
        this.contentType = message.getContentType();

        if (msgContent instanceof Multipart) {
            Multipart multipart = (Multipart) msgContent;

            LOG.info("MultiPartCount: " + multipart.getCount());
            for (int j = 0; j < multipart.getCount(); j++) {
                BodyPart bodyPart = multipart.getBodyPart(j);
                String disposition = bodyPart.getDisposition();
                if (disposition != null && (disposition.equalsIgnoreCase(Part.ATTACHMENT))) {
                    LOG.info("Mail have some attachment");
                    DataHandler handler = bodyPart.getDataHandler();
                    LOG.info("file name: " + handler.getName());
                } else {
                    //msgContent = getText(bodyPart);  // the changed code
                    msgContent = bodyPart.getContent();
                }
            }

        } else {
            msgContent = message.getContent();
        }

        this.content = msgContent.toString();
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public boolean isExpunged() {
        return expunged;
    }

    public void setExpunged(boolean expunged) {
        this.expunged = expunged;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRecipientsTo() {
        return recipientsTo;
    }

    public void setRecipientsTo(String recipientsTo) {
        this.recipientsTo = recipientsTo;
    }

    public String getRecipientsCc() {
        return recipientsCc;
    }

    public void setRecipientsCc(String recipientsCc) {
        this.recipientsCc = recipientsCc;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String parseAddresses(Address[] addresses) {
        String listOfAddress = "";
        if ((addresses == null) || (addresses.length < 1)) {
            return null;
        }
        if (!(addresses[0] instanceof InternetAddress)) {
            return null;
        }

        for (Address address : addresses) {
            InternetAddress internetAddress = (InternetAddress) addresses[0];
            listOfAddress += internetAddress.getAddress() + ",";
        }
        return listOfAddress;
    }

}
