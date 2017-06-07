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
    /*描述文字变淡消失*/
    $(".description").animate({opacity:'0'},1000,function(){
       $(".description").css("display","none");
    });
    /*背景色下半部分变淡消失*/
    $("#bg-bottom").animate({opacity:'0'},1000);
    /*输入框变小并上移*/
    $(".search-input").css("fontSize","16px");
    $(".num-input").css("fontSize","16px");
    $(".search-box").animate({height:'40px'},500,function(){
        $(".search-box").animate({width:'500px'},500,function(){
            $(".search-box").animate({marginTop:'10px'},500,function(){
                $("#bg-top").animate({height:"60px"},500,function(){
                    $(".num-input").blur().css("opacity","0").css("border","none");
                    $(".search-input").blur().css("opacity","0");
                    $(".search-box").css("width","0").css("background","rgba(255,255,255,0.0)").css("border","none").css("border-bottom","1px solid white").css("borderRadius","0");
                    $(".search-box").animate({width:"500px"},500,function(){
                        $(".num-input").animate({opacity:"1"},500);
                        $(".search-input").animate({opacity:"1"},500);
                        animateTableIn();
                    });
                });
            });
        });
    });
}
function animateTableIn(){
    $(".table-wrap").css("display","block");
    $(".table").animate({marginLeft:'0',opacity:'1'},1000);
}