<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="common/common.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		 
		 $.ajax({
             type: "GET",
             url: "/jingtuitui/search",
             data: {searchKey:""},
             dataType: "json",
             success: function(data){
            	 console.log(data);
             }

         });
		$("#main-content").html("<span style='color:red'>ffff</span>");

	});
</script>
<body>
	京推推欢迎你
	<div id="main-content"></div>
</body>
</html>