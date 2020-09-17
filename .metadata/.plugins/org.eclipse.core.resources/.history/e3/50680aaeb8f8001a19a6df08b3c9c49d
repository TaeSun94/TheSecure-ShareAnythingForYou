package com.ssafy.sharing.dao;

import java.util.ArrayList;
import java.util.Map;

public interface HostDao {

	boolean registHost(Map<String, Object> map);

	int getRecentHostNum(String member_email);

	boolean registHostImages(Map<String, Object> map);

	void removeHost(int recentHostNum);

	boolean registHostAvailableDay(Map<String,Object> insertMap);

	boolean checkDuplication(String host_address);
}
