package com.magic.ereal.business.util;

import com.magic.ereal.business.cache.MemcachedUtil;
import com.magic.ereal.business.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class LoginHelper {
	
	public static final String TOKEN = "token";

	public static boolean isLogin=false;

	/**SESSION USER*/
	public static final String SESSION_USER = "admin_user";
	

	public static void delObject(String token){
		MemcachedUtil.getInstance().delObj(token);
	}

	public static Object getCurrentUser(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//		Object obj = req.getSession().getAttribute(SESSION_USER);
		Object obj = null;
		if(null == obj){
			String token = req.getHeader(TOKEN);
			Object user = MemcachedUtil.getInstance().get(token);
			if(null == user){
				return null;
			}else{
				return user;
			}
		}
		return obj;
	}

	public static Object getCurrentUser(HttpServletRequest request){
//		Object obj = req.getSession().getAttribute(SESSION_USER);
//		if(null == obj){
		String token = request.getHeader(TOKEN);
		Object user = MemcachedUtil.getInstance().get(token);
		if(null == user){
			return null;
		}else{
			return user;
		}
//		}
//		return obj;
	}

	public static Object getCurrentUser(String token){
		Object tempObj = MemcachedUtil.getInstance().get(token);
		return tempObj;
	}
	
	public static boolean clearToken(){
		HttpServletRequest req = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
//		Object obj = req.getSession().getAttribute(SESSION_USER);
		Object obj = null;
		if(null != obj ){
			req.getSession().invalidate();
			return true;
		}else{
			String token = req.getHeader(TOKEN);
			return MemcachedUtil.getInstance().delObj(token);
		}
		
	}
	
	public static String addToken(User user){
		String token = null;
		// 用户
		if(user.getToken() != null){
			Object tempObj = MemcachedUtil.getInstance().get(user.getToken());
			if(null != tempObj){
				MemcachedUtil.getInstance().delObj(user.getToken());
			}
		}
		token = UUID.randomUUID().toString().replaceAll("-", "");
		user.setToken(token);
		MemcachedUtil.getInstance().add(token, user);
		return token;
	}
	
	public static boolean  replaceToken(String token,Object obj){
		return MemcachedUtil.getInstance().set(token, obj);
	}


	
}
