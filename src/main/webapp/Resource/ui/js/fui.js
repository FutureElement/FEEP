/**
 * Created by zhanggang on 2015/6/4.
 */
var FUI = {};
FUI.grid = {
    render: function ($element, options) {
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
            isDropdown: true,//是否向下展开
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
        if ($element.attr("isDropdown")) {
            options.isDropdown = $element.attr("isDropdown");
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
        $element.addClass("dropdown grid-search-input pull-left");//TODO up
        var html = ['<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">'];
        html.push('<span class="pull-left text-left" style="width:' + (options.relWidth - 34) + 'px;">' + options.prompt + '</span><span class="caret"></span>');
        html.push('</button>');
        html.push('<ul class="dropdown-menu" role="menu" aria-labelledby="' + options.id + '" style="min-width:' + options.listWidth + 'px;">');
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
        $element.html(html.join(''));
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
        $element.find("input[name=" + $element.data("options").name + "]").val(codeId);
        $element.attr("selectedText", codeValue);
        $element.find("span.pull-left").text(codeValue);
        var onSelect = $element.data("options").onSelect;
        if (onSelect) {
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
}