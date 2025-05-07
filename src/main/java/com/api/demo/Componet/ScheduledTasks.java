package com.api.demo.Componet;

import com.api.demo.repositories.PersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.api.demo.Apis.GoogleMaps.Geocoder;
import com.api.demo.entities.Coordenadas;
import com.api.demo.entities.Persona;
import com.api.demo.repositories.CoordenadasRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    // ========= INYECCIÓN DE DEPENDENCIAS ==========
 	@Autowired
 	private PersonaRepository IPersonaRepository;

 	@Autowired
	@Qualifier("ICoordenadasRepository")
	private CoordenadasRepository ICoordenadaRepository;



	@Scheduled(cron = "*/30 * * * * ?")
	public void scheduleTaskWithCronExpression() {
		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

		try {
			List<Persona> listPersonas = IPersonaRepository.findAll();
			if (listPersonas != null && !listPersonas.isEmpty()) {
				Geocoder geocoder = new Geocoder();

				for (Persona persona : listPersonas) {
					String latLng = geocoder.getLatLng(persona.getUbicacion());
					String[] coor = latLng.split(",");
					double nuevaLatitud = Double.parseDouble(coor[0]);
					double nuevaLongitud = Double.parseDouble(coor[1]);

					logger.info(latLng + " - {}", dateTimeFormatter.format(LocalDateTime.now()));

					Coordenadas coorXper = ICoordenadaRepository.getCoordenadaXPersona(persona.getId());

					if (coorXper == null) {
						// No existía registro previo, se crea uno nuevo
						Coordenadas nueva = new Coordenadas(
								persona.getId(),
								persona.getPrimerNombre(),
								nuevaLatitud,
								nuevaLongitud
						);
						ICoordenadaRepository.save(nueva);
					} else {
						Coordenadas nueva = new Coordenadas(
								coorXper.id,
								persona.getId(),
								persona.getPrimerNombre(),
								nuevaLatitud,
								nuevaLongitud
						);

						double epsilon = 0.000001;
						boolean latitudCambiada = Math.abs(coorXper.getLatitud() - nuevaLatitud) > epsilon;
						boolean longitudCambiada = Math.abs(coorXper.getLongitud() - nuevaLongitud) > epsilon;

						if (latitudCambiada || longitudCambiada) {
							nueva.setlatitud_anterior(coorXper.getLatitud());
							nueva.setLongitud_anterior(coorXper.getLongitud());
						} else {
							nueva.setlatitud_anterior(coorXper.getLatitud_anterior());
							nueva.setLongitud_anterior(coorXper.getLongitud_anterior());
						}

						ICoordenadaRepository.save(nueva);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error en la tarea programada: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
