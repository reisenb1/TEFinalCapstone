<template>
  <div>
      <h1>Deck Name Study Session</h1>
      <div>Front: {{card.front}}</div>
      <div>Back: {{card.back}}</div>
  </div>
</template>

<script>
// import StudySessionService from "../services/StudySessionService";
// import DeckService from "../services/DeckService";
import CardService from "../services/CardService";

export default {
    data() {
        return {
            studySession: {},
            deckId: this.$route.params.deckId,
            card: {}
        }
    },
    created() {
        CardService.getCards(this.$route.params.deckId);
    }

}
</script>

<style>

</style>