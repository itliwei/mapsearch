package com.yimayhd.mapsearch;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.yimayhd.user.client.domain.UserDO;
import com.yimayhd.user.client.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * 类描述：junit测试基础类，引入Spring配置文件，以便注入。
 * 
 * @author: zouwei@pajk.cn
 * @version: 2014年5月6日 下午5:30:00
 */

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/META-INF/spring/application-common.xml",
		"file:src/main/webapp/META-INF/spring/application-tair.xml",
		"file:src/main/webapp/META-INF/spring/application-consumer.xml",
		"file:src/main/webapp/META-INF/spring/application-manager.xml",
		"file:src/main/webapp/META-INF/spring/application-service.xml",
		"file:src/main/webapp/META-INF/spring/application-provider.xml",
		"file:src/main/webapp/META-INF/spring/application-mq.xml",
		"file:src/main/webapp/META-INF/spring/application-mongo.xml",
		"file:src/main/webapp/META-INF/spring/application-es.xml",
		"file:src/main/webapp/META-INF/spring/application-dal.xml"
})
public class DobboJunitBaseTest {
	@Resource
	private UserService userService;
	@org.junit.Test
	public void test() {
		UserDO ret = userService.getUserDOById(100);
		printResult(ret,"userService.getUserDOById(100)");
	}

	public void printResult(Object result, String method) {
		String json = JSON.toJSONString(result);
		System.err.println("**************************   " + method + " start");
		System.err.println(json);
		System.err.println("**************************   " + method + " end\n\n");
	}

}
