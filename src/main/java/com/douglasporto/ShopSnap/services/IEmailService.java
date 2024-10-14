package com.douglasporto.ShopSnap.services;

import org.springframework.mail.SimpleMailMessage;

import com.douglasporto.ShopSnap.domain.Pedido;

import jakarta.mail.internet.MimeMessage;

public interface IEmailService {
  void sendOrderConfirmationEmail(Pedido obj);

  void sendEmail(SimpleMailMessage msg);

  void sendOrderConfirmationHtmlEmail(Pedido obj);

  void sendHtmlEmail(MimeMessage msg);
}
