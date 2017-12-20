package com.example.demo.websocket2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置方式2
 * 更简单的结合Spring的实现方法如下，可以使用@Value或@Autowired
 * @author jjh
 *
 */
@Configuration  
@EnableWebSocket  
public class WebSocketConfig2 implements WebSocketConfigurer {  
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        registry.addHandler(CmdHandler(), "/my-websocket"); //url和handler的mapping  
    }  
  
    @Bean  
    public WebSocketHandler CmdHandler() {  
        return new CmdHandler();  
    }  
}  
