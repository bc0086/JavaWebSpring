package com.spring.pro24member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.pro22member.vo.MemberVO;


public interface MemberDAO {
	public List selectAllMemberList() throws DataAccessException ;
	public int insertMember(MemberVO memberVO);
	public int deleteMember(String id);

}
