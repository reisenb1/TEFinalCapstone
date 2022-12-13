<template>
<div class="both-options">
  <div class="container3">
    <form id="add-deck-form" v-on:submit.prevent="submitForm">
      <div>
        <label for="deck-name">Deck Name: </label>
        <input
          type="text"
          class="form-control9"
          name="deck-name"
          v-model="deck.deckName"
        />
      </div>

      <div>
        <label for="deck-description">Description: </label>
        <input
          type="text"
          class="form-control9"
          name="deck-description"
          v-model="deck.deckDescription"
        />
      </div>

      <div>
        <label for="accessible"
          >Check this box if you would like to make your deck public: </label
        >
        <input
          id="checkbox"
          class="checkbox"
          type="checkbox"
          name="accessible"
          v-model="deck.accessible"
        />
      </div>

      <button id="niceButton" type="submit">Save</button>
      <button id="niceButton" v-on:click.prevent="cancelDeck" type="cancel">Cancel</button>
      
    </form>   
  </div>

  <div>OR</div>


    <div class="container-random">

     
      <form id="add-deck-form" v-on:submit.prevent="submitForm">

         <h2>Select a category to generate a random deck!</h2> 

      <input type="radio" id="general" name="category" value="GENERAL" v-on:click="setCategory('GENERAL')">
      <label for="general">General</label>

      <input type="radio" id="nature" name="category" value="NATURE" v-on:click="setCategory('NATURE')">
      <label for="nature">Nature</label>
      
      <input type="radio" id="computers" name="category" value="COMPUTERS" v-on:click="setCategory('COMPUTERS')">
      <label for="computers">Computers</label>

      <!-- <input type="radio" id="math" name="category" value="MATH" v-on:click="setCategory('MATH')">
      <label for="math">Math</label> -->

      <input type="radio" id="mythology" name="category" value="MYTHOLOGY" v-on:click="setCategory('MYTHOLOGY')">
      <label for="mythology">Mythology</label>

      <input type="radio" id="sports" name="category" value="SPORTS" v-on:click="setCategory('SPORTS')">
      <label for="sports">Sports</label>

      <button id="niceButton" type="submit">Save</button>
      <button id="niceButton" v-on:click.prevent="cancelDeck" type="cancel">Cancel</button>

      </form> 
    
    </div>

  </div>
</template>

<script>
import DeckService from "../services/DeckService";
export default {
  props: {
    deckId: {}
  },
  created(){
    if(this.deckId != 0){
      DeckService.getDeckById(this.deckId)
        .then( response => {
          this.deck = response.data;
        })
        .catch(error => {
          if(error.response && error.response.status == 404){
            alert("Could not find deck");
          }
          this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
        });
    }
  },
  data() {
    return {
      deck: {
        deckName: "",
        deckDescription: "",
        category:"",
        creatorId: this.$store.getters.user.id,
        accessible: true,
      },
    };
  },
  methods: {
    submitForm() {
      if(this.deckId == 0){
        this.isLoading = true;
        DeckService.addDeck(this.deck)
          .then((response) => {
            if (response.status === 200) {
              this.showAddDeck = false;
              this.newDeck = {
                deckName: "",
                deckDescription: "",
                creatorId: this.$store.getters.user.id,
                accessible: false,
              };
            this.$router.push({ name: 'Deck', params: { deckId: response.data.deckId } })
            }
          })
          .catch((error) => {
              console.log(error);
              this.isLoading = false;
          });
      } else {
         const newDeck = {
          deckId: this.deckId,
          deckName: this.deck.deckName,
          deckDescription: this.deck.deckDescription,
          creatorId: this.$store.getters.user.id,
          accessible: this.deck.accessible,
        };
        DeckService.updateDeck(newDeck)
          .then( response => {
            if (response.status === 200){
              this.$router.push({ name: 'Deck', params: { deckId: this.deckId } })
            }
          })
          .catch(error => {
            console.log(error);
          })
      }
    },

    setCategory(category){
      this.deck.category = category;
    },
    cancelDeck() {
        this.$router.push({ name: 'loggedInHome' });
    }
  },
};
</script>
#add-deck-form {

}

<style scoped>

.both-options{
  display: flex;
  align-items: center;
  justify-content: center;
}

.container3 {
  display: flex;
  justify-content: center;
  margin-top: 100px;
  background-color: #F8EDEB;
}

.container-random {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 100px;
  background-color: #F8EDEB;
}

#add-deck-form {
  display: flex;
  flex-direction: column;
  background-color: #FCD5CE;
  width: 500px;
  padding: 25px;
  border: 1px solid #FEC89A;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  border-radius: 5px;
  /* margin-right: 75px; */
  justify-content: space-evenly;
  height: 400px;
  font-weight: bold;
  
}

.form-control9 {
  height: 35px;
  width: 100%;
  margin-top: 10px;
  border-radius: 5px;
  border: none;

  -webkit-box-shadow:inset 0 0 3px #000;
       -moz-box-shadow:inset 0 0 3px #000;
            box-shadow:inset 0 0 3px #000;
}

#niceButton {
  height: 40px;
  border-radius: 5px;
  border: none;
  font-weight: bold;
  box-shadow: 0 4px 4px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  background-color: #f7c2b9;
  
  
}

#niceButton:hover {
  background-color: #FEC89A;
  opacity: 0.8;
  
  
}

input.checkbox {
  /* size: 20px; */
  /* height: 30px; */
  transform: scale(2);
  margin-left: 10px;
}
</style>