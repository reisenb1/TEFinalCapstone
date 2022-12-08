<template>
  <div>
    <h1>Deck Name Study Session Results</h1>
    <div class="card" v-for="card in cards" v-bind:key="card.cardId" >
      <div class="front">{{ card.front }}</div>
      <div class="back">{{ card.back }}</div>
    </div>
  </div>
</template>

<script>
import CardService from "../services/CardService";

export default {
//    props: 
  data() {
    return {
      isLoading: true,
      cards: [],
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
  },
  created() {
    this.getCards();
  },
};
</script>

<style>
</style>