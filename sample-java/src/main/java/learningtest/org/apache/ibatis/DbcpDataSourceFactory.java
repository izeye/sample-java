package learningtest.org.apache.ibatis;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DbcpDataSourceFactory extends UnpooledDataSourceFactory {

	public DbcpDataSourceFactory() {
		this.dataSource = new BasicDataSource();
	}

}
