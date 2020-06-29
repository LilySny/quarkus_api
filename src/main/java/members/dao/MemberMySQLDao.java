package members.dao;


import com.google.inject.Inject;
import io.agroal.api.AgroalDataSource;
import members.dto.CreateMemberDto;
import members.dto.MemberDto;
import members.model.Member;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MemberMySQLDao implements MemberDao{

    public MemberMySQLDao(AgroalDataSource db){
        this.db = db;
    }

    @Inject
    private  AgroalDataSource db;

    @Override
    public List<Member> findAll(){
            try(Connection connection = db.getConnection()){
                PreparedStatement ps = connection.prepareStatement("SELECT member_id, groups_group_id, users_user_id FROM members");
                ResultSet resultSet = ps.executeQuery();

                List<Member> members = new ArrayList<>();
                while(resultSet.next()){
                    Member member = new Member();
                    member.setId(resultSet.getInt(1));
                    member.setGroupId(resultSet.getInt(2));
                    member.setUserId(resultSet.getInt(3));
                    members.add(member);
                }

                return members;

            }catch (SQLException e){
                e.getMessage();
                throw new RuntimeException("erro no memberDao");
            }
    }

    @Override
    public Member findById(int id) {
        try(Connection connection = db.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT member_id, groups_group_id, users_user_id WHERE member_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Member member = new Member();
            while(rs.next()){
                member.setId(rs.getInt("member_id"));
                member.setGroupId(rs.getInt("groups_group_id"));
                member.setUserId(rs.getInt("users_user_id"));
            }
            return member;
        } catch (SQLException e){
            e.getMessage();
            throw new RuntimeException("erro no memberDao");
        }
    }

    @Override
    public int save(CreateMemberDto memberDto) {
        try(Connection connection = db.getConnection()) {
            int id = 0;
            PreparedStatement ps = connection.prepareStatement("INSERT INTO members(groups_group_id, users_user_id) VALUES (?,?)"
                    , Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, memberDto.groupId);
            ps.setInt(2, memberDto.userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
            return id;
        } catch(SQLException e){
            e.getMessage();
            throw new RuntimeException("erro no memberDao");
        }
    }

    @Override
    public void update(MemberDto member) {
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection.prepareStatement("UPDATE members SET groups_group_id = ?," +
                    " users_user_id = ?" +
                    " WHERE member_id = ?");
            ps.setInt(1, member.id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.getMessage();
            throw new RuntimeException("erro no memberDao");
        }
    }


    @Override
    public void delete(int id) {
        try(Connection connection = db.getConnection()){
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM members WHERE member_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            e.getMessage();
            throw new RuntimeException("erro no memberDao");
        }
    }


}
