<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加客户信息</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">

</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:客户信息管理>>添加客户信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="form2" name="form2" action="${pageContext.request.contextPath}/customer/insert" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;添加客户&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comname" name="comname"/><span id="comnameInfor"></span></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司联系人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="companyperson" name="companyperson"/><span id="companypersonInfor"></span></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司地址：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comaddress" size="60" name="comaddress"/><span id="comaddressInfor"></span></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">联系电话：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comphone" name="comphone"/><span id="comphoneInfor"></span></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">座机：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="camera"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">公司简介：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea rows=15 cols=130 name="present"></textarea></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea rows=10 cols=130 name="remark"></textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="javascript:history.go(-1)" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    /*//公司名称的非空检验
    $("#comname").blur(function(){
        //获取uname的值
        var comname = $(this).val();
        if(comname == null || comname == ""){
            $("#comnameInfor").text("公司名称不能为空").css("color","red");
        }else{
            $("#comnameInfor").text("公司名称输入OK").css("color","green");
        }
    })
    //公司联系人的非空检验
    $("#companyperson").blur(function(){
        //获取area的值
        var companyperson  = $(this).val();
        if(companyperson == null || companyperson==""){
            $("#companypersonInfor").text("公司联系人不能为空").css("color","red");
        }else{
            $("#companypersonInfor").text("公司联系人输入OK").css("color","green");
        }
    })
    //公司地址非空检验
    $("#comaddress").blur(function(){
        //获取area的值
        var comaddress  = $(this).val();
        if(comaddress == null || comaddress==""){
            $("#comaddressInfor").text("公司地址不能为空").css("color","red");
        }else{
            $("#comaddressInfor").text("公司地址输入OK").css("color","green");
        }
    })
    //联系电话的检验
    $("#comphone").blur(function() {
        //获取phone的值
        var comphone = $(this).val();
        //非空检验
        if (comphone == null || comphone == "") {
            $("#comphoneInfor").text("联系电话不能为空").css("color", "red");
            $(this).focus();//输入错误重新聚焦，重新输入
            return;
        }
        //正则校验
        if (!(/^1[3456789]\d{9}$/.test(comphone))) {
            $("#comphoneInfor").text("联系电话格式不正确").css("color", "red");
            $(this).focus();//输入错误重新输入
            return;
        }
    }*/

    function commit() {
        $("#form2").submit();
    }
</script>
</html>