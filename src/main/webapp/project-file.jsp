<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <
    <base href="${pageContext.request.contextPath}/">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
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
                        当前位置:项目管理>>附件管理
                    </td>
                    <td>
                        <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/project-file-add.jsp';" value='添加新附件' />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
    <input type='hidden' name='dopost' value='' />
    <table width='98%'  border='1' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='160'>
                            <select name='cid' style='width:150'>
                                <option value='0'>选择类型...</option>
                                <option value='1'>附件名称</option>
                                <option value='1'>项目名称</option>
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
                                <option value='id'>排序...</option>
                                <option value='pubdate'>添加时间</option>
                                <option value='pubdate'>修改时间</option>
                            </select>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<!--  内容列表   -->
<form name="form2">

    <table id="table-list" width="98%" border="1" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
        </tr>
        <tr align="center" bgcolor="#FAFAF1" height="22">
            <td width="4%">选择</td>
            <td width="6%">序号</td>
            <td width="10%">附件名称</td>
            <td width="10%">所属项目</td>
            <td width="8%">附件信息描述</td>
            <td width="8%">备注</td>
            <td width="13%">操作</td>
        </tr>
        <c:forEach items="${attachments}" var="attachment" varStatus="index">
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
                <td><input name="id" type="checkbox" id="id" value="${attachment.id}" class="np"></td>
                <td>${index.count}</td>
                <td>${attachment.attname}</td>
                <td align="center"><a href=''><u>${attachment.project.pname}</u></a></td>
                <td>${attachment.attdis}</td>
                <td>${attachment.remark}</td>
                <td><a href="${pageContext.request.contextPath}/attachment/download/${attachment.id}">下载</a>
                    |<a href="${pageContext.request.contextPath}/attacment/delete/${attachment.id}">删除</a>
                    |<a href="${pageContext.request.contextPath}/attachment/seachAttachment/${attachment.id}">编辑</a>
                    | <a href="${pageContext.request.contextPath}/attachment/selectAttachment/${attachment.id}">查看详情</a></td>
            </tr>
        </c:forEach>


        <tr bgcolor="#FAFAF1">
            <td height="28" colspan="12">
                &nbsp;
                <a href="javascript:chooseAll()" class="coolbg">全选</a>
                <a href="javascript:reverseChoose()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:batchDelete()" class="coolbg">&nbsp;删除&nbsp;</a>
                <a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
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
        $("#table-list input").attr("checked","checked");
    }
    function reverseChoose(){
        $("#table-list input").each(function () {
            var checked = $(this).attr("checked");
            $(this).attr("checked",!checked);
        })
    }
    function batchDelete() {
        var ids = [];
        $("#table-list").find("input:checked").each(function () {
            var id = $(this).val();
            ids.push(id);
        });
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/attachment/batchDelete",
            data:{"_method":"delete","ids":ids},
            success:function (msg) {
                if(msg.statusCode == 200){
                    window.location.href = "${pageContext.request.contextPath}/attachment/list";
                }else{
                    alert(msg.message);
                    window.location.href = "${pageContext.request.contextPath}/attachment/list";
                }
            },
            error:function(msg) {
                alert("删除失败111");
                window.location.href = "${pageContext.request.contextPath}/attachment/list";
            }
        })
    }
    function exportExcel() {
        $.ajax({
            type:"get",
            url:"${pageContext.request.contextPath}/attachment/exportExcel",
            success:function (msg) {
                if(msg.code == 200){
                    alert(msg.message);
                }
            }
        })
    }
</script>
</html>