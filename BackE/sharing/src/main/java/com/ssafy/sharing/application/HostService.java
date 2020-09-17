package com.ssafy.sharing.application;

import java.util.List;

import com.ssafy.sharing.domain.Host;

public interface HostService {
	public boolean registHost(Host host);

	public boolean deleteHost(int host_num);

	public List<Host> getHosts(String member_email);
}
