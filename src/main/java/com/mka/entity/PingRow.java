package com.mka.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PingRow {
	@Id
	private String id;
	private String ipAddress;
	private String message;
	private long time;
	private UUID clientId;
	
}
