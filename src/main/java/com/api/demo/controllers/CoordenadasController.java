package com.api.demo.controllers;

import com.api.demo.entities.Coordenadas;
import com.api.demo.services.CoordenadasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/ApiLocation")
public class CoordenadasController {

	// ==========INYECCION DEL SERVICE==========
	@Autowired
	@Qualifier("CoordenadasService")
	private CoordenadasServiceImpl coordenadaService;
	
	// ==========MÉTODOS HTTP====================
	// MÉTODO GET
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/coordenadas")
	public List<Coordenadas> consultarAllCoordenadas(Pageable pageable) {
		return coordenadaService.consultarAllCoordenadas(pageable);
	}
	@CrossOrigin(origins = "http://localhost")
	@GetMapping ("/{id}")
	public Coordenadas consultarCoordenada(@PathVariable int id) {
		return coordenadaService.consultarcordenada(id);
	}
}
