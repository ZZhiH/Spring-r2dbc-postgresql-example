package com.spring.webflux.demo.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ZHIHAO
 * 2019年2月22日 下午3:37:27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client")
public class Client {
	
	public Client update(Map<String, Object> oldClient) {
		if(this.id == null)
			this.id = (String) oldClient.get("id");
		if(this.username == null)
			this.username = (String) oldClient.get("username");
		if(this.phone == null)
			this.phone = (String) oldClient.get("phone");
		if(this.email == null)
			this.email = (String) oldClient.get("email");
		if(this.name == null)
			this.name = (String) oldClient.get("name");
		return this;
	}


	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "username")
    private String username;
	
	@Column(name = "phone")
    private String phone;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "name")
    private String name;
	
}
