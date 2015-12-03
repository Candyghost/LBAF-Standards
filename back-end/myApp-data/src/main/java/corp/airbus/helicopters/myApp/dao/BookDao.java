/**
 * 	
 */
package corp.airbus.helicopters.myApp.dao;

import org.springframework.stereotype.Repository;

import corp.airbus.helicopters.efactory.commons.core.data.AbstractDao;
import corp.airbus.helicopters.myApp.model.Book;

/**
 * The Class BookDao.
 */
@Repository
public class BookDao extends AbstractDao<Book, Long> {

	/**
	 * Instantiates a new book dao.
	 */
	public BookDao() {
		super(Book.class);
	}
}
