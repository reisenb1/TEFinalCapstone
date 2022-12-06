<template>

 
  <div id="sideNav">
    <h1>My Decks</h1>
    <div class="Decks">
      <div class="loading" v-if="isLoading">
        <img src="../images/eye-blink.gif" />
      </div>
      <router-link
        :to="{ name: 'Deck', params: { deckId: deck.deckId } }"
        v-for="deck in this.decks"
        v-bind:key="deck.deckId"
      >
        {{ deck.deckName }}
      </router-link>
    </div>
    <router-link :to="{name:'DeckForm'}">Add a New Deck!!!!!!</router-link>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";

export default {
 data() {
    return {
      isLoading: true,
      decks: []
    };
  },
  created() {
    this.retrieveDecks();
  },
  computed: {
    activeDeckId() {
      return this.$store.state.activeDeckId;
    },
  },
  methods: {
    retrieveDecks() {
     DeckService.getDecks(1, false).then(response => {
        this.decks = response.data;
        this.isLoading = false;
  
      });
    },

  }
}
</script>
<style>
div#sideNav {
  height: 100%;
  width: 20%;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  padding-top: 75px;
  padding-bottom: 20px;
  overflow-x: hidden;
  border-right: solid lightgrey 1px;
  display: flex;
  flex-direction: column;
  margin-top: 80px;

  
}

h1 {
  text-align: center;
}

.decks {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.deck {
  color: #010303;
  border-radius: 10px;
  padding: 40px;
  flex: 1;
  margin: 10px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  width: 60%;
}
.addDeck {
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
}
.form-control {
  margin-bottom: 10px;
}
.btn {margin-bottom: 35px;}
.loading {
  flex: 3;
}
.deck:hover:not(.router-link-active), .addDeck:hover {
  font-weight: bold;
}
.deck-active {
  font-weight: bold;
  border: solid blue 5px;
}

</style>