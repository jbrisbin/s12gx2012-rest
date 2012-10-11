package com.springone.rest.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.module.SimpleSerializers;
import org.codehaus.jackson.map.ser.std.SerializerBase;
import org.springframework.stereotype.Component;

@Component
public class ISO8601DateFormatModule extends SimpleModule {

	public ISO8601DateFormatModule() {
		super("ISO-8601", Version.unknownVersion());
	}

	@Override
	public void setupModule(SetupContext context) {
		super.setupModule(context);

		// Set up ISO-8601 date (de)serialization
		context.getDeserializationConfig().withDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

		SimpleSerializers sers = new SimpleSerializers();
		sers.addSerializer(Timestamp.class, new SerializerBase<Timestamp>(Timestamp.class) {
			@Override
			public void serialize(Timestamp value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
					JsonGenerationException {
				jgen.writeString(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(value));
			}
		});

		context.addSerializers(sers);
	}

}
