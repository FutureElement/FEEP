/**
 * Created by Administrator on 2015-07-05.
 */
var getData = function (page, params, searchField) {
    var result = {
        moduleName: "aaa",
        page: {
            pageIndex: page.pageIndex,
            pageSize: page.pageSize,
            totalCount: 865
        },
        data: []
    };
    var totalPageNum = Math.ceil(result.page.totalCount / result.page.pageSize);
    if (page.pageIndex > totalPageNum) {
        return false;
    }
    for (var i = 1; i <= result.page.totalCount; i++) {
        result.data[i - 1] = {
            index: i,
            tableName: "aaa" + i,
            showName: "bbb" + i,
            system: "feep",
            type: (i / 5),
            remarks: "test" + i
        };
    }
    return result;
};
var getQueryItem = function () {
    var qi = [];
    qi[0] = {codeId: "tableName", codeValue: "表名", attr: {fieldType: "Text"}};
    qi[1] = {codeId: "showName", codeValue: "显示名", attr: {fieldType: "Text"}};
    qi[2] = {codeId: "system", codeValue: "所属系统", attr: {fieldType: "Text"}};
    qi[3] = {codeId: "type", codeValue: "类型", attr: {fieldType: "Text", code: "tableType"}};
    qi[4] = {codeId: "remarks", codeValue: "备注", attr: {fieldType: "TextArea"}};
    return qi;
};