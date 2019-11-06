<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/moment.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/notice/jsonList",
			success:function(msg){
				if(msg.map.statusCode == 200){
				    $(msg.map.page.list).each(function (index,item) {
				        //1.遍历数据
				        var tr ="<tr name='rowtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE'; onMouseOut='javascript:this.bgColor='#FFFFFF'; height='22' >"
									+"<td><input name='id' type='checkbox' id='id' value='"+ item.nid +"' class='np'></td>"
                        			+"<td>"+ ((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1)) +"</td>"
									+"<td>"+ item.ntitle +"</td>"
									+"<td align='center'><span >"+ item.remark +"</span></td>"
									+"<td>"+ moment(item.ndate).format('YYYY-MM-DD') +"</td>"
									+"<td><a >删除</a>|<a >编辑</a></td>"
						         +"</tr>";

						$("#tr1").before(tr);
                    });
				  	var div = "<div></div>";
				    var firstPage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+ msg.map.requestURI +"?pageNum=1"+ msg.map.queryStr +"'>首页</a>";
                    var prePage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+ msg.map.requestURI +"?pageNum="+ (msg.map.page.pageNum-1) + msg.map.queryStr +"'>上一页</a>";
					var nextPage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+ msg.map.requestURI +"?pageNum="+ (msg.map.page.pageNum+1) + msg.map.queryStr +"'>下一页</a>";
					var endPage = "<a onclick='findList(this.name)' href='javascript:void(0)' name='"+ msg.map.requestURI +"?pageNum="+ msg.map.page.pages + msg.map.queryStr +"'>尾页</a>";
					//var changePage = "跳转到<input size='1px' id='pageName' type='text' name='"+ msg.msp.pageName +"' onblur='queryList()"+ msg.map.queryStr +"'>页";
					var pages = "";
					$(msg.map.page.navigatepageNums).each(function (index,item) {
                    	pages = pages + "&nbsp;<a onclick='findList(this.name)' href='javascript:void(0)' name='"+ msg.map.requestURI +"?pageNum="+ item + msg.map.queryStr +"'>"+item +"</a> &nbsp;"
                    });

					$(div).append(firstPage).append(prePage).append(pages).append(nextPage).append(endPage).appendTo($("#pageTd"));


					//2.遍历分页
				}
			},
		});
	});
	function findList(obj) {
        $.ajax({
            type:"GET",
            url:obj,
            success:function(msg){
                if(msg.map.statusCode == 200){
                    //删除原来的tr
					$("tr[name=rowtr]").remove();
                    $(msg.map.page.list).each(function (index,item) {
                        //1.遍历数据
                        var tr ="<tr name='rowtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE'; onMouseOut='javascript:this.bgColor='#FFFFFF'; height='22' >"
                            +"<td><input name='id' type='checkbox' id='id' value='"+ item.nid +"' class='np'></td>"
                            +"<td>"+ ((msg.map.page.pageNum-1)*msg.map.page.pageSize+(index+1)) +"</td>"
                            +"<td>"+ item.ntitle +"</td>"
                            +"<td align='center'><span >"+ item.remark +"</span></td>"
                            +"<td>"+ moment(item.ndate).format('YYYY-MM-DD') +"</td>"
                            +"<td><a >删除</a>|<a >编辑</a></td>"
                            +"</tr>";
                        $("#tr1").before(tr);
                    });


                    //2.遍历分页
                }
            },
        });

		return false;
    }
</script>





</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table id="table-list" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>项目完成的咋样?</td>
	<td align="center"><span >最近工作累不？项目完成的咋样?加油哈</span></td>
	<td>2015-02-03</td>
	<td><a >删除</a></td>
</tr>--%>


<tr id="tr1" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center" id="pageTd">

	</td>
</tr>
</table>

</form>
  

</body>
</html>