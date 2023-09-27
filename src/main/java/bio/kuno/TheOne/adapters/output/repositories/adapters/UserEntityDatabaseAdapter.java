package bio.kuno.TheOne.adapters.output.repositories.adapters;

import bio.kuno.TheOne.adapters.output.repositories.mappers.UserEntityMapper;
import bio.kuno.TheOne.domain.UserEntityDto;
import bio.kuno.TheOne.adapters.output.repositories.dtos.UserEntityDatabaseDto;
import bio.kuno.TheOne.adapters.output.repositories.jpainterfaces.UserEntityRepository;
import bio.kuno.TheOne.ports.output.repositories.UserEntityPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityDatabaseAdapter implements UserEntityPort {
    @Autowired
    private UserEntityMapper userMapper;
    @Autowired
    UserEntityRepository userRepo;
    @Override
    public UserEntityDto findById(int id) {
        UserEntityDatabaseDto user = userRepo.findById(id).orElseThrow();
        return (userMapper.toUserDto(user));
    }

    @Override
    public UserEntityDto save(UserEntityDto user) {
        UserEntityDatabaseDto savedUser = userRepo.save(userMapper.toUser(user));
        return (userMapper.toUserDto(savedUser));
    }

    @Override
    public List<UserEntityDto> getAll() {
        List<UserEntityDto> users = userRepo
                .findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
        return (users);
    }
}
