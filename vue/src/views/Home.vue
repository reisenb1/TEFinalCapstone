<template>
  <main>
    <div id="used-to-push-page-down"></div>
    <div class="view">
      <!-- <img src="../images/study-photo.jpg" /> -->
      <div id="firstTextArea">
         <h1 id="title-page">YEGET </h1> 
         <!-- <img id="logo-photo" src="../images/cards.gif" /> -->


        <!-- <div class="newNew">
          <div id="hello1">
            <h2>Hello!</h2>
          </div>

          <div id="h3graph">
            <h3>Test your knowledge with flashcards from YEGET!</h3>
          </div>

          <div id="desc123">
            <p>
              The best place to increase intelligence and have fun with a little
              trivia. <br />
              Yeget is the best, most comfortable place that allows you to let
              loose, <br />
              and own your friends with useless knowledge and facts. Get right
              with YEGET!
            </p>
          </div>

          <img id="main-logo" src="..\images\cards.gif" />
          <div>
            <p id="quote">"Ye get cards, you get it??"</p>
          </div>
        </div> -->
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
      <!-- <div id="signInLink" v-show="$store.state.token == ''">
        <router-link
          style="text-decoration: none; color: white"
          v-bind:to="{ name: 'login' }"
          >Sign In</router-link
        >
      </div> -->
      <hr />

      <div id="about">
        <!-- <h1 id="about-section-title">About</h1> -->
         <h1 id="about-section-title" class="different">What makes YEGET different?</h1>


        <div class="about-desc">
          

          <div id="yeget-promo">
            <!-- <p id="different">What makes YEGET different?</p>  -->
            <p id="whatMakes">Our exclusive algorithm provides you with valuable insight into your knowledge<br> - a unique <b>confidence score</b> that assesses how well you really know your facts!</p>
            <h3>Join millions of others by 
              
              <router-link :to="{name: 'register'}">creating an account</router-link> 
              
              
              and getting started today!</h3>
            <h4>Already have an account? 
              
              <router-link :to="{name: 'login'}">Click here to log in</router-link>
              
              </h4>
          </div>



        <!-- <ul>
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
        </ul> -->
        

        <div id="img-div">
          <div id="Amber">
            <img class="imgggggg" src="../images/Amber-Bitmoji.png" />
            <h3 class="h3">Amber</h3>
          </div>

          <div>
            <img class="imgggggg" src="../images/Ying-bitmoji-removebg-preview.png" />
            <h3 class="h3">Ying</h3>
          </div>

          <div>
            <img class="imgggggg" src="../images/Emily-bitmoji-removebg-preview.png" />
            <h3 class="h3">Emily</h3>
          </div>

          <div>
            <img class="imgggggg" src="../images/Geoff-bitmoji.png" alt="">
            <h3 class="h3">Geoff</h3>
          </div>

          <div>
            <img class="imgggggg" src="../images/AnhThi-bitomji.png" alt="">
            <h3 class="h3">Thi</h3>
          </div>

          <div>
            <img class="imgggggg" src="../images/Eric-bitmoji.png" alt="">
            <h3 class="h3">Eric</h3>
          </div>

        </div>

        </div>

        <a id="top" href="#"><img src="../images/white-double-arrow.png" alt=""></a>
      </div>


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

      <!-- <div id="about">
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
      </div> -->
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
      showUp: false
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

#whatMakes {
  font-size: 24px;
}

#yeget-promo{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: black;
  margin-bottom: 100px;
  width: 60%;
}

.h3{

  display: flex;
  justify-content: center;
  margin-top: -20px;
  color: black;

}


#img-div{
  display: flex;
  justify-content: center;
}


/* #Amber {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-direction: column;

} */

.imgggggg {
  height: 200px;
  
}

.about-desc {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  
}

.different{
  font-weight: bold;
  font-size: 36px;
  text-decoration: underline;
  color: black;
}


#title-page {
  font-weight: bold;
  color: ivory;
  text-shadow: 0 0 20px black;
  padding-bottom: 200px;
  /*use for logo next to the YEGET
  /* margin-right: -50px; */
}

/* #logo-photo {
  padding-bottom: 120px;
  height: 250px;
  
} */

/* #used-to-push-page-down {
  top: 60px;
} */

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
  background-repeat: no-repeat;
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
  font-size: 17px;
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
  height: 950px;
  display: flex;
  align-items: center;
  justify-content: center;
  /* flex-direction: column; */
  font-size: 100px;

}

#categories {
  display: flex;
  align-items: center;
  flex-direction: column;
  color: ivory;
  scroll-behavior: smooth;
  background-color: rgb(255, 140, 119, 0.8);
  height: 750px;
  padding-top: 60px;
  /* flex-basis: 50%; */
  width: 100%;
  /* opacity: 95%; */
}

#about {
  /* display: flex; */
  color: white;
  background-color: #ffb5a7;
  /* height: 500px; */
  flex-basis: 900px;
  /* align-items: center; */
  /* flex-direction: row; */
  width: 100%;
  padding-top: 60px;
}

/* #about-section-title {
  display: flex;

} */

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
  /* display: none; */
  position: fixed;
  bottom: 20px;
  right: 47.5%;
  z-index: 99;
  /* border: 2px solid black; */
  outline: none;
  /* background-color: #ffb676; */
  color: black;
  cursor: pointer;
  /* padding: 15px; */
  /* border-radius: 10px; */
  /* font-size: 10px; */
  font-weight: bold;
  opacity: 0.4;
  height: 100px;
  /* width: 60px; */
  /* transition-delay: 1s; */
  /* box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19); */
}

#top:hover {
  /* background-color: #ffb676; */
  opacity: 0.8;
  
}
</style>