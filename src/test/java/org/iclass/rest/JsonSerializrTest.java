package org.iclass.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.iclass.rest.dto.NewMember;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest		//스프링부트 테스트
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)	//테스트 메소드 순서 정하기
class JsonSerializrTest {

	//직렬화(자바객체를 json 문자열로 변환),역직렬화에 공통으로 사용할 객체를 생성
	//직렬화의 목적  : 데이터 교환을 위해 간결한 형식의 문자열을 사용.
	private ObjectMapper objectMapper = new ObjectMapper();
	//ㄴ메소드를 통해 객체를 직렬화시킬수있다.
	
	@Test @Order(1)			//테스트 순서는 편의상 정의
	void test() throws JsonProcessingException {
		//자바 객체
		NewMember member = NewMember.builder()
				.id("newjeans")
				.name("뉴진스")
				.age(19)
				.address("서울")
				.hobbies("야구,스키")
				.gender("F")
//				.joinDate(LocalDateTime.now())
//커스텀으로 LocalDateTime 타입 직렬 또는 역직렬 패턴 설정해야함.				
				.build();
		
		String result = objectMapper.writeValueAsString(member);
		log.info("======java 객체 직렬화된 json : {}",result);
		assertNotEquals(0, result.length());
	}
	
	
	//데이터 교환에서 받기(수신)는 json 문자열,역직렬화의 목적 : 받은 데이터를 java 또는 js 객체로 변환하기
	@Test @Order(2)
	public void deserializr() throws JsonMappingException, JsonProcessingException {
		String result =
		"{\"id\":\"newjeans\",\"name\":\"뉴진스\",\"email\":null,\"age\":19,\"gender\":\"F\",\"hobbies\":\"야구,스키\",\"joinDate\":null,\"address\":\"서울\"}";		//json 문자열		
//		"{\"id\":\"newjeans\",\"name\":\"뉴진스\",\"email\":null,\"age\":19,\"gender\":\"F\",\"hobbies\":\"야구,스키\",\"joinDate\"\"2023-03-20\",\"address\":\"서울\"}";		//json 문자열		
		//커스텀으로 LocalDateTime 타입 직렬 또는 역직렬 패턴 설정해야함.
		//따옴표도 전달한 문자이기 때문에\"
		NewMember member = objectMapper.readValue(result, NewMember.class);
		log.info("======json 문자열 역직렬화된 java객체 : {}",member);
		assertNotNull(member);
	}
}
