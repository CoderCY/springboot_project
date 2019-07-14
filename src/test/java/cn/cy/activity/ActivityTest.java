package cn.cy.activity;

import cn.cy.util.KeyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.*;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.repository.Model;
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
import java.util.List;

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

    /**
     * 测试定时器事件
     */
    @Test
    public void timerTest() {

        // 启动AsyncExecutor
        engine.getProcessEngineConfiguration().getAsyncExecutor().start();//注意这句话开启定时器，一定要加
        // 部署流程文件
        repositoryService.createDeployment()
                .addClasspathResource("processes/timer.bpmn").deploy();
        // 启动流程
        runtimeService.startProcessInstanceByKey("cy_timerProcess_1");

        // 查询当前任务
        Task task = taskService.createTaskQuery().singleResult();
        System.out.println("定时器为触发时：" + task);
//        taskService.complete(task.getId());
        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 重新查询当前任务
        task = taskService.createTaskQuery().singleResult();
        System.out.println("定时器中间事件的触发后任务：" + task.getName());
        //执行
        taskService.complete(task.getId());
        //关闭AsyncExecutor
        engine.getProcessEngineConfiguration().getAsyncExecutor().shutdown();//注意这句话开启定时器，一定要加
    }

    @Test
    public void model() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode editObjectNode = objectMapper.createObjectNode();
//        editObjectNode.put("id", "canvas");
//        editObjectNode.put("resourceId", "canvas");
//        ObjectNode stencilsetNode = objectMapper.createObjectNode();
//        stencilsetNode.put("namespace","http://b3mn.org/stencilset/bpmn2.0#");
//        editObjectNode.put("stencilset", stencilsetNode);
//        Model model = repositoryService.newModel();
//        ObjectNode modelObjectNode = objectMapper.createObjectNode();
//        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "");
//        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
//        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "");
//        model.setMetaInfo(modelObjectNode.toString());
//        model.setName("");
//        model.setKey("123");
//        repositoryService.saveModel(model);
//        System.out.println(repositoryService.getModel("123"));
        List<Model> list = repositoryService.createModelQuery().list();
        for (Model model: list) {
            System.out.println(model.getId());
        }
    }
}
