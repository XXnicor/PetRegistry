package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.DTORequests.PetUpdateDTO;

public interface ValidatorPet {

    void validatorPet(PetRequestDTO pet);
    void validatorPetAtualizado(PetUpdateDTO petAtualizado);
    void validarStatusPet(String status);
}
