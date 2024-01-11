package com.universe.ads.node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 	java -jar wa-node-0.0.1-SNAPSHOT.jar --server.port=7770 --socket.port=7771
 * 	docker build -t node-app:latest .
 * 	docker run --name sa-node -d --restart=always -p 18800:18800  -v /Users/huangguoye/IdeaProjects/company/nayouta/wa-node/docker/logs:/logs -e NODE_PORT=118800 node-app:latest
 */
@SpringBootApplication
@EnableAsync
public class WaNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaNodeApplication.class, args);
	}

}
