package com.shopping.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.shopping.application.dto.AddresseDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.models.Address;
import com.shopping.application.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> users);
    User userDtoToUser(UserDto userDto);
    
    AddresseDto adresseToAdresseDto(Address address);
    Address adresseDtoToAdress(AddresseDto addresseDto);
}
