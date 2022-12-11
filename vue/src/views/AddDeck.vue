<template>
  <div>

      <div class="header"></div>
      <h1 id="add-new-deck">Add New Deck</h1>
      <deck-form v-bind:deckId=0></deck-form>
  </div>
</template>

<script>
import DeckForm from "../components/DeckForm.vue";
export default {
    components: {
        DeckForm
    }
}
</script>

<style scoped>
    .header {
        height: 100px;
    }
/* 
    #add-new-deck {
        display: flex;
        align-content: ;
    } */




</style>