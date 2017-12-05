package com.mka.mapping;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.mka.entity.PingRow;
import com.mka.models.PingResult;


@Component
public class PingResultMapper {

	private ModelMapper modelMapper;
	
	public PingResultMapper() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
		modelMapper = new ModelMapper();
		Converter<Long, String> toDateString = new AbstractConverter<Long, String>() {
			  protected String convert(Long source) {
				  
			    return source == null ? null : formatter.format(Date.from(Instant.ofEpochMilli(source)));
			  }
		};
		modelMapper.addConverter(toDateString);
		
		final PropertyMap<PingRow, PingResult> mapping = new PropertyMap<PingRow, PingResult>() {
            @Override
            protected void configure() {
                using(toDateString).map(source.getTime()).setTime(null);
                
            }
        };
        modelMapper.addMappings(mapping);
	}
	
	public PingRow create(final PingResult pingResult) {
		
		PingRow row = modelMapper.map(pingResult, PingRow.class);
		row.setTime(Instant.now().toEpochMilli());
		return row;
		
	}
	
	public PingResult createResult(final PingRow pingRow) {
		return modelMapper.map(pingRow, PingResult.class);
	}
	
}
