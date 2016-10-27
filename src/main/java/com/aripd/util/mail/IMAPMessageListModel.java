package com.aripd.util.mail;

import java.io.IOException;
import java.util.Date;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import org.apache.log4j.Logger;

public class IMAPMessageListModel {

    static final Logger LOG = Logger.getLogger(IMAPMessageListModel.class.getName());

    private Date receivedDate;
    private Date sentDate;
    private boolean expunged;
    private String subject;
    private int messageNumber;
    private String from;
    private String recipientsTo;
    private String recipientsCc;

    public IMAPMessageListModel() {
    }

    public IMAPMessageListModel(Message message) throws MessagingException, IOException {
        Address[] fromAddresses = message.getFrom();

        this.expunged = message.isExpunged();
        this.from = fromAddresses[0].toString();// InternetAddress.toString(message.getFrom());
        this.messageNumber = message.getMessageNumber();
        this.receivedDate = message.getReceivedDate();
        this.sentDate = message.getSentDate();
        this.subject = message.getSubject();
        this.recipientsTo = parseAddresses(message.getRecipients(Message.RecipientType.TO));// InternetAddress.toString(message.getRecipients(Message.RecipientType.TO))
        this.recipientsCc = parseAddresses(message.getRecipients(Message.RecipientType.CC));// InternetAddress.toString(message.getRecipients(Message.RecipientType.CC))
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
