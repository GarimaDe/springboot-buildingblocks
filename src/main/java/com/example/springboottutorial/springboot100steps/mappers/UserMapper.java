package com.example.springboottutorial.springboot100steps.mappers;

import com.example.springboottutorial.springboot100steps.dtos.UserMsDto;
import com.example.springboottutorial.springboot100steps.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //User To UserMsDto DTO

    UserMsDto userToUserDto(User entity);

    //List<User> to List<UserMsDto> DTO
//    @Mappings({@Mapping(source="email", target="emailaddress")
//    })
    List<UserMsDto> usersToUserDtos(List<User> users);
}
