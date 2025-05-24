package com.api.demo.Apis.GoogleMaps;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value; // Importar Value
import org.springframework.stereotype.Component; // Importar Component

@Component // Anotar como componente de Spring
public class Geocoder {

    private static final String GEOCODING_RESOURCE = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?fields=all&inputtype=textquery&key=";

    @Value("${google.maps.api.key}") // Inyectar la clave desde properties
    private String API_KEY;

    public Geocoder() {
        // Constructor vacío. Spring se encargará de inyectar @Value después de la construcción.
    }

    public String GeocodeSync(String query) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        String requestUri = GEOCODING_RESOURCE + API_KEY + "&input=" + encodedQuery;
        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();
        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());
        return (String) geocodingResponse.body();
    }

    public String getLatLng(String query) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String response = this.GeocodeSync(query);
        JsonNode responseJsonNode = mapper.readTree(response);
        // Validar si 'candidates' existe y no está vacío para evitar NullPointerException
        if (responseJsonNode.has("candidates") && responseJsonNode.get("candidates").isArray() && responseJsonNode.get("candidates").size() > 0) {
            JsonNode items = responseJsonNode.get("candidates");
            String latitud = items.get(0).get("geometry").get("location").get("lat").asText();
            String longitud = items.get(0).get("geometry").get("location").get("lng").asText();
            return latitud + "," + longitud;
        } else {
            // Manejar el caso donde no se encuentran candidatos (ej. ubicación no encontrada)
            // Puedes lanzar una excepción personalizada o devolver un valor por defecto
            throw new IOException("No candidates found for the given location: " + query);
        }
    }
}