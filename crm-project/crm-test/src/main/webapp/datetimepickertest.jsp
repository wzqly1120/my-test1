<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <!--jquery-->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <!--bootstrap框架-->
    <link rel="stylesheet" type="text/css" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <!--bs_datetimepicker插件-->
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <title>演示bs_datetimepicker日历插件</title>
    <script type="text/javascript">
        $(function () {
            //当容器加载完成之后，对容器调用工具函数
            $("#mydate").datetimepicker({
                language:'zh-CN',//语言
                format:'yyyy-mm-dd',//显示日期格式
                minView:'month',//能够选择的最小视图
                initialDate:new Date(),//日历默认选中的日期
                autoclose:true,//选择到最小视图之后，日历是否自动关闭
                todayBtn:true,//是否显示"今天"按钮
                clearBtn:true//是否显示"清空"按钮
            });
        });
    </script>
</head>
<body>
<input type="text" id="mydate" readonly>
</body>
</html>
