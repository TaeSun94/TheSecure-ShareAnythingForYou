package com.ssafy.sharing.application;

import com.ssafy.sharing.domain.Host;

public interface HostService {
	public boolean registHost(Host host);

	public boolean deleteHost(int host_num);
}
