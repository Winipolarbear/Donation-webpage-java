<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <script src="${pageContext.request.contextPath}//js/jquery.min.js"></script>
</head>
<body>
<center><h1>DONATE</h1></center>
<center><h3>${versions }</h3></center>
<center>
	<form method="post" id="formid"  >
		<input type="number" name="money" id="money">
		<input type="button" onclick="submits()" value="donata">
	</form>
</center>
</body>

<script>

	function submits(){
		
		var money=$("#money").val();
		if(money==""||money==null){
			alert("Value cannot be empty");
		}else if(money<0){
			alert("value must be greater than 1");
			$("#money").val("");
			
		}else{
			$.ajax({  
			    type: "POST",  
			    url:"${pageContext.request.contextPath }/MoneyServlet?method=add",  
			    data:$('#formid').serialize(),  
			    error: function(request) {  
			    	 alert("fail");  
			    },  
			    success: function(data) {  
			    	console.log(data);
		             
		               var code=data.code;
		               if(code==0){
		            	   alert(data.msg);
		            	   
		               }else{
		            	   
		            	   location=data.data;
		               }
			    }  
			  });
			
		}
	}
</script>
</html>