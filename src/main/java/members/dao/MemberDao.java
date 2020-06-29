package members.dao;

import members.dto.CreateMemberDto;
import members.dto.MemberDto;
import members.model.Member;

import java.util.List;

public interface MemberDao {
    List<Member> findAll();
    Member findById(int id);
    int save(CreateMemberDto createMemberDto);
    void update(MemberDto memberDto);
    void delete(int id);
}
