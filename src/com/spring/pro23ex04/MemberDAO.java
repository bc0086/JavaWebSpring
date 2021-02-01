package com.spring.pro23ex04;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.pro23ex01.MemberVO;


public class MemberDAO {
	public static SqlSessionFactory sqlMapper = null;

	private static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			try {
				/* MemberDAO의 각 메서드 호출 시 src/mybatis/SqlMapConfig.xml에서 
				 * 설정정보를 읽은 후 데이터베이스와의 연동준비를 함 */
				String resource = "pro23mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				
				// 마이바티스를 이용하는 sqlMapper객체를 가져옴
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	// 모든 회원 정보 조회
	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> memlist = null;
		
		/* 여러 개의 레코드를 조회하므로 selectList() 메서드에 실행하고자 하는 SQL문의
		 * id를 인자로 전달함 */
		memlist = session.selectList("mapper.member.selectAllMemberList");
		return memlist;
	}

	// 선택창(id)으로 회원 정보 조회
	public MemberVO selectMemberById(String id) {
		sqlMapper=getInstance();
		SqlSession session = sqlMapper.openSession();

		// 서블릿에서 넘어온 id의 값을 selectOne() 호출 시 해당 SQL문의 조건값으로 전달
		MemberVO memberVO = (MemberVO) session.selectOne("mapper.member.selectMemberById", id);
		return memberVO;
	}

	// 선택창(pwd)으로 회원 정보 조회
	public List<MemberVO> selectMemberByPwd(int pwd) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		List<MemberVO> membersList = null;
		membersList = session.selectList("mapper.member.selectMemberByPwd", pwd);
		return membersList;
	}

	// 회원 가입 하기
	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int result = 0;
		result = session.insert("mapper.member.insertMember", memberVO);
			// 지정한 id의 SQL문에 memberVO의 값을 전달하여 회원 정보를 테이블에 추가함
		session.commit(); 
			// 수동 커밋이므로 반드시 commit()을 호출하여 영구반영할 것
		return result;
	}
	
	// 회원 가입 하기 HashMap 버전
	public int insertMember2(Map<String, String> memberMap) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.insertMember2", memberMap);
			// 메서드로 전달된 HashMap을 다시 SQL문으로 전달
		session.commit(); 
		return result;
	}

	// 회원 정보 수정하기
	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.update("mapper.member.updateMember", memberVO);
		session.commit();
		return result;
	}

	// 회원 정보 삭제하기
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.delete("mapper.member.deleteMember", id);
		session.commit();
		return result;
	}

	// 동적태그 choose 이용한 회원 정보 조회
	public List<MemberVO> searchMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> list = session.selectList("mapper.member.searchMember", memberVO);
			// 회원 검색창에서 전달된 이름과 나이 값을 memberVO에 설정하여 SQL문으로 전달
		return list;
	}

	// 동적태그 foreach 이용한 회원 정보 조회
	public List<MemberVO> foreachSelect(List nameList) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List list = session.selectList("mapper.member.foreachSelect", nameList);
			// 검색 이름이 저장된 nameList를 SQL문으로 전달함. 
		return list;
	}

	// 동적태그 foreach 이용한 회원 정보 추가 하기
	public int foreachInsert(List<MemberVO> memList) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.foreachInsert", memList);
			// 회원 정보가 저장된 memList를 SQL문으로 전달함.
			// insert문이 성공적으로 실행되면 양수를 반환함.
		session.commit(); // 반드시 commit()을 호출할 것.
		return result;
	}
	
//	// HashMap을 이용한 모든 회원 정보 조회
//	public List<HashMap<String, String>> selectAllMemberList() { 
//		sqlMapper = getInstance(); 
//    	SqlSession session = sqlMapper.openSession();
//		List<HashMap<String, String>> memlist = null; 
//		memlist = session.selectList("mapper.member.selectAllMemberList"); 
//		return memlist; 
//	 }
}
