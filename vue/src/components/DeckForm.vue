<template>
  <div>
    <form v-on:submit.prevent="submitForm">
      <div>
        <label for="deck-name">Deck Name:</label>
        <input
          type="text"
          class="form-control"
          name="deck-name"
          v-model="deck.deckName"
        />
      </div>

      <div>
        <label for="deck-description">Description:</label>
        <input
          type="text"
          class="form-control"
          name="deck-description"
          v-model="deck.deckDescription"
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
          v-model="deck.accessible"
        />
      </div>

      <button type="submit">Save</button>
      <button v-on:click.prevent="cancelDeck" type="cancel">Cancel</button>
      
    </form>

   
  </div>
</template>

<script>
import DeckService from "../services/DeckService";
export default {
  props: {
    deckId: {}
  },
  created(){
    if(this.deckId != 0){
      DeckService.getDeckById(this.deckId)
        .then( response => {
          this.deck = response.data;
        })
        .catch(error => {
          if(error.response && error.response.status == 404){
            alert("Could not find deck");
          }
          this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
        });
    }
  },
  data() {
    return {
      deck: {
        deckName: "",
        deckDescription: "",
        //have to fix this
        creatorId: 1,
        accessible: false,
      },
    };
  },
  methods: {
    submitForm() {
      if(this.deckId == 0){
        this.isLoading = true;
        DeckService.addDeck(this.deck)
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
      } else {
         const newDeck = {
          deckId: this.deckId,
          deckName: this.deck.deckName,
          deckDescription: this.deck.deckDescription,
          //have to fix this
          creatorId: 1,
          accessible: this.deck.accessible,
        };
        DeckService.updateDeck(newDeck)
          .then( response => {
            if (response.status === 200){
              this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
            }
          })
          .catch(error => {
            console.log(error);
          })
      }
    },
    cancelDeck() {
        this.$router.push({ name: 'Deck', params: { deckId: this.deckId } });
    }
  },
};
</script>

<style>
</style>