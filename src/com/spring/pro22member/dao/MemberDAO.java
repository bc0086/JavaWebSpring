package com.spring.pro22member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.pro22member.vo.MemberVO;


public interface MemberDAO {
	public List selectAllMembers() throws DataAccessException ;
	public int addMember(MemberVO memberVO) throws DataAccessException ;

}
