package com.ssafy.sharing.util;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	public String getcookies(HttpServletRequest request, String cookieName) {
		String value = "";
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			return value;
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieName)) {
				value = cookie.getValue();
				break;
			}
		}
		return value;
	}
	
	public String getKey(int size) {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		int num = 0;
		do {
			num = rand.nextInt(75) + 48;
			if((num >= '0' && num <= '9') || (num >= 'a' && num <= 'z') || (num >= 'a' && num <= 'Z')) {
				sb.append((char)num);
			}
			else {
				continue;
			}
		} while(sb.length() < size);
		return sb.toString();
	}
	
	public String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		//Proxy환경
		if(ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		//웹로직 서버
		if(ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-client-IP");
		}
		if(ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public String getBrowser(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		String browser = null;
		if (agent.indexOf("Trident") != -1 || agent.indexOf("MSIE") != -1) {
			browser = "IE";
		} else if (agent.indexOf("Opera") != -1) {
			browser = "Opera";
		} else if (agent.indexOf("Firefox") != -1) {
			browser = "Firefox";
		} else if (agent.indexOf("iPhone") != -1 && agent.indexOf("Mobile") != -1) {
			browser = "iPhone";
		} else if (agent.indexOf("Android") != -1 && agent.indexOf("Mobile") != -1) {
			browser = "Android";
		} else if (agent.indexOf("Safari") != -1) {
			if (agent.indexOf("Chrome") != -1) {
				browser = "Chrome";
			} else {
				browser = "Safari";
			}
		}else {
			browser = "";
		}
		return browser;
	}
	
	public String getOs(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		if(agent.indexOf("Windows") != -1) {
			return "Windows";
		}
		else if(agent.indexOf("Linux") != -1) {
			return "Linux";
		}
		else if(agent.indexOf("Macintosh") != -1) {
			return "Macintosh";
		}
		else {
			return "";
		}
	}
}
