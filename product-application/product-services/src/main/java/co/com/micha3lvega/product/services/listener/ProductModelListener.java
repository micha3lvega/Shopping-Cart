package co.com.micha3lvega.product.services.listener;

import org.springframework.stereotype.Component;

import co.com.micha3lvega.product.services.model.Product;
import co.com.micha3lvega.product.services.services.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

@Component
public class ProductModelListener extends AbstractMongoEventListener<Product>{
	
	private SequenceGeneratorService sequenceGenerator;

	@Autowired
	public ProductModelListener(SequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Product> event) {
		if (event.getSource().getSku() == null) {
			event.getSource().setSku(this.sequenceGenerator.generateSequence(Product.SEQUENCE_NAME));
		}
	}

	
	
}
