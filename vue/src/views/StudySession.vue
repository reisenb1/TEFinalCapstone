<template>
  <div>
      <!-- <deck-header></deck-header> -->
      <study-session></study-session>
  </div>
</template>

<script>
import StudySession from "../components/StudySession.vue";
// import DeckHeader from "../components/DeckHeader.vue";

export default {
    components: {
        StudySession,
        // DeckHeader
    },
    data() {
        return {
            deckId: 0,
        };
    },
    created() {
        this.deckId = parseInt(this.$route.params.id);
        this.$store.commit("SET_ACTIVE_DECK", this.deckId);
    }

}
</script>

<style>

</style>