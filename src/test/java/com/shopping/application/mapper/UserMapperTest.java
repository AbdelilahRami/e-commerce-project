package com.shopping.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.shopping.application.dto.AddressDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.models.Address;
import com.shopping.application.models.User;

public class UserMapperTest {

    private UserDto userDto;
    private User user;
    private AddressDto addressDto;
    private Address address;

    private UserMapper mapper = Mappers.getMapper( UserMapper.class );;
    
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
    }
    
    @Test
    void mapToUserShouldReturnUser() {
        User usermapper = mapper.userDtoToUser(userDto);
        assertThat(usermapper).isEqualTo(user);
    }
    
    @Test
    void mapToUserWithIdNullShouldReturnUserWithIdNull() {
        userDto.setId(null);
        user.setId(null);
        User userMapper = mapper.userDtoToUser(userDto);
        assertThat(userMapper).isEqualTo(user);
    }
    
    @Test
    void mapToUserDtoShouldReturnUserDto() {
        UserDto userDtoMapper = mapper.userToUserDto(user);
        assertThat(userDto).isEqualTo(userDtoMapper);
    }
    
    @Test
    void mapToUsersDtosShouldReturnUsersDtos() {
        List<UserDto> usersDtosMapper = mapper.usersToUserDtos(Collections.singletonList(user));
        assertThat(userDto).isEqualTo(usersDtosMapper.get(0));
    }
    
    @Test
    void mapToAddressShouldReturnAddress() {
        Address addressMapper = mapper.adresseDtoToAdress(addressDto);
        assertThat(address).isEqualTo(addressMapper);
    }
    
    @Test
    void mapToAddressDtoShouldReturnAddressDto() {
        AddressDto addressDtoMapper = mapper.adresseToAdressDto(address);
        assertThat(addressDto).isEqualTo(addressDtoMapper);
    }

}
