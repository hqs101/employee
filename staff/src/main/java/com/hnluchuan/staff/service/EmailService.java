package com.hnluchuan.staff.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class EmailService {
	private static Logger logger = Logger.getLogger(EmailService.class);
	@Value("${email.host}")
    private String host;
	
	@Value("${email.port}")
    private int port;
	
    private String auth = "true";
    private String timeout = "25000";
    
    @Value("${email.from}")
    private String from;
    
    @Value("${email.username}")
    private String username;
    
    @Value("${email.password}")
    private String password;
    
    private String socketFactoryClass = "javax.net.SocketFactory";
    private String socketFactoryFallback = "false";

    public void sendMail(String subject, String content, List<String> receivers) {
        if (logger.isDebugEnabled()) {
            logger.debug("Start to send email, subject:    " + subject);
        }
        if (CollectionUtils.isEmpty(receivers)) {
            throw new IllegalArgumentException("Mail receivers cann't be null.");
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties javaMailProperties = new Properties();
        mailSender.setJavaMailProperties(javaMailProperties);
        mailSender.setUsername(getUsername());
        mailSender.setPassword(getPassword());
        mailSender.setHost(getHost());
        mailSender.setPort(getPort());
        
        javaMailProperties.setProperty("mail.smtp.auth", getAuth());
        javaMailProperties.setProperty("mail.smtp.timeout", getTimeout());
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", getSocketFactoryClass());
        javaMailProperties.setProperty("mail.smtp.socketFactory.fallback", getSocketFactoryFallback());

        try {
            String[] to = new String[receivers.size()];
            for (int i = 0; i < receivers.size(); i++) {
                to[i] = receivers.get(i);
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(getFrom());
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
            mailMessage.setSentDate(new Date());
            mailSender.send(mailMessage);
            if (logger.isDebugEnabled()) {
                logger.debug("Send mail successfully, subject: " + subject);
            }
        } catch (MailException e) {
            logger.error(e);
        }
    }

    public void sendMail(String subject, String content, String receiver) {
        List<String> receivers = new ArrayList<String>();
        receivers.add(receiver);
        sendMail(subject, content, receivers);
    }
    
    public void sendMail(String subject, String content, String[] receivers) {
        sendMail(subject, content, Arrays.asList(receivers));
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getTimeout() {
        return timeout;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    public void setSocketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getSocketFactoryFallback() {
        return socketFactoryFallback;
    }

    public void setSocketFactoryFallback(String socketFactoryFallback) {
        this.socketFactoryFallback = socketFactoryFallback;
    }

    public void sendHtmlMessage(String subject, String content, List<String> receivers) {
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setCharset("utf-8");
            htmlEmail.setSubject(subject);
            htmlEmail.setFrom(getFrom());
            htmlEmail.setHostName(getHost());
            htmlEmail.setSmtpPort(getPort());
            htmlEmail.setSslSmtpPort(getPort() + "");
            htmlEmail.setHostName(getHost());
            htmlEmail.setAuthentication(getUsername(), getPassword());
            List<InternetAddress> to = new ArrayList<InternetAddress>();
            for (String email : receivers) {
                InternetAddress address = new InternetAddress(email);
                to.add(address);
            }
            htmlEmail.setHtmlMsg(content);
            htmlEmail.setTo(to);
            htmlEmail.send();
        } catch (Exception e) {
            logger.error(e, e);
        }
     }

    public static void main(String[] args) {
        new EmailService().sendHtmlMessage("这是主题", "<div>test content你好呀</div>", Lists.newArrayList("441868461@qq.com"));
    }
}
