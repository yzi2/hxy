package com.shxt.framework.oa.user.service;

import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.shxt.framework.common.exception.ServiceException;
import com.shxt.framework.oa.user.dao.UserDao;
import com.shxt.framework.oa.user.model.User;

public class UserService {
	//建立联系
	private UserDao userDao = new UserDao();
	public User login(String account,String password) throws Exception{
		//调用UserDao中提供的登录方法
		User user = this.userDao.login(account, password);
		//判断是否有数据
		if(user==null){
			throw new Exception("用户名或者密码错误,请重新输入");
		}
		return user;
	}
	/**
	 * 用户列表信息
	 * @return
	 */
	public List<User> list(){
		return this.userDao.list();
	}
	
	public void save(User user){
		try {
			this.userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统用户添加失败!");
		}
		
	}
	
	public User load(Integer user_id){
		return this.userDao.load(user_id);
	}
	
	public void update(User user){
		try {
			this.userDao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统用户更新失败!");
		}
		
	}
	
	public void remove(Integer user_id){
		try {
			this.userDao.remove(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统用户删除失败!");
		}
		
	}

}
