package com.api.demo.Componet;
/* 
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
import com.PPOOII.Laboratorio.Repository.PersonaRepository;
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
 	@Qualifier("IPersonaRepository")
 	private PersonaRepository IPersonaRepository;
 	
 	@Autowired
	@Qualifier("ICoordenadasRepository")
	private CoordenadasRepository ICoordenadaRepository;
 	
    
    @Scheduled(fixedRate = 10000)
    public void scheduleTaskWithFixedRate() {
        logger.info("*************Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("++++++++++++Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }

    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("-----------Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    
    @Scheduled(cron = "*//*30 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try
        {
        	List<Persona> listPersonas = IPersonaRepository.getPersonas();
        	if(listPersonas !=null) {
        		if(listPersonas.size()>0) {
        			Geocoder geocoder = new Geocoder();
        			Coordenadas coorXper = new Coordenadas();
        			for (Persona persona : listPersonas) {
        				String LatLng = geocoder.getLatLng(persona.getUbicacion());
        				String[] coor = LatLng.split(",");
                    	logger.info(LatLng + " - {}", dateTimeFormatter.format(LocalDateTime.now()));
                    	
                    	coorXper = ICoordenadaRepository.getCoordenadaXPersona(persona.id);
                    	if(coorXper == null) {
                    		ICoordenadaRepository.save(new Coordenadas(persona.id, persona.getPnombre(), 
									Double.parseDouble(coor[0].toString()),
									Double.parseDouble(coor[1].toString())));
                    	}else if(coorXper.id>0) {
                    		ICoordenadaRepository.save(new Coordenadas(coorXper.id,persona.id, persona.getPnombre(), 
									Double.parseDouble(coor[0].toString()),
									Double.parseDouble(coor[1].toString())));
                    	}
                    	
					}
        		}
        	}
        }catch (Exception e) {
        	System.out.println(e.getMessage());
		}
    }
} */
