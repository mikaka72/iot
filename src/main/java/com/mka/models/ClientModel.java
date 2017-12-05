package com.mka.models;

import java.util.UUID;

import com.mka.entity.ClientType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ClientModel {

	UUID clientId;
	String description;
	ClientType clientType;
	
}
