<template>
  <div>
    <form v-on:submit.prevent="submitForm">
      <div>
        <label for="front">Front:</label>
        <input type="text" name="front" v-model="card.front" />
      </div>

      <div>
        <label for="back">Back:</label>
        <input type="text" name="back" v-model="card.back" />
      </div>

      <button input type="submit">Submit</button>

      <button
        v-on:click.prevent="cancelForm"
        type="cancel"
      >Cancel</button>
    </form>
  </div>
</template>

<script>
import CardService from "../services/CardService";
export default {
  name: "card-form",
  props: {
    cardId: {}
  },
  data() {
    return {
      card: {
        front: "",
        back: "",
      },
      deckId: this.$route.params.deckId
    };
  },



  methods: {
  
    submitForm() {
      if(this.cardId == 0){
        const newCard = {
          deckId: this.deckId,
          front: this.card.front,
          back: this.card.back,
          //fix
          userId: 1
        };
        CardService.addCard(newCard)
          .then((response) => {
            if (response.status === 200) {
              this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
            }
          })
          .catch((error) => {
            console.log(error);

          });
      } else {
        const newCard = {
          cardId: this.cardId,
          deckId: this.deckId,
          front: this.card.front,
          back: this.card.back,
          //fix
          userId: 1
        };
        CardService.updateCard(newCard)
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

    cancelForm() {
      this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
    },
  },


    created(){
      if(this.cardId != 0){
        CardService.getCardById(this.cardId)
          .then( response => {
            this.card = response.data;
          })
          .catch(error => {
            if(error.response && error.response.status == 404){
              alert("Could not find card");
            }
            this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
          });
      }
    },
};
</script>

<style>
</style>