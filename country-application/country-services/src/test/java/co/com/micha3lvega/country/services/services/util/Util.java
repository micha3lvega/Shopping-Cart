package co.com.micha3lvega.country.services.services.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class Util {

	private static final Logger log = LoggerFactory.getLogger(Util.class);

	@Autowired
	ResourceLoader resourceLoader;

	public String readFile(String pathFile) {

		try {
			Resource resource = resourceLoader.getResource(pathFile);
			InputStream inputStream = resource.getInputStream();
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			return new String(bdata, StandardCharsets.UTF_8);
		} catch (Exception e) {
			log.error("(readFile) Exception: {}", e.getMessage(), e);
			return null;
		}

	}

}
