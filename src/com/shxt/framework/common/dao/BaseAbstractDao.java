package com.shxt.framework.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.shxt.framework.common.util.DBUtils;


public abstract class BaseAbstractDao {
	
	protected Connection conn = DBUtils.getConnection();
	protected final Map<String,Object> queryForMap(String sql,Object... params){
		Map<String,Object> map = null;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//预处理SQL语句对象
			ps = this.conn.prepareStatement(sql);
			//判断params
			if(params!=null&&params.length>0){
				//给占位符赋值  
				int length = params.length;
				for(int i=0;i<length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//获取结果集
			rs = ps.executeQuery();
			//判断结果集是否有值
			if(rs.next()){
				map = new HashMap<String,Object>();
				//获取结果集的结构
				ResultSetMetaData rsmd=rs.getMetaData();
				//获取列数
				int columnCount = rsmd.getColumnCount();
				//迭代
				for(int j=1;j<=columnCount;j++){
					//获取列的名称
					String columnName = rsmd.getColumnLabel(j);
					map.put(columnName, rs.getObject(j));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//异常处理-将编译异常转换为运行时异常,方便自己控制
			throw new RuntimeException("queryForMap查询异常,异常信息为:"+e.getMessage());
		}finally {
			try {//关闭资源
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();//控制台输出详细信息
				//异常处理-将编译异常转换为运行时异常,方便自己控制
				throw new RuntimeException("queryForMap关闭资源异常,异常信息为:"+e2.getMessage());
			}
		}
		return map;
	}

	protected final void executeUpdate(String sql,Object...params){
		PreparedStatement ps = null;
		try {
			//获取执行SQL语句的预处理对象
			ps = this.conn.prepareStatement(sql);
			//给占位符赋值,索引从1
			if(params!=null&&params.length>0){
				int length = params.length;
				for(int i=0;i<length;i++){
					ps.setObject(i+1, params[i]);
				}
			}
			//执行
			ps.executeUpdate();
		} catch (SQLException e) {
			// 目前你们使用,如果到工作当中该行代码是没有
			e.printStackTrace();
			throw new RuntimeException("执行executeUpdate方法异常,详细异常为"+e.getMessage());
		}finally{
			try {
				if (ps!=null) {
					ps.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException("关闭资源异常,详细异常为"+e2.getMessage());
			}
			
		}
		
		
	}
	
	protected final List<Map<String,Object>> queryForList(String sql,Object...params){
		List<Map<String,Object>> dataList = new LinkedList<Map<String,Object>>();
		Map<String,Object> map = null;
		//ArrayList和LinkedList的区别: ArrayList 是查询效率快,线程不安全: LinkedList 查询效率慢,CUD操作的时候效率高,线程安全
		//问:SET和LIST的区别: SET是不允许重复,无序 List 允许重复,有序
		PreparedStatement ps = null;//显性赋值
		ResultSet rs = null;
		try {
			ps = this.conn.prepareStatement(sql);
			//赋值
			if(params!=null&&params.length>0){//&&(短路-串联)和&(逻辑与)
				int length = params.length;
				for (int i = 0; i < length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			//获取结果集
			rs = ps.executeQuery();
			//可以获取结果集的结构
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取其查询的列数
			int columnCount = rsmd.getColumnCount();
			//对结果集进行迭代
			while(rs.next()){
				map = new HashMap<String,Object>();//存储一条记录
				for (int j = 1; j <= columnCount; j++) {
					String columnName = rsmd.getColumnLabel(j);
					map.put(columnName, rs.getObject(j));
				}
				//保存到列表当中
				dataList.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//异常处理-将编译异常转换为运行时异常,方便自己控制
			throw new RuntimeException("queryForList查询异常,异常信息为:"+e.getMessage());
		}finally {
			try {//关闭资源
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();//控制台输出详细信息
				//异常处理-将编译异常转换为运行时异常,方便自己控制
				throw new RuntimeException("queryForList关闭资源异常,异常信息为:"+e2.getMessage());
			}
		}
		return dataList;
	}
}
