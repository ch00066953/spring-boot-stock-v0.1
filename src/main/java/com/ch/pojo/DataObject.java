package com.ch.pojo;

import lombok.Data;  
import lombok.Getter;  
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;  
  
@Data  
@Slf4j
public class DataObject {  
     private String id;     
     @Setter@Getter  
     private String name;     
     private String userId;     
     private String password;    
     
     private void da() {
    	 getName();

	}
}  