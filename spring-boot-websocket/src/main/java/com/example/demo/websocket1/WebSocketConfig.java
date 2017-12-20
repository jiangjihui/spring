package com.example.demo.websocket1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.example.demo.websocket2.CmdHandler;

/**
 * WebSocket配置方式1
 * 下面这种配置有个缺点，就是MyWebSocket类里面无法使用@Value或@Autowired之类的Spring注入。
 * @author jjh
 *
 */
@Configuration
public class WebSocketConfig
{
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}

