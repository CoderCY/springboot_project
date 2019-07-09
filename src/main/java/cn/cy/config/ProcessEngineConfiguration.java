package cn.cy.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by 30721 on 2019/7/7.
 */
@Order(1)
@Configuration
public class ProcessEngineConfiguration extends SpringProcessEngineConfiguration {

    @Resource
    private DataSource dataSource;

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Bean(name = "processEngine")
    public ProcessEngine initProcessEngine() {
        super.setDataSource(dataSource);
        super.setTransactionManager(dataSourceTransactionManager);
        super.setDatabaseSchemaUpdate("true");
        ProcessEngine processEngine = super.buildProcessEngine();
        return processEngine;
    }

}
