package com.mka.models;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PingResult {

	private String id;
	private String ipAddress;
	private String message;
	private String time;
	private UUID clientId;
	
}
