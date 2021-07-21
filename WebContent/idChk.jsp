<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var id = $("input[name=myid]", opener.document).val();
		$("input[name=id]").val(id);
	});
		
	function confirm(bool){
		if(bool == "true"){
			$("input[name=mypw]", opener.document).focus();
			$("input[name=myid]", opener.document).attr("title","y");
		}else{
			$("input[name=myid]", opener.document).focus();
		}
		
		self.close();
		
	}




</script>
<style type="text/css">
	
	#idchkbox{
		display: grid;
		place-items: center;	
	}
	table{
		text-align: center;
		margin-top : 30px;
	}
	
	.chkf{
        width: 240px;
        height: 56px;
        font-size: 14px;
        background-color: #19ce60;
        border: 1px solid #15c654;
        border-radius: 5px;
        font-size: 18px;
        font-weight: 900;
        color: #fff;
        margin-top: 20px;
    }

	.intext{
        width: 260px;
        height: 44px;
        padding: 0 14px;
        border: 1px solid #ccc;
        font-size: 14px;
        color: #333;
        line-height: 20px;
        border-radius: 3px;
        background: #fff;
        outline: none;
        vertical-align: top;
        text-align: center;
    }


</style>


</head>
<body>
<%
	String idnotused = request.getParameter("idnotused");
%>
	<div id="idchkbox">
	<table>
		<tr>
			<td><input type="text" name="id" readonly="readonly" class="intext"></td>
		</tr>
		<tr>
			<td><%=idnotused.equals("true")?"사용하실 수 있는 아이디입니다":"중복된 아이디입니다" %></td>
		</tr>
		<tr>
			<td><input type="button" value="확인" class="chkf" onclick="confirm('<%=idnotused%>');"></td>
		</tr>
	</table>
	</div>





</body>
</html>