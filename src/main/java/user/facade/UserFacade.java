package user.facade;

import user.dto.CreateUserDto;

public interface UserFacade {
    int saveWithMember(CreateUserDto createUserDto);
}
