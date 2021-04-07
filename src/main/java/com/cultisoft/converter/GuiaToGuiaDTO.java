package com.cultisoft.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cultisoft.entities.Guia;
import com.cultisoft.service.dto.GuiaDTO;

@Component
public class GuiaToGuiaDTO implements Converter<Guia, GuiaDTO> {

	@Override
	public GuiaDTO convert(Guia guia) {

		GuiaDTO guiaDTO = new GuiaDTO();
		BeanUtils.copyProperties(guia, guiaDTO);

		guiaDTO.setIdUsuario(guia.getUsuario().getId());

		return guiaDTO;
	}
}
