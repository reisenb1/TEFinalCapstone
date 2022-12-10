<template>
  <div class="container3">
    <form id="add-deck-form" v-on:submit.prevent="submitForm">
      <div>
        <label for="deck-name">Deck Name: </label>
        <input
          type="text"
          class="form-control9"
          name="deck-name"
          v-model="deck.deckName"
        />
      </div>

      <div>
        <label for="deck-description">Description: </label>
        <input
          type="text"
          class="form-control9"
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
        creatorId: this.$store.getters.user.id,
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
                creatorId: this.$store.getters.user.id,
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
          creatorId: this.$store.getters.user.id,
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
#add-deck-form {

}

<style scoped>
.container3 {
  display: flex;
  justify-content: center;
  margin-top: 100px;
}

#add-deck-form {
  display: flex;
  flex-direction: column;
  background-color: lavender;
  width: 500px;
  padding: 25px;
  border: 15px solid rgb(218, 107, 218);
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  border-radius: 5px;
  margin-right: 75px;
  justify-content: center;
}

.form-control9 {
  height: 25px;
}
</style>