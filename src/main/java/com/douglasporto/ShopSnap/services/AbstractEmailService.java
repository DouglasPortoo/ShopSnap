package com.douglasporto.ShopSnap.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.douglasporto.ShopSnap.domain.Pedido;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public abstract class AbstractEmailService implements IEmailService {

  @Value("${default.sender}")
  private String sender;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendOrderConfirmationEmail(Pedido obj) {
    SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
    sendEmail(sm);
  }

  protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
    SimpleMailMessage sm = new SimpleMailMessage();
    sm.setTo(obj.getCliente().getEmail());
    sm.setFrom(sender);
    sm.setSubject("Pedido confirmado! Código: " + obj.getId());
    sm.setSentDate(new Date(System.currentTimeMillis()));
    sm.setText(obj.toString());
    return sm;
  }

  protected String htmlFromTemplatePedido(Pedido obj) {
    Context context = new Context();
    context.setVariable("pedido", obj);
    return templateEngine.process("email/confirmacaoPedido", context);
  }

  @Override
  public void sendOrderConfirmationHtmlEmail(Pedido obj) {
    try {
      MimeMessage mm = prepareMimeMessageFromPedido(obj);
      sendHtmlEmail(mm);
    } catch (MessagingException e) {
      sendOrderConfirmationEmail(obj);
    }
  }

  protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {

    MimeMessage mm = javaMailSender.createMimeMessage();
    MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
    mmh.setTo(obj.getCliente().getEmail());
    mmh.setFrom(sender);
    mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
    mmh.setSentDate(new Date(System.currentTimeMillis()));
    mmh.setText(htmlFromTemplatePedido(obj), true);
    return mm;
  };

}
