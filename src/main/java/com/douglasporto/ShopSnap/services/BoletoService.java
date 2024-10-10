package com.douglasporto.ShopSnap.services;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

import com.douglasporto.ShopSnap.domain.PagamentoComBoleto;

@Service
public class BoletoService {
  public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(instante);
    cal.add(Calendar.DAY_OF_MONTH, 7);
    pagto.setDataVencimento(cal.getTime());
  }
}
