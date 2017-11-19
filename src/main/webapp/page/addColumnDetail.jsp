<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@taglib uri="http://www.springsecurity.org/jsp" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增文章</title>
    <link rel="stylesheet" type="text/css"
          href="http://192.168.15.57/imgextra/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="http://192.168.15.57/imgextra/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript"
            src="http://192.168.15.57/imgextra/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://192.168.15.57/imgextra/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="http://192.168.15.57/imgextra/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="http://192.168.15.57/imgextra/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="http://192.168.15.57/imgextra/ueditor/ueditor.all.js"></script>
   <%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css">--%>
    <script type="text/javascript" charset="utf-8"
            src="http://192.168.15.57/imgextra/ueditor/lang/zh-cn/zh-cn.js"></script>

    <link href="http://192.168.15.57/imgextra/shuanchuan/css/common.css" type="text/css" rel="stylesheet">
    <link href="http://192.168.15.57/imgextra/shuanchuan/css/index.css" type="text/css" rel="stylesheet">

    <script type="text/javascript">

var add;
var  update;
        $(function () {
            add=function add() {
                $.messager.alert("温馨提示","添加成功");
            }
            update=function update() {
                $.messager.alert("温馨提示","修改成功");
            }
            $('#qqqqqqqq').searchbox({
                // 在用户按下搜索按钮或回车键的时候调用 searcher 函数
                searcher: function (value, name) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/selectlanmu",
                        type: "post",
                        data: {"columname": value},
                        success: function (data) {
                            if (data != null) {
                                var rootNodes = $("#tt").tree('getRoots');
                                var datat;
                                for (var i = 0; i < rootNodes.length; i++) {
                                    children = $("#tt").tree("getChildren", rootNodes[i].target);
                                    if ("首页" == value) {
                                        $('#tt').tree("collapseAll");//折叠所有节点
                                        var node = $('#tt').tree('find', rootNodes[i].id);
                                        $('#tt').tree('select', node.target);
                                        var checkNode = $("#tt").tree('getSelected');
                                        $("[name='columname']").val(checkNode.text);

                                        return;
                                    }

                                    if (children) { //如果有子节点
                                        for (var j = 0; j < children.length; j++) { //循环所有子节点
                                            if (children[j].text == value) { //判断节点text是否包含搜索文本
                                                $('#tt').tree("collapseAll");//折叠所有节点
                                                var node = $('#tt').tree('find', children[j].id);
                                                $('#tt').tree('select', node.target);
                                                var checkNode = $("#tt").tree('getSelected');
                                                $("[name='columname']").val(checkNode.text);

                                                return;
                                            }
                                        }
                                    }
                                }


                            }
                        }
                    });
                    $('#tt').find('.tree-node-selected').removeClass('tree-node-selected');
                }
            });
            if ("${types}" == "add") {
                $("#but").hide();
                $("[name='columname']").val("${mycolumname}");
            } else {
                $("[name='columname']").val("${mycolumname}");
                $("#but").show();
                var ue=UE.getEditor('editor');
                $.ajax({
                    url:"${pageContext.request.contextPath}updatechuanzhi",
                    type:"post",
                    data:{"id":"${id}"},
                    success:function (data) {
                           $("[name='id']").val(data.id);
                           $("[name='title']").val(data.title);
                           $("[name='tag']").val(data.tag);
                           $("[name='defaulttitle']").val(data.defaulttitle);
                           $("[name='imgurl']").src=data.imageurl;
                        ue.ready(function(){
                            ue.setContent(data.zhengwen);
                        })
                    }
                });

            }
        });
        function getChecked() {
            $("#dlg3").dialog("close");

        }
        function openda() {
            $("#tt").tree({
                url: '${pageContext.request.contextPath}/selectallChinaMath',
                animate: true,
                cascadeCheck: false,
                loadFilter: function (data) {
                    change(data);
                    //图标的设定
                    $.each(data, function (i, v) {
                        v.iconCls = "icon-folder";
                    });
                    return data;
                },
                onLoadSuccess: function () {

                }
                /*onDblClick: function (checkNode) {

                 showcontent(checkNode);
                 }*/
            });
            $("#dlg3").dialog("open").dialog("setTitle", "栏目选择");
        }
        function change(data) {

            if (!data.length) {
                data.id = data.syscode;
                data.text = data.columnamee;
                data.children = data.chirdern;
                if (data.children) {
                    change(data.children);
                }
            } else {
                $.each(data, function (i, v) {
                    change(v);
                });
            }
        }
        function resetValue(typeid) {
            if (typeid == 0) {
                $("[name='type']").val("发布");
            }else{
                $("[name='type']").val("草稿");
            }
            if ("${types}"=="add"){

                $("#fm").form("submit",{
                    url:'${pageContext.request.contextPath}/addnews',
                    success:function(result){
                        if (result){
                            add();
                        }else{
                            alert("no");
                        }
                    }
                });
            }else{
                $("#fm").form("submit",{
                    url:'${pageContext.request.contextPath}/updatenews',
                    success:function(result){
                        if (result){
                            update();
                        }else{
                            alert("no");
                        }
                    }
                });
            }

        }

    </script>

    <style type="text/css">
        div {
            width: 100%;
        }
    </style>

</head>
<body style="margin: 1px">
<div class="easyui-layout" id="addnews" style="width:1127px">
    <form id="fm" method="post" enctype="multipart/form-data">
        <table cellpadding="6px" align="center">
            <tr>
                基本信息
                <input type="hidden" name="id"/>
                <hr>
            </tr>
            <tr>
                <td>所属栏目：</td>
                <td>
                    <input disabled="disabled" name="columname" class="easyui-textbox"
                           style="width: 130px; vertical-align: middle;"/>
                    <a id="but" class="easyui-linkbutton" onclick="openda()" plain="true" icon="icon-ok"></a>
                </td>
            </tr>
            <tr>
                <td>文章标题：</td>
                <td>
                    <input name="title"  class="easyui-textbox" required="true" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td>关键字：</td>
                <td>
                    <input name="tag" class="easyui-textbox" style="width: 130px; vertical-align: middle;"/>
                </td>
            </tr>
            <tr>
                <td> 文章图片：</td>
                <td>
                    <section class=" img-section">
                        <div class="z_photo upimg-div clear">
                            <section class="z_file fl">
                                <img src="http://192.168.15.57/imgextra/shuanchuan/img/a11.png"
                                name="imgurl"     class="add-img">
                                <input type="file" name="file" id="file" class="file"
                                       accept="image/jpg,image/jpeg,image/png,image/bmp"/>
                               <%-- <input name="imageurl" type="hidden"/>--%>
                            </section>
                        </div>
                    </section>
                    <aside class="mask works-mask">
                        <div class="mask-content">
                            <p class="del-p ">您确定要删除作品图片吗？</p>
                            <p class="check-p"><span class="del-com wsdel-ok">确定</span><span class="wsdel-no">取消</span>
                            </p>
                        </div>
                    </aside>
                </td>
            </tr>
            <tr>
                <td>摘要：</td>
                <td><textarea name="defaulttitle" style="width: 400px;height:200px"></textarea></td>
            </tr>

            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td></td>
                <td><textarea id="editor"
                              name="zhengwen"
                              style="width: 400px;height:200px">
                 </textarea>
                    <script type="text/javascript">
                        UE.getEditor('editor');
                    </script>
                </td>

            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="hidden" name="type"/>
                    <a href="javascript:resetValue(1)" class="easyui-linkbutton" iconCls="icon-submit">暂存草稿</a>&nbsp;
                    <a href="javascript:resetValue(0)" class="easyui-linkbutton" iconCls="icon-submit">发布</a>
                    <a href="javascript:submitData()" class="easyui-linkbutton" iconCls="icon-reset">关闭</a>
                </td>
            </tr>

        </table>
    </form>

</div>
<div id="dlg3" class="easyui-dialog" title="栏目选择" style="width:400px;height:500px;padding:10px;margin-bottom: -30px;"
     data-options="
				iconCls: 'icon-save',
				toolbar: '#dlg-toolbar',
				buttons: '#dlg-buttons'
			" closed="true">
    <ul id="tt"></ul>
</div>
<div id="dlg-toolbar" style="padding:2px 0">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <tr>
            <td style="text-align:right;padding-right:2px">
                <input id="qqqqqqqq" class="easyui-searchbox"
                       style="width:150px"></input>

            </td>
        </tr>
    </table>
</div>
<div id="dlg-buttons">
    <a href="javascript:getChecked()" class="easyui-linkbutton">保存</a>
    <a  class="easyui-linkbutton">关闭</a>
</div>
<script src="http://192.168.15.57/imgextra/shuanchuan/js/imgUp.js"></script>
</body>


</html>