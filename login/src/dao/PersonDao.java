package dao;

import entity.Person;
import utils.PersonBean;

public interface PersonDao {

	void getAll(PersonBean<Person> personBean);

	int getTotalCount();
}
