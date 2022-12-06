<template>
  <div>
      <h1>Add New Deck</h1>
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

<style>

</style>