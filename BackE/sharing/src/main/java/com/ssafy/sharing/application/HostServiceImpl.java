package com.ssafy.sharing.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharing.dao.HostDao;
import com.ssafy.sharing.domain.Host;
import com.ssafy.sharing.domain.HostImages;
import com.ssafy.sharing.domain.HostItems;

@Service
public class HostServiceImpl implements HostService {

	@Autowired
	HostDao hostDao;

	@Override
	public boolean registHost(Host host) {
		try {
			if (hostDao.checkDuplication(host.getHost_address())) {
				return false;
			}
			// 계좌도 넣을것인가? 연락처도
			Map<String, Object> map = new HashMap<>();
			map.put("member_email", host.getMember_email());
			map.put("host_address", host.getHost_address());
			map.put("host_type", host.getHost_type());
			map.put("host_intro", host.getHost_intro());
			map.put("host_price", host.getHost_price());
			if (hostDao.registHost(map)) {
				int host_num = hostDao.getRecentHostNum(host.getMember_email());
				map.put("host_num", host_num);
				for (int i = 1; i <= 6; i++) {
					StringBuilder sb = new StringBuilder();
					sb.append("img").append(i);
					if (host.getHost_images().length < i)
						map.put(sb.toString(), null);
					else
						map.put(sb.toString(), host.getHost_images()[i - 1]);
				}
				if (hostDao.registHostImages(map)) {
					List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

					for (String day : host.getHost_available_day()) {
						dataList.add(new HashMap<String, Object>());
						dataList.get(dataList.size() - 1).put("host_num", host_num);
						dataList.get(dataList.size() - 1).put("host_available_day", day);
					}

					Map<String, Object> insertMap = new HashMap<String, Object>();
					insertMap.put("list", dataList);

					if (hostDao.registHostAvailableDay(insertMap)) {
						for (int i = 1; i <= 10; i++) {
							StringBuilder sb = new StringBuilder();
							sb.append("item").append(i);
							map.put(sb.toString(), host.getHost_provide_items()[i - 1]);
						}
						if (hostDao.registHostItems(map)) {
							return true;
						} else {
							hostDao.removeHost(hostDao.getRecentHostNum(host.getMember_email()));
						}
					} else {
						hostDao.removeHost(hostDao.getRecentHostNum(host.getMember_email()));
						return false;
					}
				} else {
					hostDao.removeHost(hostDao.getRecentHostNum(host.getMember_email()));
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteHost(int host_num) {
		try {
			return hostDao.removeHost(host_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Host> getHosts(String member_email) {
		try {
			List<Host> host_list = hostDao.getHosts(member_email);
			for (Host host : host_list) {
				HostImages hostimages = hostDao.getHostImages(host.getHost_num());
				String[] img_list = new String[6];
				setHostImages(hostimages, img_list);
				host.setHost_images(img_list);

				host.setHost_available_day(hostDao.getHostAvailableDays(host.getHost_num()));

				HostItems hostitems = hostDao.getHostProvideItems(host.getHost_num());
				boolean[] item_list = new boolean[10];
				setHostItems(hostitems, item_list);
				host.setHost_provide_items(item_list);
			}
			return host_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Host> getLatelyHosts() {
		try {
			List<Host> host_list = hostDao.getLatelyHosts();
			for (Host host : host_list) {
				HostImages hostimages = hostDao.getHostImages(host.getHost_num());
				String[] img_list = new String[6];
				setHostImages(hostimages, img_list);
				host.setHost_images(img_list);

				host.setHost_available_day(hostDao.getHostAvailableDays(host.getHost_num()));

				HostItems hostitems = hostDao.getHostProvideItems(host.getHost_num());
				boolean[] item_list = new boolean[10];
				setHostItems(hostitems, item_list);
				host.setHost_provide_items(item_list);
			}
			return host_list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateHost(Host host) {
		try {
			// 계좌도 넣을것인가? 연락처도
			Map<String, Object> map = new HashMap<>();
			map.put("host_num", host.getHost_num());
			map.put("member_email", host.getMember_email());
			map.put("host_address", host.getHost_address());
			map.put("host_type", host.getHost_type());
			map.put("host_intro", host.getHost_intro());
			hostDao.updateHost(map);

			// 아이템, 날짜, 사진
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Host getHost(int host_num) {
		try {
			return hostDao.getHost(host_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setHostImages(HostImages hostImages, String[] tmp) {
		tmp[0] = hostImages.getImg1();
		tmp[1] = hostImages.getImg2();
		tmp[2] = hostImages.getImg3();
		tmp[3] = hostImages.getImg4();
		tmp[4] = hostImages.getImg5();
		tmp[5] = hostImages.getImg6();
	}

	private void setHostItems(HostItems hostItems, boolean[] tmp) {
		tmp[0] = hostItems.isItme1();
		tmp[1] = hostItems.isItme2();
		tmp[2] = hostItems.isItme3();
		tmp[3] = hostItems.isItme4();
		tmp[4] = hostItems.isItme5();
		tmp[5] = hostItems.isItme6();
		tmp[6] = hostItems.isItme7();
		tmp[7] = hostItems.isItme8();
		tmp[8] = hostItems.isItme9();
		tmp[9] = hostItems.isItme10();
	}

	@Override
	public List<Host> searchHost(String keyword) {
		List<Host> list = new ArrayList<Host>();
		try {
			if (keyword.equals("")) {
				list = hostDao.getAllHost();
			} else {
				list = hostDao.getSearchHost(keyword);
			}
			for (Host host : list) {
				HostImages hostimages = hostDao.getHostImages(host.getHost_num());
				String[] img_list = new String[6];
				setHostImages(hostimages, img_list);
				host.setHost_images(img_list);

				host.setHost_available_day(hostDao.getHostAvailableDays(host.getHost_num()));

				HostItems hostitems = hostDao.getHostProvideItems(host.getHost_num());
				boolean[] item_list = new boolean[10];
				setHostItems(hostitems, item_list);
				host.setHost_provide_items(item_list);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
