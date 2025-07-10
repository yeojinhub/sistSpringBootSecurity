package kr.co.sist.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.exceptions.PersistenceException;

@Mapper
public interface MemberMapper {

	public int insertMember(MemberDTO mDTO) throws PersistenceException;
	public MemberDomain selectLogin(String id) throws PersistenceException;
	
} //interface
