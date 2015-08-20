<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/Resource/include/feep-global-header.jsp" %>
<body>
<ul id="tableTab" class="nav nav-tabs" style="margin-bottom: 15px;">
    <li role="presentation" class="active"><a href="#">基本信息</a></li>
    <li role="presentation"><a href="#">字段信息</a></li>
</ul>
<form class="form-inline">
    <div class="form-group has-error has-feedback">
        <label for="name">表名</label>
        <input type="text" class="form-control c1" name="name" id="name" placeholder="表名">
    </div>
    <div class="form-group">
        <label for="showname">显示名</label>
        <input type="text" class="form-control c1" name="showname" id="showname" placeholder="显示名">
    </div>
    <div class="form-group">
        <label for="system">所属系统</label>
        <input type="text" class="form-control c1" name="system" id="system" placeholder="所属系统">
    </div>
    <div class="form-group">
        <label for="tabletype">类型</label>
        <input type="text" class="form-control c1" name="tabletype" id="tabletype" placeholder="类型">
    </div>
    <div class="form-group">
        <label for="description">备注</label>
            <textarea type="text" class="form-control c2" name="description" id="description"
                      placeholder="备注" rows="3"></textarea>
    </div>
</form>
</body>
<%@ include file="/Resource/include/feep-js-lib.jsp" %>
<script>
    function main() {
        $('#tableTab a').click(function (e) {
            e.preventDefault();
            $(this).tab('show')
        })
    }
    function test(arg) {
        alert("add");
    }
</script>
</html>
