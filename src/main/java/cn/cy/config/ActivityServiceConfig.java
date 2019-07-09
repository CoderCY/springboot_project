package cn.cy.config;

import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * Created by 30721 on 2019/7/7.
 */
@Order(2)
@Configuration
public class ActivityServiceConfig {

    @Autowired
    private ProcessEngine engine;

    //管理流程定义的相关操作(部署,查询,删除等)
    @Bean
    public RepositoryService repositoryService() {
        return engine.getRepositoryService();
    }

    //管理执行的,流程实例的管理操作,一次具体执行的信息,包括启动、推进、删除Execution等操作
    @Bean
    public RuntimeService runtimeService() {
        return engine.getRuntimeService();
    }

    //管理任务的(查询任务,办理任务)
    @Bean
    public TaskService taskService() {
        return engine.getTaskService();
    }

    //管理历史的数据(执行完的数据管理,主要是查询)大家都在发
    @Bean
    public HistoryService historyService() {
        return engine.getHistoryService();
    }


}
