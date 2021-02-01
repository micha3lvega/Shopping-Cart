/**
 * 
 */
package co.com.micha3lvega.customer.services.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;
import co.com.micha3lvega.customer.services.exception.CustomerInvalidInformacionException;
import co.com.micha3lvega.customer.services.util.Util;

/**
 * Clase de pruebas para {@link CustomerServices}
 * 
 * @author micha3lvega
 *
 */
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test") // Cambiar el profile del "application.properties[@ActiveProfiles]" activo
@RunWith(SpringRunner.class)
class CustomerServicesTest {

	private static final Logger log = LoggerFactory.getLogger(CustomerServicesTest.class);

	@Autowired
	private Util util;

	@Autowired
	private CustomerServices services;

	private String pathCountry = "classpath:customer.json";

	@BeforeEach
	void beforeEach() {

		// Validar que se pueda inyectar el servicio
		assertNotNull(services);

		log.info("################################################################################");

	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.services.impl.CustomerServices#findAll()}.
	 */
	@Test
	@DisplayName("[testFindAll] Prueba de los metodos: [findAll], expected ok")
	void testFindAll() {

		log.info("(testFindAll) [[start]]");

		// Obtener el numero de clientes actuales
		List<CustomerDTO> currentCustomers = services.findAll();
		assertNotNull(currentCustomers);

		int currentNumberCustomers = currentCustomers.size();
		log.debug("(testFindAll) currentNumberCustomers: {}", currentNumberCustomers);

		// Crea un nuevo cliente
		createCustomer();

		// Obtener nuevamente los clientes
		currentCustomers = services.findAll();
		assertNotNull(currentCustomers);

		int newCurrentNumberCustomers = currentCustomers.size();
		log.debug("(testFindAll) newCurrentNumberCustomers: {}", newCurrentNumberCustomers);

		// Validar que si aumento el numero de clientes
		assertThat(newCurrentNumberCustomers).isEqualTo(currentNumberCustomers + 1);

		log.info("(testFindAll) [[success]]");
	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.services.impl.CustomerServices#findByID(java.lang.String)}.
	 */
	@Test
	@DisplayName("[testFindByID] Prueba de los metodos: [findByID], expected ok")
	void testFindByID() {

		log.info("(testFindByID) [[start]]");

		// Crear un nuevo cliente
		CustomerDTO customer = createCustomer();
		assertNotNull(customer);
		assertNotNull(customer.getId());
		assertNotNull(customer.getUser());

		log.debug("(testFindByID) id nuevo cliente: {}", customer.getId());
		log.debug("(testFindByID) nombre nuevo cliente: {}", customer.getUser().getName());

		// Buscar el cliente nuevamente
		CustomerDTO findCustomer = services.findByID(customer.getId());
		assertNotNull(findCustomer);
		assertNotNull(findCustomer.getId());
		assertNotNull(findCustomer.getUser());

		// Validar que los nombres sean iguales para validar que sean los mismos
		assertThat(findCustomer.getUser().getName()).isEqualTo(customer.getUser().getName());
		log.debug("(testFindByID) nombre del cliente encontrado: {}", customer.getUser().getName());

		log.info("(testFindByID) [[success]]");

	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.services.impl.CustomerServices#create(co.com.micha3lvega.customer.commons.dto.CustomerDTO)}.
	 */
	@Test
	void testCreate() {

		log.info("(testCreate) [[start]]");

		// Crear un nuevo cliente
		CustomerDTO customer = createCustomer();
		assertNotNull(customer);
		assertNotNull(customer.getId());
		assertNotNull(customer.getUser());

		log.debug("(testCreate) id nuevo cliente: {}", customer.getId());
		log.debug("(testCreate) nombre nuevo cliente: {}", customer.getUser().getName());

		log.info("(testCreate) [[success]]");

	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.services.impl.CustomerServices#create(co.com.micha3lvega.customer.commons.dto.CustomerDTO)}.
	 */
	void testErrorCreate() {

		log.info("(testErrorCreate) [[start]]");

		// Probar cuando el objecto se envia nulo
		assertThrows(CustomerInvalidInformacionException.class, () -> {
			services.create(null);
		});

		CustomerDTO customer = new CustomerDTO();
		customer.setUser(null);

		// Probar cuando el usuario se envia nulo
		assertThrows(CustomerInvalidInformacionException.class, () -> {

			services.create(customer);
		});

		log.info("(testErrorCreate) [[success]]");
	}

	/**
	 * Test method for
	 * {@link co.com.micha3lvega.customer.services.services.impl.CustomerServices#update(co.com.micha3lvega.customer.commons.dto.CustomerDTO)}.
	 */
	@Test
	void testUpdate() {

		log.info("(testUpdate) [[start]]");

		// Crear un nuevo cliente
		CustomerDTO customer = createCustomer();
		assertNotNull(customer);
		assertNotNull(customer.getId());
		assertNotNull(customer.getUser());

		log.debug("(testUpdate) id nuevo cliente: {}", customer.getId());
		log.debug("(testUpdate) nombre nuevo cliente: {}", customer.getUser().getName());

		// Actualizar la direccion
		customer.getUser().setName("updated");
		services.update(customer);

		// Buscar el cliente
		CustomerDTO findCustomer = services.findByID(customer.getId());
		assertNotNull(findCustomer);
		assertNotNull(findCustomer.getId());
		assertNotNull(findCustomer.getUser());

		log.debug("(testUpdate) nombre actualizado del cliente: {}", findCustomer.getUser().getName());

		// Verificar que el nombre se haya actualizado
		assertThat(findCustomer.getUser().getName()).isEqualTo("updated");

		log.info("(testUpdate) [[success]]");

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
