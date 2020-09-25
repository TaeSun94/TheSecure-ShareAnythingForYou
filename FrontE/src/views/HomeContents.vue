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
        listener(e) {
            e.preventDefault();
            e.stopPropagation();
        },
    },
    mounted(){
        
        var f = true
        $(window).bind('mousewheel', function(e) {
                // console.log("scroll!")
                if(f){
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
        window.addEventListener('mousewheel',this.listener,{passive:false});
        document.documentElement.scrollTop = 0;
    },
    beforeDestroy() {
        window.removeEventListener("mousewheel", this.listener);
        $(window).unbind();
        // console.log("destroy");
    },
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