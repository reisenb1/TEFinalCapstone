<template>
  <div id="app">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Klee One"
    />
    <header>
      <div class="header">
        <router-link
          style="
            text-decoration: none;
            color: #ffb5a7;
            text-shadow: 0 0 1.5px darkblue;
          "
          id="homeLogo"
          v-bind:to="{ name: 'home' }"
        >
          <img id="logoHeaderBar" src=".\images\cards.gif" />
          <h2>YEGET</h2>
        </router-link>
      </div>

      <router-link
        class="header-bar-font"
        style="text-decoration: none; color: black;"
        v-bind:to="{ name: 'loggedInHome' }"
        v-show="$store.getters.user.id > 0"
        >My Stats</router-link
      >

      <router-link
        class="header-bar-font"
        style="text-decoration: none; color: black"
        v-bind:to="{ name: 'home' }"
        v-show="$store.getters.user.id === undefined"
        >Home</router-link
      >
      &nbsp;|&nbsp;<a
        class="header-bar-font"
        style="text-decoration: none; color: black"
        href="/#about"
        >About</a
      >
      &nbsp;|&nbsp;<a
        class="header-bar-font"
        style="text-decoration: none; color: black"
        href="/#categories"
        >Categories</a
      >
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">Categories</router-link> -->
      
      &nbsp;|&nbsp;
      <router-link
        class="header-bar-font"
        id="search-button"
        v-bind:to="{ name: 'Search' }"
        >Search All Decks</router-link
      >
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">About</router-link> -->

      <div id="registerLogin" class="header-bar-font">
        <router-link
          class="header-bar-font"
          style="text-decoration: none; color: black"
          v-bind:to="{ name: 'login' }"
          v-show="$store.state.token == ''"
          >Sign In</router-link
        >

        <!-- removed 
        </div> -->

        <router-link
          style="text-decoration: none; color: black"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
        <!-- added  -->
      </div>
    </header>

    <router-view
      id="mainSection"
      :class="{ 'not-logged-in': $store.state.token == '' }"
    />

    <!-- <footer>
      <p>Copyright &copy;2022</p>
    </footer> -->
  </div>
</template>

<script>
export default {};
</script>

<style>
.header-bar-font {
  font-weight: bold;
}

/* @font-face {
  font-family: "Klee";
  src: url(https://fonts.google.com/specimen/Klee+One?preview.text=i%20dont%20remember%20the%20name%20of%20the%20project&preview.text_type=custom);
} */
body {
  /* added */
  background-color: #f8ede8;

  font-family: "Klee One"; /* ,system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji"; */

  /* placeholder image to test function, will be removed */

  /* height: 100%;
  width: 100vw; */
  width: 100%;
  margin: 0px;
}

header {
  display: flex;
  text-decoration: none;
  align-items: center;
  /* padding: 10px; */
  color: black;
  height: 60px; /*keep*/
  background-color: #f9dcc4;
  position: fixed; /*keep*/
  top: 0; /*keep*/
  left: 0; /*keep*/
  z-index: 9999;

  /* removed
  width: 100vw;*/ /*keep*/

  /* added */
  width: 100%;
  flex-direction: row;
  /* grid-area: head; */
  /* margin-bottom: 5px; */
}

/* added */
.not-logged-in {
  height: 100%;
}

#mainSection {
  /* grid-area: content; */
  margin-top: 60px;
  /* margin-bottom: 50px; */
  /* margin-left: 0px; */
  /* height: 100vh; */

  /* removed
  height: 100vh;
  width: 100vw;
  /*

  /* added */
  width: 100%;
  /* height: 100%; */

  left: 0;
  /* position: static; */
  /* top: 40px; */

  /* removed 
  background-color: rgb(230, 227, 227);
  */
  height: 100%;
}

footer {
  background-color: rgb(230, 227, 227);
  text-indent: 0.5%;
  font-size: 0.65em;
  max-height: 20px;
  padding-bottom: 5px;
}

/* changed renamed to avoid commenting issues */
footerxxx {
  /* display: flex; */
  /* grid-area: foot; */

  /* removed 
  position: absolute;
  */

  left: 0; /*keep*/
  bottom: 0; /*keep*/
  width: 100%; /*keep*/
  background-color: rgb(230, 227, 227);
  text-indent: 0.5%;
  font-size: 0.65em;
  /* height: 50px; */
  /* position: fixed;
  align-items: bottom; */
}

#registerLogin,
.login-out {
  display: flex;

  /* removed 
  width: 82%;
  */

  /* margin-left: 73%; */
  justify-content: flex-end;

  /* added */
  flex-grow: 1;
  padding-right: 25px;
}

#app {
  /* display: grid;
  grid-template-areas:
    "head"
    "content"
    "foot"; */
  /* grid-template-rows: 0.5fr 8fr 0.1fr; */

  /* removed

   position: absolute;
  top: 0px;
  left: 0px; */

  /* added */
  height: 100vh;
  width: 100%;
  /* margin-left: 0px; */
}

.header {
  display: flex;
  justify-content: left;
  align-items: center;
  padding: 10px 30px 10px 20px;
  color: #ffb5a7;
  text-shadow: 0 0 1.5px darkblue;
}

#homeLogo {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#search-button {
  text-decoration: none;
  color: black;
}

#logoHeaderBar {
  height: 50px;
  width: auto;
}

/* #welcome-span {
  margin-top: 80px;
  width: 100%;
  height: 750px;
  
} */
</style>
