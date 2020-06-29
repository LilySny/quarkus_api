package members.service;

import members.dto.CreateMemberDto;
import members.dto.MemberDto;
import members.model.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
    Member findById(int id);
    int save(CreateMemberDto memberDto);
    void update(MemberDto memberDto);
    void delete(int id);



}
