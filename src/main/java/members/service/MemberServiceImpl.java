package members.service;

import com.google.inject.Inject;
import members.dao.MemberDao;
import members.dto.CreateMemberDto;
import members.dto.MemberDto;
import members.model.Member;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MemberServiceImpl implements MemberService {

    public MemberServiceImpl(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    @Inject
    private MemberDao memberDao;


    @Override
    public List<Member> findAll() {
        try {
            return memberDao.findAll();
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException("erro no MemberService");
        }
    }

    @Override
    public Member findById(int id) {
        try {
            return memberDao.findById(id);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException("erro no MemberService");
        }
    }

    @Override
    public void update(MemberDto memberDto) {
        try{
            memberDao.update(memberDto);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException("erro no MemberService");
        }
    }

    @Override
    public int save(CreateMemberDto memberDto) {
        try {
            return memberDao.save(memberDto);
        } catch (RuntimeException e){
            e.printStackTrace();
            e.getMessage();
            throw new RuntimeException("erro no save, MemberService");
        }
    }

    @Override
    public void delete(int id) {
        try {

            memberDao.delete(id);
        } catch (RuntimeException e){
            e.getMessage();
            throw new RuntimeException("erro no MemberService");
        }
    }
}
