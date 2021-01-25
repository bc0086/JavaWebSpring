package com.spring.pro23ex01;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
		
//	public List<MemberVO> selectAllMemberList() {
//		sqlMapper = getInstance();
//		SqlSession session = sqlMapper.openSession();
//		List<MemberVO> memlist = null;
//		
//		/* 여러 개의 레코드를 조회하므로 selectList() 메서드에 실행하고자 하는 SQL문의
//		 * id를 인자로 전달함 */
//		memlist = session.selectList("mapper.member.selectAllMemberList");
//		return memlist;
//	}
	
	// HashMap을 이용한 모든 회원 정보 조회
	public List<HashMap<String, String>> selectAllMemberList() { 
		sqlMapper = getInstance(); 
    	SqlSession session = sqlMapper.openSession();
		List<HashMap<String, String>> memlist = null; 
		memlist = session.selectList("mapper.member.selectAllMemberList"); 
		return memlist; 
	 }
}
