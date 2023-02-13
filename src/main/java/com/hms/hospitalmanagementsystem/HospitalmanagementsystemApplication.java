package com.hms.hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hms.hospitalmanagementsystem.Entity.UserLogin;
import com.hms.hospitalmanagementsystem.Entity.UserRole;

@SpringBootApplication
public class HospitalmanagementsystemApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(HospitalmanagementsystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Map<String, Object>> usercount=jdbcTemplate.queryForList("select count(*) as count from user_login");
		Map<String,Object> _map=usercount.get(0);
		if(Integer.parseInt(_map.get("count").toString())==0 ){
					
			UserLogin login=new UserLogin();
			//List<UserRole> roles=new ArrayList<>();
			login.setUsername("parevsh");
			login.setPassword(encoder.encode("pravesh"));
			login.setMobileNo("7007340557");
			jdbcTemplate.update("insert into user_login(`username`,`mobileNo`,`password`)values(?,?,?)", login.getUsername(),login.getMobileNo(),login.getPassword());
//userrole
		UserRole role=new UserRole();
		role.setAuthority("admin".toUpperCase());
		role.setUsername("pravesh");
		jdbcTemplate.update("insert into user_roles(authority,username)values(?,?)",role.getAuthority(),role.getUsername());
		
	

		
		}
	
		
	}

}
