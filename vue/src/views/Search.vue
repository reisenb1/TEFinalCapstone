<template>
  <div id="searchPage">

      <div id="search">
          <h2 id="searchTitle">Search: </h2>
          <input type="text" v-on:keyup.enter.prevent= "retrieveSearchedDecks" placeholder="search by name or description...">
      </div>

      <div v-bind:key="deck.deckId" v-for="deck in this.decks" > 
          <router-link v-bind:to="{ name: 'Deck' , params: {deckId: deck.deckId}}">
            {{ deck.deckName }}
          </router-link>
        
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
        retrieveAllDecks(){
            // DeckService.getDecks(this.$store.getters.user.id).then((response) => {
            //     this.decks = response.data;
            DeckService.getAllDecks().then((response) => {
                this.decks = response.data;
            });
        }
    },
    created() {
        this.retrieveAllDecks();
    }
}
</script>

<style>

#searchTitle {
    padding-right: 10px;
    color: #ffb5a7;
    text-shadow: 0 0 1.5px darkblue;
}

#searchPage {
    display: flex;
    justify-content: center;
}

#search {
    display: flex;
    height: 60px;
    justify-content: center;
    align-items: center;
}

input {
    width: 400px;
    border-radius: 15px;
    height: 35px;
    padding-left: 10px;
}

</style>