/**
 * 
 */
package co.com.micha3lvega.customer.services.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;

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

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;
import co.com.micha3lvega.customer.services.services.impl.CustomerServices;
import co.com.micha3lvega.customer.services.util.Util;

/**
 * Clase que realiza las pruebas de los metodos de la clase
 * {@link CustomerRestController}
 * 
 * @author micha3lvega
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // Cambiar el profile del "application.properties[@ActiveProfiles]" activo
class CustomerRestControllerTest {

	private static final Logger log = LoggerFactory.getLogger(CustomerRestControllerTest.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private Util util;

	@Autowired
	private CustomerServices services;

	private String pathCountry = "classpath:customer.json";
	private String path = "/api/v1/customer";

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.rest.controller.CustomerRestController#findAll()}.
	 */
	@Test
	@DisplayName("[testfindAll] Prueba de los metodos: [findAll], expected ok")
	void testFindAll() {

		log.info("(testFindAll) [[[start]]");

		String url = "http://localhost:" + port + path;
		log.debug("(testfindAll) url: {}", url);

		CustomerDTO[] customers = restTemplate.getForObject(url, CustomerDTO[].class);
		assertNotNull(customers);

		log.debug("(testFindAll) numero customers: {}", customers.length);

		log.info("(testFindAll) [[[Success]]");
	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.rest.controller.CustomerRestController#findByID(java.lang.String)}.
	 */
	@Test
	@DisplayName("[testFindByID] Prueba de los metodos: [create,findByID], expected ok")
	void testFindByID() {

		log.info("(testFindByID) [[[start]]");

		// Crear un customer
		CustomerDTO newCustomer = createCustomer();
		log.debug("(testFindById) customer creado: {}", newCustomer);
		assertNotNull(newCustomer);
		assertNotNull(newCustomer.getId());

		// Armar la url
		String url = "http://localhost:" + port + path + "/" + newCustomer.getId();
		log.debug("(testFindByID) url: {}", url);

		// Buscar el customer
		newCustomer = restTemplate.getForObject(url, CustomerDTO.class);
		log.debug("(testFindById) customer encontrado: {}", newCustomer);
		assertNotNull(newCustomer);
		assertNotNull(newCustomer.getId());

		log.info("(testFindByID) [[[success]]");

	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.rest.controller.CustomerRestController#create(co.com.micha3lvega.customer.commons.dto.CustomerDTO)}.
	 */
	@Test
	@DisplayName("[testCreate] Prueba de los metodos: [create], expected ok")
	void testCreate() {

		log.info("(testCreate) [[[start]]");

		// Customer que se va a crear
		String jsonString = util.readFile(pathCountry);
		Gson gson = new Gson();
		CustomerDTO newCustomer = gson.fromJson(jsonString, CustomerDTO.class);
		log.debug("(testCreate) Nuevo customer: {}", newCustomer);
		assertNotNull(newCustomer);
		assertNotNull(newCustomer.getUser());

		// Cambiar el nombre del usuario por uno aleatorio
		newCustomer.getUser().setName("" + System.currentTimeMillis());

		// Cambiar el correo por uno aleatorio
		newCustomer.getUser().setEmail(System.currentTimeMillis() + "@example.com");

		String url = "http://localhost:" + port + path;
		log.debug("(testCreate) url: {}", url);

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<CustomerDTO> request = new HttpEntity<>(newCustomer, headers);

		// Llamar el servicio
		CustomerDTO response = restTemplate.postForObject(url, request, CustomerDTO.class);
		log.debug("(testCreate) customer creado: {}", response);

		assertNotNull(response);
		assertNotNull(response.getId());

		log.info("(testCreate) [[[success]]");
	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.rest.controller.CustomerRestController#update(co.com.micha3lvega.customer.commons.dto.CustomerDTO)}.
	 */
	@Test
	@DisplayName("[testUpdate] Prueba de los metodos: [create,update], expected ok")
	void testUpdate() {

		log.info("(testUpdate) [[[start]]");

		String url = "http://localhost:" + port + path;
		log.debug("(testUpdate) url: {}", url);

		// Crear un customer
		CustomerDTO newCustomer = createCustomer();
		log.debug("(testUpdate) customer creado: {}", newCustomer);
		assertNotNull(newCustomer);

		log.debug("(testUpdate) [user] customer creado: {}", newCustomer.getUser());
		assertNotNull(newCustomer.getId());
		assertNotNull(newCustomer.getUser());

		// Cambiar el nombre del usuario
		newCustomer.getUser().setName("updated");

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// build the request
		HttpEntity<CustomerDTO> request = new HttpEntity<>(newCustomer, headers);

		// Llamar el servicio
		ResponseEntity<CustomerDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, CustomerDTO.class);
		assertNotNull(response);
		assertNotNull(response.getBody());

		CustomerDTO updateCustomer = response.getBody();
		assertNotNull(updateCustomer);
		assertNotNull(updateCustomer.getId());
		assertNotNull(updateCustomer.getUser());

		// Validar que se cambio el nombre
		assertThat(updateCustomer.getUser().getName()).isEqualTo("updated");

		log.info("(testUpdate) [[[success]]");
	}

	/***
	 * Metodo que crea un customer en la base de datos Leyendo los datos desde el
	 * json
	 *
	 * @return un nuevo customer {@link CustomerDTO}
	 */
	private CustomerDTO createCustomer() {

		// Customer que se va a crear
		String jsonString = util.readFile(pathCountry);
		Gson gson = new Gson();
		CustomerDTO newCustomer = gson.fromJson(jsonString, CustomerDTO.class);
		log.trace("(createCountry) Nuevo customer: {}", newCustomer);

		// Cambiar el nombre del usuario por uno aleatorio
		newCustomer.getUser().setName("" + System.currentTimeMillis());

		// Cambiar el correo por uno aleatorio
		newCustomer.getUser().setEmail(System.currentTimeMillis() + "@example.com");

		// Guardar el nuevo pais
		newCustomer = services.create(newCustomer);
		log.trace("(createCountry) Nuevo customer: {}", newCustomer);
		return newCustomer;

	}
}
