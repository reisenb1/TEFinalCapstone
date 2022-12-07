<template>
  <div class="page">
    <div class="deck-header">
      <h2 id="cards-label">Cards in this deck</h2>
      <div class="add-card">
        <router-link :to="{ name: 'AddCard' }">
          <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
        </router-link>
      </div>
    </div>

  <div class="card" v-for="card in cards" v-bind:key="card.cardId" >
    <div class="front">{{card.front}}</div>
    <div class="back">{{card.back}}</div>


    <div class="confidence" v-show="card.confidence=='green'">Green</div>
    <div class="confidence" v-show="card.confidence=='yellow'">Yellow</div>
    <div class="confidence" v-show="card.confidence==0">Red</div>



    <div class="edit">
      <router-link :to="{name: 'EditCard', params:{cardId:card.cardId}}">Edit</router-link>
    </div>
    <div class="delete">Delete</div>
    </div>

   <div class="add-card-bottom">
      <router-link :to="{ name: 'AddCard' }">
          <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
        </router-link>
      </div>



  </div>
</template>

<script>
import CardService from "../services/CardService";
export default {
  data(){
    return {
      isLoading: true,
      cards: []
    }
  },
  methods: {
    getCards() {
      CardService.getCards(this.$route.params.deckId)
      .then(response => {
        this.cards = response.data;
        this.isLoading = false;
      })
      .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert(
              "Deck cards not available."
            );
            this.$router.push({ name: "Home" });
          } 
        });
    }
  },
  created(){
    this.getCards();
  }

}
</script>

<style>

.card{
  display: grid;
  
  grid-template-areas:
    "front back confidence edit"
    "front back confidence delete";

  grid-template-columns: 10fr 10fr 2fr 1fr;

  align-items: center;
  border-style: solid;
  border-color:rgb(223, 223, 223);
  padding: 40px;
  margin: 10px;
  background-color: white;
}

.front{
  grid-area: front;
}

.back{
  grid-area: back;
}

.confidence{
  grid-area: confidence;
}

.edit{
  grid-area: edit;
}

.delete{
  grid-area: delete;
}

.page{
  margin-left: 300px;
  margin-right: 300px;
  margin-top: 30px;
  margin-bottom: 30px;
}

#cards-label {
  justify-self: left;
}

.deck-header{
  display:flex;
  justify-content: space-between;
  align-items: center;
  margin:15px;
}

.add-card{
  font-display: green;
}

.add-card-image{
  max-height: 50px;
}

.add-card-bottom{
  display: flex;
  justify-content: flex-end;
  margin: 15px;
}




</style>