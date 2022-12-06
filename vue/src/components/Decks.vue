<template>

 
  <div id="sideNav">
    <h1>My Decks</h1>
    <div class="Decks">
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>
      <div class="loading" v-if="isLoading">
        <img src="../images/eye-blink.gif" />
      </div>
      <router-link
        :to="{ name: 'Deck', params: { deckId: deck.deckId } }"
        class="deck"
        :class="{ 'deck-active': deck.deckId == activeDeckId }"
        v-for="deck in this.$store.state.decks"
        v-bind:key="deck.deckId"
      >
        {{ deck.deckName }}
      </router-link>
      <button
        class="btn addDeck"
        v-if="!isLoading && !showAddDeck"
        v-on:click="showAddDeck = !showAddDeck"
      >
        Add Deck
      </button>
    </div>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";

export default {
 data() {
    return {
      isLoading: true,
      showAddDeck: false,
      newDeck: {
          deckName: '',
          deckDescription: '',
          //have to fix this
          creatorId: 1,
          accessible: false,
      },
      errorMsg: ''
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
        this.$store.commit("SET_DECKS", response.data);
        this.isLoading = false;

        if (this.$store.state.decks.length > 0) {
          const deckId = response.data[0].deckId;
          this.$store.commit("SET_ACTIVE_DECK", deckId);

          // forward to correct deck to display cards for active deck
          //this.$router.push({ name: "Deck", params: { deckId: deckId } });
        } else {
          this.$store.commit("SET_ACTIVE_DECK", null);
        }
      });
    },
    saveNewDeck() {
      this.isLoading = true;
      DeckService.addDeck(this.newDeck).then( (response) => {
        if (response.status === 200) {
          this.retrieveDecks();
          this.showAddDeck = false;
          this.newDeck = {
            deckName: '',
          deckDescription: '',
          //also would have to fix creator id here
          creatorId: 1,
          accessible: false
          }
        }
      }).catch( (error) => {
        if (error.response) {
          this.errorMsg = "Error submitting deck. Response was " + error.response.statusText;
        } else if (error.request) {
          this.errorMsg = "Error submitting deck.  Server could not be reached."
        } else {
          this.errorMsg = "Request could not be created."
        }
        this.isLoading = false;
      });
   
    }
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
  color: #f7fafc;
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