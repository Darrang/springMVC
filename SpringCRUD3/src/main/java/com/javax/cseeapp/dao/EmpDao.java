package com.javax.cseeapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.javax.cseeapp.bean.Emp;

public class EmpDao {
private JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(Emp one) {
		String sql="insert into Emp99(name,salary,designation) values('"+one.getName()+"',"+one.getSalary()+",'"+one.getDesignation()+"')";
		return template.update(sql);
	}
	
	public int update(Emp one) {
	    String sql="update Emp99 set name='"+one.getName()+"', salary="+one.getSalary()+",designation='"+one.getDesignation()+"' where id="+one.getId()+"";    
		return template.update(sql);
	}
	
	public int delete(int id) {
		String sql = "delete from Emp99 where id="+id+"";
		return template.update(sql);
	}
	
	public Emp getEmpById(int id) {
		String sql ="select * from Emp99 where id=?";
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Emp>(Emp.class));
	}
	
	
	public List<Emp> getList(){
		
		return template.query("select * from Emp99", new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int row) throws SQLException{
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				e.setRegdate(rs.getDate(5));
				return e;
			}
		});
	}
}
