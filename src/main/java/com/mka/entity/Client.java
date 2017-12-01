package com.mka.entity;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Client {

	UUID clientId;
	String description;
	ClientType clientType;

	public static UUID generateId() {
		return UUID.randomUUID();
	}
	
}
