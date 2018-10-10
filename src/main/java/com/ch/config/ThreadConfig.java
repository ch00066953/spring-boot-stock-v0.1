package com.ch.config;
import java.util.concurrent.Executor;  
  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.scheduling.annotation.EnableAsync;  
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;  
  
@Configuration  
@EnableAsync  // 启用异步任务  
public class ThreadConfig  {  
  
	ThreadPoolTaskExecutor executor ;
     // 执行需要依赖线程池，这里就来配置一个线程池  
     @Bean  
     public Executor getExecutor() {  
          executor = new ThreadPoolTaskExecutor();  
          executor.setCorePoolSize(8);  
          executor.setMaxPoolSize(40);  
          executor.setQueueCapacity(100); 
          executor.initialize();  
          return executor;  
     }  
     
     public ThreadPoolTaskExecutor seeExecutor() { 
    	 return executor;  
     }
     
     private void getC() {
    	 executor.getCorePoolSize();
	}
     private void getA() {
    	 executor.getActiveCount();
     }
}  