package test;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import entity.User;
import utils.jdbcUtils;

class Person {
	// 单元测试，添加31组数组到数据库
	@Test
	void test() throws SQLException {
		// 创建虚拟数据
		Object[][] Persons = new Object[41][2];
		for (int i = 0; i < Persons.length; i++) {
			for (int j = 0; j < Persons[i].length; j++) {
				Persons[i][j] = i + j;
			}
		}
		String sql = "INSERT INTO person(name,gender) values(?,?)";
		QueryRunner queryRunner = jdbcUtils.getQueryRunner();
		// 批量处理数据
		queryRunner.batch(sql, Persons);
		String sql1 = "SELECT COUNT(*) FROM person";
		try {

			QueryRunner queryRunner1 = jdbcUtils.getQueryRunner();
			Long count = queryRunner1.query(sql1, new ScalarHandler<Long>());
			int intCount = count.intValue();
			System.out.println(intCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Test
	void test2() throws SQLException {
		String sql = "SELECT *FROM user WHERE name=? AND password=?";
		QueryRunner queryRunner = jdbcUtils.getQueryRunner();
		String name = "jjc";
		String password = "123456";
		try {
			User newUser = queryRunner.query(sql, new BeanHandler<User>(User.class), name, password);
			System.out.println(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
