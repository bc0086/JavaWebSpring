package com.spring.pro23ex03;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

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
	
//	// HashMap을 이용한 모든 회원 정보 조회
//	public List<HashMap<String, String>> selectAllMemberList() { 
//		sqlMapper = getInstance(); 
//    	SqlSession session = sqlMapper.openSession();
//		List<HashMap<String, String>> memlist = null; 
//		memlist = session.selectList("mapper.member.selectAllMemberList"); 
//		return memlist; 
//	 }
}
