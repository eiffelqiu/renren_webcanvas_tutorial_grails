package com.yourdomain.website.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 存取帐号关联表的DAO类。
 * 帐号关联表用于存储从人人帐号ID到网站帐号的映射关系。
 * 在这个类的实现中，我们用一个静态Map来模拟帐号关联表。
 * 真实的帐号关联表应该存储在数据库中，可以持久化。
 * 但本类的模拟实现并不能持久化，重启服务器后帐号关联表清空。
 */
public class RenrenUserMappingDAO {
	/**
	 * 单例（Singleton）模式
	 */
	private static RenrenUserMappingDAO instance = new RenrenUserMappingDAO();
	private RenrenUserMappingDAO(){
	}	
	public static RenrenUserMappingDAO getInstance() {
		return instance;
	}
	/**
	 * 用来模拟帐号关联表的静态Map；保存从人人网UID到网站username的映射
	 */
	private static Map<Integer, String> userMappingTable = new HashMap<Integer, String>();

	public synchronized String getUsername(int rrUid) {
		return userMappingTable.get(rrUid);
	}

	public synchronized void addMapping(int rrUid, String username) {
		userMappingTable.put(rrUid, username);
	}
}