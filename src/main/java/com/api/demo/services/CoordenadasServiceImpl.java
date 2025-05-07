package com.api.demo.services;
/* 
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.demo.entities.Coordenadas;
import com.api.demo.repositories.CoordenadasRepository;


@Service("CoordenadasService")
public class CoordenadasServiceImpl implements CoordenadasService{

	// ========= INYECCIÓN DE DEPENDENCIAS ==========
	@Autowired
	@Qualifier("ICoordenadasRepository")
	private CoordenadasRepository ICoordenadaRepository;
	//==================== LOGS ============================
	//LOGS DE ERROR
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(PersonaServiceImpl.class);
		
	@Override
	public List<Coordenadas> consultarAllCoordenadas(Pageable pageable) {
		return  ICoordenadaRepository.findAll(pageable).getContent();
	}

} */

