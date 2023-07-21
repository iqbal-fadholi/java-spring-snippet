package co.id.bankbsi.middlewaresnippet.repository.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@RequiredArgsConstructor
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "co.id.bankbsi.middlewaresnippet.repository.database.jparepository", entityManagerFactoryRef = "snippetEMF", transactionManagerRef = "snippetTMR")
public class DbConfig {

    final Environment env;

    @Bean
    public HikariDataSource snippetHikari() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource-snippet.driver-class-name"));
        dataSource.setJdbcUrl(env.getProperty("spring.datasource-snippet.url"));
        dataSource.setUsername(env.getProperty("spring.datasource-snippet.username"));
        dataSource.setPassword(env.getProperty("spring.datasource-snippet.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean snippetEMF() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect",
                env.getProperty("spring.datasource-snippet.jpa.hibernate.dialect"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto",
                env.getProperty("spring.datasource-snippet.jpa.hibernate.ddl-auto"));
        jpaProperties.setProperty("hibernate.show_sql",
                env.getProperty("spring.datasource-snippet.jpa.show-sql"));

        return new LocalContainerEntityManagerFactoryBean() {
            {
                setJpaProperties(jpaProperties);
                setDataSource(snippetHikari());
                setPackagesToScan("co.id.bankbsi.middlewaresnippet.repository.database.entity");
                setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            }
        };
    }

    @Bean
    public PlatformTransactionManager snippetTMR() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(snippetEMF().getObject());
        return transactionManager;
    }
}
