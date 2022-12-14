<template>
  <div id="main-grid" class="container1">
    <div class="scroll" id="sideNav">

      <div id="mySearchBar">
        <!-- <h3 id="mySearchTitle">Search:</h3> -->
        <input
          id="search-box"
          type="text"
          v-on:keyup.enter="retrieveMyDeck"
          placeholder="search my decks..."
          v-model="mySearchTerm"
        />
      </div>

      <h1 id="h1">My Decks</h1>

      <router-link :to="{ name: 'AddDeck' }"
        ><button id="createNewDeck" v-show="decks.length == 0">Create Deck</button></router-link
      >
    
      <router-link :to="{ name: 'AddDeck' }"
        ><button id="addDeck" v-show="decks.length > 0">New Deck</button></router-link
      >

      <div class="decks">
        <div class="loading" v-if="isLoading">
          <img src="../images/eye-blink.gif" />
        </div>

        <router-link
          class="deckName"
          id="activeDeck"
          :to="{ name: 'Deck', params: { deckId: deck.deckId } }"
          v-for="deck in this.decks"
          v-bind:key="deck.deckId"
        >
          {{ deck.deckName }}
          <img src="../images/cards.gif" alt="" />
        </router-link>
      </div>
      <!-- <router-link :to="{ name: 'AddDeck' }"><button id="addDeck" >New Deck</button></router-link> -->
    </div>

    <a id="top" href="#"><img src="../images/white-double-arrow.png" alt=""></a>

    <div id="home1">
      <h1 id="greeting">Hello, {{ this.$store.getters.user.username }}!</h1>

      <div id="my-stats">
        <h2>My Stats</h2>
        <stats></stats>
      </div>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";
import Stats from "../components/Stats.vue";

export default {
  data() {
    return {
      isLoading: true,
      decks: [],
      mySearchTerm: "",
    };
  },
  created() {
    this.retrieveDecks();
  },
  components: {
    Stats,
  },
  computed: {
    activeDeckId() {
      return this.$store.state.activeDeckId;
    },
  },
  methods: {
    retrieveDecks() {
      DeckService.getDecks(this.$store.getters.user.id, false).then(
        (response) => {
          this.decks = response.data;
          this.isLoading = false;
        }
      );
    },
    retrieveMyDeck() {
      DeckService.getMySearchedDecks(
        this.$store.getters.user.id,
        this.mySearchTerm
      ).then((response) => {
        this.decks = response.data;
      });
    },
  },
};
</script>


<style scoped>
body {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
    "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
}

.container1 {
  display: grid;
  /* background-image: url("../images/pic2.jpg"); */
  background-color: #f8ede8;
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
}

#search-box {
  display: flex;
  align-items: center;
  height: 15px;
  width: 120px;
  margin: auto;
  margin-bottom: -5%;
}

#h1 {
  margin: 35px 0px -5px 0px;
}

/* #mySearchTitle {
  display: flex;
  height: 30px;
  justify-content: center;
  align-items: center;
  margin-bottom: 5px;
} */

#main-grid {
  display: grid;
  grid-template-areas:
    "sideNav   home1    home1"
    "sideNav   home1    home1"
    "sideNav   home1    home1";

  grid-template-columns: 15% 2fr 2fr;
  grid-template-rows: 1fr 2fr 2fr;

  align-items: center;
  width: 100%;
  /* height: 100vh */
  column-gap: 0%;
}

#sideNav {
  grid-area: sideNav;
}

#home1 {
  grid-area: home1;
}

#home1 {
  display: grid;
  grid-template-areas:
    "greeting    greeting    greeting"
    "my-stats    my-stats    my-stats"
    "confidence  confidence  high-score";
  grid-template-columns: 1fr 1fr 1fr;
  grid-auto-rows: 10% 5% 3fr;

  /* row-gap: 0px; */
  /* column-gap: 50px; */

  height: 100vh;
}

#greeting {
  display: flex;
  grid-area: greeting;
  width: 100%;
  background-color: #f3c096;
  height: 95px;
  align-items: center;
  text-indent: 20px;
  /* border-radius: 10px; */
}

#my-stats {
  grid-area: my-stats;
  height: 70px;
  background-color: #fcb2a4;
  align-items: center;
  text-indent: 20px;
  width: 91%;
  border-radius: 10px;
  box-shadow: 0 6px 14px 0 rgba(0, 0, 0, 0.2), 0 6px 10px 0 rgba(0, 0, 0, 0.19);
}

#confidence {
  grid-area: confidence;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin-left: 70px;
  margin-top: 50px;
}

#high-score {
  grid-area: high-score;
  margin-top: 50px;
}

div#sideNav {
  height: 90%;
  width: 15%;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  padding-top: 20px;
  padding-bottom: 20px;
  border-right: solid rgb(192, 186, 186) 5px;
  display: flexbox;
  flex-direction: column;
  margin-top: 60px;
  background-color: rgb(214, 198, 198, 0.7);
  text-align: center;
  justify-content: space-evenly;
  align-items: center;
  overflow-x: hidden;
}

.decks {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

#createNewDeck {
  background-color: #fcb2a4;
  color: black;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  height: 20%;
  width: 80%;
  box-shadow: 0 6px 14px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  transition-duration: 1s;
  margin-left: 10px;
  text-decoration: none;
  margin-top: 80%;
  border-radius: 10px;
  font-weight: bold;
  font-size: 250%;
  /* background-color: rgb(#F8EDE8, 0.6); */
}

#addDeck {
  background-color: #fcb2a4;
  color: black;
  padding: 14px 20px;
  margin: 40px 0px 35px 10px;
  border: none;
  cursor: pointer;
  height: 40px;
  width: 40%;
  box-shadow: 0 6px 14px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  transition-duration: 1s;
  text-decoration: none;
  border-radius: 10px;
  /* background-color: rgb(#F8EDE8, 0.6); */
}

#addDeck:hover {
  background-color: rgb(#f1ae8f, 0.6);
  opacity: 0.6;
}

/* .deck {
  color: #010303;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 10px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  width: 60%;
} */
/* .addDeck {
  display: flex;
  justify-content: center;
  color: #f7fafc;
  border-radius: 20px;
  background-color: #196ae4;
  font-size: 16px;
  width: 60%;
  margin: 10px;
  padding: 10px;
  cursor: pointer;
} */
/* .form-control {
  margin-bottom: 10px;
}
.btn {
  margin-bottom: 35px;
}
.loading {
  flex: 3;
}
.deck:hover:not(.router-link-active),
.addDeck:hover {
  font-weight: bold;
}
.deck-active {
  font-weight: bold;
  border: solid blue 5px;

} */

/* .home {
     display: flex;
     justify-content: space-between;
     flex-direction: column;
     align-items: center;
     color: black;
     grid-area: home;
     overflow-y: auto;
  overflow-x:hidden;
} */

#activeDeck {
  box-sizing: border-box;
  width: 165px;
  height: 215px;
  border: 3px solid black;
  border-radius: 15px;
  background-color: #fec89a;
  padding: 5px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  text-decoration: none;
  justify-content: center;
  box-shadow: 0 6px 14px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

#activeDeck:hover {
  /* background-color: (#f3790e);
  opacity: 0.7; */

   background-color: #f8b172;
  border: solid black 3px;
}

.deckName {
  margin-bottom: 50px;
  font-weight: bold;
  color: black;
}

img {
  height: 50%;
  margin-top: 20px;
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
  opacity: 0.6;
  height: 100px;
  /* width: 60px; */
  /* transition-delay: 1s; */
  /* box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19); */
}

#top:hover {
  /* background-color: #ffb676; */
  opacity: 1;
  
}
</style>