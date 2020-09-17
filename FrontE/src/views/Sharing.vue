<template>
    <div>
    <h1> hi </h1>
    <section class="target-section section"></section>
    <section class="target-section ready section1"></section>
    <section class="target-section ready section2"></section>
    <section class="target-section ready section3"></section>
    <section class="target-section ready section4"></section>
    <section class="target-section ready section5"></section>
    </div>
</template>
<script>

export default {
    mounted(){
        document.documentElement.scrollTop = 0;
    }    
}
</script>
<style scoped>
.target-section {height: 800px; background: #f5f5f5; transition: background 0.5s;}
      .section1.active {background: #64bcaa;}
      .section2.active {background: #68a0da;}
      .section3.active {background: #956fea;}
      .section4.active {background: #da68d9;}
      .section5.active {background: #f5b539;}
</style>