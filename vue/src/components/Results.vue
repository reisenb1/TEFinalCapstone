<template>
  <div class="resultsContainer">
      
    <div class="header-results"></div>

    <div class="button-header">
      <!-- <router-link
        :to="{ name: 'Deck', params: { deckId: this.studySession.deckId } }"
      > -->
        
          <router-link
        :to="{ name: 'loggedInHome' }"
      >
          
          <button class="finishRetry">Finish Session </button>
      </router-link>

      <router-link
        :to="{
          name: 'StudySession',
          params: { deckId: this.studySession.deckId },
        }"
      >
        <button class="finishRetry2">Try Again</button>
      </router-link>
    </div>
    <h1>{{deck.deckName}} Study Session Results</h1>

    <div
      class="card-results-main backgroundColorGreen"
      v-if="studySession.correctPercent >= 70"
    >
      <!-- <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of:
        {{ studySession.correctPercent }}%
      </div> -->
      <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }} correct
      </div>
      <!-- <div>You have a confidence score of: {{ studySession.confidencePercent }}%</div> -->
       <div>Your confidence score is {{ studySession.confidencePercent }}%</div>
    </div>

    <div
      class="card-results-main backgroundColorYellow"
      v-if="
        studySession.correctPercent >= 50 && studySession.correctPercent < 70
      "
    >
      <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }} correct
     
      </div>
      <div>Your confidence score is {{ studySession.confidencePercent }}%</div>
    </div>

    <div
      class="card-results-main backgroundColorRed"
      v-if="studySession.correctPercent < 50"
    >
      <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }} correct

    
      </div>
      <div>Your confidence score is {{ studySession.confidencePercent }}%</div>
    </div>

    <div class="card-results" v-for="card in cards" v-bind:key="card.cardId">
      <div class="front">{{ card.front }}</div>
      <div class="back">{{ card.back }}</div>
      <div class="confidence" v-show="card.confidence == 2">
        <img
          class="confidence-image"
          src="../images/green-circle.png"
          alt="Green"
        />
      </div>
      <div class="confidence" v-show="card.confidence == 1">
        <img
          class="confidence-image"
          src="../images/yellow-circle.png"
          alt="Yellow"
        />
      </div>
      <div class="confidence" v-show="card.confidence == 0">
        <img
          class="confidence-image"
          src="../images/red-circle.png"
          alt="Red"
        />
      </div>
    </div>
  </div>
</template>

<script>
import CardService from "../services/CardService";
import StudySession from "../services/StudySessionService";
import DeckService from "../services/DeckService";

export default {
  //    props:
  data() {
    return {
      isLoading: true,
      cards: [],
      studySession: [],
      deck: []
    };
  },
  methods: {
    getCards() {
      CardService.getCards(this.$route.params.deckId)
        .then((response) => {
          this.cards = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Deck cards not available.");
            this.$router.push({ name: "Home" });
          }
        });
    },
    getStudySession() {
      StudySession.getStudySessionById(this.$route.params.studySessionId)
        .then((response) => {
          this.studySession = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Study session not available.");
            this.$router.push({ name: "Deck" });
          }
        });
    },
    getDecks() {
      DeckService.getDeckById(this.$route.params.deckId)
        .then((response) => {
          this.deck = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Deck cards not available.");
            this.$router.push({ name: "Home" });
          }
        });
    },
  },
  created() {
    this.getCards();
    this.getStudySession();
    this.getDecks();
  },
};
</script>

<style>
.header-results {
  height: 20px;
}

.card-results {
  display: grid;
  
  grid-template-areas:
    "front back confidence";

  grid-template-columns: 10fr 10fr 2fr;

  align-items: center;
  padding: 40px;
  margin: 10px;
  background-color: white;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  /* margin-bottom: 50px; */
  border-radius: 10px;
  
}

.card-results-main {
  display: flex;
  
  grid-template-areas:
    "correct confidence-main";

  grid-template-columns: 10fr 10fr;


  align-items: center;
  justify-content: space-evenly;
  padding: 20px;
  margin: 10px;
  background-color: white;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  margin-bottom: 40px;
  border-radius: 10px;
  
}

.front {
    grid-area: front;
    padding-left: 20%;
    padding-right: 20%;
}

.back {
    grid-area: back;
    padding-right: 20%;
}

.confidence {
    grid-area: confidence;
    opacity: 75%;
}

.resultsContainer {
    margin: 30px 10% 30px 10%;
}

h1{
    text-align: center;
}

.button-header {
    display: grid;

    grid-template-areas:
    "finishRetry finishRetry2";
    justify-content: space-between;
    align-items: center;
    margin: 10px;

    margin-bottom: 20px;
}

.finishRetry {
  background-color: white;
  /* display: flex;
  justify-content: center;
  align-items: center; */
  padding: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: none;
  border-radius: 10px;
  font-weight: bold;
  font-size: 100%;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  width:200px;
 
}

.finishRetry2 {
  background-color:white;
  /* display: flex;
  justify-content: center;
  align-items: center; */
  padding: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: none;
  border-radius: 10px;
  font-weight: bold;
  font-size: 100%;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  width: 200px;
}

.backgroundColorGreen {
  background-color:lightgreen;
  font-size: 24px;
  font-weight: bold;
}

.backgroundColorYellow {
  background-color: yellow;
  font-size: 24px;
  font-weight: bold;
}

.backgroundColorRed {
  background-color:  rgba(255, 0, 0, 0.61);
  font-size: 24px;
  font-weight: bold;
  
}
</style>