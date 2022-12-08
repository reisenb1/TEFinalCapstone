<template>
  <main class="view">
    <!-- <img src="../images/study-photo.jpg" /> -->
    <div id="firstTextArea">
      <h2>Hello!</h2>
      <h3>Test your knowledge with flashcards from YEGET!</h3>
      <p>"Ye get cards, you get it??"</p>
    </div>

<!--   removed in order to move v-show to div  
    <div id="signInLink">
      <router-link
        v-bind:to="{ name: 'login' }"
        v-show="$store.state.token == ''"
        >Sign In</router-link
      >
    </div> -->

    <!-- added to move v-show from router-link to div -->
    <div id="signInLink" 
        v-show="$store.state.token == ''">
      <router-link
        v-bind:to="{ name: 'login' }"
        >Sign In</router-link
      >
    </div>
    <hr />
    <div id="categories">
          <h1>Categories</h1>
          <p>Here is more information</p>
        </div>

        <div id="about">
          <h1>About</h1>
          <p>Here is more information</p>

          <a id="top" href="#">Back to top</a>
        </div>

  </main>
</template>

<script>
export default {
  name: "home",
};
</script>

<style scoped>
main {
  /* display: flex;
  justify-content: space-between;
  flex-direction: column;
  align-items: center; */
  /* color: ivory; */
  /* background-color: cadetblue; */
}
h2,
h3,
p {
  padding-top: 30px;
  text-align: center;
}

#signInLink {
  font-weight: bold;
  border: solid rgb(185, 68, 68) 5px;
  background-color: rgb(185, 68, 68);
  margin: auto;
  width: 5vw;
  text-align: center;
  border-radius: 6px;
}

/* #firstTextArea {
  height: 100%;
} */

#categories {
  display: flex;
  align-items: center;
  flex-direction: column;
  color: white;
  scroll-behavior: smooth;
  background-color: rgba(110, 101, 238, 0.89);
  height: 750px;
  width: 100%;
}

#about {
  display: flex;
  color: white;
  background-color: rgb(235, 83, 109, 0.89);
  height: 500px;
  align-items: center;
  flex-direction: column;
  width: 100%;
} 


</style>