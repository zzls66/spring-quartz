package com.zzl.dao;


import com.zzl.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {

	//注：方法名和要UserMapper.xml中的id一致
	List<User> query(@Param("userName") String userName);
	void insert(User u);
}
