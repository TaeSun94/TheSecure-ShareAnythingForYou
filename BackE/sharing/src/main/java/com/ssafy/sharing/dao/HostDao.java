package com.ssafy.sharing.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sharing.domain.Host;
import com.ssafy.sharing.domain.HostImages;
import com.ssafy.sharing.domain.HostItems;

@Mapper
public interface HostDao {

	boolean registHost(Map<String, Object> map);

	int getRecentHostNum(String member_email);

	boolean registHostImages(Map<String, Object> map);

	boolean removeHost(int host_num);

	boolean registHostAvailableDay(Map<String,Object> insertMap);

	boolean checkDuplication(String host_address);

	List<Host> getHosts(String member_email);

	HostImages getHostImages(int host_num);

	String[] getHostAvailableDays(int host_num);

	HostItems getHostProvideItems(int host_num);

	boolean registHostItems(Map<String, Object> map);

	void updateHost(Map<String, Object> map);

	Host getHost(int host_num);

	int checkAvailableDay(Map<String, Object> map);

	List<Host> getLatelyHosts();
}
