<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<feep>
    <ul id="tableTab" class="nav nav-tabs" role="tablist" style="margin-bottom: 15px;">
        <li role="presentation" class="active">
            <a href="#baseInfo" aria-controls="baseInfo" role="tab" data-toggle="tab">基本信息</a>
        </li>
        <li role="presentation">
            <a href="#fieldList" aria-controls="fieldList" role="tab" data-toggle="tab">字段信息</a>
        </li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane fade in active" id="baseInfo">
            <form class="fui-form form-inline">
                <div class="form-group">
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
                              placeholder="备注" rows="5"></textarea>
                </div>
            </form>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="fieldList">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="name" class="col-sm-4 control-label">表名</label>
                    <div class="col-sm-16">
                        <input type="text" class="form-control" name="name" id="name" placeholder="表名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="showname" class="col-sm-4 control-label">显示名</label>
                    <div class="col-sm-16">
                        <input type="text" class="form-control" name="showname" id="showname" placeholder="显示名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="system" class="col-sm-4 control-label">所属系统</label>
                    <div class="col-sm-16">
                        <input type="text" class="form-control" name="system" id="system" placeholder="所属系统">
                    </div>
                </div>
                <div class="form-group">
                    <label for="tabletype" class="col-sm-4 control-label">类型</label>
                    <div class="col-sm-16">
                        <input type="text" class="form-control" name="tabletype" id="tabletype" placeholder="类型">
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-4 control-label">备注</label>
                    <div class="col-sm-16">
                        <textarea type="text" class="form-control" name="description" id="description" placeholder="备注"></textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <script>
        function main() {

        }
        function test() {
            alert("add");
        }
        function load(){
            alert("load");
        }
    </script>
</feep>
