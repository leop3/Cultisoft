package com.cultisoft.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cultisoft.converter.GuiaDTOtoGuia;
import com.cultisoft.converter.GuiaToGuiaDTO;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

	@Autowired(required=true)
	private GuiaDTOtoGuia guiaDTOtoGuia;

	@Autowired
	private GuiaToGuiaDTO guiaToGuiaDTO;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(guiaDTOtoGuia);
		registry.addConverter(guiaToGuiaDTO);
	}

}
