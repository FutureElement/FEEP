/**
 * Created by ZhangGang on 2015/4/4.
 */
var Feep = {};
Feep.initContext = function () {
    var pathName, index, url;
    try {
        pathName = document.location.pathname;
        index = pathName.substr(1).indexOf("/");
        Feep.contextPath = pathName.substr(0, index + 1);
        url = location.href;
        index = url.indexOf(Feep.contextPath);
        Feep.serverPath = url.substr(0, index + Feep.contextPath.length);
    } finally {
        pathName = index = url = null;
    }
};
Feep.pageTo = {};
Feep.pageTo.home = function () {
    window.location.href = Feep.contextPath + "/feep_index/link.feep";
};
Feep.pageTo.login = function () {
    window.location.href = Feep.contextPath + "/feep_login/link.feep";
};
Feep.pageTo.back = function () {
    window.history.back();
};
Feep.pageTo.url = function (domain) {
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
}
Feep.toJson = function (arg) {
    return JSON.stringify(arg);
};
Feep.parseJson = function (arg) {
    return eval("(" + arg + ")");
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
    }
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
}
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
                day = "-"
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
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
            : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
Feep.request = function (methodName) {
    var url, args, params, html, result;
    var i, data;
    try {
        url = Feep.serverPath + "/service.feep";
        args = null;
        if (arguments && arguments.length >= 2) {
            args = [];
            for (i = 1; i < arguments.length; i++) {
                args[i - 1] = arguments[i];
            }
        }
        var html = $.ajax({
            type: "POST",
            url: url,
            data: {
                "methodName": methodName,
                "parameters": Feep.toJson(args)
            },
            async: false,
            timeout: 30000
        }).responseText;
        data = Feep.parseJson(html);
        switch (data.status) {
            case 200:
                return data.result;
            case 500:
                throw new Error("系统异常，请稍后再试！");
                break;
            default:
                break;
        }
    } finally {
        url = args = params = html = result = i = null;
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
        cookieString = cookieString + "; expires=" + date.toGMTString() + "; path=" + Feep.contextPath;
        document.cookie = cookieString;
    } finally {
        cookieString = date = null;
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
        arrCookie = i = null;
    }
};
Feep.ClassNameConvert = function (str) {
    var char;
    try {
        if (str.indexOf("-") > 0) {
            var reg = str.substring(str.indexOf("-"), str.indexOf("-") + 2);
            var char = str.substring(str.indexOf("-") + 1, str.indexOf("-") + 2);
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
    Feep.initContext();
    $(window).resize(Feep.resize);
    Feep.resize();
    try {
        if (main) {
            main.call();
        }
    } catch (e) {
    }
});
