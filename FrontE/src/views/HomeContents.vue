<template>
    <div class="container">
        <div class="container_contents" id="introduction">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요!</h4>
        </div>
        <div class="container_contents" id="reservation">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요1</h4>
        </div>
        <div class="container_contents" id="home_sharing">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요2</h4>
        </div>
        <div class="container_contents" id="review">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요3</h4>
        </div>
        <div class="container_contents" id="faq">
            <h2>2%</h2>
            <h4>여러분의 숙소가 안전한지 확인하세요4</h4>
        </div>
        <!-- <v-row
            v-scroll:#reservation="onScroll"
            align="center"
            justify="center"
            style="height: 1000px"
        >
        </v-row> -->
    </div>




</template>
<script>

window.addEventListener('scroll',() =>{
    const scrollable = document.documentElement.scrollHeight - window.innerHeight;
    const scrolledY = window.scrollY;
    // console.log(scrollable);
    const page = Math.floor(scrollable/scrolledY)
    console.log(page)
    if(Math.ceil(scrolledY) === scrollable){
        alert('you\'ve reached bottom');
    }
})
export default {
    name: 'HomeContents',
    data(){
        return{
            // winH : window.innerHeight,
            // scrollY : window.scroll(),
            // posFromTop : document.querySelector("#reservation").getBoundingClientRect().top,
            // absolutePos : scrollY + posFromTop
        }
    },
}
</script>


<style scoped>
.container{
    margin: auto;
    padding-top: 0px;
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
#home_sharing{
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