package utils;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class jdbcUtils {
	private static ComboPooledDataSource comboPooledDataSource;
	static {
		comboPooledDataSource = new ComboPooledDataSource();
	}

	public static QueryRunner getQueryRunner() {
		QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
		return queryRunner;
	}
}
