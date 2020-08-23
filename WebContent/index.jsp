<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(function(){
	alert("111111");
	 $("#f1").hide();
	 $("#f2").hide();
	 $("#bt1").click(function(){
		$("#f1").show();
	});
	$("#bt2").click(function(){
		$("#f2").show();
	}); 
});
</script>
</head>
<body>
<input type="button" id="bt1" value="转出">
<input type="button" id="bt2" value="转入">

<form id="f1" action="bankaction.do" method="post">
转出账户:<input type="text" name="id" id="id"><br>
转出金额:<input type="text" name="acount" id="acount"><br>
<input type="hidden" name="op" id="op" value="1"><br>
<input type="submit"  value="确定">
</form>

<form id="f2" action="bankaction.do" method="post">
转入账户:<input type="text" name="id" id="id"><br>
转入金额:<input type="text" name="acount" id="acount"><br>
<input type="hidden" name="op" id="op" value="2"><br>
<input type="submit"  value="确定">
</form>


</body>
</html>