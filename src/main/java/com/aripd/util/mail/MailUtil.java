package com.aripd.util.mail;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder.FetchProfileItem;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;

public class MailUtil implements Serializable {

    static final Logger LOG = Logger.getLogger(MailUtil.class.getName());

    @Resource(name = "mail/bizibee")
    private Session mailSession;

    public MailUtil() {
    }

    @PostConstruct
    public void init() {
    }

    public static void main(String[] args) {
        MailUtil bean = new MailUtil();
        //bean.getNewEmails("imap", "mail.kutubox.com", "143", "sales@kutubox.com", "PASSWORD");
        //bean.getNewEmails("imaps", "secure195.servconfig.com", "993", "sales@kutubox.com", "PASSWORD");
        bean.getNewEmails("imaps", "imap.yandex.com.tr", "993", "dev@aripd.com", "PASSWORD");
        //bean.getNewEmails("imaps", "imap.gmail.com", "993", "dev@gmail.com", "PASSWORD");
    }

    public IMAPMessageShowResponseModel getEmail(int messageNumber, String protocol, String host, String port, String userName, String password) {
        IMAPMessageShowResponseModel model = new IMAPMessageShowResponseModel();

        long start = System.currentTimeMillis();

        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        Store store = null;
        Folder inbox = null;
        try {
            store = session.getStore(protocol);
            store.connect(userName, password);

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message message = inbox.getMessage(messageNumber);
            model.setSuccess(true);
            model.setShowModel(new IMAPMessageShowModel(message));

        } catch (NoSuchProviderException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } catch (MessagingException | IOException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } finally {
            try {
                if (null != inbox) {
                    inbox.close(false);
                }
                if (null != store) {
                    store.close();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage());
                model.setSuccess(false);
                model.setMessage(ex.getMessage());
            }
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Found message. In [" + diff + "] millis.");

        return model;
    }

    public IMAPMessageListResponseModel getNewEmails(String protocol, String host, String port, String userName, String password) {
        IMAPMessageListResponseModel model = new IMAPMessageListResponseModel();
        List<IMAPMessageListModel> list = new ArrayList<>();

        long start = System.currentTimeMillis();

        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        Store store = null;
        Folder inbox = null;
        try {
            store = session.getStore(protocol);
            store.connect(userName, password);

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            int count = inbox.getMessageCount();
            LOG.info("number of messages: " + count);
            //Message[] messages = inbox.getMessages(1, count);
            Message[] messages = inbox.getMessages(count - 10, count);
            List<Message> amessages = Arrays.asList(messages);
            Collections.reverse(amessages);
            for (Message message : amessages) {
                IMAPMessageListModel m = new IMAPMessageListModel(message);
                list.add(m);
            }

            model.setSuccess(true);
            model.setList(list);

        } catch (NoSuchProviderException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } catch (MessagingException | IOException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } finally {
            try {
                if (null != inbox) {
                    inbox.close(false);
                }
                if (null != store) {
                    store.close();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage());
                model.setSuccess(false);
                model.setMessage(ex.getMessage());
            }
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Found [" + list.size() + "] messages. In [" + diff + "] millis.");

        return model;
    }

    public IMAPMessageShowResponseModel getEmail(Integer messageNumber) {
        IMAPMessageShowResponseModel model = new IMAPMessageShowResponseModel();

        long start = System.currentTimeMillis();

        Store store = null;
        Folder inbox = null;
        try {
            store = mailSession.getStore();
            store.connect();

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message message = inbox.getMessage(messageNumber);
            model.setSuccess(true);
            model.setShowModel(new IMAPMessageShowModel(message));

        } catch (NoSuchProviderException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } catch (MessagingException | IOException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } finally {
            try {
                if (null != inbox) {
                    inbox.close(false);
                }
                if (null != store) {
                    store.close();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage());
                model.setSuccess(false);
                model.setMessage(ex.getMessage());
            }
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Found message. In [" + diff + "] millis.");

        return model;
    }

    public IMAPMessageListResponseModel getNewEmails() {
        IMAPMessageListResponseModel model = new IMAPMessageListResponseModel();
        List<IMAPMessageListModel> list = new ArrayList<>();

        long start = System.currentTimeMillis();

        Store store = null;
        Folder inbox = null;
        try {
            store = mailSession.getStore();
            store.connect();

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            int count = inbox.getMessageCount();
            LOG.info("number of messages: " + count);
            //Message[] amessages = inbox.getMessages(1, count);
            //Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            Message[] messages = inbox.getMessages(count - 10, count);

            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            fp.add(FetchProfileItem.FLAGS);
            fp.add(FetchProfileItem.CONTENT_INFO);
            fp.add("X-mailer");
            inbox.fetch(messages, fp); // Load the profile of the messages in 1 fetch.

            //List<Message> amessages = Arrays.asList(messages);
            //Collections.reverse(amessages);
            for (Message message : messages) {
                IMAPMessageListModel m = new IMAPMessageListModel(message);
                list.add(m);
            }

            model.setSuccess(true);
            model.setList(list);

        } catch (NoSuchProviderException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } catch (MessagingException | IOException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } finally {
            try {
                if (null != inbox) {
                    inbox.close(false);
                }
                if (null != store) {
                    store.close();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage());
                model.setSuccess(false);
                model.setMessage(ex.getMessage());
            }
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Found [" + list.size() + "] messages. In [" + diff + "] millis.");

        return model;
    }

    private Properties getServerProperties(String protocol, String host, String port) {
        boolean fallback = !protocol.equalsIgnoreCase("imaps") && !protocol.equalsIgnoreCase("smtps");

        Properties properties = new Properties();
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);
        properties.setProperty(String.format("mail.%s.socketFactory.class", protocol), "javax.net.ssl.SSLSocketFactory");
        properties.setProperty(String.format("mail.%s.socketFactory.fallback", protocol), String.valueOf(fallback));
        properties.setProperty(String.format("mail.%s.socketFactory.port", protocol), String.valueOf(port));

        return properties;
    }

    public IMAPMessageSendResponseModel sendEmail(IMAPMessageShowModel model, String protocol, String host, String port, String userName, String password) {
        return sendEmail(model.getFrom(), model.getRecipientsTo(), model.getSubject(), model.getContent(), protocol, host, port, userName, password);
    }

    public IMAPMessageSendResponseModel sendEmail(String from, String to, String subject, String content, String protocol, String host, String port, String userName, String password) {
        IMAPMessageSendResponseModel model = new IMAPMessageSendResponseModel();

        long start = System.currentTimeMillis();

        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from, from, "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(subject, "UTF-8");
            message.setText(content, "UTF-8");
            //message.setContent(msg, "text/html; charset=utf-8");
            message.setReplyTo(message.getFrom());
            message.setHeader("X-Mailer", "My Mailer");
            message.setSentDate(new Date());

            Transport.send(message);
            model.setSuccess(true);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Found message. In [" + diff + "] millis.");

        return model;
    }

    public IMAPMessageSendResponseModel sendEmail(IMAPMessageShowModel model) {
        return sendEmail(model.getRecipientsTo(), model.getSubject(), model.getContent());
    }

    public IMAPMessageSendResponseModel sendEmail(String to, String subject, String content) {
        IMAPMessageSendResponseModel model = new IMAPMessageSendResponseModel();

        long start = System.currentTimeMillis();

        try {
            MimeMessage message = new MimeMessage(mailSession);

            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from"), mailSession.getProperty("mail.from"), "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject(subject, "UTF-8");
            message.setText(content, "UTF-8");
            //message.setContent(msg, "text/html; charset=utf-8");
            message.setReplyTo(message.getFrom());
            message.setHeader("X-Mailer", "My Mailer");
            message.setSentDate(new Date());

            Transport.send(message);
            model.setSuccess(true);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Found message. In [" + diff + "] millis.");

        return model;
    }

    public IMAPMessageSendResponseModel replyEmail(int messageNumber, IMAPMessageShowModel model, String protocol, String host, String port, String userName, String password) {
        return replyEmail(messageNumber, model.getContent(), protocol, host, port, userName, password);
    }

    public IMAPMessageSendResponseModel replyEmail(int messageNumber, String content, String protocol, String host, String port, String userName, String password) {
        IMAPMessageSendResponseModel model = new IMAPMessageSendResponseModel();

        long start = System.currentTimeMillis();

        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);

        Store store = null;
        Folder inbox = null;
        try {
            store = session.getStore();
            store.connect();

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message incoming = inbox.getMessage(messageNumber);

            MimeMessage replyMessage = (MimeMessage) incoming.reply(false);

            replyMessage.setFrom(InternetAddress.toString(incoming.getRecipients(Message.RecipientType.TO)));
            //replyMessage.setText(content, "UTF-8");
            //replyMessage.setContent(msg, "text/html; charset=utf-8");
            //replyMessage.setReplyTo(incoming.getReplyTo());
            replyMessage.setHeader("X-Mailer", "My Mailer");
            replyMessage.setSentDate(new Date());

            MimeMultipart multipart = new MimeMultipart();
            BodyPart part = new MimeBodyPart();
            part.setText(content);
            multipart.addBodyPart(part);
            part = new MimeBodyPart();
            part.setText("\n\n----- Original Message -----");
            multipart.addBodyPart(part);
            part = new MimeBodyPart();
            part.setContent(incoming.getContent(), incoming.getContentType());
            multipart.addBodyPart(part);
            replyMessage.setContent(multipart);

            Transport.send(replyMessage, replyMessage.getAllRecipients());
            model.setSuccess(true);

        } catch (NoSuchProviderException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } catch (MessagingException | IOException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } finally {
            try {
                if (null != inbox) {
                    inbox.close(false);
                }
                if (null != store) {
                    store.close();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage());
                model.setSuccess(false);
                model.setMessage(ex.getMessage());
            }
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Sent message. In [" + diff + "] millis.");

        return model;
    }

    public IMAPMessageSendResponseModel replyEmail(int messageNumber, IMAPMessageShowModel model) {
        return replyEmail(messageNumber, model.getContent());
    }

    public IMAPMessageSendResponseModel replyEmail(int messageNumber, String content) {
        IMAPMessageSendResponseModel model = new IMAPMessageSendResponseModel();

        long start = System.currentTimeMillis();

        Store store = null;
        Folder inbox = null;
        try {
            store = mailSession.getStore();
            store.connect();

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message incoming = inbox.getMessage(messageNumber);

            MimeMessage replyMessage = (MimeMessage) incoming.reply(false);

            replyMessage.setFrom(InternetAddress.toString(incoming.getRecipients(Message.RecipientType.TO)));
            //replyMessage.setText(content, "UTF-8");
            //replyMessage.setContent(msg, "text/html; charset=utf-8");
            //replyMessage.setReplyTo(incoming.getReplyTo());
            replyMessage.setHeader("X-Mailer", "My Mailer");
            replyMessage.setSentDate(new Date());

            MimeMultipart multipart = new MimeMultipart();
            BodyPart part = new MimeBodyPart();
            part.setText(content);
            multipart.addBodyPart(part);
            part = new MimeBodyPart();
            part.setText("\n\n----- Original Message -----");
            multipart.addBodyPart(part);
            part = new MimeBodyPart();
            part.setContent(incoming.getContent(), incoming.getContentType());
            multipart.addBodyPart(part);
            replyMessage.setContent(multipart);

            Transport.send(replyMessage, replyMessage.getAllRecipients());
            model.setSuccess(true);

        } catch (NoSuchProviderException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } catch (MessagingException | IOException ex) {
            LOG.error(ex.getMessage());
            model.setSuccess(false);
            model.setMessage(ex.getMessage());
        } finally {
            try {
                if (null != inbox) {
                    inbox.close(false);
                }
                if (null != store) {
                    store.close();
                }
            } catch (Exception ex) {
                LOG.error(ex.getMessage());
                model.setSuccess(false);
                model.setMessage(ex.getMessage());
            }
        }

        long end = System.currentTimeMillis();
        long diff = end - start;
        LOG.info("Sent message. In [" + diff + "] millis.");

        return model;
    }

}
