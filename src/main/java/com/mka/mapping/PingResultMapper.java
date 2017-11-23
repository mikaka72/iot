package com.mka.mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

import com.mka.entity.PingRow;
import com.mka.models.PingResult;


@Component
public class PingResultMapper {

	public PingRow create(final PingResult pingResult) {
		return new PingRow()
				.setIpAddress(pingResult.getIpAddress())
				.setMessage(pingResult.getMessage())
				.setTime(pingResult.getTime().toEpochSecond(ZoneOffset.UTC));
	}
	
	public PingResult createResult(final PingRow pingRow) {
		return new PingResult()
				.setId(pingRow.getId())
				.setIpAddress(pingRow.getIpAddress())
				.setMessage(pingRow.getMessage())
				.setTime( LocalDateTime.ofInstant(Instant.ofEpochMilli(pingRow.getTime()), 
						ZoneOffset.UTC));
		
	}
	
}
