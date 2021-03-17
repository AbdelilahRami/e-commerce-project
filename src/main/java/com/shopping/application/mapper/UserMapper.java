package com.shopping.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shopping.application.dto.AddressDto;
import com.shopping.application.dto.UserDto;
import com.shopping.application.models.Address;
import com.shopping.application.models.User;

@Mapper( imports = Helper.class, componentModel = "spring")
public abstract class UserMapper {
 
    @Mapping(target = "id" , expression = "java(user.getId().toString())")
    public abstract UserDto userToUserDto(User user);
    public abstract List<UserDto> usersToUserDtos(List<User> users);
    
    @Mapping(target = "id",  expression = "java(Helper.manageUUIDConversion(userDto.getId()))")
    public abstract User userDtoToUser(UserDto userDto);
    
    public abstract AddressDto adresseToAdressDto(Address address);
    public abstract Address adresseDtoToAdress(AddressDto addresseDto);
}
