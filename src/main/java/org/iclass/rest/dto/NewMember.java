package org.iclass.rest.dto;


import java.sql.Timestamp;

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
@ToString
public class NewMember {
	private String id;
	private String name;
	private String password;
	private String email;
	private int age;
	private String gender;  
	@Setter
	private String hobbies;		//취미를 , 로 나열
	private String[] hobby;		
//컬럼에 없는 변수이기 때문에 sql xml에서 매핑할때 꼭 기본생성자와 setter 가 있어야 합니다.
//기본생성자가 없으면 AllArg생성자메소드로  할때 , 컬럼순서와 변수선언(타입) 순서가 일치해야 합니다.	
//이럴때 String[] hobby 이런 변수사용은 오류를 만듭니다.	
	private Timestamp joinDate;		//년월일 시분초
	private String address;
	
	
}