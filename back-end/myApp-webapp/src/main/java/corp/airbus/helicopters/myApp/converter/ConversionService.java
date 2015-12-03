package corp.airbus.helicopters.myApp.converter;

import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Component;

@Component
public class ConversionService extends DefaultFormattingConversionService {

	public ConversionService() {
		// DefaultFormattingConversionService's default constructor
		// creates default formatters and converters
		super(); // no need for explicit super()?

		// add custom formatters and converters
		addConverter(new DateConverter());
	}

}
