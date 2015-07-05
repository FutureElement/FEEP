/**
 * Created by zhanggang on 2015/6/4.
 */
var FUI = {};
FUI.grid = {
    render: function ($element, options) {
        var options = {

        };
        var topHtml = ['<div class="panel panel-default grid-search-panel">'];
        topHtml.push('<div class="panel-body grid-search-body">');
        topHtml.push('<div>');
        topHtml.push('<form class="form-inline">');
        topHtml.push('<div class="form-group grid-search-group">');
        topHtml.push('<label for="dropdownTest" class="grid-search-label text-center">过滤条件：</label>');
        topHtml.push('<div class="fui-dropdown"></div>');
        topHtml.push('</div>');
        topHtml.push('<div class="form-group grid-search-group">');
        topHtml.push('<button type="button" class="btn btn-primary btn-sm">');
        topHtml.push('<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>');
        topHtml.push('</button>');
        topHtml.push('</div>');
        topHtml.push('<div class="clearfix"></div>');
        topHtml.push('<div class="form-group grid-search-group">');
        topHtml.push('<label class="grid-search-label text-center">');
        topHtml.push('<input type="checkbox" checked>');
        topHtml.push('</label>');
        topHtml.push('<div class="panel panel-default form-control">');
        topHtml.push('<div class="panel-body text-left">');
        topHtml.push('<strong>表名</strong>');
        topHtml.push('</div>');
        topHtml.push('</div>');
        topHtml.push('</div>');
        topHtml.push('<div class="form-group grid-search-group">');
        topHtml.push('<div class="fui-dropdown"></div>');
        topHtml.push('</div>');
        topHtml.push('<div class="form-group grid-search-group">');
        topHtml.push('<input type="email" class="form-control grid-search-input">');
        topHtml.push('</div>');
        topHtml.push('<div class="form-group grid-search-group">');
        topHtml.push('<button type="button" class="btn btn-danger btn-sm">');
        topHtml.push('<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>');
        topHtml.push('</button>');
        topHtml.push('</div>');
        topHtml.push('<div class="clearfix"></div>');
        topHtml.push('</form>');
        topHtml.push('</div>');
        topHtml.push('<div class="grid-search-btn-group">');
        topHtml.push('<table>');
        topHtml.push('<tr>');
        topHtml.push('<td>');
        topHtml.push('<div class="separate-line"></div>');
        topHtml.push('</td>');
        topHtml.push('<td width="6"></td>');
        topHtml.push('<td width="10" class="hand" onclick="hideSearch(this)">');
        topHtml.push('<div class="dropup">');
        topHtml.push('<span class="glyphicon glyphicon-chevron-up"></span>');
        topHtml.push('</div>');
        topHtml.push('</td>');
        topHtml.push('<td width="10" class="hand" style="display: none" onclick="showSearch(this)">');
        topHtml.push('<div class="dropdown">');
        topHtml.push('<span class="glyphicon glyphicon-chevron-down"></span>');
        topHtml.push('</div>');
        topHtml.push('</td>');
        topHtml.push('<td width="5"></td>');
        topHtml.push('<td>');
        topHtml.push('<div class="separate-line"></div>');
        topHtml.push('</td>');
        topHtml.push('</tr>');
        topHtml.push('</table>');
        topHtml.push('<button type="button" class="btn btn-success btn-sm grid-search-btn" onclick="add()">添加</button>');
        topHtml.push('<button type="button" class="btn btn-primary btn-sm grid-search-btn">查询</button>');
        topHtml.push('<button type="button" class="btn btn-danger btn-sm grid-search-btn">重置</button>');
        topHtml.push('</div>');
        topHtml.push('</div>');
        topHtml.push('</div>');


        var bottomHtml = ['<div class="table-responsive grid-bottom">'];
        bottomHtml.push('<table class="table table-bordered table-hover table-striped table-condensed grid-table">');
        bottomHtml.push('<thead class="grid-thead">');
        bottomHtml.push('<tr>');
        bottomHtml.push('<th width="50px" class="text-center">序号</th>');
        bottomHtml.push('<th width="15%" class="hand">表名</th>');
        bottomHtml.push('<th width="15%" class="hand">显示名</th>');
        bottomHtml.push('<th width="15%" class="hand">所属系统</th>');
        bottomHtml.push('<th width="15%" class="hand">类型</th>');
        bottomHtml.push('<th width="auto" class="hand">备注</th>');
        bottomHtml.push('<th width="200px" class="text-center">操作</th>');
        bottomHtml.push('</tr>');
        bottomHtml.push('</thead>');
        bottomHtml.push('<tbody>');
        bottomHtml.push('<tr>');
        bottomHtml.push('<td class="text-center">1</td>');
        bottomHtml.push('<td>2</td>');
        bottomHtml.push('<td>3</td>');
        bottomHtml.push('<td>4</td>');
        bottomHtml.push('<td>5</td>');
        bottomHtml.push('<td>6</td>');
        bottomHtml.push('<td class="text-center">');
        bottomHtml.push('<button type="button" class="btn btn-link grid-operate-btn">查看</button>');
        bottomHtml.push('<button type="button" class="btn btn-link grid-operate-btn">修改</button>');
        bottomHtml.push('<button type="button" class="btn btn-link grid-operate-btn">删除</button>');
        bottomHtml.push('</td>');
        bottomHtml.push('</tr>');
        bottomHtml.push('</tbody>');
        bottomHtml.push('</table>');
        //TODO
        bottomHtml.push('<div class="text-center" style="min-height: 200px;border: 1px solid #ddd;margin-bottom: 5px;padding-top: 90px;">没有数据</div>');
        bottomHtml.push('<div class="btn-toolbar grid-pager-toolbar" role="toolbar" aria-label="...">');
        bottomHtml.push('<div class="btn-group" role="group" aria-label="...">');
        bottomHtml.push('<nav>');
        bottomHtml.push('<ul class="pagination grid-pager-toolbar-nav">');
        bottomHtml.push('<li>');
        bottomHtml.push('<a href="#" aria-label="Previous">');
        bottomHtml.push('<span aria-hidden="true">上一页</span>');
        bottomHtml.push('</a>');
        bottomHtml.push('</li>');
        bottomHtml.push('<li><a href="#">1</a></li>');
        bottomHtml.push('<li><a href="#">2</a></li>');
        bottomHtml.push('<li><a href="#">3</a></li>');
        bottomHtml.push('<li><a href="#">4</a></li>');
        bottomHtml.push('<li class="active"><a href="#">5</a></li>');
        bottomHtml.push('<li><a href="#">6</a></li>');
        bottomHtml.push('<li><a href="#">7</a></li>');
        bottomHtml.push('<li><a href="#">8</a></li>');
        bottomHtml.push('<li><a href="#">9</a></li>');
        bottomHtml.push('<li><a href="#">10</a></li>');
        bottomHtml.push('<li><a href="#">...</a></li>');
        bottomHtml.push('<li><a href="#">20</a></li>');
        bottomHtml.push('<li>');
        bottomHtml.push('<a href="#" aria-label="Next">');
        bottomHtml.push('<span aria-hidden="true">下一页</span>');
        bottomHtml.push('</a>');
        bottomHtml.push('</li>');
        bottomHtml.push('</ul>');
        bottomHtml.push('</nav>');
        bottomHtml.push('</div>');
        bottomHtml.push('<div class="input-group grid-pager-toolbar-jump pull-right">');
        bottomHtml.push('<span class="input-group-btn">');
        bottomHtml.push('<div class="btn-group dropup">');
        bottomHtml.push('<button type="button" class="btn btn-default dropdown-toggle grid-pager-toolbar-btn" data-toggle="dropdown" aria-expanded="false">');
        bottomHtml.push('每页20条 <span class="caret"></span>');
        bottomHtml.push('</button>');
        bottomHtml.push('<ul class="dropdown-menu dropdown-menu-left" role="menu">');
        bottomHtml.push('<li><a href="#">10</a></li>');
        bottomHtml.push('<li><a href="#">30</a></li>');
        bottomHtml.push('<li><a href="#">50</a></li>');
        bottomHtml.push('<li><a href="#">100</a></li>');
        bottomHtml.push('<li class="divider"></li>');
        bottomHtml.push('<li><a href="#">20（默认）</a></li>');
        bottomHtml.push('</ul>');
        bottomHtml.push('</div>');
        bottomHtml.push('</span>');
        bottomHtml.push('<input type="text" class="form-control" placeholder="输入页数">');
        bottomHtml.push('<span class="input-group-btn">');
        bottomHtml.push('<button class="btn btn-default grid-pager-toolbar-btn" type="button">跳转</button>');
        bottomHtml.push('<button class="btn btn-default grid-pager-toolbar-btn" type="button">导出</button>');
        bottomHtml.push('</span>');
        bottomHtml.push('</div>');
        bottomHtml.push('</div>');
        bottomHtml.push('</div>');

        $element.append(topHtml.join(' '));
        $element.append(bottomHtml.join(' '));
        //alert($element.attr("class"));
    },
    addRow: function ($element, data, index) {

    },
    getRows: function ($element) {

    }
};
FUI.dropdown = {
    render: function ($element, data) {
        var options = {
            id: "",
            name: "",
            data: "",//静态数据codeId,codeValue
            code: "",//代码表name
            controller: "",//自定义方法获得数据
            relWidth: 179,//实际宽度
            listWidth: 179,
            isDown: true,//是否向下展开,
            isRight: true,
            value: "",//初始值
            prompt: "请选择",
            onSelect: null
        };
        options.id = $element.attr("id");
        options.code = $element.attr("code");
        options.controller = $element.attr("controller");
        options.onSelect = $element.attr("onSelect");
        if ($element.attr("relWidth")) {
            options.relWidth = $element.attr("relWidth");
        }
        if ($element.attr("listWidth")) {
            options.listWidth = $element.attr("listWidth");
        }
        if ($element.attr("isDown")) {
            options.isDown = $element.attr("isDown");
        }
        if ($element.attr("isRight")) {
            options.isRight = $element.attr("isRight");
        }
        if ($element.attr("value")) {
            options.value = $element.attr("value");
        }
        if ($element.attr("prompt")) {
            options.prompt = $element.attr("prompt");
        }
        if ($element.attr("name")) {
            options.name = $element.attr("name");
            $element.removeAttr("name");
        }
        if (data) {
            options.data = data;
        } else if (options.controller) {
            //TODO get data
            options.data = Feep.request(options.controller);
        } else if (options.code) {
            //TODO get data
            options.data = Feep.request("feep_getSelectByCode", options.code);
        } else if ($element.attr("data")) {
            options.data = Feep.parseJson($element.attr("data"));
        }
        if (options.isRight) {
            $element.addClass("dropdown grid-search-input pull-left");
        } else {
            $element.addClass("dropup grid-search-input pull-left");
        }
        var html = ['<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">'];
        html.push('<span class="pull-left text-left" style="width:' + (options.relWidth - 34) + 'px;">' + options.prompt + '</span><span class="caret"></span>');
        html.push('</button>');
        var ulAttr = 'aria-labelledby="' + options.id + '" style="min-width:' + options.listWidth + 'px;"';
        if (options.isRight) {
            html.push('<ul class="dropdown-menu dropdown-menu-right" role="menu" ' + ulAttr + '>');
        } else {
            html.push('<ul class="dropdown-menu dropdown-menu-left" role="menu" ' + ulAttr + '>');
        }
        if ($.isArray(options.data) && options.data.length > 0) {
            $(options.data).each(function (i, item) {
                if (item.type && item.type == "divider") {
                    html.push('<li role="presentation" class="divider"></li>');
                } else {
                    html.push('<li role="presentation" codeId="' + item.codeId + '"><a role="menuitem" tabindex="-1">' + item.codeValue + '</a></li>');
                }
            });
        } else {
            html.push('<li role="presentation"><a role="menuitem" tabindex="-1">&nbsp;</a></li>');
        }
        html.push('</ul>');
        if (options.name) {
            html.push('<input type="hidden" name="' + options.name + '">');
        }
        $element.html(html.join(' '));
        $element.data("options", options);
        $element.attr("selectedId", options.value);
        $(options.data).each(function (i, item) {
            if (item.attr) {
                $element.find("li[codeId=" + item.codeId + "]").data("attr", item.attr);
            }
        });
        $element.find("li").click({"$element": $element}, this.clickEvent);
        if (options.value) {
            this.setValue($element, options.value);
        }
    },
    clickEvent: function (event) {
        var codeId = $(this).attr("codeId");
        var codeValue = $(this).children().text();
        var $element = event.data.$element;
        $element.attr("selectedId", codeId);
        $element.attr("selectedText", codeValue);
        $element.find("span.pull-left").text(codeValue);
        if ($element.data("options").name) {
            $element.find("input[name=" + $element.data("options").name + "]").val(codeId);
        }
        var onSelect = $element.data("options").onSelect;
        if (onSelect && window[onSelect]) {
            window[onSelect].call(null, codeId, codeValue, $element.find("li[codeId=" + codeId + "]").data("attr"));
        }
    },
    setValue: function ($element, codeId) {
        if (codeId) {
            $element.find("li[codeId=" + codeId + "]").click();
        } else {
            $element.attr("selectedId", null);
            $element.find("input[name=" + $element.data("options").name + "]").val(null);
            $element.attr("selectedText", null);
            $element.find("span.pull-left").text($element.data("options").prompt);
        }
    },
    getValue: function ($element) {
        return $element.attr("selectedId");
    },
    getText: function ($element) {
        return $element.attr("selectedText");
    },
    addItem: function ($element, data) {
        var $html;
        if (data.type && data.type == "divider") {
            $html = $('<li role="presentation" class="divider"></li>');
        } else {
            $html = $('<li role="presentation" codeId="' + data.codeId + '"><a role="menuitem" tabindex="-1">' + data.codeValue + '</a></li>');
        }
        $element.find("ul").append($html);
        if (data.attr) {
            $html.data("attr", data.attr);
        }
        $html.click({"$element": $element}, this.clickEvent);
    },
    updateItem: function ($element, data) {
        var li = $element.find("li[codeId=" + data.codeId + "]");
        if (li && li.length > 0) {
            li.children().text(data.codeValue);
            if (data.attr) {
                li.data("attr", data.attr);
            }
            if (data.codeId == this.getValue($element)) {
                this.setValue($element, data.codeId);
            }
        }
    },
    removeItem: function ($element, codeId) {
        $element.find("li[codeId=" + codeId + "]").remove();
        if (codeId == this.getValue($element)) {
            this.setValue($element, null);
        }
    },
    setAttr: function ($element, codeId, attrName, attrValue) {
        var attr = $element.find("li[codeId=" + codeId + "]").data("attr");
        if (!attr) {
            attr = {};
        }
        attr[attrName] = attrValue;
        $element.find("li[codeId=" + codeId + "]").data("attr", attr);
    },
    getAttr: function ($element, codeId, attrName) {
        return $element.find("li[codeId=" + codeId + "]").data("attr")[attrName];
    }
};
FUI.renderAll = function () {
    var fui = ["fui-grid", "fui-dropdown"];
    $(fui).each(function (i, item) {
        $("." + item).each(function (num, element) {
            FUI[item.split("-")[1]].render($(element));
        });
    });
};