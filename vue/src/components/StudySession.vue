<template>
  <div id="study-session-component">
    <div class="header-session"></div>
    <h1>{{deck.deckName}} Study Session</h1>
    <div v-for="card in cards" v-bind:key="card.cardId">
      <div v-show="card.cardId == currentCardId">


          



        <div class="flip-card-container">
          <div class="flip-card-inner">
            <div class="card_face front--front" v-on:click="flipCard" v-show="showFront">
              
              {{ card.front }}
              
<!-- 
        <button id="card-front-study" v-on:click="flipCard" v-show="showFront">
          {{ card.front }}
        </button> -->
            </div>

            <div class="card_face back--back" v-show="showBack">
              {{ card.back }}
              
        <!-- <button id="card-back-study" v-show="showBack">{{ card.back }}</button> -->
            </div>

          </div>
        </div>





        

        <div id="candy" v-show="showCorrectIncorrectForm">
          <button id="correct" v-on:click.prevent="submitCorrectIncorrect('correct')">
            <img class="correctIncorrectImg" src="../images/green-checkmark.png" alt="checkmark">
            Correct
          </button>
          <button id="incorrect" v-on:click.prevent="submitCorrectIncorrect('incorrect')">
             <img class="correctIncorrectImg" src="../images/red-x.png" alt="x">
            Incorrect
          </button>
        </div>

        <div id="moreButtons" v-show="showConfidenceForm">
          <button id="nice" v-on:click.prevent="submitConfidence('green', card)">
            Yes I got this 100%
          </button>
          <button id="what" v-on:click.prevent="submitConfidence('yellow', card)">
            meh
          </button>
          <button id="nahSon" v-on:click.prevent="submitConfidence('red', card)">
            Yikes!!!
          </button>
        </div>
      </div>
    </div>

    <button id="cancelButton" v-on:click.prevent="goBackToDeckPage">Cancel Study Session</button>
  </div>
</template>

<script>
import StudySessionService from "../services/StudySessionService";
 import DeckService from "../services/DeckService";
import CardService from "../services/CardService";

export default {
  data() {
    return {
      deckId: this.$route.params.deckId,
      cards: [],
      deck: [],
      currentCardId: "",
      position: 0,
      showCorrectIncorrectForm: false,
      showConfidenceForm: false,
      cardCorrectIncorrect: "",
      studySession: {
        userId: this.$store.getters.user.id,
        deckId: this.deckId,
        numberOfCards: 0,
        numberCorrect: 0,
        numberGreen: 0,
        numberYellow: 0,
        numberRed: 0,
      },
      allCardsUpdated: true,
      // showFront: this.flipCard(),
      // showBack: this.flipCard(),
      showFront: true,
      showBack: true,
      flipCardCount: 0
      
    };
  },
  created() {
    this.getCards();
    this.getDecks();
    
  },
  methods: {
    getCards() {
      CardService.getCards(this.$route.params.deckId)
        .then((response) => {
          this.cards = response.data;
          this.currentCardId = this.cards[0].cardId;
          this.studySession.numberOfCards = this.cards.length;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Deck cards not available.");
            this.$router.push({ name: "loggedInHome" });
          }
        });
    },
    getDecks() {
      DeckService.getDeckById(this.$route.params.deckId)
        .then((response) => {
          this.deck = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("Deck cards not available.");
            this.$router.push({ name: "Home" });
          }
        });
    },

    submitCorrectIncorrect(result) {
      if (result == "correct") {
        this.studySession.numberCorrect = this.studySession.numberCorrect + 1;
      }
      this.showCorrectIncorrectForm = false;
      this.showConfidenceForm = true;
    },

    submitConfidence(result, currentCard) {
      let card = {
        cardId: this.currentCardId,
        deckId: this.deckId,
        front: currentCard.front,
        back: currentCard.back,
        userId: this.$store.getters.user.id,
        confidence: "",
      };

      if (result == "green") {
        this.studySession.numberGreen = this.studySession.numberGreen + 1;
        card.confidence = 2;
      } else if (result == "yellow") {
        this.studySession.numberYellow = this.studySession.numberYellow + 1;
        card.confidence = 1;
      } else {
        this.studySession.numberRed = this.studySession.numberRed + 1;
      }

      CardService.updateCard(card)
        .then((response) => {
          if (response.status == 200) {
            this.allCardsUpdated = true;
          }
        })
        .catch((error) => {
          console.log(error);
          this.allCardsUpdated = false;
        });

      this.showConfidenceForm = false;
      if (this.position == this.cards.length-1) {
        this.submitStudySession();
      } else {
        this.position = this.position + 1;
      }
      this.currentCardId = this.cards[this.position].cardId;
      // this.showFront = true;
      // this.showBack = false;
      this.flipCardCount = 0;

    },

    goBackToDeckPage() {
      this.$router.push({ name: "Deck", params: { deckId: this.deckId } });
    },

    // flipCard() {
    //   this.showCorrectIncorrectForm = true;
    //   this.showFront = false;
    //   this.showBack = true;
    // },

    submitStudySession(){
      this.studySession.deckId = this.deckId;
      StudySessionService.addStudySession(this.studySession)
      .then(response => {
        if(response.status == 200){
          this.$router.push({ name: "Results", params: { deckId: this.deckId, studySessionId: response.data.studySessionId } });
        }
      }).catch( error => {
        console.log(error);
        this.goBackToDeckPage;
      })
      
    },
    flipCard() {
    
    if (this.flipCardCount == 0) {
      this.showCorrectIncorrectForm = true;
    }
    
    this.flipCardCount = this.flipCardCount + 1;

    let cards = document.querySelectorAll('.flip-card-inner');

        [...cards].forEach((card)=>{
        card.addEventListener( 'click', function() {
        card.classList.toggle('is-flipped');
        this.showFront = false;
        this.showBack = false;
        });
      });
    }

    // flipBackToFront(){
    //   this.showFront=true;
    //   this.showBack = false;
    // }
  },
};
</script>

<style>

.correctIncorrectImg{
  height: 20px;
  margin-right: 10px;
}

#moreButtons {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

#nice {
   align-self: right;
  justify-self: right;
  background-color:rgb(0, 255, 0, 0.6);
  height: 30px;
  width: 150px;
  font-weight: bold;
  font-size: small;
  border: none;
  border-radius: 5px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

#nice:hover {
  background-color:rgb(0, 255, 0);
  opacity: 1;
}

#what {
   align-self: right;
  justify-self: right;
  background-color:rgba(255, 230, 0, 0.6);
  height: 30px;
  width: 150px;
  font-weight: bold;
  font-size:small;
  border: none;
  border-radius: 5px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

#what:hover {
  background-color:rgba(255, 230, 0);
  opacity: 1;
}

#nahSon {
   align-self: right;
  justify-self: right;
  background-color:rgba(255, 0, 0, 0.6);
  height: 30px;
  width: 150px;
  font-weight: bold;
  font-size:small;
  border: none;
  border-radius: 5px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

#nahSon:hover {
  background-color:rgba(255, 0, 0);
  opacity: 1;
}


#correct {
  display: flex;
  align-items: center;
  justify-content: center;
  /* background-color:rgb(0, 255, 0, 0.3); */
  height: 40px;
  width: 120px;
  font-weight: bold;
  font-size:medium;
  border: solid, 1px;
  border-color: rgb(0,255,0);
  border-radius: 5px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

#incorrect {
  display: flex;
  align-items: center;
  justify-content: center;
  /* background-color: rgb(255, 0, 0, 0.6); */
  height: 40px;
  width: 120px;
  font-weight: bold;
  font-size:medium;
  border: solid, 1px;
  border-color: rgb(255,0,0);
  border-radius: 5px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

#correct:hover { 
  background-color: #00b919;
  opacity: 1;
}

#incorrect:hover {
  background-color: rgb(255, 0, 0);
  opacity: 1;
}

#candy {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.header-session {
  margin-top: 100px;
  
}

#cancelButton{
  align-self: right;
  justify-self: right;
  background-color: #FFB5A7;
  height: 50px;
  width: 250px;
  margin-top: 20px;
  font-weight: bold;
  font-size:medium;
  border: none;
  border-radius: 5px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

#cancelButton:hover {
  background-color: #FFB5A7;
  opacity: 0.7;

}

#study-session-component{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.flip-card-container {

  display: inline-block;
  width: 650px;
  height: 400px;
  /* border: 1px solid #CCC; */
  margin: 40px 0;
  perspective: 600px;
  border-radius: 10px;
  /* box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); */

}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  cursor: pointer;
  transform-style: preserve-3d;
  transform-origin: center right;
  transition: transform 1s;
}

.flip-card-inner.is-flipped {
  transform: translateX(-100%) rotateY(-180deg);
}

.card_face {
  

  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  width: 100%;
  height: 100%;
  /* line-height: 260px; */
  color: white;
  text-align: center;

  font-weight: bold;
  font-size: 30px;
  backface-visibility: hidden;
}

.front--front {
  background: #FFB5A7;
  border-radius: 10px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.back--back {
  background: #FEC89A;
  transform: rotateY(180deg);
  border-radius: 10px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

/* #card-front-study {
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 800px;
  padding: 25px;
  border-style: solid;
  border-color: rgb(223, 223, 223);
  height: 400px;
  /* box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); */
  /* align-content: center;
  justify-items: center;
  margin: 50px;
}

#card-back-study {
  display: flex;
  flex-direction: column;
  background-color: white;
  width: 800px;
  padding: 25px;
  border-style: solid;
  border-color: rgb(223, 223, 223);
  height: 400px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin: 50px;
 
} */ 
</style>