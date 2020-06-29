package user.facade;

import members.dto.CreateMemberDto;
import members.service.MemberService;
import user.dto.CreateUserDto;
import user.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserFacadeImpl implements UserFacade{

    public UserFacadeImpl(UserService userService, MemberService memberService){
        this.userService = userService;
        this.memberService = memberService;
    }

    @Inject
    private UserService userService;

    @Inject
    private MemberService memberService;

    @Transactional
    @Override
    public int saveWithMember(CreateUserDto createUserDto){
        try {
            int userId = userService.save(createUserDto);
            CreateMemberDto createMemberDto = new CreateMemberDto(2, userId);
            memberService.save(createMemberDto);
            return userId;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}


