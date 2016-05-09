package ywd.std.spring.jpa.hellojpa.cfg;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan("ywd.std.spring.jpa.hellojpa")
@EnableJpaRepositories("ywd.std.spring.jpa.hellojpa.repositories")
@ImportResource("classpath:applicationContext-resources.xml")
public class AppConfig {
	@Autowired
	private DataSource dataSource;
	
//	@Bean
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		 HibernateJpaVendorAdapter jpaVendorAdapter = new
		 HibernateJpaVendorAdapter();
		 jpaVendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.MYSQL);
		 jpaVendorAdapter.setGenerateDdl(true);
		 return jpaVendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		System.out.println("in AppConfig.class method entityManagerFactory invoked...");
		LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
		lemfb.setDataSource(dataSource);
		lemfb.setJpaVendorAdapter(jpaVendorAdapter());
		lemfb.setPackagesToScan("ywd.std.spring.jpa.hellojpa.entity");
		return lemfb;
	}
}
