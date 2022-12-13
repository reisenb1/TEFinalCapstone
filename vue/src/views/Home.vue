<template>
  <main>
    <div class="view">
      <!-- <img src="../images/study-photo.jpg" /> -->
      <div id="firstTextArea">
        <h2>Hello!</h2>
        <h3>Test your knowledge with flashcards from YEGET!</h3>
        <img src="..\images\cards.gif" />
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
      <div id="signInLink" v-show="$store.state.token == ''">
        <router-link
          style="text-decoration: none; color: white"
          v-bind:to="{ name: 'login' }"
          >Sign In</router-link
        >
      </div>
      <hr />
      <div id="categories">
        <h1>Categories</h1>
        <div id="categoriesContainer">
          <div class="category">General</div>
          <div class="category">Nature</div>
          <div class="category">Computers</div>
          <div class="category">Mythology</div>
          <div class="category">Sports</div>
          <div class="category">Books</div>
          <div class="category">Film</div>
          <div class="category">Music</div>
          <div class="category">Musicals</div>
          <div class="category">Television</div>
          <div class="category">Geography</div>
          <div class="category">History</div>
          <div class="category">Art</div>
          <div class="category">Celebrities</div>
          <div class="category">Animals</div>
          <div class="category">Vehicles</div>
        </div>
      </div>

      <div id="about">
        <h1>About</h1>
        <ul>
          Team Members:
          <li>
            Ying Huang: Digimon player extraordinare. When it comes to
            ping-pong, let him beat himself.
          </li>
          <li>
            Thi Pham: Color guard FTW. We are glad she made it to the end
            without getting kicked out.
          </li>
          <li>
            Emily Hamilton: Don't let her go hungry or you'll regret it. She is
            also a velociraptor.
          </li>
          <li>
            Geoffrey Jones: Will smoke you on the basketball court. Has said
            sorry 5000 times since the beginning of cohort.
          </li>
          <li>
            Eric Reisenbuckler: We don't know why he did this program when he
            used to be an aerospace engineer at Boeing...
          </li>
        </ul>

        <a id="top" href="#">Back to top</a>
      </div>
    </div>
  </main>
</template>

<script>
import TagService from "../services/TagService.js";

export default {
  name: "home",
  data() {
    return {
      tags: [],
    };
  },
  created() {
    this.retrieveTags();
  },
  methods: {
    retrieveTags() {
      TagService.getTags().then((response) => {
        this.tags = response.data;
      });
    },
  },
};
</script>

<style scoped>
#categoriesContainer {
  display: flex;
  width: 100vw;
  justify-content: space-evenly;
  flex-wrap: wrap;
  gap: 20px;
  margin-bottom: 20px;
  max-width: 55%;
}
main {
  /* display: flex;
  justify-content: space-between;
  flex-direction: column;
  align-items: center; */
  /* color: ivory; */
  /* background-color: cadetblue; */
  background-image: url("../images/study-photo.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}
h2,
h3,
p {
  padding-top: 30px;
  text-align: center;
}

#signInLink {
  font-weight: bold;
  border: solid rgb(199, 92, 92) 2px;
  background-color: #ffb5a7;
  margin: auto;
  width: 100px;
  text-align: center;
  border-radius: 6px;
}

.category {
  border: 5px;
  border-style: solid;
  border-color: #f9dcc4;
  border-radius: 8px;
  font-size: 25px;
  background-color: #fec89a;
  padding: 10px;
}

#firstTextArea {
  margin: auto;
  color: white;
}

#categories {
  display: flex;
  align-items: center;
  flex-direction: column;
  color: white;
  scroll-behavior: smooth;
  background-color: rgb(252, 213, 206, 0.9);
  /* height: 750px; */
  flex-basis: 25%;
  width: 100%;
  /* opacity: 95%; */
}

#about {
  display: flex;
  color: white;
  background-color: #ffb5a7;
  /* height: 500px; */
  flex-basis: 500px;
  align-items: center;
  flex-direction: column;
  width: 100%;
}

.view {
  display: flex;
  flex-direction: column;
}
</style>