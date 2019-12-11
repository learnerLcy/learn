//layer弹窗方法
var layer;
layui.use(['layer'], function(){
    layer=layui.layer;
});
function openLayer(title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    }
    ;
    if (url == null || url == '') {
        url = "/error/404";
    }
    ;
    if (w == null || w == '') {
        w = ($(window).width() * 0.9);
    }
    ;
    if (h == null || h == '') {
        h = ($(window).height() - 50);
    }
    ;
    layer.open({
        id: 'user-add',
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false,
        maxmin: true,
        shadeClose: false,
        shade: 0.4,
        title: title,
        content: url
    }, function(value, index){
        layer.close(index);
    });
}
//生成table
var ptable = {
    table: null
    , elem: null
    , spreadable: false //设置是否全展开，默认不展开
    , url: null //数据接口
    , cellMinWidth: 20         //最小宽度
    , height: 'full-100'       //高度
    , toolbar: null  //加载操作按钮
    , title: null //作为导出时候的文件名
    , page: true //开启分页
    , limits: [10, 20, 50]       //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
    , limit: 30                //每页默认显示的数量
    , totalRow: true           //开启合并汇总 ,totalRowText: '合计'，显示汇总名称，totalRow: true,是否汇总项
    , cols: null
    , createTable: function (id, dataurl, toolbar, title, colsarray,callBackFunction) {
        ptable.elem = id;
        ptable.url = dataurl;
        ptable.toolbar = toolbar;
        ptable.title = title;
        ptable.cols = colsarray;
        layui.use('table', function () {
            ptable.table = layui.table;
            return  ptable.table.render({
                elem: ptable.elem
                , spreadable: ptable.spreadable //设置是否全展开，默认不展开
                , url: ptable.url //数据接口
                , cellMinWidth: ptable.cellMinWidth        //最小宽度
                , height: ptable.height       //高度
                , toolbar: ptable.toolbar  //加载操作按钮
                , title: ptable.title //作为导出时候的文件名
                , page: ptable.page //开启分页
                , limits: ptable.limits       //每页条数的选择项，默认：[10,20,30,40,50,60,70,80,90]。
                , limit: ptable.limit               //每页默认显示的数量
                , totalRow: ptable.totalRow           //开启合并汇总 ,totalRowText: '合计'，显示汇总名称，totalRow: true,是否汇总项
                , cols: ptable.cols
                ,done: function () {
                    if(callBackFunction!=null){
                        callBackFunction();
                    }
                    layer.closeAll('loading');
                }
            });
        })
    }
};
//生成treeTable:带上下级关系的table
var treeTable = {
    table: null
    ,treetable: null
    , elem: null
    , spreadable: false //设置是否全展开，默认不展开
    , url: null //数据接口
    , cellMinWidth: 20         //最小宽度
    , height: 'full-100'       //高度
    , toolbar: null  //加载操作按钮
    , title: null //作为导出时候的文件名
    , totalRow: true           //开启合并汇总 ,totalRowText: '合计'，显示汇总名称，totalRow: true,是否汇总项
    , cols: null
    , treeColIndex: 1//树形图标显示在第几列
    , treeSpid: null//最上级的父级id
    , treeIdName: null//id字段的名称
    , treePidName: null//pid字段的名称
    , treeDefaultClose: true//是否默认折叠
    , treeLinkage: false//父级展开时是否自动展开所有子级
    , createTable: function (id, dataurl, toolbar, title, colsarray,callBackFunction) {
        treeTable.elem = id;
        treeTable.url = dataurl;
        treeTable.toolbar = toolbar;
        treeTable.title = title;
        treeTable.cols = colsarray;
        layui.config({
            base: '/layui/lay/modules/'
        }).extend({
            treetable: 'treetable'
        }).use(['layer', 'table', 'treetable'], function () {
            var $ = layui.jquery;
            var layer = layui.layer;

            treeTable.table = layui.table;
            treeTable.treetable = layui.treetable;
            // 渲染表格
            layer.load(2);
            return treeTable.treetable.render({
                treeColIndex: treeTable.treeColIndex,//树形图标显示在第几列
                treeSpid: treeTable.treeSpid,//最上级的父级id
                treeIdName: treeTable.treeIdName,//id字段的名称
                treePidName: treeTable.treePidName,//pid字段的名称
                treeDefaultClose: treeTable.treeDefaultClose,//是否默认折叠
                treeLinkage: treeTable.treeLinkage,//父级展开时是否自动展开所有子级
                toolbar: treeTable.toolbar,  //加载操作按钮
                height: treeTable.height,    //高度
                elem: treeTable.elem,
                url: treeTable.url,
                cols: treeTable.cols,
                title: treeTable.title, //作为导出时候的文件名
                done: function () {
                    if(callBackFunction!=null){
                        callBackFunction();
                    }
                    layer.closeAll('loading');
                }
            });
        });
    }
}
/**
 * 保存方法 返回json格式：{"success":true/false,"msg":保存成功"}不弹出提示
 * @param _formId 表单id
 * @param _url 请求路径
 * @param _success  成功后回调函数
 * @param _fail  失败后回调函数
 */
function doAjaxForm( _url, _formId, _success, _fail){
    var result = null;
    $.ajax({
        type:"post",
        url:_url,
        async: false,
        data:$('#' + _formId).serializeArray(),
        success:function(data){
            if(data.success){
                result = data.data;
                if(_success != "" && _success != null && _success != undefined){
                    _success(data);
                }
            } else {
                if(_fail != "" && _fail != null && _fail != undefined){
                    _fail(data);
                }
            }
        },
        error:function(val){

        },
        beforeSend:function(val){

        }
    });
    return result;
}
function doAjaxData(_url, _data, _success, _fail){
    var result = null;
    $.ajax({
        type:"post",
        url:_url,
        data:_data,
        async: false,
        success:function(data){
            if(data.success){
                result = data.data;
                if(_success != "" && _success != null && _success != undefined){
                    _success(data);
                }
            } else {
                if(_fail != "" && _fail != null && _fail != undefined){
                    _fail(data);
                }
            }
        },
        error:function(val){

        },
        beforeSend:function(val){

        }
    });
    return result;
}

/**
 * tree选择信息,点击确定页面回填
 */
function selectedZTreeNode(treeObj,target_id,source_id,target_name,source_name){
    var nodes = treeObj.getCheckedNodes(true);
    var ids = new Array();
    var names = new Array();
    var $source_id = source_id;
    var $source_name = source_name;
    for (var i = 0; i < nodes.length; i++) {
        ids.push(nodes[i][$source_id]);
        names.push(nodes[i][$source_name]);
    }
    parent.$("#"+target_id).val(ids.join(','));
    parent.$("#"+target_name).val(names.join(','));
    var index =parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
/*点击选中事件*/
function checkedClickNode(treeObj,treeNode,checked,checkTypeFlagBoolean,checkTypeFlagBoolean){
    treeObj.checkNode(treeNode, checked,checkTypeFlagBoolean, checkTypeFlagBoolean);
}

/*序列化form表单事件*/
$.fn.serializeObject=function(){
    var hasOwnProperty=Object.prototype.hasOwnProperty;
    return this.serializeArray().reduce(function(data,pair){
        if(!hasOwnProperty.call(data,pair.name)){
            data[pair.name]=pair.value;
        }else{
            data[pair.name]=data[pair.name]+","+pair.value;
        }
        return data;
    },{});
};
/*獲取font_family_extend*/
var font_family_extend ;
var prefix;
loadProperties();
function loadProperties(){
    jQuery.i18n.properties({// 加载properties文件
        name:'layui_extend', // properties文件名称
        path:'/layui_extend', // properties文件路径
        mode:'map', // 用 Map 的方式使用资源文件中的值
        callback: function() {// 加载成功后设置显示内容
            font_family_extend = $.i18n.prop("font_family_extend");
            prefix = $.i18n.prop("prefix");
        }
    });
}