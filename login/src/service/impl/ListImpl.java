package service.impl;
import dao.PersonDao;
import dao.impl.PersonDaoImpl;
import entity.Person;
import service.ListService;
import utils.PersonBean;

public class ListImpl implements ListService {
	private PersonDao personDao =new PersonDaoImpl(); 
	@Override
	public void findAll(PersonBean<Person> personBean) {
		 personDao.getAll(personBean);
	}
}
