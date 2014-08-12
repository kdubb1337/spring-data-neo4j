package com.kdubb.springdataneo4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdubb.springdataneo4j.model.Node;
import com.kdubb.springdataneo4j.repository.NodeNeo4JRepository;

@Controller
public class HelloController {
	
	@Autowired
	private NodeNeo4JRepository nodeRepo;
	
	@Autowired
	private Neo4jTemplate template;
	
	@Autowired
	private ThreadPoolTaskExecutor executor;
	
	@ResponseBody
    @RequestMapping(value="/createNodes", method=RequestMethod.GET)
    public String createNodes() throws InterruptedException {
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 100; i++) {
			save(getRandomNode());
		}
		
		while(executor.getActiveCount() != 0) {
			Thread.sleep(200);
			System.out.println("executor.getActiveCount()=" + executor.getActiveCount());
		}
		
		System.out.println("time=" + (System.currentTimeMillis() - start) + "ms");
		
    	return "done";
    }
	
	private void save(final Node node) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				template.save(node);
			}
		});
	}
	
	private Node getRandomNode() {
		Node node = new Node();
		node.setWebspineId(UUID.randomUUID().toString());
		node.setName(UUID.randomUUID().toString());
		node.setType("PERSON");
		node.setCreateDate(new Date());
		
		return node;
	}
	
	@ResponseBody
    @RequestMapping(value="/createConnections", method=RequestMethod.GET)
    public String createConnections() {
    	return "done";
    }
}