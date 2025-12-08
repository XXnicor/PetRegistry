package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.DTORequests.PetUpdateDTO;
import br.com.PetRegistry.Util.RegrasDeNegocioExceptions;
import br.com.PetRegistry.model.*;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class Validators  implements ValidatorPet {
    private PetStatus petStatus;

    @Override
    public void validatorPet(PetRequestDTO petDTO) {
        if (petDTO.getNome() == null || petDTO.getNome().isBlank()) {
            throw new RegrasDeNegocioExceptions("O nome do pet não pode ser vazio");
        }
        if (petDTO.getPortePet() == null) {
            throw new RegrasDeNegocioExceptions("O porte do pet tem que ser informado");
        }
        if (petDTO.getStatusPet() == null) {
            throw new RegrasDeNegocioExceptions("O status do pet tem que ser informado");
        }
        if (petDTO.getIdade() < 0) {
            throw new RegrasDeNegocioExceptions("A idade aproximada tem que ser informada");
        }
        if (petDTO.getEntradaPet() == null) {
            throw new RegrasDeNegocioExceptions("A data de entrada tem que ser informada");
        }
    }

    @Override
    public void validatorPetAtualizado(PetUpdateDTO petAtualizado) {
        if (petAtualizado.getNome() == null || petAtualizado.getNome().isBlank()) {
            throw new RegrasDeNegocioExceptions("O nome do pet não pode ser vazio");
        }
        if (petAtualizado.getPortePet() == null) {
            throw new RegrasDeNegocioExceptions("O porte do pet tem que ser informado");
        }
        if (petAtualizado.getIdade() < 0) {
            throw new RegrasDeNegocioExceptions("A idade aproximada tem que ser informada");
        }
    }
    @Override
    public void validarStatusPet(String status) {
        try{
            PetStatus.valueOf(status.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new RegrasDeNegocioExceptions("Status inválido: " + status +". Valores aceitos: " + Arrays.toString(PetStatus.values()));
        }
    }
}
