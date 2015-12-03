package corp.airbus.helicopters.myApp.facade.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import corp.airbus.helicopters.efactory.commons.core.config.core.properties.ReloadableProperty;
import corp.airbus.helicopters.efactory.commons.core.controller.AbstractController;
import corp.airbus.helicopters.efactory.commons.core.service.model.AbstractDTO;
import corp.airbus.helicopters.efactory.commons.core.service.model.IdentifierDTO;
import corp.airbus.helicopters.myApp.model.BookDTO;
import corp.airbus.helicopters.myApp.security.Right;
import corp.airbus.helicopters.myApp.security.UserHasRight;
import corp.airbus.helicopters.myApp.service.BookService;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.XDocReportException;

/**
 * The Class BookController.
 */
@Controller
public class BookController extends AbstractController<BookDTO> {

	/** The book service. */
	@Autowired
	private BookService bookService;

	/** Hot update of myConfig var. */
	@ReloadableProperty("myConfig")
	private Integer myConfig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * corp.airbus.helicopters.efactory.commons.core.controller.AbstractController
	 * #all()
	 */
	@UserHasRight(Right.GET_ALL_BOOK)
	@ResponseBody
	public List<BookDTO> all() {
		return bookService.all();
	}

	/**
	 * Gets the pdf.
	 * 
	 * @param id
	 *            the id
	 * @return the pdf
	 * @throws IOException
	 * @throws XDocReportException
	 * @throws XDocConverterException
	 */
	@UserHasRight(Right.GET_PDF_BOOK)
	@ResponseBody
	public AbstractDTO<Long> get(@PathVariable Long id, @RequestParam(value = "pdf", required = false) boolean pdf) throws XDocConverterException, XDocReportException, IOException {
		if (pdf == true) {
			return bookService.getPdf(id);
		} else {
			return bookService.get(id);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * corp.airbus.helicopters.efactory.commons.core.controller.AbstractController
	 * #create(corp.airbus.helicopters.efactory.commons.core.service.model.
	 * AbstractDTO)
	 */
	@UserHasRight(Right.CREATE_BOOK)
	@ResponseBody
	public IdentifierDTO<Long> create(@RequestBody BookDTO book) {
		return new IdentifierDTO<Long>(bookService.save(book));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * corp.airbus.helicopters.efactory.commons.core.controller.AbstractController
	 * #update(corp.airbus.helicopters.efactory.commons.core.service.model.
	 * AbstractDTO)
	 */
	@UserHasRight(Right.UPDATE_BOOK)
	@ResponseBody
	public void update(@RequestBody BookDTO book) {
		bookService.save(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * corp.airbus.helicopters.efactory.commons.core.controller.AbstractController
	 * #delete(java.lang.Long)
	 */
	@UserHasRight(Right.DELETE_BOOK)
	public void delete(@PathVariable Long id) {
		bookService.delete(id);
	}
}
