/**
 * 	
 */
package corp.airbus.helicopters.myApp.dao;

import org.springframework.beans.factory.annotation.Autowired;

import corp.airbus.helicopters.efactory.commons.core.data.GenericDao;
import corp.airbus.helicopters.efactory.commons.test.data.DaoTestSupport;
import corp.airbus.helicopters.myApp.model.Book;

/**
 * The Class BookDaoTest.
 */
public class BookDaoTest extends DaoTestSupport<Book, Long> {

	/** The dao. */
	@Autowired
	private BookDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see corp.airbus.helicopters.efactory.commons.test.data.DaoTestSupport#
	 * getNewInstance (int)
	 */
	@Override
	protected Book getNewInstance(int index) {
		Book instance = new Book();
		setAllParameters(instance, index);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * corp.airbus.helicopters.efactory.commons.test.data.DaoTestSupport#getDao
	 * ()
	 */
	@Override
	protected GenericDao<Book, Long> getDao() {
		return dao;
	}
}
