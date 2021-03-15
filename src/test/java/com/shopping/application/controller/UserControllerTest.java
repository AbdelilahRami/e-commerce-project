package com.shopping.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shopping.application.controller.exception.UserControllerException;
import com.shopping.application.dto.AddressDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.exception.UserNotFoundException;
import com.shopping.application.mapper.UserMapper;
import com.shopping.application.models.Address;
import com.shopping.application.models.User;
import com.shopping.application.service.UserService;

public class UserControllerTest {

    private User user;
    private Address address;
    private UserDto userDto;
    private AddressDto addressDto;
    private MockMvc mockMvc;
    private UserMapper mappeur;
    private UserService service;
    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule());
    
    @BeforeEach
    void setUp() {
        address = new Address();
        address.setPostalCode("test");
        address.setAddress("test");
        address.setCity("test");
        
        user = new User();
        user.setId(UUID.fromString("ec91018d-5103-4774-bd0c-f0bd85321f38"));
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("test");
        user.setPhone("test");
        user.setAddress(address);
        
        addressDto = new AddressDto();
        addressDto.setPostalCode("test");
        addressDto.setAddress("test");
        addressDto.setCity("test");

        userDto = new UserDto();
        userDto.setId("ec91018d-5103-4774-bd0c-f0bd85321f38");
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setPassword("test");
        userDto.setPhone("test");
        userDto.setAddress(addressDto);

        service = mock(UserService.class);
        mappeur = mock(UserMapper.class);
        UserController controller = new UserController(service, mappeur);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).
                setControllerAdvice(new UserControllerException()).build();
    }
    
    /*--------------------get all--------------------*/
    @Test
    void getAllShouldReturnStatusOkAndUsersList() throws Exception {
        List<User> users = Collections.singletonList(user);

        when(service.getAll()).thenReturn(users);
        when(mappeur.usersToUserDtos(users)).thenReturn(Collections.singletonList(userDto));

        String mockResult = mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<UserDto> result = Arrays.asList(objectMapper.readValue(mockResult, UserDto[].class));

        verify(service, times(1)).getAll();
        verifyNoMoreInteractions(service);
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(userDto);
    }
    
    /*--------------------create--------------------*/
    @Test
    void createShouldReturnOkAndTheUser() throws Exception {
        userDto.setId(null);
        user.setId(null);
        when(mappeur.userDtoToUser(userDto)).thenReturn(user);
        when(service.createUser(user)).thenReturn(user);
        when(mappeur.userToUserDto(user)).thenReturn(userDto);

        String mockResult = mockMvc
                .perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(service, times(1)).createUser(user);
        verifyNoMoreInteractions(service);

        UserDto result = objectMapper.readValue(mockResult, UserDto.class);
        assertThat(result).isEqualTo(userDto);
    }
    
    /*----------------------getById------------------------------*/
    @Test
    void getByIdShouldReturnUserDto() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f38";

        when(service.getById(id)).thenReturn(user);
        when(mappeur.userToUserDto(user)).thenReturn(userDto);

        String mockResult = mockMvc.perform(get("/api/v1/users/{id}", id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto result = objectMapper.readValue(mockResult, UserDto.class);
        assertThat(result).isEqualTo(userDto);
    }
    
    @Test
    void getByIdShouldReturn404WhenUserNotFound() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321455";

        when(service.getById(id)).thenThrow(UserNotFoundException.class);
        
        mockMvc.perform(get("/api/v1/users/{id}", id))
                .andExpect(status().isNotFound());
            
    }
    
    /*----------------------update ------------------------------*/
    @Test
    void updateShouldRetunUserDtoUpdate() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f38";

        when(mappeur.userDtoToUser(userDto)).thenReturn(user);
        when(service.updateUser(user)).thenReturn(user);
        when(mappeur.userToUserDto(user)).thenReturn(userDto);

        String mockResult = mockMvc.perform(put("/api/v1/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto result = objectMapper.readValue(mockResult, UserDto.class);
        assertThat(result).isEqualTo(userDto);
    }

    @Test
    void updateShouldRetun400WhenIdIsDifferent() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321555";

        mockMvc.perform(put("/api/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isBadRequest());
    }
    
    /*----------------------delete ------------------------------*/
    @Test
    void deleteShouldRetunUserDtoUpdate() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321f38";

        when(mappeur.userDtoToUser(userDto)).thenReturn(user);
        
        mockMvc.perform(delete("/api/v1/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteShouldRetun400WhenIdIsDifferent() throws Exception {
        String id = "ec91018d-5103-4774-bd0c-f0bd85321555";

        mockMvc.perform(delete("/api/v1/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isBadRequest());
    }
}
