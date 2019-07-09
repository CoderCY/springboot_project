package cn.cy.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by 30721 on 2019/7/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityTest {

    @Autowired
    private ProcessEngine engine;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DataSource dataSource;

    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Test
    public void getbean() {
        System.out.println(engine);
        System.out.println(runtimeService);
        System.out.println(jdbcTemplate);
        System.out.println(dataSource);
        System.out.println(dataSourceTransactionManager);
    }

    @Test
    public void test() {
//        RepositoryService repositoryService = engine.getRepositoryService();
//		// 部署流程文件
//        repositoryService.createDeployment()
//                .addClasspathResource("processes/First.bpmn").deploy();
        // 启动流程
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process1");
		// 查询第一个任务
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		System.out.println("当前节点名称" + task.getName());
		// 完成第一个任务
		taskService.complete(task.getId());
		task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		System.out.println("流程结束后，查找任务：" + task);
		engine.close();
		System.out.println("================================================");
    }
}
