package br.com.PetRegistry.service;

import br.com.PetRegistry.Util.RegrasDeNegocioExceptions;
import br.com.PetRegistry.model.*;
import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class StatusValidator {
    /**
     * Valida se uma string corresponde a um valor do enum PetStatus.
     * @param status A string a ser validada.
     * @throws RegrasDeNegocioExceptions se o status for inválido.
     */
    public void validarStatusPet(String status) {
        try{
            PetStatus.valueOf(status.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new RegrasDeNegocioExceptions("Status inválido: " + status +". Valores aceitos: " + Arrays.toString(PetStatus.values()));
        }
    }
}
