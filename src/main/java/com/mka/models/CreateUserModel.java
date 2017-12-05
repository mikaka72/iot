package com.mka.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CreateUserModel {

	private String foreName;
	private String surName;
	private String email; 
	private String password;
	private String username; 
	
}
