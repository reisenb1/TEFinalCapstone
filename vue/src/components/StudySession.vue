<template>
  <div id="study-session-component">
    <h1>Deck Name Study Session</h1>
    <div v-for="card in cards" v-bind:key="card.cardId">
      <div v-show="card.cardId == currentCardId">
        <button id="card-front-study" v-on:click="flipCard" v-show="showFront">
          {{ card.front }}
        </button>
        <button id="card-back-study" v-show="showBack">{{ card.back }}</button>

        <div v-show="showCorrectIncorrectForm">
          <button v-on:click.prevent="submitCorrectIncorrect('correct')">
            Correct
          </button>
          <button v-on:click.prevent="submitCorrectIncorrect('incorrect')">
            Incorrect
          </button>
        </div>

        <div v-show="showConfidenceForm">
          <button v-on:click.prevent="submitConfidence('green', card)">
            Yes I got this 100%
          </button>
          <button v-on:click.prevent="submitConfidence('yellow', card)">
            meh
          </button>
          <button v-on:click.prevent="submitConfidence('red', card)">
            Yikes!!!
          </button>
        </div>
      </div>
    </div>

    <button id="cancelButton" v-on:click.prevent="goBackToDeckPage">Cancel Study Session</button>
  </div>
</template>

<script>
import StudySessionService from "../services/StudySessionService";
// import DeckService from "../services/DeckService";
import CardService from "../services/CardService";

export default {
  data() {
    return {
      deckId: this.$route.params.deckId,
      cards: [],
      currentCardId: "",
      position: 0,
      showCorrectIncorrectForm: false,
      showConfidenceForm: false,
      cardCorrectIncorrect: "",
      studySession: {
        userId: this.$store.getters.user.id,
        deckId: this.deckId,
        numberOfCards: 0,
        numberCorrect: 0,
        numberGreen: 0,
        numberYellow: 0,
        numberRed: 0,
      },
      allCardsUpdated: true,
      showFront: true,
      showBack: false,
    };
  },
  created() {
    this.getCards();
  },
  methods: {
    getCards() {
      CardService.getCards(this.$route.params.deckId)
        .then((response) => {
          this.cards = response.data;
          this.currentCardId = this.cards[0].cardId;
          this.studySession.numberOfCards = this.cards.length;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Deck cards not available.");
            this.$router.push({ name: "loggedInHome" });
          }
        });
    },

    submitCorrectIncorrect(result) {
      if (result == "correct") {
        this.studySession.numberCorrect = this.studySession.numberCorrect + 1;
      }
      this.showCorrectIncorrectForm = false;
      this.showConfidenceForm = true;
    },

    submitConfidence(result, currentCard) {
      let card = {
        cardId: this.currentCardId,
        deckId: this.deckId,
        front: currentCard.front,
        back: currentCard.back,
        userId: this.$store.getters.user.id,
        confidence: "",
      };

      if (result == "green") {
        this.studySession.numberGreen = this.studySession.numberGreen + 1;
        card.confidence = 2;
      } else if (result == "yellow") {
        this.studySession.numberYellow = this.studySession.numberYellow + 1;
        card.confidence = 1;
      } else {
        this.studySession.numberRed = this.studySession.numberRed + 1;
      }

      CardService.updateCard(card)
        .then((response) => {
          if (response.status == 200) {
            this.allCardsUpdated = true;
          }
        })
        .catch((error) => {
          console.log(error);
          this.allCardsUpdated = false;
        });

      this.showConfidenceForm = false;
      if (this.position == this.cards.length-1) {
        this.submitStudySession();
      } else {
        this.position = this.position + 1;
      }
      this.currentCardId = this.cards[this.position].cardId;
      this.showFront = true;
      this.showBack = false;
    },

    goBackToDeckPage() {
      this.$router.push({ name: "Deck", params: { deckId: this.deckId } });
    },

    flipCard() {
      this.showCorrectIncorrectForm = true;
      this.showFront = false;
      this.showBack = true;
    },

    submitStudySession(){
      this.studySession.deckId = this.deckId;
      StudySessionService.addStudySession(this.studySession)
      .then(response => {
        if(response.status == 200){
          this.$router.push({ name: "Results", params: { deckId: this.deckId, studySessionId: response.data.studySessionId } });
        }
      }).catch( error => {
        console.log(error);
        this.goBackToDeckPage;
      })
      
    }

    // flipBackToFront(){
    //   this.showFront=true;
    //   this.showBack = false;
    // }
  },
};
</script>

<style>

#cancelButton{
  align-self: right;
  justify-self: right;
  background-color: rgba(255, 0, 0, 0.425);
}

#study-session-component{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#card-front-study {
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 800px;
  padding: 25px;
  border-style: solid;
  border-color: rgb(223, 223, 223);
  height: 400px;
  /* box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); */
  align-content: center;
  justify-items: center;
  margin: 50px;
}

#card-back-study {
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 800px;
  padding: 25px;
  border-style: solid;
  border-color: rgb(223, 223, 223);
  height: 400px;
  /* box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); */
  margin: 50px;
 
}
</style>