package ticketguru.ticketguru.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ticketguru.DTO.AppUserDTO;
import ticketguru.domain.AppUser;
import ticketguru.domain.Role;
import ticketguru.repository.AppUserRepository;
import ticketguru.repository.RoleRepository;
import ticketguru.service.AppUserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppUserServiceTests {

    @InjectMocks
    private AppUserService appUserService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ReturnsAllUsers() {
        
        // Luodaan roolit käyttäjille
        Role roleUser = new Role();
        roleUser.setRoleId(1L);
        roleUser.setRoleName("ROLE_USER");

        Role roleAdmin = new Role();
        roleAdmin.setRoleId(2L);
        roleAdmin.setRoleName("ROLE_ADMIN");

        // Luodaan käyttäjät
        AppUser user1 = new AppUser("user1", "hash1", roleUser, null, null);
        AppUser user2 = new AppUser("user2", "hash2", roleAdmin, null, null);
        
        // Mockataan käyttäjätietojen haku
        when(appUserRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<AppUserDTO> result = appUserService.getAllUsers();

        // Varmistetaan, että käyttäjät palautettiin oikein
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    void getUserById_WhenUserExists_ReturnsUser() {

        Role roleUser = new Role();
        roleUser.setRoleId(1L);
        roleUser.setRoleName("ROLE_USER");

        AppUser user = new AppUser("user1", "hash1", roleUser, null, null);
        
        // Mockataan käyttäjän löytyminen ID:n perusteella
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<AppUserDTO> result = appUserService.getUserById(1L);

        // Varmistetaan, että käyttäjä löytyy
        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ReturnsEmpty() {
        when(appUserRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AppUserDTO> result = appUserService.getUserById(1L);

        // Varmistetaan, että käyttäjää ei löydy
        assertFalse(result.isPresent());
    }

    @Test
    void createUser_WhenUsernameDoesNotExist_CreatesUser() {

        // Luodaan uusi käyttäjätieto
        AppUserDTO dto = new AppUserDTO(null, "newUser", "newPassword", 1L, null, null);
        
        Role roleUser = new Role();
        roleUser.setRoleId(1L);
        roleUser.setRoleName("ROLE_USER");

        AppUser user = new AppUser("newUser", "encodedPassword", roleUser, null, null);

        // Mockataan tarkistukset ja tallennus
        when(appUserRepository.existsByUsername(dto.getUsername())).thenReturn(false);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(roleUser));
        when(passwordEncoder.encode(dto.getPasswordHash())).thenReturn("encodedPassword");
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUserDTO result = appUserService.createUser(dto);

        // Varmistetaan, että käyttäjä luotiin oikein
        assertEquals("newUser", result.getUsername());
        assertEquals("encodedPassword", result.getPasswordHash());
    }

    @Test
    void createUser_WhenUsernameExists_ThrowsException() {

        // Luodaan käyttäjätieto, jossa tunnus on jo olemassa
        AppUserDTO dto = new AppUserDTO(null, "existingUser", "password", 1L, null, null);
        when(appUserRepository.existsByUsername(dto.getUsername())).thenReturn(true);

        // Varmistetaan, että poikkeus heitetään
        assertThrows(IllegalArgumentException.class, () -> appUserService.createUser(dto));
    }

    @Test
    void updateUser_WhenUserExists_UpdatesUser() {

        // Luodaan olemassa oleva rooli ja päivitetty rooli
        Role existingRole = new Role();
        existingRole.setRoleId(1L);
        existingRole.setRoleName("ROLE_USER");

        Role updatedRole = new Role();
        updatedRole.setRoleId(2L);
        updatedRole.setRoleName("ROLE_ADMIN");

        // Luodaan olemassa oleva käyttäjä
        AppUser existingUser = new AppUser("existingUser", "oldPassword", existingRole, null, null);
        AppUserDTO updatedDTO = new AppUserDTO(1L, "updatedUser", "newPassword", 2L, null, null);

        // Mockataan tarkistukset ja tallennus
        when(appUserRepository.existsById(1L)).thenReturn(true);
        when(appUserRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(roleRepository.findById(2L)).thenReturn(Optional.of(updatedRole));
        when(passwordEncoder.encode(updatedDTO.getPasswordHash())).thenReturn("newEncodedPassword");
        when(appUserRepository.save(any(AppUser.class))).thenReturn(existingUser);

        Optional<AppUserDTO> result = appUserService.updateUser(1L, updatedDTO);

        // Varmistetaan, että käyttäjä on päivitetty oikein
        assertTrue(result.isPresent());
        assertEquals("updatedUser", result.get().getUsername());
        assertEquals("newEncodedPassword", result.get().getPasswordHash());
    }

    @Test
    void updateUser_WhenUserDoesNotExist_ReturnsEmpty() {

        // Luodaan päivitetty käyttäjätieto
        AppUserDTO updatedDTO = new AppUserDTO(1L, "updatedUser", "newPassword", 2L, null, null);
        when(appUserRepository.existsById(1L)).thenReturn(false);

        Optional<AppUserDTO> result = appUserService.updateUser(1L, updatedDTO);

        // Varmistetaan, että palautetaan tyhjä Optional
        assertFalse(result.isPresent());
    }

    @Test
    void deleteUser_WhenUserExists_DeletesUser() {
        when(appUserRepository.existsById(1L)).thenReturn(true);

        boolean result = appUserService.deleteUser(1L);

        // Varmistetaan, että poisto onnistui ja tallennus suoritettiin
        assertTrue(result);
        verify(appUserRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ReturnsFalse() {
        when(appUserRepository.existsById(1L)).thenReturn(false);

        boolean result = appUserService.deleteUser(1L);

        // Varmistetaan, että poisto ei onnistu
        assertFalse(result);
    }
}
