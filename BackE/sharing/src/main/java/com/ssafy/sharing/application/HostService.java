package com.ssafy.sharing.application;

import java.util.List;

import com.ssafy.sharing.domain.Host;

public interface HostService {
	public boolean registHost(Host host);

	public boolean deleteHost(int host_num);

	public List<Host> getHosts(String member_email);

	public boolean updateHost(Host host);
	
	public Host getHost(int host_num);
	
	public List<Host> getLatelyHosts();

	public List<Host> searchHost(String keyword);
}
