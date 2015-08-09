/**
 * FUI 客户端UI框架
 * Created by zhanggang on 2015/6/4.
 */
var FUI = {};
FUI.grid = {
    render: function ($element, customOptions) {
        var options = {
            data_module: $element.attr("data-module"),
            data_controller: $element.attr("data-controller"),
            data_js: $element.attr("data-js"),
            data_json: $element.attr("data-json"),
            sf_module: $element.attr("sf-module"),
            sf_controller: $element.attr("sf-controller"),
            sf_js: $element.attr("sf-js"),
            sf_json: $element.attr("sf-json"),
            params: null,
            column: null,
            index: true,
            page: true,
            pageSize: 20,
            checkbox: false,
            radio: false,
            operate: null,
            searchable: true,
            columnType: {
                operate: "operate",
                showColumn: "showColumn"
            }
        };
        var domain = this;
        var timeStamp = new Date().getTime();
        options.timeStamp = timeStamp;
        if ($element.attr("index") == "false") {
            options.index = false;
        }
        if ($element.attr("page") == "false") {
            options.page = false;
        }
        if ($element.attr("searchable") == "false") {
            options.searchable = false;
        }
        if ($element.attr("checkbox") == "true") {
            options.checkbox = true;
        }
        if ($element.attr("radio") == "true") {
            options.radio = true;
        }
        if ($element.attr("pageSize")) {
            options.pageSize = $element.attr("pageSize");
        }
        if ($element.attr("operate")) {
            options.operate = $element.attr("operate");
        }
        if ($element.attr("pageSize")) {
            options.pageSize = $element.attr("pageSize");
        }
        if ($element.attr("params")) {
            options.params = $element.attr("params");
        }
        if (customOptions) {
            if (customOptions.data_module) {
                options.data_module = customOptions.data_module;
            }
            if (customOptions.data_controller) {
                options.data_controller = customOptions.data_controller;
            }
            if (customOptions.data_js) {
                options.data_js = customOptions.data_js;
            }
            if (customOptions.data_json) {
                options.data_json = customOptions.data_json;
            }
            if (customOptions.sf_module) {
                options.sf_module = customOptions.sf_module;
            }
            if (customOptions.sf_controller) {
                options.sf_controller = customOptions.sf_controller;
            }
            if (customOptions.sf_js) {
                options.sf_js = customOptions.sf_js;
            }
            if (customOptions.sf_json) {
                options.sf_json = customOptions.sf_json;
            }
            if (customOptions.params) {
                options.params = customOptions.params;
            }
            if (customOptions.column) {
                options.column = customOptions.column;
            }
            if (customOptions.index == false) {
                options.index = false;
            }
            if (customOptions.page == false) {
                options.page = false;
            }
            if (customOptions.searchable == false) {
                options.searchable = false;
            }
            if (customOptions.operate) {
                options.operate = operate;
            }
            if (customOptions.pageSize) {
                options.pageSize = pageSize;
            }
            if (customOptions.checkbox == true) {
                options.checkbox = true;
            }
            if (customOptions.radio == true) {
                options.radio = true;
            }
        }
        //获取列
        if (options.data_module) {
            options.column = null;
        } else {
            var colElements = $element.find(".column");
            if (colElements && colElements.length > 0) {
                options.column = [];
                for (var i = 0; i < colElements.length; i++) {
                    var col = {};
                    if ($(colElements[i]).attr("name")) {
                        col.name = $(colElements[i]).attr("name");
                    }
                    col.showName = $(colElements[i]).html();
                    col.width = $(colElements[i]).attr("width");
                    col.sortable = $(colElements[i]).attr("sortable");
                    col.render = $(colElements[i]).attr("render");
                    if ($(colElements[i]).attr("type")) {
                        col.type = $(colElements[i]).attr("type");
                        if (col.type == options.columnType.operate) {
                            col.sortable = false;
                            col.links = $(colElements[i]).attr("links");
                        }
                    } else {
                        col.type = options.columnType.showColumn;
                    }
                    if ($(colElements[i]).attr("align")) {
                        col.align = $(colElements[i]).attr("align");
                    } else {
                        if (col.type == options.columnType.operate) {
                            col.align = "center";
                        } else {
                            col.align = "left";
                        }
                    }
                    options.column[i] = col;
                }
            } else {
                return;
            }
        }
        $element.empty();
        //搜索条件
        var topHtml = ['<div class="panel panel-default grid-search-panel">'];
        topHtml.push('<div class="panel-body grid-search-body">');
        topHtml.push('<div>');
        if (options.searchable) {
            topHtml.push('<div class="form-inline">');
            topHtml.push('<div class="form-group grid-search-group">');
            topHtml.push('<label for="dropdownTest" class="grid-search-label text-center">过滤条件：</label>');
            //获取sf_data
            var sf_data;
            if (options.sf_module) {
                //TODO
            } else if (options.sf_controller) {
                //TODO
            } else if (options.sf_json) {
                sf_data = Feep.parseJson(options.data_json);
            } else if (options.sf_js) {
                if ($.isFunction(window[options.sf_js])) {
                    sf_data = window[options.sf_js].call(null, options.params);
                }
            }
            var select_sf_onSelect = "select_sf_onSelect" + timeStamp;
            topHtml.push('<div class="fui-dropdown select_sf" data=\'' + Feep.toJson(sf_data) + '\' onSelect="' + select_sf_onSelect + '"></div>');
            //选择查询条件
            window[select_sf_onSelect] = function (codeId, codeValue, attr) {
                $element.find(".addSearchField").click();
            };
            topHtml.push('</div>');
            topHtml.push('<div class="form-group grid-search-group">');
            topHtml.push('<button type="button" class="btn btn-primary btn-sm addSearchField">');
            topHtml.push('<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>');
            topHtml.push('</button>');
            topHtml.push('</div>');
            topHtml.push('<div class="clearfix"></div>');
            topHtml.push('</div>');

            topHtml.push('<form class="form-inline">');
            topHtml.push('</form>');
            topHtml.push('</div>');
            topHtml.push('<div class="grid-search-btn-group">');
            topHtml.push('<div class="grid-count" >');
            topHtml.push('<small>总共搜索出</small>');
            topHtml.push('<span class="totalCount">');
            topHtml.push('</span>');
            topHtml.push('<small>条记录</small>');
            topHtml.push('</div>');
            topHtml.push('<table>');
            topHtml.push('<tr>');
            topHtml.push('<td>');
            topHtml.push('<div class="separate-line"></div>');
            topHtml.push('</td>');
            topHtml.push('<td width="6"></td>');
            topHtml.push('<td width="10" class="hand hideSF">');
            topHtml.push('<div class="dropup">');
            topHtml.push('<span class="glyphicon glyphicon-chevron-up"></span>');
            topHtml.push('</div>');
            topHtml.push('</td>');
            topHtml.push('<td width="10" class="hand showSF" style="display: none">');
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
        }

        topHtml.push('<button type="button" class="btn btn-success btn-sm grid-search-btn">添加</button>');
        topHtml.push('<button type="button" class="btn btn-primary btn-sm grid-search-btn defaultSearch">查询</button>');
        topHtml.push('<button type="button" class="btn btn-danger btn-sm grid-search-btn defaultReset">重置</button>');

        topHtml.push('</div>');
        topHtml.push('</div>');
        topHtml.push('</div>');
        //获取数据
        var defaultPage = {
            pageIndex: 1,
            pageSize: options.pageSize
        };
        var result = this.loadData(null, options, defaultPage);
        //创建表格
        var bottomHtml = ['<div class="table-responsive grid-bottom">'];
        bottomHtml.push('<table class="table table-bordered table-hover table-striped table-condensed grid-table initFui">');
        bottomHtml.push('<thead class="grid-thead">');
        bottomHtml.push('<tr>');
        if (options.index) {
            bottomHtml.push('<th width="55px" class="text-center">序号</th>');
        }
        if (options.column && options.column.length > 0) {
            for (var i = 0; i < options.column.length; i++) {
                var hand = options.column[i].sortable ? "hand" : "";
                bottomHtml.push('<th width="' + options.column[i].width + '" class="' + hand + ' text-' + options.column[i].align + '" name="' + options.column[i].name + '">' + options.column[i].showName + '</th>');
            }
        }
        bottomHtml.push('</tr>');
        bottomHtml.push('</thead>');
        bottomHtml.push('<tbody class="dataContent">');
        bottomHtml.push('</tbody>');
        bottomHtml.push('</table>');
        bottomHtml.push('<div class="noDataBox">');
        bottomHtml.push('</div>');
        //分页组件
        if (options.page && result && result.data && result.data.length) {
            bottomHtml.push('<div class="btn-toolbar grid-pager-toolbar dropdown" role="toolbar" aria-label="分页">');
            bottomHtml.push('<div class="btn-group" role="group">');
            bottomHtml.push('<nav>');
            bottomHtml.push('<ul class="pagination grid-pager-toolbar-nav pageNumBox">');
            bottomHtml.push('</ul>');
            bottomHtml.push('</nav>');
            bottomHtml.push('</div>');
            bottomHtml.push('<div class="input-group grid-pager-toolbar-jump pull-right">');
            bottomHtml.push('<span class="input-group-btn">');
            var afterPageNumListLoad = "afterPageNumListLoad" + timeStamp;
            var pageNumListSelect = "pageNumListSelect" + timeStamp;
            bottomHtml.push('<div class="fui-dropdown btn-group pageNumList" onload="' + afterPageNumListLoad + '" onSelect="' + pageNumListSelect + '" relWidth="110" isRight="false" isDown="false" prompt="每页20条" data=\'[{codeId:"10",codeValue:"10"},{codeId:"30",codeValue:"30"},{codeId:"50",codeValue:"50"},{codeId:"100",codeValue:"100"},{type:"divider"},{codeId:"20",codeValue:"20（默认）"}]\'></div>');
            window[afterPageNumListLoad] = function ($pageNumList) {
                $pageNumList.find("button").addClass("grid-pager-toolbar-btn");
            };
            //页面大小切换
            window[pageNumListSelect] = function (codeId, codeValue, attr) {
                var $pageNumList = $element.find(".pageNumList");
                FUI.dropdown.renderShowText($pageNumList, "每页" + codeId + "条");
                var options = $element.data("options");
                var pageSize = Number(FUI.dropdown.getValue($pageNumList));
                options.pageSize = pageSize;
                $element.data("options", options);
                domain.gotoPage.call(domain, $element, 1, pageSize);
                $element.find(".jump2PageIndex").val("");
            };
            bottomHtml.push('</span>');
            bottomHtml.push('<input type="text" class="form-control jump2PageIndex" placeholder="输入页数">');
            bottomHtml.push('<span class="input-group-btn">');
            bottomHtml.push('<button class="btn btn-default grid-pager-toolbar-btn jump2Page" type="button">跳转</button>');
            bottomHtml.push('</span>');
            bottomHtml.push('</div>');
            bottomHtml.push('</div>');
        }
        bottomHtml.push('</div>');
        $element.html(topHtml.join(' ') + bottomHtml.join(' '));
        $element.data("options", options);
        this.renderData($element, result);
        this.renderPageGroup($element, result.page);
        FUI.renderAll($element);
        this.initEvents($element, options, domain);
    },
    initEvents: function ($element, options, domain) {
        if (options.page == true) {
            //跳转
            $element.find(".jump2Page").click(function () {
                var lastPage = Number($element.find(".grid-pager-toolbar-nav.pageNumBox").find(".nextPage").prev().find("a").html());
                var index = $element.find(".jump2PageIndex").val();
                if ($.isNumeric(index) && Number(index) > 0 && index <= lastPage) {
                    domain.gotoPage.call(domain, $element, $element.find(".jump2PageIndex").val());
                }
            });
            $element.find(".jump2PageIndex").keydown(function (event) {
                if (event.keyCode == 13) {
                    $element.find(".jump2Page").click();
                }
            });
        }
        if (options.searchable == true) {
            //增加条件点击事件
            $element.find(".addSearchField").click(function () {
                var select_sf = $element.find(".select_sf");
                var value = FUI.dropdown.getValue(select_sf);
                if (!value)return;
                var showName = FUI.dropdown.getText(select_sf);
                var attr = FUI.dropdown.getAttr(select_sf, value);
                var $form = $element.find("form.form-inline");
                var st = value + new Date().getTime();
                var sf = ['<div class="' + st + '">'];
                sf.push('<div class="form-group grid-search-group">');
                sf.push('<label class="grid-search-label text-center">');
                sf.push('<input class="fui-check" type="checkbox" st=' + st + ' checked>');
                sf.push('</label>');
                sf.push('<div class="panel panel-default form-control">');
                sf.push('<div class="panel-body text-left">');
                sf.push('<strong>' + showName + '</strong>');
                sf.push('</div>');
                sf.push('</div>');
                sf.push('</div>');
                switch (attr.fieldType) {
                    case 'Text':
                    case 'TextArea':
                        //匹配方式
                        sf.push('<div class="form-group grid-search-group">');
                        sf.push('<div class="fui-dropdown" data=\'[{codeId:"LIKE",codeValue:"包含"},{codeId:"NOTLIKE",codeValue:"不包含"},{codeId:"EQUALS",codeValue:"等于"},{codeId:"NOTEQUALS",codeValue:"不等于"},{codeId:"LEFTLIKE",codeValue:"以开头"},{codeId:"RIGHTLIKE",codeValue:"以结尾"}]\'></div>');
                        sf.push('</div>');
                        //输入方式
                        sf.push('<div class="form-group grid-search-group">');
                        sf.push('<input type="text" name="' + value + '" class="form-control grid-search-input" st=' + st + '>');
                        sf.push('</div>');
                        break;
                    default:
                        break;
                }
                sf.push('<div class="form-group grid-search-group">');
                sf.push('<button type="button" class="btn btn-danger btn-sm delete_sf" st=' + st + '>');
                sf.push('<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>');
                sf.push('</button>');
                sf.push('</div>');
                sf.push('<div class="clearfix"></div>');
                sf.push('</div>');
                $form.append(sf.join(''));
                FUI.renderAll($form);
                $form.find(".delete_sf").click(function () {
                    var sf = $(this).attr("st");
                    $form.find("." + sf).remove();
                });
            });
            //展开,收缩
            $element.find(".hideSF").click(function () {
                $(this).parents(".grid-search-btn-group").prev().hide(300);
                $(this).hide();
                $(this).next().show();
            });
            $element.find(".showSF").click(function () {
                $(this).parents(".grid-search-btn-group").prev().show(300);
                $(this).hide();
                $(this).prev().show();
            });
            //查询按钮
            $element.find(".defaultSearch").click(function () {
                domain.gotoPage.call(domain, $element, 1);
            });
            //重置
            $element.find(".defaultReset").click(function () {
                var $form = $element.find("form.form-inline");
                $form.empty();
            });
        }
    },
    addRow: function ($element, data, index) {

    },
    getRows: function ($element) {

    },
    lastPage: function ($element) {
        if ($element && $element.data("result")) {
            var page = $element.data("result").page;
            var index = page.pageIndex;
            this.gotoPage($element, --index, page.pageSize);
        }
    },
    nextPage: function ($element) {
        if ($element && $element.data("result")) {
            var page = $element.data("result").page;
            var index = page.pageIndex;
            this.gotoPage($element, ++index, page.pageSize);
        }
    },
    gotoPage: function ($element, pageNum, pageSize) {
        if ($element && $element.data("options")) {
            var options = $element.data("options");
            var result = $element.data("result");
            if (pageNum > 0) {
                result.page.pageIndex = pageNum;
                if (pageSize) {
                    result.page.pageSize = pageSize;
                } else {
                    result.page.pageSize = options.pageSize;
                }
                var newResult = this.loadData(null, options, result.page);
                this.renderData($element, newResult);
                var page;
                if (newResult && newResult.page) {
                    page = newResult.page;
                } else {
                    page = {pageIndex: 0, pageSize: options.pageSize, totalPageNum: 0};
                }
                this.renderPageGroup($element, page);
            }
        }
    },
    loadData: function ($element, options, page) {
        var data = null;
        try {
            if ($element && $element.data("options")) {
                options = $element.data("options");
            }
            if (options.data_module) {
                data = null;
            } else if (options.data_controller) {
                data = null;
            } else if (options.data_js) {
                if ($.isFunction(window[options.data_js])) {
                    data = window[options.data_js].call(null, page, options.params);
                }
            } else if (options.data_json) {
                data = Feep.parseJson(options.data_json);
            }
            if (options.page) {
                data.page.totalPageNum = Math.ceil(data.page.totalCount / data.page.pageSize);
            }
            return data;
        } catch (e) {
        }
    },
    renderPageGroup: function ($element, page) {
        if ($element) {
            var btnSize = 10;
            var pageNumBox = $element.find(".grid-pager-toolbar-nav.pageNumBox");
            pageNumBox.empty();
            if (page.totalPageNum == 0) {
                return;
            }
            var btnHTML = [];
            btnHTML.push('<li class="lastPage ' + (page.pageIndex == 1 ? "disabled" : "") + '" >');
            btnHTML.push('<a href="javascript:0" aria-label="Previous">');
            btnHTML.push('<span aria-hidden="true">上一页</span>');
            btnHTML.push('</a>');
            btnHTML.push('</li>');
            btnHTML.push('<li class="pageIndexBtn ' + (page.pageIndex == 1 ? "active" : "") + '"><a href="javascript:0">1</a></li>');
            var piStart, piEnd;
            var smallPage, bigPage;
            bigPage = page.totalPageNum > btnSize;
            if (page.pageIndex > btnSize) {
                piStart = Math.floor(page.pageIndex / btnSize) * btnSize + 1;
                if (page.pageIndex % btnSize == 0) {
                    piStart -= btnSize;
                }
                piEnd = piStart + btnSize - 1;
                if (piStart > btnSize) {
                    smallPage = true;
                }
            } else {
                piStart = 2;
                piEnd = btnSize;
                smallPage = false;
            }
            if (piEnd > page.totalPageNum) {
                piEnd = page.totalPageNum;
                bigPage = false;
            }
            if (smallPage) {
                btnHTML.push('<li class="last10Page"><a href="javascript:0">...</a></li>');
            }
            for (var pi = piStart; pi <= piEnd; pi++) {
                btnHTML.push('<li class="pageIndexBtn ' + (page.pageIndex == pi ? "active" : "") + '"><a href="javascript:0">' + pi + '</a></li>');
            }
            if (bigPage) {
                btnHTML.push('<li class="next10Page"><a href="javascript:0">...</a></li>');
                btnHTML.push('<li class="pageIndexBtn ' + (page.pageIndex == page.totalPageNum ? "active" : "") + '"><a href="javascript:0">' + page.totalPageNum + '</a></li>');
            }
            btnHTML.push('<li class="nextPage ' + (page.pageIndex == page.totalPageNum ? "disabled" : "") + '">');
            btnHTML.push('<a href="javascript:0" aria-label="Next">');
            btnHTML.push('<span aria-hidden="true">下一页</span>');
            btnHTML.push('</a>');
            btnHTML.push('</li>');
            pageNumBox.empty();
            pageNumBox.html(btnHTML.join(''));
            //初始化分页点击事件
            var domain = this;
            $element.find(".nextPage").click(function () {
                if (!$(this).hasClass("disabled")) {
                    domain.nextPage.call(domain, $element);
                }
            });
            $element.find(".lastPage").click(function () {
                if (!$(this).hasClass("disabled")) {
                    domain.lastPage.call(domain, $element);
                }
            });
            $element.find(".pageIndexBtn").click(function () {
                domain.gotoPage.call(domain, $element, $(this).find("a").text());
            });
            $element.find(".next10Page").click(function () {
                var index = Number($(this).prev().find("a").text()) + 1;
                domain.gotoPage.call(domain, $element, index);
            });
            $element.find(".last10Page").click(function () {
                var index = Number($(this).next().find("a").text()) - 1;
                domain.gotoPage.call(domain, $element, index);
            });
        }
    },
    renderData: function ($element, result) {
        if ($element) {
            var dataContent = $element.find(".dataContent");
            var noDataBox = $element.find(".noDataBox");
            var totalCount = $element.find(".totalCount");
            var options = $element.data("options");
            $element.data("result", result);
            var hasData = (result && result.data && result.data.length && result.data.length > 0);
            if (totalCount) {
                if (hasData) {
                    if (options.page) {
                        totalCount.html(result.page.totalCount);
                    } else {
                        totalCount.html(result.data.length);
                    }
                } else {
                    totalCount.html(0);
                }
            }
            if (hasData) {
                noDataBox.empty();
                var dataHTML = [];
                var start = 0;
                var end = result.data.length;
                if (options.page) {
                    start = (result.page.pageIndex - 1) * result.page.pageSize;
                    end = result.page.pageIndex * result.page.pageSize;
                    if (end > result.page.totalCount) {
                        end = result.page.totalCount;
                    }
                }
                for (var i = start; i < end; i++) {
                    var item = result.data[i];
                    dataHTML.push('<tr>');
                    if (options.index) {
                        dataHTML.push('<td class="text-center">' + (i + 1) + '</td>');
                    }
                    if (options.column && options.column.length > 0) {
                        for (var j = 0; j < options.column.length; j++) {
                            var col = options.column[j];
                            if (col.type == options.columnType.showColumn) {
                                if (col.render) {
                                    if ($.isFunction(window[col.render])) {
                                        window[col.render].call(null, i, item);
                                    }
                                } else {
                                    dataHTML.push('<td>' + item[col.name] + '</td>');
                                }
                            } else {
                                dataHTML.push('<td class="text-center">');
                                if (col.links) {
                                    var linkArray = col.links.split(",");
                                    if (linkArray && linkArray.length > 0) {
                                        for (var bi = 0; bi < linkArray.length; bi++) {
                                            var btn = linkArray[bi].split(":");
                                            dataHTML.push('<button type="button" class="btn btn-link grid-operate-btn" js-function="' + btn[1] + '">' + btn[0] + '</button>');
                                        }
                                    }
                                }
                                dataHTML.push('</td>');
                            }
                        }
                    }
                    dataHTML.push('</tr>');
                }
                dataContent.html(dataHTML.join(' '));
            } else {
                dataContent.empty();
                noDataBox.html('<div class="text-center grid-noData">没有数据</div>');
            }
        }
    }
};
FUI.dropdown = {
    render: function ($element, customOptions) {
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
            onSelect: null,
            onLoad: null
        };
        options.id = $element.attr("id");
        options.code = $element.attr("code");
        options.controller = $element.attr("controller");
        options.onSelect = $element.attr("onSelect");
        options.onLoad = $element.attr("onLoad");
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
        if (customOptions && customOptions.data) {
            options.data = customOptions.data;
        } else if (options.controller) {
            //TODO get data
            options.data = Feep.request(options.controller);
        } else if (options.code) {
            //TODO get data
            options.data = Feep.request("feep_getSelectByCode", options.code);
        } else if ($element.attr("data")) {
            options.data = Feep.parseJson($element.attr("data"));
        }
        if (options.isDown == true || options.isDown == "true") {
            $element.addClass("dropdown grid-search-input pull-left");
        } else {
            $element.addClass("dropup grid-search-input pull-left");
        }
        $element.width(options.relWidth + "px");
        var html = ['<button class="btn btn-default dropdown-toggle initFui" type="button" data-toggle="dropdown" aria-expanded="true">'];
        html.push('<span class="pull-left text-left" style="width:' + (options.relWidth - 34) + 'px;">' + options.prompt + '</span><span class="caret"></span>');
        html.push('</button>');
        var ulAttr = 'aria-labelledby="' + options.id + '" style="min-width:' + options.listWidth + 'px;"';
        if (options.isRight == true || options.isRight == "true") {
            html.push('<ul class="dropdown-menu dropdown-menu-right" role="menu" ' + ulAttr + '>');
        } else {
            html.push('<ul class="dropdown-menu dropdown-menu-left" role="menu" ' + ulAttr + '>');
        }
        if ($.isArray(options.data) && options.data.length > 0) {
            $(options.data).each(function (i, item) {
                if (item.type && item.type == "divider") {
                    html.push('<li role="presentation" class="divider"></li>');
                } else {
                    html.push('<li class="hand" role="presentation" codeId="' + item.codeId + '"><a role="menuitem" tabindex="-1">' + item.codeValue + '</a></li>');
                }
            });
        } else {
            html.push('<li role="presentation"><a role="menuitem" tabindex="-1">&nbsp;</a></li>');
        }
        html.push('</ul>');
        if (options.name) {
            html.push('<input type="hidden" name="' + options.name + '">');
        }
        $element.empty();
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
        if (options.onLoad) {
            try {
                window[options.onLoad].call(null, $element);
            } catch (e) {
            }
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
        if (!codeId) {
            codeId = FUI.dropdown.getValue($element);
        }
        if (!codeId) {
            return;
        }
        var attr = $element.find("li[codeId=" + codeId + "]").data("attr");
        if (attrName) {
            return attr[attrName];
        } else {
            return attr;
        }
    },
    renderShowText: function ($element, text) {
        $element.find("span.pull-left").text(text);
    }
};
FUI.check = {
    render: function ($element) {
        $element.iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    }
};
FUI.renderAll = function (box) {
    var fui = $(["fui-grid", "fui-dropdown", "fui-check"]);
    var $elements;
    if (box) {
        $elements = $(box);
    } else {
        $elements = $("html");
    }
    fui.each(function (i, item) {
        $elements.find("." + item).each(function (num, element) {
            if ($(element).find(".initFui").length == 0) {
                FUI[item.split("-")[1]].render($(element));
            }
        });
    });
};