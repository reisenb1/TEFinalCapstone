<template>
  <div id="app">
    <nav id="topBar">
      <div class="header">
        <h2>Emily's FlashCards</h2>
      </div>

      <router-link
        style="text-decoration: none; color: blue"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >
      &nbsp;|&nbsp;<a
        style="text-decoration: none; color: blue"
        href="#categories"
        >Categories</a
      >
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">Categories</router-link> -->
      &nbsp;|&nbsp;<a style="text-decoration: none; color: blue" href="#about"
        >About</a
      >
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">About</router-link> -->
      &nbsp;|&nbsp;
      <div id="registerLogin">
        <router-link
          v-bind:to="{ name: 'login' }"
          v-show="$store.state.token == ''"
          >Sign In</router-link
        >
        &nbsp;|&nbsp;<router-link
          v-bind:to="{ name: 'register' }"
          v-show="$store.state.token == ''"
          >Register</router-link
        >
      </div>

      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </nav>

    <router-view />
  </div>
</template>

<script>
export default {};
</script>

<style>
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";

  /* placeholder image to test function, will be removed */
}


nav {
  display: flex;
  text-decoration: none;
  align-items: center;
  padding: 10px;
  color: blue;
  height: 60px;
  /* background-color:green; */
  position: fixed;
  top: 0;
  width: 100%;
  flex-direction: row;
}

#registerLogin {
  display: flex;
  margin-left: auto;
}



/* #app {
} */

.header {
  display: flex;
  justify-content: left;
  align-items: center;
  padding: 10px;
  color: rgb(233, 216, 64);
}

/* #welcome-span {
  margin-top: 80px;
  width: 100%;
  height: 750px;
  
} */
</style>
