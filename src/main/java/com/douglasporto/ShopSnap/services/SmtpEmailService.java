package com.douglasporto.ShopSnap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

  private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

  @Autowired
  private MailSender mailSender;

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendEmail(SimpleMailMessage msg) {

    LOG.info("Enviando...");
    LOG.info(msg.toString());
    LOG.info("Email enviado!");

    mailSender.send(msg);
  }

  @Override
  public void sendHtmlEmail(MimeMessage msg) {
    LOG.info("Enviando...");
    LOG.info(msg.toString());
    LOG.info("Email enviado!");

    javaMailSender.send(msg);
  }
}
