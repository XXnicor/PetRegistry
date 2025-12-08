package br.com.PetRegistry.DTORequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventoRequestDTO {

    @NotBlank(message = "O tipo do evento é obrigatório")
    @Size(max = 50, message = "O tipo do evento deve ter no máximo 50 caracteres")
    private final String tipoEvento;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição do evento deve ter no máximo 500 caracteres")
    private final String descricao;

    public EventoRequestDTO(String tipoEvento, String descricao) {
        this.tipoEvento = tipoEvento;
        this.descricao = descricao;
    }
    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

}
