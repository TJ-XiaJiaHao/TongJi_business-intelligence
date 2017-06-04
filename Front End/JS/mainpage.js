/**
 * Created by Administrator on 2017/6/3.
 */
var hasSearched = false;
$(document).ready(function(){
    init();
});
function init(){
    /*初始化消息提示框样式*/
    toastr.options = {
        closeButton: true,
        progressBar: true,
        showMethod: 'slideDown',
        showDuration: "100",
        showEasing: "swing",
        timeOut: 2000
    };

    /*搜索框回车事件初始化*/
    $(".search-input").keydown(function(e){if(e.which == 13) searchEvent();});
       $(".num-input").keydown(function(e){if(e.which == 13) searchEvent();});
}
function checkKeyWord(){
    var val = $(".search-input").val();
    if(val == null || val == ""){
        toastr.error("关键字不能为空！");
        return false;
    }
    return true;
}
function checkNum(){
    var num = $(".num-input").val();
    var reg=/^[1-9]\d*$|^0$/;
    if(num == null || num == ""){
        toastr.error("推荐数量输入错误，数量不能为空！");
        return false;
    }
    if(!reg.test(num)){
        toastr.error("推荐数量输入错误，数量必须是正整数！");
        return false;
    }
    if(num == "0"){
        toastr.info("推荐数量为0？这还有意义么？")
        return false;
    }
    return true;
}
function searchEvent(){
    if(checkKeyWord() && checkNum()){
        //alert("[ KEYDOWN ] - " + $(".search-input").val() + " - " + $(".num-input").val());
        if(!hasSearched)animateSearchSmall();
    }
}
function animateSearchSmall(){
    hasSearched = true;
    $(".description").animate({opacity:'0'},1000,function(){
       $(".description").css("display","none");
    });
    $(".search-input").css("fontSize","16px");
    $(".num-input").css("fontSize","16px");
    $(".search-box").animate({height:'40px'},500,function(){
        $(".search-box").animate({width:'500px'},500,function(){
            $(".search-box").animate({marginTop:'10px'},500,function(){
                animateTableIn();
            });
        });
    });
}
function animateTableIn(){
       $(".num-input").blur();
    $(".search-input").blur();

    $("#main").css("background","black");
    $(".num-input").css("border","none");
    $(".table-wrap").css("display","block");
    $(".search-box").css("background","rgba(255,255,255,0.0)").css("border","none").css("border-bottom","1px solid white").css("borderRadius","0");

    $(".table").animate({marginLeft:'0',opacity:'1'},1000);
}