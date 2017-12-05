package com.mka.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)

public class UserModel {

	private String id;
	private String foreName;
	private String surName;
	private String email; 
	private String username; 
	private Long version;


}
