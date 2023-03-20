package org.iclass.rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.rest.dao.MemberMapper;
import org.iclass.rest.dto.NewMember;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
//Service 클래스에 비지니스 로직 구현 - 트랜잭션 처리 등등..
@Slf4j
public class MemberService {
	
	private final MemberMapper dao;		//생성자 주입
	
	public NewMember login(String userid,String password) {
		Map<String, String> map= new HashMap<>();
		map.put("id",userid);
		map.put("password",password);
		log.info(password);
		return dao.login(map);
	}
	
	public NewMember login(Map<String, String> map) {
		return dao.login(map);
	}
	
	public NewMember member(String id) {
		return dao.selectOne(id);
	}
	
	public List<NewMember> list(){
		return dao.selectAll();
	}

	public void insert(NewMember vo) {
		//db 저장
		dao.insert(vo);
	}

	public int update(NewMember vo) {
		return dao.update(vo);
	}
}