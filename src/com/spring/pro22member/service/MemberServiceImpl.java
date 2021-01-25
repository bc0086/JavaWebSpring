package com.spring.pro22member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.pro22member.dao.MemberDAO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;

	// memberDAO속성에 setter를 이용하여 설정 파일에서 생성된 memberDAO 빈을 주입함
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMembers();
		return membersList;
	}

}
