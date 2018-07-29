package dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.PersonDao;
import entity.Person;
import utils.PersonBean;
import utils.jdbcUtils;

public class PersonDaoImpl implements PersonDao {

	@Override
	public void getAll(PersonBean<Person> personBean) {

		// 获取全部数据数
		int totalCount = this.getTotalCount();
		personBean.setTotalCount(totalCount);
		// System.out.println(personBean.getCurrentPage().intValue());
		// 判断是否超出界限
		if (personBean.getCurrentPage().intValue() <= 0) {
			personBean.setCurrentPage(1);
		} else if (personBean.getCurrentPage().intValue() > personBean.getTotalCount().intValue()) {
			personBean.setCurrentPage(totalCount);
		}

		int currentPage = personBean.getCurrentPage();
		int pageCount = personBean.getPageCount();
		// 获取当前页的第一个数据的下标
		int index = (currentPage - 1) * pageCount;
		String sql = "SELECT * FROM person LIMIT ?,?";
		try {
			QueryRunner queryRunner = jdbcUtils.getQueryRunner();
			List<Person> list = queryRunner.query(sql, new BeanListHandler<Person>(Person.class), index, pageCount);
			personBean.setPageData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalCount() {
		String sql = "SELECT COUNT(*) FROM person";
		try {
			QueryRunner queryRunner = jdbcUtils.getQueryRunner();
			Long count = queryRunner.query(sql, new ScalarHandler<Long>());
			int intCount = count.intValue();
			return intCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
