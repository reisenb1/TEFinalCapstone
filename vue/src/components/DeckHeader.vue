<template>
  <div class="deck-header-page">




      <h1 class="deck-header-deck-name">{{deck.deckName}}</h1>


      <div class="description">

      <p>{{deck.deckDescription}}</p>

      </div>

      
        <button class="button-deck">
      <router-link :to="{name:'EditDeck', params:{deckId:this.$route.params.deckId}}" class="deck-edit">
          <img class = "edit" src="../images/image_from_ios.jpg" alt="edit deck">
          </router-link>
        </button>
   

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
    background-color: #FEC89A;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 2%;
  
}

.edit{
    
    height: 30px;
    
}

.button-deck {
    border-radius: 5px;
}

.button-deck{
    background-color: white;
    border-style: solid;
    border-width: 1px;

    justify-self: right;

    display: flex; 
    flex-direction: row;
    justify-content: flex-end;
    
}


</style>