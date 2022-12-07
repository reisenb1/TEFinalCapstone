<template>
  <div id="main-grid">
    <div id="sideNav">
      <h1 id="h1">My Decks</h1>
      <div class="Decks">
        <div class="loading" v-if="isLoading">
          <img src="../images/eye-blink.gif" />
        </div>
        <router-link id="activeDeck"
          :to="{ name: 'Deck', params: { deckId: deck.deckId } }"
          v-for="deck in this.decks"
          v-bind:key="deck.deckId"
        >
          {{ deck.deckName }}
        </router-link>
      </div>
      <router-link :to="{ name: 'AddDeck' }">Add a New Deck!!!!!!</router-link>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";

export default {
  data() {
    return {
      isLoading: true,
      decks: [],
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
      DeckService.getDecks(1, false).then((response) => {
        this.decks = response.data;
        this.isLoading = false;
      });
    },
  },
};
</script>
<style>
/* #main-grid {
  display: grid;
  grid-template-areas: 
    "sideNav   home    home"
    "sideNav   home    home"
    "sideNav   home    home"
  ;
  grid-template-columns: 1fr 2fr 2fr;
  grid-template-rows: 2fr 2fr 2fr;

  align-items: center;
} */

#main-grid {
  margin-top: 60px;
}

#sideNav {
  grid-area: sideNav;
}

#sideNav > #h1 {
  
}

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
  margin-top: 60px;
  background-color: rgb(214, 198, 198);
  text-align: center;
  justify-content: space-evenly;
  align-items: center;

  
}

h1 {
  text-align: center;
}

.decks {
  /* display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center; */
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

}

.home {
     display: flex;
     justify-content: space-between;
     flex-direction: column;
     align-items: center;
     color: black;
     grid-area: home;
}

#activeDeck {
  box-sizing: border-box;
  width: 150px;
  border: 15px solid rgb(128, 0, 0);
  padding: 50px;
  margin: 20px;
  display: flex;
  flex-direction: column;

}
</style>