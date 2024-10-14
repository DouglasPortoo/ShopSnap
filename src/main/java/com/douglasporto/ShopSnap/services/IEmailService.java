package com.douglasporto.ShopSnap.services;

import org.springframework.mail.SimpleMailMessage;

import com.douglasporto.ShopSnap.domain.Pedido;

public interface IEmailService {
  void sendOrderConfirmationEmail(Pedido obj);
  void sendEmail(SimpleMailMessage msg);
}
