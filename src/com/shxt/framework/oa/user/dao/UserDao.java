package com.shxt.framework.oa.user.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.shxt.framework.common.dao.BaseAbstractDao;
import com.shxt.framework.oa.user.model.User;

public class UserDao extends BaseAbstractDao {
	/**
	 * 用户登录查询
	 * @param account
	 * @param password
	 * @return 一条记录或者null
	 */
	public User login(String account,String password){
		//1.指令
		String sql = "SELECT user_id,account FROM sys_user WHERE account=? AND password=?";
		//2.返回一个Map
		Map<String,Object> map = super.queryForMap(sql, account,password);
		//3.判断是否有数据
		User user = null;
		if(map!=null){
			//4.封装数据
			user = new User();//实例化
			user.setUser_id(Integer.parseInt(map.get("user_id").toString()));
			user.setAccount(map.get("account").toString());
		}
		
		return user;
		
	}
	
	public List<User> list(){
		List<User> userList = new LinkedList<User>();
		
		String sql = "SELECT user_id,account,user_name,sex,create_time FROM sys_user ORDER BY create_time DESC";
		List<Map<String,Object>> tempList = super.queryForList(sql);
		
		for (Map<String, Object> map : tempList) {
			User user = new User();//实例化
			user.setUser_id(Integer.parseInt(map.get("user_id").toString()));
			user.setAccount(map.get("account").toString());
			user.setUser_name(map.get("user_name").toString());
			user.setSex(map.get("sex").toString());
			user.setCreate_time((Date)map.get("create_time"));
			userList.add(user);
		}
		return userList;
	}

	
	public void save(User user){
		String sql = "INSERT INTO sys_user (account,user_name,sex,create_time,password) VALUES (?,?,?,NOW(),?)";
		
		super.executeUpdate(sql, user.getAccount(),user.getUser_name(),user.getSex(),"123456");
		
	}
	
	public User load(Integer user_id){
		String sql = "SELECT user_id,account,user_name,sex FROM sys_user WHERE user_id=?";
		
		Map<String,Object> map = super.queryForMap(sql, user_id);
		User user = null;
		if(map!=null){
			//4.封装数据
			user = new User();//实例化
			user.setUser_id(Integer.parseInt(map.get("user_id").toString()));
			user.setAccount(map.get("account").toString());
			user.setUser_name(map.get("user_name").toString());
			user.setSex(map.get("sex").toString());
		}
		
		return user;
	}
	
	public void update(User user){
		String sql = "UPDATE sys_user SET account=?,user_name=?,sex=? WHERE user_id=?";
		super.executeUpdate(sql, user.getAccount(),user.getUser_name(),user.getSex(),user.getUser_id());
	}
	
	public void remove(Integer user_id){
		String sql = "DELETE FROM sys_user WHERE user_id=?";
		super.executeUpdate(sql, user_id);
	}
}
