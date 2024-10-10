package com.douglasporto.ShopSnap.services.validadions;

import java.util.List;
import java.util.ArrayList;

import com.douglasporto.ShopSnap.domain.enums.TipoCliente;
import com.douglasporto.ShopSnap.dto.ClienteNewDTO;
import com.douglasporto.ShopSnap.resources.handlers.FieldMessage;
import com.douglasporto.ShopSnap.services.validadions.utils.BR;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(objDto.tipo().equals((TipoCliente.PESSOAFISICA.getCod())) && !BR.isValidCPF(objDto.cpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if(objDto.tipo().equals((TipoCliente.PESSOAJURIDICA.getCod())) && !BR.isValidCNPJ(objDto.cpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
