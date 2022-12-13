<template>
  <!-- <div id="main-grid45"> -->
    <div class="container2">

      
      <form v-on:submit.prevent="submitForm">
        <div class="frontBackEdit">
        <div id="cardFront">
          <label for="front">Question:</label>
          <textarea id="text" type="text" name="back" v-model="card.front" class="cardText" />
        </div>

        <div id="cardBack">
          <label for="back">Answer:</label>
          <!-- <input id="text" type="text" name="back" v-model="card.back" class="cardText"/> -->
          <textarea id="text" type="text" name="back" v-model="card.back" class="cardText" />
        </div>
      </div>

        <div id="newButtons2">
        <button class="submit" input type="submit">Submit</button>

        <button class="cancel" v-on:click.prevent="cancelForm" type="cancel">
          Cancel
        </button>
        </div>
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
          userId: this.$store.getters.user.id,
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
          userId: this.$store.getters.user.id,
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
/* .container2 {
  display: grid;

  grid-template-areas: "cardFront  cardBack";

  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;

  align-items: center;
} */

.cardText {
  inline-size: 390px;
  /* overflow-wrap: break-word; */
  white-space: wrap;
  height: 200px;
  resize: none;
}

.frontBackEdit{
  display: flex;
  justify-content: center;
  margin-top: 50px;
  margin-bottom: 40px;
}

#cardFront {
  /* grid-area: cardFront; */
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 400px;
  padding: 25px;
  border: none;
  border-top: 20px solid #FFB5A7;
  /* border-top-color: lavender; */
  height: 200px;

  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

  margin-right: 25px;
  border-radius: 10px;
}

#cardBack {
  /* grid-area: cardBack; */
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 400px;
  padding: 25px;
  border: none;
  border-top: 20px solid #FEC89A;
  /* margin-top: 20px; */

  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  height: 200px;

  margin-left: 25px;
  border-radius: 10px;
}

#cardBack:after {
  border: 2px solid black;
}

.container2 {
  display: flex;
  justify-content: center;
  align-items: center;
  
}

#text {
  margin-top: 20px;
  border: none;
  /* border-bottom: 1px solid black; */
}

.submit {
  background-color: rgba(255, 255, 255);
  color: white;
  /* border: 2px solid blue; */
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 25%;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  transition-duration: 1s; 
}

.cancel {
  background-color: rgb(255, 255, 255);
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 25%;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  transition-duration: 1s;
  margin-left: 10px;
}

.submit:hover {
  background-color: #FFB5A7;
  opacity: 0.7;
}

.cancel:hover {
  background-color: rgb(143, 143, 143);
  opacity: 1;
}

.cancel,
.submit {
  margin-top: 15px;
  border-radius: 5px;
  
}

#newButtons2 {
  display: flex;
  justify-content: center;

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