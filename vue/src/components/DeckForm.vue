<template>
  <div>
    <form v-on:submit="saveNewDeck">
      <div>
        <label for="deck-name">Deck Name:</label>
        <input
          type="text"
          class="form-control"
          name="deck-name"
          v-model="newDeck.deckName"
        />
      </div>

      <div>
        <label for="deck-description">Description:</label>
        <input
          type="text"
          class="form-control"
          name="deck-description"
          v-model="newDeck.deckDescription"
        />
      </div>

      <div>
        <label for="accessible"
          >Check this box if you would like to make your deck public</label
        >
        <input
          id="checkbox"
          type="checkbox"
          name="accessible"
          v-model="newDeck.accessible"
        />
      </div>

      <button type="submit">Save</button>
      
    </form>

    <button v-on:click="cancelDeck()">Cancel</button>
  </div>
</template>

<script>
import DeckService from "../services/DeckService";
export default {
  data() {
    return {
      newDeck: {
        deckName: "",
        deckDescription: "",
        //have to fix this
        creatorId: 1,
        accessible: false,
      },
    };
  },
  methods: {
    saveNewDeck() {
      this.isLoading = true;
      DeckService.addDeck(this.newDeck)
        .then((response) => {
          if (response.status === 200) {
            this.showAddDeck = false;
            this.newDeck = {
              deckName: "",
              deckDescription: "",
              //also would have to fix creator id here
              creatorId: 1,
              accessible: false,
            };
           this.$router.push({ name: 'Deck', params: { deckId: response.data.deckId } })
          }
        })
        .catch((error) => {
            console.log(error);
            this.isLoading = false;
        });
    },
    cancelDeck() {
        this.$router.push({name: 'loggedInHome'});
    }
  },
};
</script>

<style>
</style>