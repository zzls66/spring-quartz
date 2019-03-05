package com.zzl.service;

import com.zzl.dao.UserMapper;
import com.zzl.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	


	public void query(){
		List<User> users = this.userMapper.query("ss");
		System.out.println(users.get(0).getId());
	}

	//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void insertUser(User u){
		this.userMapper.insert(u);
		System.out.println("wwww");
		//int i = 1/0;
		this.userMapper.insert(u);
		//int i=1/0;
//		this.db02UserMapper.insert(u);
		//如果类上面没有@Transactional,方法上也没有，哪怕throw new RuntimeException,数据库也会成功插入数据
		//	throw new RuntimeException("测试插入事务");
	}
	


}
