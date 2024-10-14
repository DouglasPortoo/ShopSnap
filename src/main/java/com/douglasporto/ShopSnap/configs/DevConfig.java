package com.douglasporto.ShopSnap.configs;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.douglasporto.ShopSnap.services.DBService;
import com.douglasporto.ShopSnap.services.IEmailService;

import com.douglasporto.ShopSnap.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
  
  @Autowired
  private DBService dbService;

  @Bean
  public boolean instantiateDatabase() throws ParseException {

    dbService.instantiateTestDatabase();

    return true;
  }

  @Bean
  public IEmailService emailService() {
    return new SmtpEmailService();
  }

}
