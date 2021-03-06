package com.mka.models;

import com.mka.entity.ClientType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CreateClientModel {

	String description;
	ClientType clientType;
	
}
