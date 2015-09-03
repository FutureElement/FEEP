/**
 * 基础方法js
 * Created by ZhangGang on 2015/4/4.
 */
var Feep = {};
Feep.contextPath = $("#feepcss").attr("contextPath");
Feep.errorMsg = "系统异常,请稍后再试！";
Feep.pageTo = {};
Feep.pageTo.home = function () {
    window.location.href = Feep.contextPath + "/pm/feep_index/link.feep";
};
Feep.pageTo.login = function () {
    window.location.href = Feep.contextPath + "/feep_login/link.feep";
};
Feep.pageTo.back = function () {
    window.history.back();
};
Feep.pageTo.url = function () {
    window.location.href = Feep.contextPath + url;
};
Feep.resize = function () {
    try {
        if (onSizeChange) {
            onSizeChange.call(null, $(window).width(), $(window).height());
        }
    } catch (e) {
    }
};
Feep.scroll = function (f) {
    if (f && typeof (f) == "function") {
        $(window).scroll(function () {
            var clientHeight = document.body.clientHeight || document.documentElement.clientHeight;
            var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
            var scrollLeft = document.body.scrollLeft || document.documentElement.scrollLeft;
            f.call(null, clientHeight, scrollTop, scrollLeft);
        });
    }
};
Feep.scroll2Top = function () {
    $("body,html").animate({
        scrollTop: 0
    }, 500);
};
Feep.toJson = function (arg) {
    if (arg) {
        return JSON.stringify(arg);
    } else {
        return null;
    }
};
Feep.parseJson = function (arg) {
    return eval("(" + arg + ")");
};
Feep.getFormData = function (selector) {
    var data = {};
    var array = $(selector).serializeArray();
    if (array && array.length > 0) {
        for (var i in array) {
            if (array[i].value) {
                data[array[i].name] = array[i].value;
            }
        }
    }
    return data;
};
Feep.asyn = function (n, l, m) {
    if (!n) {
        return;
    }
    if (m == null) {
        m = 10;
    }
    var d = null;
    if (arguments && arguments.length >= 4) {
        d = [];
        for (var g = 3; g < arguments.length; g++) {
            d[g - 3] = arguments[g];
        }
    }
    var j = function () {
        try {
            if (n != null) {
                if (d != null) {
                    n.apply(l, d);
                } else {
                    n.apply(l);
                }
            }
        } finally {
            l = d = n = null;
        }
    };
    setTimeout(j, m);
    j = null;
};
Feep.runTask = function (method, roundtime, endtime, domain) {
    if (!method) return;
    var taskId;
    var d = [];
    if (arguments && arguments.length > 4) {
        for (var g = 4; g < arguments.length; g++) {
            d[g - 4] = arguments[g];
        }
    }
    var run = function () {
        method.apply(domain, d);
    };
    if (domain) {
        taskId = domain.setInterval(run, roundtime);
    } else {
        taskId = setInterval(run, roundtime);
    }
    if (endtime) {
        Feep.asyn(Feep.stopTask, this, endtime, taskId, domain);
    }
    return taskId;
};
Feep.stopTask = function (taskId, domain) {
    try {
        if (domain) {
            taskId = domain.clearInterval(taskId);
        } else {
            taskId = clearInterval(taskId);
        }
        return taskId;
    } catch (e) {

    }
};
Feep.dateFormat = function (date, format) {
    var getWeek = function (arg) {
        var day = null;
        switch (parseInt(arg)) {
            case 0:
                day = "日";
                break;
            case 1:
                day = "一";
                break;
            case 2:
                day = "二";
                break;
            case 3:
                day = "三";
                break;
            case 4:
                day = "四";
                break;
            case 5:
                day = "五";
                break;
            case 6:
                day = "六";
                break;
            default:
                day = "-";
                break;
        }
        return day;
    };
    var o = {
        "M+": date.getMonth() + 1, // month
        "d+": date.getDate(), // day
        "h+": date.getHours(), // hour
        "m+": date.getMinutes(), // minute
        "s+": date.getSeconds(), // second
        "q+": Math.floor((date.getMonth() + 3) / 3), // quarter
        "w+": getWeek(date.getDay()),
        "S": date.getMilliseconds()
    };
    if (!format) {
        format = "yyyy-MM-dd";
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
            : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
Feep.loadModule = function ($box, moduleName, callBack, isOpen) {
    if ($box && moduleName) {
        $box.load(Feep.contextPath + "/" + moduleName + "/link.feep", {isOpen: isOpen}, callBack);
    }
};
Feep.request = function (methodName, params, success, error, domain) {
    $.post(Feep.contextPath + "/service.feep", {
        "methodName": methodName,
        "parameters": Feep.toJson(params)
    }, function (feedback) {
        if (!domain) {
            domain = null;
        }
        if (feedback.status == 200) {
            if (success && $.isFunction(success)) {
                success.call(domain, feedback.result);
            }
        } else {
            if (error && $.isFunction(error)) {
                error.call(domain, feedback.result);
            }
        }
    }, "json");
};
Feep.syncRequest = function (methodName) {
    var args, html;
    var i;
    try {
        args = null;
        if (arguments && arguments.length >= 2) {
            args = [];
            for (i = 1; i < arguments.length; i++) {
                args[i - 1] = arguments[i];
            }
        }
        html = $.ajax({
            type: "POST",
            url: Feep.contextPath + "/service.feep",
            data: {
                "methodName": methodName,
                "parameters": Feep.toJson(args)
            },
            async: false,
            timeout: 30000
        });
        if (!html || !html.status || html.status != 200) {
            throw new Error("系统异常，请稍后再试！");
        } else {
            var data = Feep.parseJson(html.responseText);
            switch (data.status) {
                case 200:
                    return data.result;
                default:
                    throw new Error("系统异常，请稍后再试！");
            }
        }
    } finally {
        i = null;
        args = null;
    }
};
/* cookie操作类 */
Feep.cookie = {};
Feep.cookie.add = function (key, value, time) {
    var cookieString, date;
    try {
        cookieString = key + "=" + escape(value);
        date = new Date();
        if (time) {
            date.setTime(date.getTime() + time);
        } else {
            date.setTime(date.getTime() + 1209600000);// 2 week
        }
        cookieString = cookieString + "; expires=" + date.toGMTString();
        if (Feep.contextPath) {
            cookieString += "; path=" + Feep.contextPath;
        }
        document.cookie = cookieString;
    } finally {
        date = null;
    }
};
Feep.cookie.remove = function (key) {
    Feep.cookie.add(key, "", -3600);
};
Feep.cookie.get = function (key) {
    var arrCookie, i;
    try {
        arrCookie = document.cookie.split("; ");
        for (i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            if (arr[0] == key) return arr[1];
        }
        return "";
    } finally {
        i = null;
    }
};
Feep.ClassNameConvert = function (str) {
    var char;
    try {
        if (str.indexOf("-") > 0) {
            var reg = str.substring(str.indexOf("-"), str.indexOf("-") + 2);
            char = str.substring(str.indexOf("-") + 1, str.indexOf("-") + 2);
            str = str.replace(reg, char.toLocaleUpperCase());
            return this.ClassNameConvert(str);
        }
        return str;
    } finally {
        char = null;
    }
};
/* 页面加载完执行 */
$(function () {
    try {
        $(window).resize(Feep.resize);
        Feep.resize();
    } catch (e) {
    }
    try {
        /*页面渲染之前调用*/
        if (beforeRender && $.isFunction(beforeRender)) {
            beforeRender.call();
        }
    } catch (e) {
    }
    try {
        /*渲染FUI*/
        FUI.renderAll();
    } catch (e) {
    }
    try {
        /*页面加载完成后调用的主函数*/
        if (main && $.isFunction(main)) {
            main.call();
        }
    } catch (e) {
    }
});
