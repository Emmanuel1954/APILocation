package com.api.demo.controllers;
/* 
@RestController
@RequestMapping("/LaboratorioV1")
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
	
} */
