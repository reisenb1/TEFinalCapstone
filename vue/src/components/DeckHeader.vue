<template>
  <div class="deck-header-page">

      <router-link :to="{name: 'loggedInHome'}">Back to Home Page</router-link>


      <h1 class="deck-header-deck-name">{{deck.deckName}}</h1>


      <div class="description-edit">

      <p>{{deck.deckDescription}}</p>

      <router-link :to="{name:'EditDeck', params:{deckId:this.$route.params.deckId}}" class="deck-edit">Edit Deck</router-link>
    </div>


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
.deck-header-page{
 
   
    width: 100%;
    background-color: #F9DCC4;
  
}

.description-edit{
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.deck-header-deck-name{
    display: flex;
    justify-content: center;
}


</style>