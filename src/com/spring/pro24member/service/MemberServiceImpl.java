package com.spring.pro24member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.pro24member.dao.MemberDAO;
import com.spring.pro24member.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO;
	
	// 속성 memberDAO에 memberDAO빈을 주입하기 위해 setter를 구현함
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		return memberDAO.insertMember(memberVO);
	}
	
	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.deleteMember(id);
	}
	
	@Override
	public MemberVO findMember(String id) throws DataAccessException {
		MemberVO memberVO = memberDAO.selectMember(id);
		return memberVO;
	}
}
