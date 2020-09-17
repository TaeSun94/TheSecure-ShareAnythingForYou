<template>
    <div class="container">
        <div class="container_contents" id="introduction">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요!</h4>
            <span>introduction</span>
            <a href="#review" class="scroll">Scroll to target</a>
        </div>
        <div class="container_contents" id="reservation">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요1</h4>
            <span>reservation</span>
        </div>
        <div class="container_contents" id="homesharing">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요2</h4>
            <span>home_sharing</span>
        </div>
        <div class="container_contents" id="review">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요3</h4>
            <span>review</span>
        </div>
        <div class="container_contents" id="faq">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요4</h4>
            <span>faq</span>
        </div>
    </div>




</template>
<script>
export default {
    name: 'HomeContents',
    
    methods:{
    },
    computed:{
        // curPos(){
        //     curPos = Math.floor($(document).scrollTop() / 100)
        //     if()
        // }
    },
    mounted(){
        document.documentElement.scrollTop = 0;
        var f = true
        $(window).bind('mousewheel', function(e) {
        // window.addEventListener('mousewheel', function(e) {
                console.log("scroll!")
                if(f){
                    // console.log("현재위치 : " + $(document).scrollTop());
                    // console.log("1 : "+ $('#introduction').offset().top);
                    // console.log("2 : "+ $('#reservation').offset().top);
                    // console.log("3 : "+ $('#homesharing').offset().top);
                    // console.log("4 : "+ $('#review').offset().top);
                    // console.log("5 : "+ $('#faq').offset().top);
                    // f = false;
                    f = false
                    if (e.originalEvent.wheelDelta >= 0) {

                        switch(Math.floor($(document).scrollTop() / 100)){
                            case Math.floor($('#reservation').offset().top / 100):
                                $('html,body').animate({scrollTop: $('#introduction').offset().top}, 1000);
                                break;
                            case Math.floor($('#homesharing').offset().top/100):
                                $('html,body').animate({scrollTop: $('#reservation').offset().top}, 1000);
                                break;
                            case Math.floor($('#review').offset().top/100):
                                $('html,body').animate({scrollTop: $('#homesharing').offset().top}, 1000);
                                break;
                            case Math.floor($('#faq').offset().top/100):
                                $('html,body').animate({scrollTop: $('#review').offset().top}, 1000);
                                break;
                        }
                        setTimeout(function() { f = true }, 1000);
                        // window.scrollTo({top:$('#introduction').offset().top, behavior:'smooth'});
                    } else {
                        switch(Math.floor($(document).scrollTop()/100)){
                            case Math.floor($('#introduction').offset().top/100):
                                $('html,body').animate({scrollTop: $('#reservation').offset().top}, 1000);
                                break;
                            case Math.floor($('#reservation').offset().top/100):
                                $('html,body').animate({scrollTop: $('#homesharing').offset().top}, 1000);
                                break;
                            case Math.floor($('#homesharing').offset().top/100):
                                $('html,body').animate({scrollTop: $('#review').offset().top}, 1000);
                                break;
                            case Math.floor($('#review').offset().top/100):
                                $('html,body').animate({scrollTop: $('#faq').offset().top}, 1000);
                                break;
                        }
                        // setTimeout(function() { f = true }, 1000);
                        setTimeout(function() {f = true}, 1000);
                    }
                }
        }),
        window.addEventListener('resize', function() {
            document.documentElement.scrollTop = 0;
        }, true);
        function listener(e){
            e.preventDefault();
            e.stopPropagation();
        }
        window.addEventListener('mousewheel',listener,{passive:false});


        
    }
}
</script>


<style scoped>
.container{
    padding-top: 0px;
    padding-bottom: 0px;
    margin-bottom : 0px;
    margin-top: 0px;

}
#introduction{
    /* width: ; */
    height: 100vh;
    background-color: aliceblue;
}
#reservation{
    height: 100vh;
    background-color:antiquewhite
}
#homesharing{
    height: 100vh;
    background-color:aqua
}
#review{
    height: 100vh;
    background-color:blue
}
#faq{
    height: 100vh;
    background-color:tomato
}   
</style>