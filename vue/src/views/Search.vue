<template>
  <div id="searchPage">
      <div id="header-search"></div>

    <div id="search">
      <h2 id="searchTitle">Search:</h2>
      <input
        id="search-box"
        type="text"
        v-on:keyup.enter="retrieveSearchedDecks"
        placeholder="search by name or description..."
        v-model="searchTerm"
      />
    </div>

    <!-- used to scroll to top -->
    <!-- <a id="top" href="#"><img src="../images/white-double-arrow.png" alt=""></a> -->

    <div id="searchResults">
      <div id="searchLinks" v-bind:key="deck.deckId" v-for="deck in this.decks">
        
        <div id="firstLine">
          <!-- <div v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }">
            Deck ID: {{ deck.deckId }}
          </div> -->
          <img id="search-img" src="../images/cards.gif" alt="" />
          <router-link id="deckNameId"
            v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }"
          >
            {{ deck.deckName }}
          </router-link>
          <!-- <div v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }">
            Creator ID: {{ deck.creatorId }}
          </div> -->
        </div>
        <div id="secondLine">
          <div v-bind:to="{ name: 'Deck', params: { deckId: deck.deckId } }">
            Description: {{ deck.deckDescription }}
          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";

export default {
  name: "Search",
  data() {
    return {
      decks: [],
      searchTerm: "",
    };
  },
  methods: {
    retrieveSearchedDecks() {
      DeckService.getAllSearchedDecks(this.searchTerm).then((response) => {
        this.decks = response.data;
      });
    },
    retrieveAllDecks() {
      // DeckService.getDecks(this.$store.getters.user.id).then((response) => {
      //     this.decks = response.data;
      DeckService.getAllDecks().then((response) => {
        this.decks = response.data;
      });
    },
  },
  created() {
    this.retrieveAllDecks();
  },
};
</script>

<style>
#firstLine {
  display: flex;
  /* justify-content: space-between; */
  align-content: center;
  /* margin-right: 25px; */
}

#firstLine img {
  margin-right: 30px;
}

#searchResults {
  display: flex;
  justify-content: center;
  /* width: 1800px; */
  flex-wrap: wrap;
}

#searchLinks {
  box-sizing: border-box;
  width: 300px;
  height: 200px;
  border: 2px solid black;
  border-radius: 15px;
  background-color: #fec89a;
  padding: 10px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  text-decoration: none;
  box-shadow: 0 6px 14px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  /* flex-direction: column; */
  justify-content: space-between;
  margin-top: 50px;
  text-decoration: none;
  /* text-transform: uppercase; */

  /* display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 60vw;
  background-color: #f9dcc4;
  margin: auto;
  margin-top: 10px;
  border-style: solid;
  border-width: thin;
  padding: 3px;
  border-radius: 5px; */
}

#searchLinks:hover {
  background-color: #f8b172;
  /* opacity: 0.6; */
  border: solid black 3px;
}

#searchTitle {
  padding-right: 10px;
  color: #ffb5a7;
  text-shadow: 0 0 1.5px darkblue;
}

#searchPage {
  display: flex;
  /* justify-content: center; */
  
}

#search {
  display: flex;
  height: 60px;
  justify-content: center;
  align-items: center;
}

#search-box {
  width: 400px;
  border-radius: 15px;
  height: 35px;
  padding-left: 10px;
}

#header-search {
  height: 50px;
}

#search-img {
  height: 60px;
  display: flex;
}

#deckNameId {
  text-decoration: none;
  font-weight: bold;
  color: black;
  text-transform:capitalize;
}

/* #top {
  /* display: none; */
  /* position: fixed;
  bottom: 20px;
  right: 47.5%;
  z-index: 99;
  border: 2px solid black;
  outline: none;
  background-color: #ffb676;
  color: black;
  cursor: pointer;
  padding: 15px;
  border-radius: 10px;
  font-size: 10px;
  font-weight: bold;
  opacity: 0.4;
  height: 100px;
  width: 60px;
  transition-delay: 1s;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
} */

/* #top:hover {
  background-color: #ffb676;
  opacity: 0.8;
  
}  */
</style>