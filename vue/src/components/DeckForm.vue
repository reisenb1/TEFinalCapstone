<template>
  <div>
      <form v-on:submit="saveNewDeck">

        <div>
          <label for="deck-name">Deck Name:</label>
          <input type="text" class="form-control" name="deck-name" v-model="newDeck.deckName" />
        </div>
       
        <div>
          <label for="deck-description">Description:</label>
          <input type="text" class="form-control" name="deck-description" v-model="newDeck.deckDescription"/>
        </div>

        <div>
          <label for="accessible">Check this box if you would like to make your deck public</label>
          <input id="checkbox" type="checkbox" name="accessible" v-model="newDeck.accessible" />
        </div>
        
        <button type="submit">Save</button>
        <button v-on:click="cancelDeck">Cancel</button>
      </form>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>