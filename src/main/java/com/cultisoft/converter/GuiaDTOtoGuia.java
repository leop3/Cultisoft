package com.cultisoft.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cultisoft.entities.Guia;
import com.cultisoft.entities.Usuario;
import com.cultisoft.service.UsuarioService;
import com.cultisoft.service.dto.GuiaDTO;

@Component
public class GuiaDTOtoGuia implements Converter<GuiaDTO, Guia> {

	@Override
	public Guia convert(GuiaDTO guiaDTO) {

		UsuarioService usuarioService = new UsuarioService();
		Guia guia = new Guia();
		BeanUtils.copyProperties(guiaDTO, guia);

		Usuario usuario = usuarioService.buscar(guiaDTO.getIdUsuario().toString());

		guia.setUsuario(usuario);

		return guia;
	}

}
