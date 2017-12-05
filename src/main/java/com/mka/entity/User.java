package com.mka.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document
public class User {

	@Id
	private String id;
	@Version
	private Long version;
	private String foreName;
	private String surName;
	private String email; 
	private String password;
	private String username; 
	
	
}
