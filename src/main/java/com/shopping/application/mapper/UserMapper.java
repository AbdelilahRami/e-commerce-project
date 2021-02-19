package com.shopping.application.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shopping.application.dto.AddresseDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.models.Address;
import com.shopping.application.models.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
 
    @Mapping(target = "id" , expression = "java(user.getId().toString())")
    public abstract UserDto userToUserDto(User user);
    public abstract List<UserDto> usersToUserDtos(List<User> users);
    @Mapping(target = "id" , expression = "java(java.util.UUID.fromString(userDto.getId()))")
    public abstract User userDtoToUser(UserDto userDto);
    
    public abstract AddresseDto adresseToAdresseDto(Address address);
    public abstract Address adresseDtoToAdress(AddresseDto addresseDto);
}
