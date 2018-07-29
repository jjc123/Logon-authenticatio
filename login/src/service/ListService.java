package service;

import entity.Person;
import utils.PersonBean;

public interface ListService {
	void findAll(PersonBean<Person> personBean);
}
