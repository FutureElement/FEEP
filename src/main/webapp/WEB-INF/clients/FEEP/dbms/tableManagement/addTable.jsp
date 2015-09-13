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
            <div class="row">
                <div class="col-sm-18" style="border-right: 1px solid #ddd;">
                    <form id="tableForm" class="form-horizontal">
                        <div class="form-group">
                            <label for="name" class="col-sm-4 control-label">表名</label>

                            <div class="col-sm-18">
                                <input type="text" class="form-control" name="name" id="name" placeholder="表名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="showname" class="col-sm-4 control-label">显示名</label>

                            <div class="col-sm-18">
                                <input type="text" class="form-control" name="showname" id="showname" placeholder="显示名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tabletype" class="col-sm-4 control-label">类型</label>

                            <div class="col-sm-18">
                                <div class="fui-radio" name="tabletype" id="tabletype" value="2" data='[{codeId:1,codeValue:"系统表"},{codeId:2,codeValue:"业务表"}]'>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="system" class="col-sm-4 control-label">所属系统</label>

                            <div class="col-sm-18">
                                <div class="fui-dropdown" id="system" name="system"
                                     data='[{codeId:1,codeValue:"FEEP"}]'></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="description" class="col-sm-4 control-label">备注</label>

                            <div class="col-sm-18">
                                <textarea type="text" class="form-control" name="description" rows="5" id="description"
                                          placeholder="备注"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-sm-6" style="padding: 10px;">
                    <p class="text-warning">填写数据表基本信息，表名不能重复。</p>
                </div>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane fade" id="fieldList">

        </div>
    </div>

    <script>
        function main() {
        }
        function getData() {
            return Feep.form.getData("#tableForm");
        }
        function load() {

        }
    </script>
</feep>
