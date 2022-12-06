<template>

  <div id="app">
    
    <div id="nav">
      <div class="header">
      <h2>Emily's FlashCards</h2>
      </div>


      <router-link style="text-decoration: none; color: white" v-bind:to="{ name: 'home' }">Home</router-link>
      &nbsp;|&nbsp;<a style="text-decoration: none; color: white" href="#categories">Categories</a>
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">Categories</router-link> -->
      &nbsp;|&nbsp;<a style="text-decoration: none; color: white" href="#about">About</a>
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">About</router-link> -->


      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>


    </div>

    
    <router-view />

  

    

  </div>
</template>

<script>


export default {
  
}

</script>

<style>
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto,
    'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji',
    'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';

    /* placeholder image to test function, will be removed */
    background-image: url("./images/study-photo.jpg");
  

    
}

#nav {
  display: flex;
  text-decoration: none;
  align-items: center;
  padding: 10px;
  color: white;
  display: flex;
  height: 60px;
  background-color: rgb(122, 120, 120, 0.7);
  position: fixed;
  top: 0;

  
}

.header {
  display: flex;
  justify-content: left;
  align-items: center;
  padding: 10px;
  color: rgb(233, 216, 64);
}

#welcome-span {
  margin-top: 80px;
  width: 100%;
  height: 750px;
}
</style>
