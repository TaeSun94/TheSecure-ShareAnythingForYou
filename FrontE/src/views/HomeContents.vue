<template>
    <div>
        <div id="introduction">
            <img src="../assets/main1.png">
        </div>
        <div class="container_contents" id="reservation">
            <img src="../assets/main2.png">
        </div>
        <div class="container_contents" id="homesharing">
            <img src="../assets/main3.png">
        </div>
        <div class="container_contents" id="review">
            <img src="../assets/main4.png">
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