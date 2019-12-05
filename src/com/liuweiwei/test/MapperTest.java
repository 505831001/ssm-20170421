package com.liuweiwei.test;

import com.liuweiwei.bean.EmployeeExample;
import com.liuweiwei.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:dispatcherServlet-servlet.xml"})
public class MapperTest {

	private ApplicationContext app;
	@Test
	public void springConnectDBTest() {
		app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		EmployeeMapper mapper = app.getBean(EmployeeMapper.class);
		List<EmployeeExample> list = mapper.selectAll();
		System.out.println(list.toString());
	}

	@Test
	public void mybatisConnectDBTest() throws IOException {
		InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
		SqlSession session = factory.openSession();
		System.out.println("MyBatis Connect DB -> " + session);
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		List<EmployeeExample> list = mapper.selectAll();
		System.out.println(list.toString());
	}
}
