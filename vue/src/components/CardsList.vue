<template>
  <div class="page">
    <div class="deck-header">
      
      <router-link :to="{name: 'StudySession', params:{deckId: deckId}}">
        <button class="study-session-link">
        START STUDY SESSION
        </button>
        </router-link>
      
      <h2 id="cards-label">Cards in this deck</h2>  
     
      <div class="add-card">
        <!-- <router-link :to="{ name: 'AddCard' }">
          <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
        </router-link> -->
         
      <button class="header-bottom" v-on:click="showCardForm=true" v-show="!showCardForm">
        <img class="add-card-image" src="../images/black-plus-sign.png" alt="Add Card">
      </button>
      </div>

    </div>

    <div class="add-card">
<!-- 
    <h3 id="cards-terms" class="cards-label">Terms</h3>
      <h3 id="cards-definitions" class="cards-label">Definitions</h3>
      <h3 id="cards-confidence" class="cards-label">Confidence</h3>  -->
    

      <div class="card-form" v-show="showCardForm">
        <form v-on:submit.prevent="submitForm">

          <div class="card-form-cards">
          <div class="add-form" >
            <label for="front">Question:</label>
            <!-- <input id="text" type="text" name="front" v-model="card.front" /> -->
            <textarea id="text" class="cardText" type="text" name="front" v-model="card.front" />
          </div>

          <div class="add-form">
            <label for="back">Answer:</label>
            <!-- <input id="text" type="text" name="back" v-model="card.back" /> -->
            <textarea id="text" class="cardText" type="text" name="back" v-model="card.back" />
          </div>
          </div>


          <div class="form-buttons">
          <button class="submit" input type="submit">Add Card</button>

          <button class="cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
          </div>
        </form>
      </div>

    </div>

    <div class="card-header">
      <div class="front">Question</div>
      <div class="back">Answer</div>

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
      <button class="edit-delete">
      <router-link :to="{name: 'EditCard', params:{cardId:card.cardId}}">
        <img class="imgggg" src="../images/image_from_ios.jpg" alt="pencil-icon">
      </router-link>
      </button>
    </div>
    <button class="delete edit-delete" v-on:click="deleteCard(card.cardId)">
      <img class="imgggg" src="../images/trashcan.png" alt="trashcan">
    </button>
    </div>

   <!-- <div class="add-card-bottom">
      <router-link :to="{ name: 'AddCard' }">
          <img class="add-card-image" src="../images/plus-sign.png" alt="Add Card">
        </router-link>
      </div> -->

    <div class="cards-list-footer"></div>

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
          userId:this.$store.getters.user.id
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
  grid-template-rows: 1fr 1fr;
/* 
  display: flex;
  flex-wrap: wrap;
  word-wrap: break-word; */

  align-items: center;
  /* border-style: solid;
  border-color:rgb(223, 223, 223); */
  padding: 40px;
  margin: 10px;
  background-color: white;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  font-weight: bold;
  border-radius: 10px;

}

.card-header{
   display: grid;
  
  grid-template-areas:
    "front back confidence edit"
    "front back confidence delete";

  grid-template-columns: 10fr 10fr 2fr 1fr;
  grid-template-rows: 1fr 1fr;


  align-items: center;

  padding-left: 40px;
  padding-right: 40px;
  margin: 10px;
  /* box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19); */
  font-weight: bold;
  font-size: 150%;
  text-decoration: underline;
}

.front{
  grid-area: front;
  /* border-style: solid;
  border-right: rgb(200, 200, 200), 1px;
  border-left: none;
  border-top: none;
  border-bottom: none;
  height: 100%; */
  /* display: flex;
  flex-direction: column;
  flex-wrap: wrap; */

   padding-left: 20%;
  padding-right: 20%;
}

.back{
  grid-area: back;
  padding-right: 20%;
 
  /* display: flex;
  flex-wrap: wrap;
  flex-direction: column; */
  
}

.confidence{
  grid-area: confidence;
  opacity: 75%;

}

.edit{
  grid-area: edit;
}

.delete{
  grid-area: delete;
}

.page{
  margin-left: 10%;
  margin-right: 10%;
  margin-top: 30px;
  margin-bottom: 30px;
  
}

#cards-label {
  justify-self: left;
  grid-area: cards-in-deck;
}

.deck-header{
  display:grid;

  grid-template-areas: 
    "study-session-link study-session-link "
    "cards-in-deck add-card";

  justify-content: space-between;
  align-items: center;
  margin:15px;
}

.header-bottom{
  background-color: white;
  border-style: solid;
  border-width: 1px;
  opacity: 75%;
  border-radius: 10px;
}

.header-bottom:hover{
  background-color: rgba(218, 216, 216, 0.7);
  transition-duration: 1s;
  opacity: 1;

}

.study-session-link{
  grid-area: study-session-link;
  background-color: #FFB5A7;
  /* display: flex; */
  justify-content: center;
  align-items: center;
  padding:30px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: none;
  border-radius: 10px;
  font-weight: bold;
  font-size: 150%;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
}

.study-session-link:hover{
  background-color: #f79986;
  
}

.add-card{
  grid-area: add-card;

}

.cards-label{
  display: flex;
  justify-content: center;
  flex-direction: row;
  
}

.add-card-image{
  max-height: 50px;
  padding: 10px;

}

.add-card-bottom{
  display: flex;
  justify-content: flex-end;
  margin: 15px;
  background-color: white;
  
}

.add-form{
  /* align-items: center;
  border-style: solid;
  border-color:rgb(223, 223, 223);
  height: 200px;
  width: 400px;
  padding: 40px;
  margin: 10px;
  background-color: white; */


  display: flex;
  flex-direction: column;
  background-color: white;
  width: 400px;
  padding: 25px;
  border: none;
  /* border-top: 20px solid #FFB5A7; */
  /* border-top-color: lavender; */
  height: 200px;

  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

  margin-right: 25px;
  border-radius: 10px; 
  
}

#text {
  margin-top: 20px;
  border: none;
  /* border-bottom: 1px solid black; */
}

.card-form{
  margin-bottom:50px;
  margin-top: 50px;
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
  background-color: #f79986;
  width: 200px;
  color: black;
  /* border: solid #f79986 2px; */
}

/* .submit-cards-list:hover{
  background-color: #f79986;
} */

.cancel {
  background-color: rgb(179, 179, 179);
  width: 200px;
}

.confidence-image{
  max-width: 30px;
}

.imgggg{
  width: 50%;
  
}
  
.edit-delete{
  background-color: white;
  border-style: none;
  opacity: 75%;

}

.cards-list-footer{
  height: 150px;
}

</style>