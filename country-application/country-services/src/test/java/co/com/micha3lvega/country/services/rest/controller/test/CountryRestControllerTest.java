package co.com.micha3lvega.country.services.rest.controller.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.google.gson.Gson;

import co.com.micha3lvega.country.commons.dto.CountryDTO;
import co.com.micha3lvega.country.services.rest.controller.CountryRestController;
import co.com.micha3lvega.country.services.services.impl.CountryServices;
import co.com.micha3lvega.country.services.services.util.Util;

/**
 * Clase que realiza las pruebas de los metodos de la clase
 * {@link CountryRestController}
 * 
 * @author micha3lvega
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Cambiar el profile del "application.properties[@ActiveProfiles]" activo
class CountryRestControllerTest {

	private static final Logger log = LoggerFactory.getLogger(CountryRestControllerTest.class);

	@Autowired
	private Util util;

	@Autowired
	private CountryServices services;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String pathCountry = "classpath:country.json";
	private String path = "/api/v1/country";

	/**
	 * Prueba que obtiene todos los paises
	 */
	@Test
	@DisplayName("[testfindAll] Prueba de los metodos: [findAll], expected ok")
	void testfindAll() {

		log.info("(testfindAll) [[[start]]");

		String url = "http://localhost:" + port + path;
		log.debug("(testfindAll) url: {}", url);

		CountryDTO[] countries = restTemplate.getForObject(url, CountryDTO[].class);
		assertNotNull(countries);

		log.debug("(testfindAll) numero paises: {}", countries.length);

		log.info("(testfindAll) [[[Success]]");
	}

	/**
	 * Prueba que crea un pais y luego lo busca por su id
	 */
	@Test
	@DisplayName("[testFindById] Prueba de los metodos: [create,testFindById], expected ok")
	void testFindById() {

		log.info("(testFindById) [[[start]]");

		// Crear el pais
		CountryDTO country = createCountry();
		log.debug("(testFindById) country encontrado: {}", country);
		assertNotNull(country);
		assertNotNull(country.getId());

		String url = "http://localhost:" + port + path + "/" + country.getId();
		log.debug("(testFindById) url: {}", url);

		// Buscar el pais
		country = restTemplate.getForObject(url, CountryDTO.class);
		log.debug("(testFindById) country encontrado: {}", country);
		assertNotNull(country);
		assertNotNull(country.getId());

		log.info("(testFindById) [[[Success]]");

	}

	/**
	 * Metodo que prueba la creacion de un pais
	 */
	@Test
	@DisplayName("[testCreate] Prueba de los metodos: [create], expected ok")
	void testCreate() {

		log.info("(testCreate) [[[start]]");

		String url = "http://localhost:" + port + path;
		log.debug("(testCreate) url: {}", url);

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// Pais que se va a crear
		String jsonString = util.readFile(pathCountry);
		Gson gson = new Gson();
		CountryDTO newCountry = gson.fromJson(jsonString, CountryDTO.class);
		log.debug("(testCreate) Nuevo pais: {}", newCountry);

		// build the request
		HttpEntity<CountryDTO> request = new HttpEntity<>(newCountry, headers);

		// Llamar el servicio
		CountryDTO response = restTemplate.postForObject(url, request, CountryDTO.class);
		log.debug("(testCreate) pais creado: {}", response);

		assertNotNull(response);
		assertNotNull(response.getId());

		log.info("(testCreate) [[[success]]");

	}

	/**
	 * Metodo que prueba la actualizacion de un pais
	 */
	@Test
	@DisplayName("[testUpdate] Prueba de los metodos: [update], expected ok")
	void testUpdate() {

		log.info("(testUpdate) [[[start]]");

		// Crear un pais
		CountryDTO createCountry = createCountry();
		assertNotNull(createCountry);
		assertNotNull(createCountry.getId());
		log.debug("(testCreate) nombre pais creado: {}", createCountry.getName());

		// Craear url
		String url = "http://localhost:" + port + path + "/";
		log.debug("(testUpdate) url: {}", url);

		// Cambiar el nombre para validar que se actualize
		createCountry.setName("updated");

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// build the request
		HttpEntity<CountryDTO> request = new HttpEntity<>(createCountry, headers);

		// Llamar el servicio
		ResponseEntity<CountryDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, CountryDTO.class);
		assertNotNull(response);
		assertNotNull(response.getBody());

		CountryDTO updateCountry = response.getBody();
		log.debug("(testCreate) nombre pais actualizado: {}", updateCountry.getName());

		// Validar que se haya actualizado el nombre
		assertThat(updateCountry.getName()).isEqualTo("updated");

		log.info("(testUpdate) [[[success]]");
	}

	/**
	 * Metodo que prueba el metodo de actualizacion masiva
	 */
	@Test
	@DisplayName("[testSaveAll] Prueba de los metodos: [updateAll], expected ok")
	void testSaveAll() {

		log.info("(testSaveAll) [[[start]]");

		// Craear url
		String url = "http://localhost:" + port + path + "/";
		log.debug("(testSaveAll) url: {}", url);

		// Obtener el numero de paises actuales
		List<CountryDTO> currentCountries = services.findAll();
		assertNotNull(currentCountries);

		// Actual numero de paises
		int currentNumberCountries = currentCountries.size();
		log.debug("(testSaveAll) currentNumberCountries: {}", currentNumberCountries);

		// Numero aleatorio que va a ser el numero de paises que se va crear
		int countriesToCreate = (int) Math.floor(Math.random() * 6 + 1);
		log.debug("(testSaveAll) countriesToCreate: {}", countriesToCreate);

		CountryDTO[] countries = new CountryDTO[countriesToCreate];

		for (int i = 0; i < countriesToCreate; i++) {

			// Pais que se va a crear
			String jsonString = util.readFile(pathCountry);
			Gson gson = new Gson();
			CountryDTO newCountry = gson.fromJson(jsonString, CountryDTO.class);
			log.trace("(testSaveAll) Nuevo pais: {}", newCountry);

			// Cambiar el nombre del pais por uno aleatorio
			newCountry.setName("new country [" + System.currentTimeMillis() + "]");

			countries[i] = newCountry;

		}

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// build the request
		HttpEntity<CountryDTO[]> request = new HttpEntity<>(countries, headers);
		ResponseEntity<CountryDTO[]> response = restTemplate.exchange(url, HttpMethod.PATCH, request,
				CountryDTO[].class);
		assertNotNull(response);
		assertNotNull(response.getBody());

		CountryDTO[] saveCountries = response.getBody();
		assertNotNull(saveCountries);

		// Obtener el nuevo numero de paises
		currentCountries = services.findAll();
		assertNotNull(currentCountries);

		int newNumberCountries = currentCountries.size();
		log.debug("(testSaveAll) newNumberCountries: {}", newNumberCountries);
		log.debug("(testSaveAll) expect: {}", currentNumberCountries + countriesToCreate);

		// Validar si se el numero de paises aumento en el numero de paie
		assertEquals(newNumberCountries, currentNumberCountries + countriesToCreate);

		log.info("(testSaveAll) [[[success]]");

	}

	/***
	 * Metodo que crea un pais en la base de datos Leyendo los datos desde el json
	 *
	 * @return un nuevo pais {@link CountryDTO}
	 */
	private CountryDTO createCountry() {

		// Pais que se va a crear
		String jsonString = util.readFile(pathCountry);
		Gson gson = new Gson();
		CountryDTO newCountry = gson.fromJson(jsonString, CountryDTO.class);
		log.trace("(createCountry) Nuevo pais: {}", newCountry);

		// Cambiar el nombre del pais por uno aleatorio
		newCountry.setName("new country [" + System.currentTimeMillis() + "]");

		// Guardar el nuevo pais
		newCountry = services.create(newCountry);
		log.trace("(createCountry) Nuevo pais: {}", newCountry);
		return newCountry;
	}

}
