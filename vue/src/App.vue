<template>

  <div id="app">
    <div class="header">
      <h2>Emily's FlashCards</h2>
    </div>

    <div id="nav">
      <router-link style="text-decoration: none; color: white" v-bind:to="{ name: 'home' }">Home</router-link>
      &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">Categories</router-link>
      &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">About</router-link>


      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      
    </div>
    <router-view />

    <!-- <div class="img"> 
      
      <img src="./images/study-photo.jpg" alt="study">
    </div> -->







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
    background-size: auto; 
    
}

#app {
  display: flex;
  height: 60px;
  /* test header colors. This may need to be put in the header section */
  background-color: rgba(71, 133, 204, 0.7);

}

#nav {
  display: flex;
  justify-content: space-around;
  text-decoration: none;
  align-items: center;
  padding: 10px;
  color: white;
}

.header {
  display: flex;
  justify-content: left;
  align-items: center;
  padding: 10px;
  color: rgb(233, 216, 64);
}

/* .img {
  
  max-width: 100%;
  height: auto;
} */
</style>
