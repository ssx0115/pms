<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <style>
        #uploadImg{
            font-size:16px;
            overflow:hidden;
            position:absolute
        }
        #file{
            position:absolute;
            z-index:100;
            margin-left:-180px;
            font-size:60px;
            opacity:0;
            filter:alpha(opacity=0); margin-top:-5px;
        }
    </style>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='${pageContext.request.contextPath}/project/search' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150'>
            <option value='0'>选择类型...</option>
          	<option value='1'>项目名称</option>
          	<option value='2'>客户方负责人</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:120px'>
            <option value='0'>正序排序...</option>
            <option value='1'>倒序</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table id="list-table" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr id="tr1" align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="6%">立项时间</td>
	<td width="8%">最近更新时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>
    <c:forEach items="${projects}" var="project" varStatus="index">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${project.pid}" class="np"></td>
            <td>${index.count}</td>
            <td align="left"><a href=''><u>${project.pname}</u></a></td>
            <td>${project.customer.comname}</td>
            <td>${project.comper}</td>
            <td>${project.employee.ename}</td>
            <td>${project.empcount}</td>
            <td>
                <fmt:formatDate value="${project.starttime}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td>
                <fmt:formatDate value="${project.buildtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td>
                <fmt:formatDate value="${project.endtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
            </td>
            <td>${project.remark}</td>
            <td><a href="project-base-edit.jsp">编辑</a> | <a href="${pageContext.request.contextPath}/project/selectProjectById/${project.pid}">查看详情</a></td>
        </tr>
    </c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:chooseAll()" class="coolbg">全选</a>
	<a href="javascript:reverseChoose()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:batchDelete()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
    <span id="uploadImg">
       <input type="file" id="file" size="1"onclick="importExcel()" >
       <a href="javascript:void(0)" >上传Excel</a>
    </span>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
    function chooseAll() {
        $("#list-table input").attr("checked","checked");
    }
    function reverseChoose() {
        $("#list-table input").each(function () {
            var checked = $(this).attr("checked");
            $(this).attr("checked",!checked);
        })
    }
    function batchDelete() {
        var ids = [];
        $("#list-table").find("input:checked").each(function (index,item) {
            var id = $(this).val();
            ids.push(id);
        });

        $.ajax({
            type:"POST",
            url:"${pageContext.request.contextPath}/project/batchDelete",
            data:{"_method":"delete","ids":ids},
            success:function(msg) {
                if(msg.statusCode == 200){
                    window.location.href = "${pageContext.request.contextPath}/project/list";
                }else{
                    alert(msg.message);
                    window.location.href = "${pageContext.request.contextPath}/project/list";
                }
            },
            error:function(msg) {
                alert("删除失败111");
                window.location.href = "${pageContext.request.contextPath}/project/list";
            }
        })
    }
    function importExcel(){
        var formData = new FormData();
        //使用定时器判断是否获取到文件
        var result = setInterval(function () {
            var file = $("file")[0].files[0];//将jquery对象转换成js对象
            if(file != undefined){
                clearInterval(result);//获取到文件后取消定时器，并发送异步请求文件上传
                formData.append("excel",file);
                //异步上传
                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/attachment/importExcel",
                    cache:false,
                    processData:false,
                    contentType:false,
                    data:formData,
                    success:function (msg) {
                        alert(msg.message);
                    }
                });
            }
        },1000)
        return false;
    }

</script>
</html>