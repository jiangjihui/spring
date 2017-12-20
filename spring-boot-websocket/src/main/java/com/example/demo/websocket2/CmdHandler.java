package com.example.demo.websocket2;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket配置方式2
 * @author jjh
 *
 */
public class CmdHandler extends TextWebSocketHandler {  
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());  
  
    @Value("${spring.profiles}")  
    private String env; 
    
//    @Autowired  
//    MyService myService;
  
    @Override  
    public void handleTextMessage(WebSocketSession session, TextMessage message) {  
    	System.out.println("env:"+env);
        TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");  
  
        logger.info("message received: {}", message.getPayload());  
        try {  
                session.sendMessage(msg);  
        }catch (IOException e){  
            logger.error(e.getMessage(), e);  
        }  
    }  
}  
