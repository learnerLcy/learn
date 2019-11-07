//index.html页面js
$(function () {
    var width_leftTree =$(".layui-side").width();
    //伸缩事件
    $("#flexible").click(function () {
        //隐藏/显示侧边栏
        //var width_iframe =$("iframe").width();
        if($(".layui-side").is(":hidden")){//隐藏变显示
            $(".layui-side").show();
            //$("iframe").css("width", width_iframe-width_leftTree+ "px");
            $(".layui-tab").css("margin-left",width_leftTree+"px");

            //$(".layui-footer").css("width", width_iframe-width_leftTree+ "px");
            $(".layui-footer").css("margin-left","0px");
        }else{//显示变隐藏
            $(".layui-side").hide();
            // $("iframe").css("width", width_iframe+width_leftTree+ "px");
            $(".layui-tab").css("margin-left","0px");

            //$(".layui-footer").css("width", width_iframe+width_leftTree+ "px");
            $(".layui-footer").css("margin-left","-"+width_leftTree+"px");

        }
    });
    //换肤
    $(".skin dd").click(function () {
        var color = $(this).children().eq(0).css("color");
        $(".layui-layout-admin .layui-header").css("background-color",color);
        $(".layui-nav ").css("cssText","background-color:"+color+" !important");
        $(".layui-bg-black").css("cssText","background-color:"+color+" !important");
    });
})
//加载左侧菜单
function innitLeftTree(){
    $.ajax( {
        url:'/menu/getLeftMenuTree',// 跳转到 action
        type:'post',
        cache:false,
        dataType:'json',
        success:function(data) {
            if(data.success ==true ){
                var html = "";
                $.each(data.data, function(i,item){
                    var chrildrenList = item.chrildrenList;
                    var menuUrl = item.menuUrl==null||item.menuUrl==""?"javascript:;":item.menuUrl;
                    /*layui-nav-itemed*/
                    if(chrildrenList!=null){
                        html += "<li class='layui-nav-item'><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"'>"+item.menuName+"</a>";
                        html += " <dl class='layui-nav-child'>";
                        $.each(chrildrenList, function(i,item){
                            if(item.chrildrenList!=null){
                                html += eachChrildLeftTree(item,"");
                            }else{
                                menuUrl = item.menuUrl==null||item.menuUrl==""?"javascript:;":item.menuUrl;
                                html += " <dd><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"' target='option' class='site-url'>"+item.menuName+"</a></dd>";
                            }
                        });
                        html += "</dl>";
                    }else{
                        html += "<li class='layui-nav-item'><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"' target='option' class='site-url'>"+item.menuName+"</a>";
                    }
                    html += "</li>";
                });
                $(".layui-nav-tree").append(html);
                //需要手动渲染效果,没有的话二级菜单显示不出来
                layui.use('element', function () {
                    var element = layui.element;
                    element.render('nav', "leftTree");
                })
            }else{

            }
        },
        error : function() {
            alert("系统异常！");
        }
    });
}

function eachChrildLeftTree(item,html){
    var html = "";
    var menuUrl = item.menuUrl==null||item.menuUrl==""?"javascript:;":item.menuUrl;
    if(item.chrildrenList!=null){
        html += "<li class='layui-nav-item'><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"'>"+item.menuName+"</a>";
        html += " <dl class='layui-nav-child'>";
        $.each(item.chrildrenList, function(i,item){
            if(item.chrildrenList!=null){
                html += "<li class='layui-nav-item'><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"'>"+item.menuName+"</a>";
                html += " <dl class='layui-nav-child'>";
                $.each(item.chrildrenList, function(i,item){
                    if(item.chrildrenList!=null){
                        html += eachChrildLeftTree(item,html);
                    }else{
                        menuUrl = item.menuUrl==null||item.menuUrl==""?"javascript:;":item.menuUrl;
                        html += " <dd><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"' target='option' class='site-url'>"+item.menuName+"</a></dd>";
                    }
                });
                html += "</dl>";
            }else{
                menuUrl = item.menuUrl==null||item.menuUrl==""?"javascript:;":item.menuUrl;
                html += " <dd><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"' target='option' class='site-url'>"+item.menuName+"</a></dd>";
            }
        });
        html += "</dl>";
    }else{
        html += "<li class='layui-nav-item'><a data-url='"+menuUrl+"' data-title='"+item.menuName+"' data-id='"+item.id+"' target='option' class='site-url'>"+item.menuName+"</a>";
    }
    return html;
}
//导航栏点击加载tab
layui.use('element', function() {
    var element = layui.element;
    var active = {
        tabAdd: function (url, id, name) {
            element.tabAdd('contentnav', {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;"></iframe>',
                id: id
            });
            rightMenu();
            iframeWH();
        },
        tabChange: function (id) {
            element.tabChange('contentnav', id);
        },
        tabDelete: function (id) {
            element.tabDelete('contentnav', id);
        },
        tabDeleteAll: function (ids) {
            $.each(ids, function (index, item) {
                element.tabDelete('contentnav', item);
            });
        }
    };
    $(document).on('click','.site-url',function (){
        var nav = $(this);
        var length = $("ul.layui-tab-title li").length;
        if (length <= 0) {
            active.tabAdd(nav.attr("data-url"), nav.attr("data-id"), nav.attr("data-title"));
        } else {
            var isData = false;
            $.each($("ul.layui-tab-title li"), function () {
                if ($(this).attr("lay-id") == nav.attr("data-id")) {
                    isData = true;
                }
            });
            if (isData == false) {
                active.tabAdd(nav.attr("data-url"), nav.attr("data-id"), nav.attr("data-title"));
            }
            active.tabChange(nav.attr("data-id"));
        }
    });
    function    rightMenu() {
        //右击弹出
        $(".layui-tab-title li").on('contextmenu', function (e) {
            var menu = $(".rightmenu");
            menu.find("li").attr('data-id', $(this).attr("lay-id"));
            l = e.clientX;
            t = e.clientY;
            menu.css({left: l, top: t}).show();
            return false;
        });
        //左键点击隐藏
        $("body,.layui-tab-title li").click(function () {
            $(".rightmenu").hide();
        });
    }

    $(".rightmenu li").click(function () {
        if ($(this).attr("data-type") == "closethis") {
            active.tabDelete($(this).attr("data-id"));
        } else if ($(this).attr("data-type") == "closeall") {
            var tabtitle = $(".layui-tab-title li");
            var ids = new Array();
            tabtitle.each(function (i) {
                ids.push($(this).attr("lay-id"));
            });
            //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
            active.tabDeleteAll(ids);
        }
        $('.rightmenu').hide(); //最后再隐藏右键菜单
    });

    function iframeWH() {
        var H = $(window).height() - $(".layui-footer").height();
        $("iframe").css("height", H + "px");
    }

    $(window).resize(function () {
        iframeWH();
    });
});