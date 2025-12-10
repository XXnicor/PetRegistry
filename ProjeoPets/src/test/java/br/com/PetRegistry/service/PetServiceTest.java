package br.com.PetRegistry.service;

import br.com.PetRegistry.DTORequests.PetRequestDTO;
import br.com.PetRegistry.model.*;
import br.com.PetRegistry.repository.EventoRepository;
import br.com.PetRegistry.repository.LarTemporarioRepository;
import br.com.PetRegistry.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;
    @Mock
    private EventoRepository eventoRepository;
    @Mock
    private LarTemporarioRepository larTemporarioRepository;
    @Mock
    private StatusValidator statusValidator;
    @Mock
    private PetMapper petMapper;

    @InjectMocks
    private PetService petService;

    private PetRequestDTO petRequestDTO;
    private Pet pet;
    private LarTemporario larTemporario;

    @BeforeEach
    void setUp() {
        petRequestDTO = new PetRequestDTO(
                "Rex",
                "http://example.com/foto.jpg",
                "Canina",
                "Labrador",
                2,
                "Pet amigável",
                "Vacinado em dia",
                true,
                true,
                PetType.CACHORRO,
                SexType.MACHO,
                Porte.GRANDE,
                PetStatus.DISPONIVEL_ADOCAO,
                LocalDate.now(),
                1L
        );

        larTemporario = new LarTemporario();

        PetProfile profile = new PetProfile(
                "Rex",
                "http://example.com/foto.jpg",
                PetType.CACHORRO,
                SexType.MACHO,
                Porte.GRANDE,
                2,
                LocalDate.now(),
                "Vacinado em dia",
                "Pet amigável",
                true,
                true,
                false,
                false,
                false
        );
        pet = new Pet(PetStatus.DISPONIVEL_ADOCAO, profile, larTemporario);
    }

    @Test
    void cadastrarPet_ComLarTemporario_DeveRetornarPetSalvo() {

        when(larTemporarioRepository.findLarTemporarioById(1L)).thenReturn(Optional.of(larTemporario));
        when(petMapper.toEntity(petRequestDTO, larTemporario)).thenReturn(pet);
        when(petRepository.save(any(Pet.class))).thenReturn(pet);


        Pet petSalvo = petService.cadastrarPet(petRequestDTO);


        assertNotNull(petSalvo);
        assertEquals("Rex", petSalvo.getNomeCompleto());
        assertNotNull(petSalvo.getLarTemporario());

        verify(larTemporarioRepository, times(1)).findLarTemporarioById(1L);
        verify(petMapper, times(1)).toEntity(petRequestDTO, larTemporario);
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void deveCadastrarPetSemLarTemporario() {
        PetRequestDTO dtoSemLar = new PetRequestDTO(
                "Rex",
                "http://example.com/foto.jpg",
                "Canina",
                "Labrador",
                2,
                "Pet amigável",
                "Vacinado em dia",
                true,
                true,
                PetType.CACHORRO,
                SexType.MACHO,
                Porte.GRANDE,
                PetStatus.DISPONIVEL_ADOCAO,
                LocalDate.now(),
                null
        );

        pet.setLarTemporario(null);
        when(petMapper.toEntity(dtoSemLar, null)).thenReturn(pet);
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        Pet resultado = petService.cadastrarPet(dtoSemLar);

        assertNotNull(resultado);
        assertNull(resultado.getLarTemporario());
        verify(larTemporarioRepository, never()).findLarTemporarioById(anyLong());
        verify(petMapper).toEntity(dtoSemLar, null);
        verify(petRepository).save(pet);
    }
}