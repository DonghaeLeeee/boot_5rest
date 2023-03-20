package org.iclass.rest.controller;
//rest api 서비스 제공을 위한 컨트롤러로 구현

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.rest.dto.NewMember;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestApiTestController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	//GET : 조회
	//POST : 데이터 가져오고 저장.
	
	
	@GetMapping("/api/hello")	//http://localhost:8083/api/hello
	public String apiHello() {
		return "비동기 ajax 통신에 대한 응답으로 보내는 문자열입니다.";			
		//리턴되는 값이 응답 데이터
	}
	
	@GetMapping("/api/spring")		//PK 또는 유일값으로 1개를 조회하는 경우(또는 검색서비스)에는 파라미터로 했습니다.
	public String getOne(String id) {		//메소드 인자들 파라미터(기존방식)
		log.info("요청 파라미터 id:{}",id);
		return "success";
		
	}
	
	@GetMapping("/api/spring/{id}")	//접근하려는 자원을 uri로 지정합니다. - REST 방식중 하나
	public String getOne2(@PathVariable String id) {	//@PathVariable는 path(경로) 로 전달된 변수값 저장 어노테이션
		log.info("===path 변수 id :{}",id);
		return "success";
	}
	
	@PostMapping("/api/json")
	public String apiPost (@RequestBody String member) {
		//요청 데이터에 대한 어노테이션 설정 @RequestBody를 안적으면 파라미터
		log.info("=== POST 방식 요청 데이터 : {}",member);
		//역직렬화(json 문자열을 NewMember 객체로 변환)
		//db에 저장
		return "success";
		
	}
	
	@GetMapping("/api/json")		//조회 결과를 json 문자열로 보내주기
	public String apiJson() throws JsonProcessingException {
		NewMember m1 = NewMember.builder()
				.id("newjeans")
				.name("뉴진스")
				.age(19)
				.address("서울")
				.hobbies("야구,스키")
				.gender("F")
				.build();
		
		NewMember m2 = NewMember.builder()
				.id("twice")
				.name("트와이스")
				.age(23)
				.address("대구")
				.hobbies("축구,농구")
				.gender("F")
				.build();
		
		NewMember m3 = NewMember.builder()
				.id("kara")
				.name("카라")
				.age(31)
				.address("대전")
				.hobbies("농구,배구")
				.gender("F")
				.build();
				
		Map<String, Object> result = new HashMap<>();
		result.put("member", "회원");
		result.put("count", 1);
		result.put("list", List.of(m1,m2,m3));
		result.put("test", m1);
		//Map 객체를 직렬화
		String jsonString = objectMapper.writeValueAsString(result);
		return jsonString;
	}
}
