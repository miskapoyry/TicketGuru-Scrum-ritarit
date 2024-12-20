package ticketguru.ticketguru.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ticketguru.DTO.SaleDTO;
import ticketguru.DTO.TicketDTO;
import ticketguru.domain.AppUser;
import ticketguru.domain.EventTicketType;
import ticketguru.domain.PaymentMethod;
import ticketguru.domain.Sale;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.EventTicketTypeRepository;
import ticketguru.repository.PaymentMethodRepository;
import ticketguru.repository.SaleRepository;
import ticketguru.repository.TicketRepository;
import ticketguru.exception.ResourceNotFoundException;
import ticketguru.service.SaleService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaleServiceTests {

    @InjectMocks
    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventTicketTypeRepository eventTicketTypeRepository;

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    private AppUser appUser;
    private EventTicketType eventTicketType;
    private PaymentMethod paymentMethod;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Valmistellaan tarvittavat mock-objektit
        appUser = new AppUser();
        appUser.setUserId(1L);

        eventTicketType = new EventTicketType();
        eventTicketType.setPrice(20.0);

        paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId(1L);
    }

    @Test
    void createSale_WhenValidData_ReturnsCreatedSale() {

        // Testidata
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setEventId(1L);
        ticketDTO.setTicketTypeId(1L);
        ticketDTO.setQuantity(2);
        ticketDTO.setUsed(false);
        ticketDTOs.add(ticketDTO);

        SaleDTO saleDTO = new SaleDTO(1L, 1L, 40.0,
                Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), 1L, ticketDTOs);

        // Mockataan käyttäjä ja lipputyyppi sekä maksutapa
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(eventTicketTypeRepository.findByEvent_EventIdAndTicketType_TicketTypeId(1L, 1L))
                .thenReturn(Optional.of(eventTicketType));
        when(paymentMethodRepository.findById(1L)).thenReturn(Optional.of(paymentMethod));

        // Luo uusi Sale-olio
        Sale newSale = new Sale(appUser, new Timestamp(System.currentTimeMillis()), paymentMethod, 40.0);
        newSale.setSaleId(1L);

        // Mockataan, että Sale tallennetaan
        when(saleRepository.save(any(Sale.class))).thenReturn(newSale);

        // Suoritetaan myynnin luominen
        SaleDTO result = saleService.createSale(saleDTO);

        // Tarkistetaan, että myynti on luotu oikein
        assertNotNull(result);
        assertEquals(1L, result.getPaymentMethodId());
        assertEquals(40.0, result.getTotalPrice());
        assertEquals(1L, result.getSaleId());
    }

    @Test
    void createSale_WhenUserNotFound_ThrowsResourceNotFoundException() {

        List<TicketDTO> ticketDTOs = new ArrayList<>();
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setEventId(1L);
        ticketDTO.setTicketTypeId(1L);
        ticketDTO.setQuantity(2);
        ticketDTOs.add(ticketDTO);

        SaleDTO saleDTO = new SaleDTO(1L, 1L, 40.0,
                Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), 1L, ticketDTOs);

        // Mockataan käyttäjän puuttuminen
        when(appUserRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> saleService.createSale(saleDTO));
    }

    @Test
    void updateSale_WhenValidData_ReturnsUpdatedSale() {

        List<TicketDTO> ticketDTOs = new ArrayList<>();
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setEventId(1L);
        ticketDTO.setTicketTypeId(1L);
        ticketDTO.setQuantity(2);
        ticketDTOs.add(ticketDTO);

        SaleDTO saleDTO = new SaleDTO(1L, 2L, 40.0,
                Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), 1L, ticketDTOs);

        // Luo olemassa oleva Sale-olio, jota päivitetään
        Sale existingSale = new Sale(appUser, new Timestamp(System.currentTimeMillis()), paymentMethod, 40.0);
        existingSale.setSaleId(1L);

        // Mockataan käyttäjä, lipputyyppi, ja maksutapa
        when(saleRepository.findById(1L)).thenReturn(Optional.of(existingSale));
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(appUser));
        when(eventTicketTypeRepository.findByEvent_EventIdAndTicketType_TicketTypeId(1L, 1L))
                .thenReturn(Optional.of(eventTicketType));
        when(paymentMethodRepository.findById(2L)).thenReturn(Optional.of(paymentMethod));

        Sale updatedSale = new Sale(appUser, new Timestamp(System.currentTimeMillis()), paymentMethod, 40.0);
        updatedSale.setSaleId(1L);
        when(saleRepository.save(any(Sale.class))).thenReturn(updatedSale);

        SaleDTO result = saleService.updateSale(1L, saleDTO);

        // Tarkistetaan, että myynti on päivitetty oikein
        assertNotNull(result);
        assertEquals(1L, result.getSaleId());
        assertEquals(1L, result.getPaymentMethodId());
        assertEquals(40.0, result.getTotalPrice());
    }

    @Test
    void updateSale_WhenSaleNotFound_ThrowsResourceNotFoundException() {

        List<TicketDTO> ticketDTOs = new ArrayList<>();
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setEventId(1L);
        ticketDTO.setTicketTypeId(1L);
        ticketDTO.setQuantity(1);
        ticketDTOs.add(ticketDTO);

        SaleDTO saleDTO = new SaleDTO(1L, 2L, 20.0,
                Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault())), 1L, ticketDTOs);

        // Mockataan, että myyntiä ei löydy
        when(saleRepository.findById(1L)).thenReturn(Optional.empty());

        // Suoritetaan ja tarkistetaan poikkeus
        assertThrows(ResourceNotFoundException.class, () -> saleService.updateSale(1L, saleDTO));
    }

    @Test
    void getSaleById_WhenSaleExists_ReturnsSale() {

        Sale existingSale = new Sale(appUser, new Timestamp(System.currentTimeMillis()), paymentMethod, 50.0);
        existingSale.setSaleId(1L);

        // Mockataan, että myynti löytyy
        when(saleRepository.findById(1L)).thenReturn(Optional.of(existingSale));

        // Suoritetaan haku
        SaleDTO result = saleService.getSaleById(1L);

        // Tarkistetaan tulos
        assertNotNull(result);
        assertEquals(1L, result.getSaleId());
        assertEquals(1L, result.getPaymentMethodId());
    }

    @Test
    void getSaleById_WhenSaleNotFound_ThrowsResourceNotFoundException() {
        // Mockataan, että myyntiä ei löydy
        when(saleRepository.findById(1L)).thenReturn(Optional.empty());

        // Suoritetaan ja tarkistetaan poikkeus
        assertThrows(ResourceNotFoundException.class, () -> saleService.getSaleById(1L));
    }

    @Test
    void deleteSale_WhenSaleExists_DeletesSale() {

        Sale existingSale = new Sale(appUser, new Timestamp(System.currentTimeMillis()), paymentMethod, 50.0);
        existingSale.setSaleId(1L);

        // Mockataan, että myynti löytyy
        when(saleRepository.existsById(1L)).thenReturn(true);

        // Suoritetaan poisto
        saleService.deleteSale(1L);

        // Varmistetaan, että poisto on suoritettu
        verify(saleRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteSale_WhenSaleNotFound_ThrowsResourceNotFoundException() {
        // Mockataan, että myyntiä ei löydy
        when(saleRepository.existsById(1L)).thenReturn(false);

        // Suoritetaan ja tarkistetaan poikkeus
        assertThrows(ResourceNotFoundException.class, () -> saleService.deleteSale(1L));
    }
}
