<template>
  <div>
    <results></results>
  </div>
</template>

<script>
import Results from "../components/Results.vue";
// import DeckHeader from "../components/DeckHeader.vue"

export default {
  components: {
    Results,
  },
  data() {
    return {
      deckId: 0,
    };
  },
  created() {
    this.deckId = parseInt(this.$route.params.id);
    this.$store.commit("SET_ACTIVE_DECK", this.deckId);
  },
};
</script>

<style>
</style>