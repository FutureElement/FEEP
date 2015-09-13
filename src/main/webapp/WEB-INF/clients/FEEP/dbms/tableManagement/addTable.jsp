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
            <form id="tableForm" class="fui-form" warn="填写数据表基本信息，表名不能重复。">
                <div>
                    <label>表名</label>

                    <div>
                        <input type="text" name="name" placeholder="表名">
                    </div>
                </div>
                <div>
                    <label>显示名</label>

                    <div>
                        <input type="text" name="showname" placeholder="显示名">
                    </div>
                </div>
                <div>
                    <label>类型</label>

                    <div>
                        <div class="fui-radio" name="tabletype" value="2"
                             data='[{codeId:1,codeValue:"系统表"},{codeId:2,codeValue:"业务表"}]'>
                        </div>
                    </div>
                </div>
                <div>
                    <label>所属系统</label>

                    <div>
                        <div class="fui-dropdown" name="system" data='[{codeId:1,codeValue:"FEEP"}]'></div>
                    </div>
                </div>
                <div>
                    <label>备注</label>

                    <div>
                        <textarea type="text" name="description" rows="5" id="description"
                                  placeholder="备注"></textarea>
                    </div>
                </div>
            </form>
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
