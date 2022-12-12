<template>
  <div class="home">
    <!-- <h1>HOME</h1> HOME should be within the label but I removed -->
    <!-- <p>You must be authenticated to see this</p> -->
    <decks />
  </div>
</template>

<script>
import Decks from "../components/Decks";

export default {
  name: "home",
  components: {
    Decks,
  },
  created() {
    // if (this.$store.state.decks.length > 0) {
    //   const firstDeck = this.$store.state.decks[0];
    //   this.$router.push({ name: 'Deck', params: { id : firstDeck.id }})
    // }
  },
};
</script>

<style scoped>
/* .home {
     display: flex;
     justify-content: space-between;
     flex-direction: column;
     align-items: center;
     color: ivory;
   } */
</style>