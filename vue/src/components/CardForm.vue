<template>
  <!-- <div id="main-grid"> -->
    <div class="container2">
      <form v-on:submit.prevent="submitForm">
        <div id="cardFront">
          <label for="front">Front:</label>
          <input id="text" type="text" name="front" v-model="card.front" />
        </div>

        <div id="cardBack">
          <label for="back">Back:</label>
          <input id="text" type="text" name="back" v-model="card.back" />
        </div>

        <button input type="submit">Submit</button>

        <button v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
      </form>
    </div>
  <!-- </div> -->
</template>

<script>
import CardService from "../services/CardService";
export default {
  name: "card-form",
  props: {
    cardId: {},
  },
  data() {
    return {
      card: {
        front: "",
        back: "",
      },
      deckId: this.$route.params.deckId,
    };
  },

  methods: {
    submitForm() {
      if (this.cardId == 0) {
        const newCard = {
          deckId: this.deckId,
          front: this.card.front,
          back: this.card.back,
          //fix
          userId: 1,
        };
        CardService.addCard(newCard)
          .then((response) => {
            if (response.status === 200) {
              this.$router.push({
                name: "Deck",
                params: { deckId: this.deckId },
              });
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
          userId: 1,
        };
        CardService.updateCard(newCard)
          .then((response) => {
            if (response.status === 200) {
              this.$router.push({
                name: "Deck",
                params: { deckId: this.deckId },
              });
            }
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },

    cancelForm() {
      this.$router.push({ name: "Deck", params: { deckId: this.deckId } });
    },
  },

  created() {
    if (this.cardId != 0) {
      CardService.getCardById(this.cardId)
        .then((response) => {
          this.card = response.data;
        })
        .catch((error) => {
          if (error.response && error.response.status == 404) {
            alert("Could not find card");
          }
          this.$router.push({ name: "Deck", params: { deckId: this.deckId } });
        });
    }
  },
};
</script>

<style>
#cardFront {
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 400px;
  padding: 25px;
  border: none;
  border-top: 20px solid red;
  /* border-top-color: lavender; */
  height: 200px;

  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

  margin-right: 50px;

  grid-area: frontCard;
}

#cardBack {
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 400px;
  padding: 25px;
  border: none;
  border-top: 20px solid rgb(163, 160, 160);
  margin-top: 20px;

  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  height: 200px;

  margin-right: 50px;
  grid-area: backCard;
}

#cardBack:after {
  border: 2px solid black;
}

.container2 {
  display: flex;
  justify-content: center;
}

#text {
  border: none;
  border-bottom: 1px solid black;
}

/* #main-grid {
  display: grid;
  grid-template-areas:
    "frontCard  backCard"
    ;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 3fr;

  align-items: center;

  row-gap: 20px;
  column-gap: 50px;
} */
</style>