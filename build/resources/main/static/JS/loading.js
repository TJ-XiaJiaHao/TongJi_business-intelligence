/**
 * Created by Administrator on 2017/6/3.
 */
$(document).ready(function(){
    //获取时间并动态修改问候语
    var curTime = new Date();
    var greeting;
    var curHour = curTime.getHours();
    if(curHour < 12)         greeting = 'Morning   ,';
    else if(curHour < 17)   greeting = 'Afternoon,';
    else if(curHour < 24)   greeting = "Evening  ,";
    $(".msg-1").html(greeting);

    //欢迎动画
    var animateTime = 400;
    //文字切入
    setTimeout(function(){
        $(".msg-2").animate({left:'5px',opacity:'1'},animateTime * 3,'swing');
        $(".msg-1").animate({top:'0px',opacity:'1'},animateTime * 3,'swing');
    },500);
    //条形框切入
    setTimeout(function(){
        $(".bar-1").animate({top:'0px',opacity:'1'},animateTime,'swing');
    },800);
    setTimeout(function(){
        $(".bar-2").animate({top:'0px',opacity:'1'},animateTime,'swing');
    },1100);
    setTimeout(function(){
        $(".bar-3").animate({top:'0px',opacity:'1'},animateTime,'swing');
    },1400);
    //淡出
    setTimeout(function(){
        //文字切出
        $(".msg-2").animate({top:'-30px',opacity:'0'},animateTime * 2,'swing');
        $(".msg-1").animate({top:'-30px',opacity:'0'},animateTime * 2,'swing');
        //条形框切出
        $(".bar-3").animate({top:'-30px',opacity:'0'},animateTime * 3,'swing');
        $(".bar-2").animate({top:'-30px',opacity:'0'},animateTime * 3,'swing');
        $(".bar-1").animate({top:'-30px',opacity:'0'},animateTime * 3,'swing');

        $("#loading").animate({opacity:'0'},1000,'swing',function(){
            this.isShow = false;
            $("#loading").css("display","none");
        });
    },2500);
});