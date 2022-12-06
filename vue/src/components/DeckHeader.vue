<template>
  <div>

      <router-link :to="{name: 'loggedInHome'}">Back to Home Page</router-link>

      <h1>{{deck.deckName}}</h1>

      <p>{{deck.deckDescription}}</p>

      <router-link :to="{name:'EditDeck', params:{deckId:this.$route.params.deckId}}">Edit</router-link>



  </div>
</template>

<script>
import DeckService from '../services/DeckService';
export default {

    data() {
        return {
            deck: {}
        }
    },

    created() {
        DeckService.getDeckById(this.$route.params.deckId)
        .then(response => {
            this.deck = response.data;
        })
        .catch(error => {
            console.log(error);
            this.$router.push({name: 'loggedInHome'})
        })
    }
}
</script>

<style>

</style>