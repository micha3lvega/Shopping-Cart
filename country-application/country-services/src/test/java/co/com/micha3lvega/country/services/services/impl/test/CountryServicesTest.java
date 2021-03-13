package co.com.micha3lvega.country.services.services.impl.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
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

import co.com.micha3lvega.country.commons.dto.CountryDTO;
import co.com.micha3lvega.country.services.exception.country.CountryNotExistsExcepction;
import co.com.micha3lvega.country.services.services.impl.CountryServices;
import co.com.micha3lvega.country.services.services.util.Util;

/**
 * Clase encargada de las pruebas de {@link CountryServices}
 * 
 * @author micha3lvega
 *
 */
@SpringBootTest
@DirtiesContext
@ActiveProfiles("test") // Cambiar el profile del "application.properties[@ActiveProfiles]" activo
@RunWith(SpringRunner.class)
class CountryServicesTest {

	private static final Logger log = LoggerFactory.getLogger(CountryServicesTest.class);

	@Autowired
	private CountryServices services;

	@Autowired
	private Util util;

	String pathCountry = "classpath:country.json";

	@BeforeEach
	void beforeEach() {

		// Validar que se pueda inyectar el servicio
		assertNotNull(services);

	}

	/**
	 * Prueba que obtiene el numero de paises en la base de datos, inserta un nuevo
	 * pais, vuelve a obtener los paises y valida que el numero aumente
	 *
	 * @throws IOException
	 */
	@Test
	@DisplayName("[findAllSuccess] Prueba de los metodos: [findAll,create], expected ok")
	void findAllSuccess() throws IOException {

		log.info("(findAllSuccess) [[[start]]");

		// Obtener los paises
		List<CountryDTO> countries = services.findAll();
		assertNotNull(countries);

		// Guardar el numero de paises
		int numberCountries = countries.size();

		log.debug("(findAllSuccess) Numero de paises {}", numberCountries);

		CountryDTO newCountry = createCountry();

		// Validar que se guardo el pais
		assertNotNull(newCountry);
		assertNotNull(newCountry.getId());
		log.debug("(findAllSuccess) Nuevo pais: {}", newCountry);

		// Contar el nuevo numero de paises
		countries = services.findAll();
		int newNumberCountries = countries.size();
		log.debug("(findAllSuccess) Nuevo numero de paises: {}", newNumberCountries);

		// Validar que el numero de paises aumento
		assertEquals(newNumberCountries, numberCountries + 1);

		log.info("(findAllSuccess) [[[Success]]]");
	}

	/**
	 * Prueba que busca un id que no existe en la base de datos, Valida que se
	 * retorne una excepcion de tipo {@link CountryNotExistsExcepction}
	 */
	@Test
	@DisplayName("[findByIdNotExists] Prueba de los metodos: [findById], expected failed")
	void findByIdNotExists() {

		log.info("(findByIdNotExists) [[[start]]");

		// Validar que retorne una excepcion de tipo CountryNotExistsExcepction
		assertThrows(CountryNotExistsExcepction.class, () -> services.findById("1"));

		log.info("(findByIdNotExists) [[[Success]]");
	}

	/**
	 * Prueba que busca un id nulo y vacio, Valida que se retorne una excepcion de
	 * tipo {@link CountryNotExistsExcepction}
	 */
	@Test
	@DisplayName("[findByIdNullAndEmpty] Prueba de los metodos: [findById], expected failed")
	void findByIdNullAndEmpty() {

		log.info("(findByIdNull) [[[start]]");

		// Validar que retorne una excepcion de tipo CountryNotExistsExcepction
		assertThrows(CountryNotExistsExcepction.class, () -> services.findById(null));

		// Validar que retorne una excepcion de tipo CountryNotExistsExcepction
		assertThrows(CountryNotExistsExcepction.class, () -> services.findById(""));

		log.info("(findByIdNull) [[[Success]]");
	}

	/**
	 * inserta un nuevo pais en la base de datos, Luego lo busca por su id
	 */
	@Test
	@DisplayName("[findByIdAndCreateSuccess] Prueba de los metodos: [create,findById], expected ok")
	void findByIdAndCreateSuccess() {

		log.info("(findByIdSuccess) [[[start]]");

		// Crear nuevo pais
		CountryDTO newCountry = createCountry();
		log.debug("(findByIdSuccess) newCountry: {}", newCountry);
		assertNotNull(newCountry);
		assertNotNull(newCountry.getId());

		// Buscarlo por su id
		CountryDTO findCountry = services.findById(newCountry.getId());
		log.debug("(findByIdSuccess) findCountry: {}", findCountry);
		assertNotNull(findCountry);
		assertNotNull(findCountry.getId());

		log.info("(findByIdSuccess) [[[Success]]");
	}

	/**
	 * inserta un nuevo pais en la base de datos, Luego lo busca por su id y
	 * actualiza su nombre, despues lo busca nuevamente y valida que se haya
	 * actualizado correctamente
	 */
	@Test
	@DisplayName("[updateSuccess] Prueba de los metodos: [create,update], expected ok")
	void updateSuccess() {

		log.info("(updateSuccess) [[[start]]");

		// Crear nuevo pais
		CountryDTO newCountry = createCountry();
		assertNotNull(newCountry);
		assertNotNull(newCountry.getId());

		// Cambiar el nombre
		newCountry.setName("updated");

		// actualizar el nombre del pais
		services.update(newCountry);

		// Buscar el pais actualizado
		CountryDTO findCountry = services.findById(newCountry.getId());
		log.debug("(updateSuccess) findCountry: {}", findCountry);
		assertNotNull(findCountry);
		assertNotNull(findCountry.getId());
		assertNotNull(findCountry.getName());

		// Validar que se haya actualizado el pais
		assertThat(findCountry.getName()).isEqualTo("updated");

		log.info("(updateSuccess) [[[success]]");
	}

	/**
	 * Prueba que trata de actualizar un pais que no existe en la base de datos
	 */
	@Test
	@DisplayName("[updateFailed] Prueba de los metodos: [update], expected failed")
	void updateCountryNoExistAndCountryNull() {

		log.info("(updateFailed) [[[start]]");

		// Pais que no existe en la base de datos
		CountryDTO noExistsCountry = new CountryDTO();
		noExistsCountry.setId("id");

		// Tratar de actualizar y experar una excepcion de tipo
		assertThrows(CountryNotExistsExcepction.class, () -> {
			services.update(noExistsCountry);
		});

		// Tratar de actualizar un pais nulo y experar una excepcion de tipo
		assertThrows(CountryNotExistsExcepction.class, () -> {
			services.update(null);
		});

		log.info("(updateFailed) [[[start]]");
	}

	/**
	 * Prueba que trata de actualizar un pais nulo
	 */
	@Test
	@DisplayName("[updateFailedCountryNull] Prueba de los metodos: [update], expected failed")
	void updateFailedCountryNullAndIdIsEmprty() {

		log.info("(updateFailedCountryNull) [[[start]]");

		// Tratar de actualizar y experar una excepcion de tipo
		assertThrows(CountryNotExistsExcepction.class, () -> {
			services.update(null);
		});

		// Pais con id vacion
		CountryDTO country = new CountryDTO();
		country.setId("");

		// Tratar de actualizar y experar una excepcion de tipo
		assertThrows(CountryNotExistsExcepction.class, () -> {
			services.update(country);
		});

		log.info("(updateFailedCountryNull) [[[start]]");
	}

	/**
	 * Prueba que crea varios paises en la base de datos
	 */
	@Test
	@DisplayName("[saveAllSuccess] Prueba de los metodos: [saveAll], expected ok")
	void saveAllSuccess() {

		log.info("(saveAllSuccess) [[[start]]");

		// Obtener el numero de paises actuales
		List<CountryDTO> currentCountries = services.findAll();
		assertNotNull(currentCountries);

		// Actual numero de paises
		int currentNumberCountries = currentCountries.size();
		log.debug("(saveAllSuccess) currentNumberCountries: {}", currentNumberCountries);

		// Numero aleatorio que va a ser el numero de paises que se va crear
		int countriesToCreate = (int) Math.floor(Math.random() * 6 + 1);
		log.debug("(saveAllSuccess) countriesToCreate: {}", countriesToCreate);

		List<CountryDTO> countries = new ArrayList<>();

		for (int i = 0; i < countriesToCreate; i++) {

			// Pais que se va a crear
			String jsonString = util.readFile(pathCountry);
			Gson gson = new Gson();
			CountryDTO newCountry = gson.fromJson(jsonString, CountryDTO.class);
			log.trace("(saveAllSuccess) Nuevo pais: {}", newCountry);

			// Cambiar el nombre del pais por uno aleatorio
			newCountry.setName("new country [" + System.currentTimeMillis() + "]");

			countries.add(newCountry);

		}

		// Guardar los paises
		assertNotNull(services.saveAll(countries));

		// Obtener el nuevo numero de paises
		currentCountries = services.findAll();
		assertNotNull(currentCountries);

		int newNumberCountries = currentCountries.size();
		log.debug("(saveAllSuccess) newNumberCountries: {}", newNumberCountries);
		log.debug("(saveAllSuccess) expect: {}", currentNumberCountries + countriesToCreate);

		assertEquals(newNumberCountries, currentNumberCountries + countriesToCreate);

		log.info("(saveAllSuccess) [[[Success]]");
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
