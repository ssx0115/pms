<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>对标管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/echarts.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/benchmarking/list/2019",
                success:function (msg) {
                    var company = [];
                    var salesAccount = [];

                    var echart1 =  echarts.init(document.getElementById("box1"));
                    var echart2 =  echarts.init(document.getElementById("box2"));
                    $(msg).each(function (index,item) {
                        company.push(item.companyName);
                        salesAccount.push(item.salesAmount);
                    });
                    var option = {
                        title:{
                            text:'2019年教育行业收入比较',
                            left:"center",
                            top:"bottom"
                        },
                        legend:{
                            data:['收入']
                        },
                        xAxis:{
                            data:company
                        },
                        yAxis:{
                            type:'value',
                            axisLabel:{
                                formatter:'{value}(亿)',
                            },
                        },
                        series:[{
                            name:'收入',
                            type:'bar',
                            data:salesAccount
                        }],
                    };
                    var option1 = {
                        title:{
                            text:'2019年教育行业收入占比',
                            left:"center",
                            top:"bottom"
                        },
                        series:[{
                            name:'收入',
                            type:'pie',
                            data:salesAccount
                        }],
                    };
                    echart1.setOption(option);
                    echart2.setOption(option1);
                }
            })
        })
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
    当前位置:对标管理>>对标分析
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<div id="box" style="width:1000px;">
    <div id="box1" style="height:300px;"></div>
    <div id="box2" style="height:300px;"></div>
</div>

</body>
</html>