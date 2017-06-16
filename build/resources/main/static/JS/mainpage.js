/**
 * Created by Administrator on 2017/6/3.
 */
var hasSearched = false;
var hasGetData = false;
var getDataTryCnt = 0;
$(document).ready(function () {
    init();
});

/*初始化消息提示样式和部分事件*/
function init() {
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
    $(".search-input").keydown(function (e) {
        if (e.which == 13) searchEvent();
    });
    $(".num-input").keydown(function (e) {
        if (e.which == 13) searchEvent();
    });
    /*初始化滚动条*/
    $("html").niceScroll({styler:"fb",cursorcolor:"rgb(16, 167, 175)", cursorwidth: '6', cursorborderradius: '10px', background: '#424f63', spacebarenabled:false, cursorborder: '0',  zindex: '1000'});
    $(".table").niceScroll({styler:"fb",cursorcolor:"rgb(16, 167, 175)", cursorwidth: '6', cursorborderradius: '10px', background: '#424f63', spacebarenabled:false, cursorborder: '0',  zindex: '1000'});

}

/*检查输入框的东西是否为空*/
function checkKeyWord() {
    var val = $(".search-input").val();
    if (val == null || val == "") {
        toastr.error("关键字不能为空！");
        return false;
    }
    return true;
}

/*检查返回结果数量是否符合规范*/
function checkNum() {
    var num = $(".num-input").val();
    var reg = /^[1-9]\d*$|^0$/;
    if (num == null || num == "") {
        toastr.error("推荐数量输入错误，数量不能为空！");
        return false;
    }
    if (!reg.test(num)) {
        toastr.error("推荐数量输入错误，数量必须是正整数！");
        return false;
    }
    if (num == "0") {
        toastr.info("推荐数量为0？这还有意义么？")
        return false;
    }
    return true;
}

/*把数据填到表格中*/
function addItemToTable(ASIN,Categories,Group,ID,Salesrank,Title){
    $(".table-body").append("<tr>" +
        "<td>" + ASIN + "</td>" +
        "<td>" + Categories + "</td>" +
        "<td>" + Group + "</td>" +
        "<td>" + ID + "</td>" +
        "<td>" + Salesrank + "</td>" +
        "<td>" + Title + "</td>" +
        "</tr>")
}

/*回车搜索事件*/
function searchEvent() {
    if (checkKeyWord() && checkNum()) {
        var rtnCnt = $(".num-input").val();
        var inputArr = $(".search-input").val().split("#");
        var input = "";
        input += inputArr.length + "&";
        input += rtnCnt;
        for(var i = 0; i < inputArr.length;i++){
            input += "&" + inputArr[i];
        }
        var URL = "http://localhost:8080/products/products";
        $.ajax({
            type: "post",
            async: true,            //异步请求
            url: URL,    //请求发送到TestServlet处
            data: {
                info:input
            },
            dataType: "json",        //返回数据形式为json
            success: function (result) {
                // console.log("[ 返回结果 ]",result);
                $(".table").animate({marginLeft: '50px', opacity: '0'}, 0);
                $(".table-body").html("");
                for(var i = 0;i < result.length;i++){
                    addItemToTable(result[i].ASIN,result[i].Categories,result[i].Group,result[i].Id,result[i].Salesrank,result[i].Title);
                }
                getDataTryCnt = 0;
                hasGetData = true;
                toastr.success("加载成功！");
            },
            error: function (errorMsg) {
                toastr.error("获取搜索结果失败！");
            }
        });
        if (!hasSearched)animateSearchSmall();
        else {
            hasGetData = false;
            animateTableIn();
            // toastr.info("正在加载.....");
        }
    }
}

/*过度动画*/
function fallingDown(dom,startTop,endTop,step,distance,opacity,callback){
    opacity = opacity > 0.9 ? 1 : opacity + 0.1;
    dom.css("marginTop",startTop).css("opacity",opacity);
    //console.log("[ 进入了掉落函数 ] - " + startTop + " " + endTop + " " + step + " " + distance + " " + (distance+step));
    if(startTop >= endTop){
        dom.css("opacity","1");
        if(callback != null)callback();
        return;
    }
    if(startTop + distance > endTop) {
        //console.log("[ 超了 ] - " + (startTop + distance + step) + " " + endTop);
        distance = endTop - startTop;
    }
    dom.animate({marginTop:startTop + distance + 'px'},30,function(){
        fallingDown(dom,startTop+distance,endTop,step,distance+step,opacity,callback);
    })
}
function titleIn(index,callback){
    if(index >= $(".title-item").length){
        if(callback!=null)callback();
        return;
    }
    $(".title-item").eq(index).css("transform","scale(1)");
    $(".title-item").eq(index).animate({opacity:'1'},500);
    setTimeout(titleIn,300,index+1,callback);
}
function animateSearchIn(){
    $("#bg-top").animate({height:'400px'},500,function(){
        $("#bg-bottom").animate({left:'0'},500);
        /*输入框掉落*/
        fallingDown($(".search-box"),0,300,10,10,0,function(){
            $(".search-box").animate({marginTop:'200px'},200,function(){
                setTimeout(function(){
                    fallingDown($(".search-box"),200,300,10,10,1,function(){
                        $(".search-box").animate({marginTop:'250px'},200,function(){
                            setTimeout(function(){
                                fallingDown($(".search-box"),250,300,4,4,1,function(){
                                    /*标题掉落*/
                                    titleIn(0,function(){
                                        $(".description").animate({marginLeft:'20px',opacity:'0.5'},500);
                                    });
                                });
                            },40);
                        });
                    });
                },40);
            });
        });

    });
}
function animateSearchSmall() {
    hasSearched = true;
    /*描述文字变淡消失*/
    $(".description").animate({opacity: '0'}, 1000, function () {
        $(".description").css("display", "none");
    });
    $("#big-title").animate({marginTop:'0px',opacity:'0',transform:'scale(0.8)'},1000);
    /*背景色下半部分变淡消失*/
    $("#bg-bottom").animate({opacity: '0'}, 1000);
    /*输入框变小并上移*/
    $(".search-input").css("fontSize", "16px");
    $(".num-input").css("fontSize", "16px");
    $(".search-box").blur();
    $(".search-box").animate({height: '40px'}, 500, function () {
        $(".search-box").animate({width: '500px'}, 500, function () {
            $(".search-box").animate({marginTop: '10px'}, 500, function () {
                $("#bg-top").animate({height: "60px"}, 500, function () {
                    $(".num-input").blur().css("opacity", "0").css("border", "none");
                    $(".search-input").blur().css("opacity", "0");
                    $(".search-box").css("width", "0").css("background", "rgba(255,255,255,0.0)").css("border", "none").css("border-bottom", "1px solid white").css("borderRadius", "0");
                    $(".search-box").animate({width: "500px"}, 500, function () {
                        $(".num-input").animate({opacity: "1"}, 500);
                        $(".search-input").animate({opacity: "1"}, 500);
                        animateTableIn();
                    });
                });
            });
        });
    });
}
function animateTableIn() {
    if(!hasGetData){
        toastr.info("正在进行第" + (getDataTryCnt + 1) + "次加载.....");
        getDataTryCnt += 1;
        if(getDataTryCnt < 10) setTimeout(animateTableIn,1000);
        else {
            getDataTryCnt = 0;
            hasGetData = true;
            animateTableIn();
            toastr.error("获取数据时间过长！")
        }
    }
    else{
        $(".table-wrap").css("display", "block");
        $(".table").animate({marginLeft: '0', opacity: '1'}, 1000);
    }
}