package com.spring.pro22member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.pro22member.vo.MemberVO;


public class MemberDAOImpl implements MemberDAO {
	private JdbcTemplate jdbcTemplate;
	
	// 설정파일에서 생성한 dataSource빈을 setter을 이용해 JdbcTemplate 클래스 생성자의 인자로 입력함
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List selectAllMembers() throws DataAccessException {
		String query = "select id,pwd,name,email,joinDate" + " from t_member " + " order by joinDate desc";
		List membersList = new ArrayList();

		/* JdbcTemplate클래스의 query() 메서드 인자로 select문을 전달해 조회한 레코드의
		 * 개수만큼 MemberVO객체를 생성함. 각 레코드의 값을 속성에 저장하고
		 * 다시 memberList에 저장함 */
		membersList = this.jdbcTemplate.query(query, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				return memberVO;
			}
		});
		return membersList;
	}

	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String query = "insert into t_member(id,pwd, name,email) values  ("
		                 + "'" + id + "' ,"
	 	                 + "'" + pwd + "' ,"
		                 + "'" + name + "' ,"
		                 + "'" + email + "') ";
		System.out.println(query);
		
		// JdbcTemplate클래스의 update() 메서드로 회원 정보를 추가함
		int result = jdbcTemplate.update(query);
		System.out.println(result);
		return result;
	}
}
