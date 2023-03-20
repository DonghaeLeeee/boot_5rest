package org.iclass.rest.dto;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor  //기본생성자를 사용할때에는 setter가 있어야 파라미터값을 매핑합니다.
@AllArgsConstructor
@Builder
@ToString(exclude = {"password","hobby"})	//toString 문자열 만들때 제외시킬 필드
public class NewMember {
	private String id;
	private String name;
	@JsonIgnore		//직렬 또는 역직렬화 항목에서 제외
	private String password;
	private String email;
	private int age;
	private String gender;  
	@Setter
	private String hobbies;		//취미를 , 로 나열
	
	@JsonIgnore		//직렬 또는 역직렬화 항목에서 제외
	private String[] hobby;		
//컬럼에 없는 변수이기 때문에 sql xml에서 매핑할때 꼭 기본생성자와 setter 가 있어야 합니다.
//기본생성자가 없으면 AllArg생성자메소드로  할때 , 컬럼순서와 변수선언(타입) 순서가 일치해야 합니다.	
//이럴때 String[] hobby 이런 변수사용은 오류를 만듭니다.	
	private LocalDateTime joinDate;		//커스텀으로 LocalDateTime 타입 직렬 또는 역직렬 패턴 설정
	private String address;
	
	
}