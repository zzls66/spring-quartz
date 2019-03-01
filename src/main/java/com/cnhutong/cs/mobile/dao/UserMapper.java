package com.cnhutong.cs.mobile.dao;


import com.cnhutong.cs.mobile.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	//注：方法名和要UserMapper.xml中的id一致
	List<User> query(@Param("userName") String userName);
	void insert(User u);
}
