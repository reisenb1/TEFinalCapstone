<template>
  <div class="page">
    <div class="deck-header">
      <h2 id="cards-label">Cards in this deck</h2>      
      <div class="add-card">
        <!-- <router-link :to="{ name: 'AddCard' }">
          <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
        </router-link> -->
         
      <button v-on:click="showCardForm=true" v-show="!showCardForm">
        <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
      </button>
      </div>
    </div>

    <div class="add-card">

    

      <div class="card-form" v-show="showCardForm">
        <form v-on:submit.prevent="submitForm">

          <div class="card-form-cards">
          <div class="add-form" >
            <label for="front">Term</label>
            <input id="text" type="text" name="front" v-model="card.front" />
          </div>

          <div class="add-form">
            <label for="back">Definition</label>
            <input id="text" type="text" name="back" v-model="card.back" />
          </div>
          </div>


          <div class="form-buttons">
          <button class="submit" input type="submit">Add Card</button>

          <button class="cancel" v-on:click.prevent="cancelForm" type="cancel">Nvm I changed my mind</button>
          </div>
        </form>
      </div>

    </div>

  <div class="card" v-for="card in cards" v-bind:key="card.cardId" >
    <div class="front">{{card.front}}</div>
    <div class="back">{{card.back}}</div>


    <div class="confidence" v-show="card.confidence==2">
      <img class="confidence-image" src="../images/green-circle.png" alt="Green">
    </div>
    <div class="confidence" v-show="card.confidence==1">
      <img class="confidence-image" src="../images/yellow-circle.png" alt="Yellow">
    </div>
    <div class="confidence" v-show="card.confidence==0">
      <img class="confidence-image" src="../images/red-circle.png" alt="Red">
    </div>



    <div class="edit">
      <router-link :to="{name: 'EditCard', params:{cardId:card.cardId}}">Edit</router-link>
    </div>
    <button class="delete" v-on:click="deleteCard(card.cardId)">Delete</button>
    </div>

   <!-- <div class="add-card-bottom">
      <router-link :to="{ name: 'AddCard' }">
          <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
        </router-link>
      </div> -->



  </div>
</template>

<script>
import CardService from "../services/CardService";

export default {
  data(){
    return {
      isLoading: true,
      cards: [],
      showCardForm: false,
         card: {
        front: "",
        back: "",
      },
      deckId: this.$route.params.deckId,
  
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
    },


     submitForm() {

      if(this.card.front == '' || this.card.back == ''){
        this.cancelForm();
      } else {
        const newCard = {
          deckId: this.deckId,
          front: this.card.front,
          back: this.card.back,
          //fix
          userId: 1,
        }

    
        CardService.addCard(newCard)
          .then((response) => {
            if (response.status === 200) {
              this.getCards();
              this.showCardForm = false;
              this.card = {
                front: "",
                back: "",
              }
            }
          })
      
          .catch((error) => {
            console.log(error);
            this.showCardForm = false;
            this.card = {
                front: "",
                back: "",
              }
          });
        }
      },

    cancelForm() {
       this.showCardForm = false;
            this.card = {
                front: "",
                back: "",
              }
    },
    deleteCard(cardId){
    CardService.deleteCard(cardId)
    .then(
      this.getCards()
    )
    .catch(error => {
      console.log(error)
    })
  }
  },
  created(){
    this.getCards();
  },
 

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
  /* border-style: solid;
  border-color:rgb(223, 223, 223); */
  padding: 40px;
  margin: 10px;
  background-color: white;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
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
  margin-left: 20%;
  margin-right: 20%;
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


.add-card-image{
  max-height: 50px;
}

.add-card-bottom{
  display: flex;
  justify-content: flex-end;
  margin: 15px;
}

.add-form{
  align-items: center;
  border-style: solid;
  border-color:rgb(223, 223, 223);
  height: 200px;
  width: 400px;
  padding: 40px;
  margin: 10px;
  background-color: white;
}

.card-form-cards{
  display: flex;
  justify-content: center;
}

.form-buttons{
  display: flex;
  justify-content: center;
}

.submit{
  background-color: teal;
  width: 200px;
}

.cancel {
  background-color: gray;
  width: 200px;
}

.confidence-image{
  max-width: 30px;
}

</style>