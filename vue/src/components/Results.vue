<template>
  <div>
    <h1>Deck Name Study Session Results</h1>
    <button>Finish Session</button>


<!-- <div class="percentColorGreen" v-if="studySession.correctPercent >= 70">
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of
        {{ studySession.correctPercent }}
      </div>
      <div
        class="percentColorYellow"
        v-if="
          studySession.correctPercent >= 50 && studySession.correctPercent < 70
        "
      >
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of
        {{ studySession.correctPercent }}
      </div>
      <div class="percentColorRed" v-if="studySession.correctPercent < 50">
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of
        {{ studySession.correctPercent }}
      </div> -->





    <div class="card backgroundColorGreen" v-if="studySession.correctPercent >= 70">
      <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of
        {{ studySession.correctPercent }}%
      </div>
      <div>Confidence Score: {{ studySession.confidencePercent }}%</div>
    </div>

    <div class="card backgroundColorYellow" v-if="studySession.correctPercent >= 50 && studySession.correctPercent < 70">
      <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of
        {{ studySession.correctPercent }}%
      </div>
      <div>Confidence Score: {{ studySession.confidencePercent }}%</div>
    </div>

    <div class="card backgroundColorRed" v-if="studySession.correctPercent < 50">
      <div>
        You got {{ studySession.numberCorrect }}/{{
          studySession.numberOfCards
        }}
        a percentage of
        {{ studySession.correctPercent }}%
      </div>
      <div>Confidence Score: {{ studySession.confidencePercent }}%</div>
    </div>

    <div class="card" v-for="card in cards" v-bind:key="card.cardId">
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

export default {
  //    props:
  data() {
    return {
      isLoading: true,
      cards: [],
      studySession: [],
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
  },
  created() {
    this.getCards();
    this.getStudySession();
  },
};
</script>

<style>

.backgroundColorGreen {
    background-color: green;
    font-size: 24px;
    font-weight: bold;
}

.backgroundColorYellow {
    background-color: yellow;
    font-size: 24px;
    font-weight: bold;
}

.backgroundColorRed {
    background-color: red;
    font-size: 24px;
    font-weight: bold;
}

.percentColorYellow {
    font-size: 24px;
  color: yellow;
  font-weight: bold;
}

.percentColorGreen {
  color: green;
}

.percentColorRed {
  color: red;
}
</style>