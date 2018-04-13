$('#search-input').bind('keypress',function(event){ 
         
          
         if(event.keyCode == 13)      

         {  
             //alert('你输入的内容为1：' + $('#search-input').val());  
             window.location.href="/"+ $('#search-input').val();
         }  

     });