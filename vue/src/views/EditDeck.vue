<template>
  <div>
       <div class="header"></div>
      <h1>Edit Deck</h1>
      <deck-form v-bind:deckId="parseInt($route.params.deckId)"></deck-form>
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

<style>

</style>