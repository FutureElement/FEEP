<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<feep>
    <div class="fui-grid" data-controller="feep_queryFeepTable" sf-js="getQueryItem"
         params='{"module":"feeptable"}'>
        <div class="top-toolbar">
            <div class="fui-button" renderType="2" id="test" icon="glyphicon glyphicon-plus" onClick="add">增 加</div>
        </div>
        <div class="bottom-grid">
            <div class="column" width="15%" name="name" sortable="true">表名</div>
            <div class="column" width="15%" name="showname" sortable="true">显示名</div>
            <div class="column" width="15%" name="system" sortable="true">所属系统</div>
            <div class="column" width="15%" name="tabletype" code="dbType" sortable="true">类型</div>
            <div class="column" width="auto" name="description" sortable="true">备注</div>
            <div class="column" width="200px" type="operate" links="查看:innerView,修改:innerUpdate,删除:innerDelete">
                操作
            </div>
        </div>
    </div>
    <script>
        var main = function () {

        };
        var getQueryItem = function () {
            var qi = [];
            qi[0] = {codeId: "name", codeValue: "表名", attr: {fieldType: "Text"}};
            qi[1] = {codeId: "showname", codeValue: "显示名", attr: {fieldType: "Text"}};
            qi[2] = {codeId: "system", codeValue: "所属系统", attr: {fieldType: "Text"}};
            qi[3] = {codeId: "tabletype", codeValue: "类型", attr: {fieldType: "Text", code: "tableType"}};
            qi[4] = {codeId: "description", codeValue: "描述", attr: {fieldType: "TextArea"}};
            return qi;
        };
        var add = function () {
            FUI.open({
                name: "feep_addTable",
                title: "增加数据表",
                okName: "创 建",
                ok: function (modal) {
                    var data = modal.getData();
                    FUI.alert(Feep.toJson(data));
                }
            });
        };
        function test() {
            alert("index");
        }
    </script>
</feep>