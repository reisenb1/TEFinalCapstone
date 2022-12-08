<template>
  <div>
    <!-- <decks /> -->
    <deck-header></deck-header>
  
    <cards-list></cards-list>
  </div>
</template>

<script>
import CardsList from "../components/CardsList";
import DeckHeader from "../components/DeckHeader.vue"
// import Decks from "../components/Decks.vue"

export default {
  components: {
    CardsList,
    DeckHeader,
    // Decks
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