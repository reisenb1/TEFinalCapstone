<template>
  <main>
    <div id="used-to-push-page-down"></div>
    <div class="view">
      <!-- <img src="../images/study-photo.jpg" /> -->
      <div class="newNew" id="firstTextArea">
        <div id="hello1">
        <h2>Hello!</h2>
        </div>

        <div id="h3graph">
        <h3>Test your knowledge with flashcards from YEGET!</h3>
        </div>

        <div id="desc123">
        <p>The best place to increase intelligence and have fun with a little trivia. <br>
        Yeget is the best, most comfortable place that allows you to let loose, <br>
        and own your friends with useless knowledge and facts. Get right with YEGET!</p>
        </div>

        <!-- <img id="main-logo" src="..\images\cards.gif" /> -->
        <div>
        <p id="quote">"Ye get cards, you get it??"</p>
        </div>
      </div>

        <!-- removed in order to move v-show to div  
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

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">General</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Nature</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Computers</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Mythology</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Sports</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Books</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Film</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Music</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Board Games</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Television</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Geography</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">History</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Art</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Celebrities</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Animals</div>
          </router-link>

          <router-link id="noUnder" :to="{ name: 'Search' }">
          <div class="category">Vehicles</div>
          </router-link>
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
#quote {
  display: flex;
  justify-content: left;
  padding-left: 45%;
}

#desc123 {
  display: flex;
  justify-content: left;
  flex-direction: column;
  padding-left: 25%;
}

#hello1 {
  display: flex;
  justify-content: left;
  padding-left: 57%;
}

#h3graph {
   display: flex;
  justify-content: left;
  padding-left: 25%;
}

#used-to-push-page-down {
   top: 60px;
}

#main-logo {
  height: 200px;
  width: 200px;
}

#categoriesContainer {
  display: flex;
  /* width: 60%; */
  justify-content: space-evenly;
  flex-wrap: wrap;
  gap: 20px;
  margin-bottom: 20px;
  max-width: 40%;
   text-decoration: none;
   height: 70%;
}

main {
  /* display: flex;
  flex-direction: column;
  align-items: center; 
  color: ivory; */
  /* background-color: cadetblue; */
  background-image: url("../images/study-photo.jpg");
  background-repeat:no-repeat;
  background-size: cover;
  background-attachment: fixed;
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

h2 h3 {
  /* margin-top: 60px; */
}

.category {
  display: flex;
  justify-content: center;
  border: 2px;
  border-style: solid;
  border-color: #f9dcc4;
  border-radius: 5px;
  font-size: 15px;
  background-color: #fec89a;
  padding: 10px;
  height: 50px;
  width: 120px;
  align-items: center;
  color: black;
   text-decoration: none;
}

.category:hover {
  box-shadow: 0 6px 14px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

}

#firstTextArea {
  /* margin: auto; */
  /* color: white; */
  height: 800px;
  /* display: flex;
  align-items: center;
  justify-content: left;
  flex-direction: column; */
}

.newNew {
  /* display: flex;
  justify-content: center; */
  /* align-items: center; */
  /* flex-wrap: wrap; */
  /* flex-direction: column; */
  color: ivory;
  width: 30%;
  /* border: 2px solid black; */
  margin-left: 10%;
  padding-right: 7%;
  font-weight: bold;
} 

#categories {
  display: flex;
  align-items: center;
  flex-direction: column;
  color: white;
  scroll-behavior: smooth;
  background-color: rgb(253, 227, 222, 0.9);
  height: 750px;
  /* flex-basis: 50%; */
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

h1 {
  margin-bottom: 75px;
}

#noUnder {
  text-decoration: none;
  font-weight: bold;
}

#top {
   
  position: fixed;
  bottom: 20px; 
  right: 30px; 
  z-index: 99;
  border: 2px solid black;
  outline: none;
  background-color: #ffb676;
  color: white;
  cursor: pointer; 
  padding: 15px; 
  border-radius: 10px; 
  font-size: 10px;
  font-weight: bold;
  opacity: 0.6;
}

#top:hover {
  background-color: #ffb676;
  opacity: 1;
}
</style>