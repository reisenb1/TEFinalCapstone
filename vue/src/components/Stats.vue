<template>
  
  <div>

  <div class="no-stats" v-show="studySessions.length == 0">

      <div class="no-stats-top">
      <img class="img-new-user-arrow" src="../images/arrow.png" alt="arrow">
     
        <h2 class="no-stats-directions">Welcome to YEGET! Ready to start studying? Start by adding a new deck on the left!
        </h2>

        <!-- <img class="no-stats-top-img" src="../images/cards.gif" alt="cards"> -->

        </div>

        <div class="did-you-know">

            <div>
            <h3 class="stat-dyk">Did you know?</h3>
            <p class="stat-p">By spending just <b>1 hour</b> a day studying on YEGET, you can increase your grades by a whole letter grade!</p>
            </div>

            <div>
                <h3 class="stat-dyk">Did you know?</h3>
                <p class="stat-p"><b>97% of users</b> would recommend YEGET to their friends! The other 3% don't know what they are talking about.</p>
            </div>

            <div>
                <h3 class="stat-dyk">Did you know?</h3>
                <p class="stat-p">YEGET was recognized as the <b>#1 flash card app</b> in the US according to the most popular flash card rater!</p>
            </div>

        </div>
      
  </div>
  
  <div class="container-stats" v-show="studySessions.length !== 0">
    <div class="card-study-session">
      
      <!-- <h3>Deck ID</h3>
      <h3>Number Correct</h3>
      <h3>Number of Green</h3>
      <h3>Number of Yellow</h3>
      <h3>Number of Red</h3>
      <h3>Confidence %</h3>
      <h3>Correct(%)</h3> -->
    
      
      <h3 id='deck-name-label'>Deck Name</h3>
      <h3 id='unfamiliar-terms'>Unfamiliar Terms</h3>
      <h3 id='still-learning'>Still Learning</h3>
      <h3 id='mastered-terms'>Mastered Terms</h3>
      <h3 id='cards-correct'>Cards Correct</h3>
      <h3 id='percent-correct-label'>Percent Correct</h3>
      <h3 id='confidence-percent-label'>Confidence Percentage</h3>
    </div>


    <div class="cards-study-session" v-for="session in studySessions" v-bind:key="session.studySessionId">
      

      <div id="deck-name">

         
          <img class="img-deck-stat" src="../images/cards.gif" alt="cards">
           <router-link class="deck-name-link" :to="{ name: 'Deck', params: { deckId: session.deckId } }">
          {{deckNames[studySessions.indexOf(session)]}}
          </router-link>
      </div>
    
      <div id="number-of-red">
        {{session.numberRed}}
        <img class="img-stat" src="../images/red-circle.png" alt="green">
      </div>

      <div id="number-of-yellow">
        {{session.numberYellow}}
        <img class="img-stat" src="../images/yellow-circle.png" alt="yellow">
      </div>

      <div id="number-of-green">
        {{session.numberGreen}}
        <img class="img-stat" src="../images/green-circle.png" alt="green">
      </div>
      

      <div id="number-correct">
        {{session.numberCorrect}}/{{session.numberOfCards}}
      </div>

      <div id="percent-correct">
        {{session.correctPercent}}%
        <img v-show="session.correctPercent == 100" class="img-star-stat" src="../images/star.png" alt="star">
      </div>

      <div id="confidence-percent">
        {{session.confidencePercent}}%
        <img v-show="session.confidencePercent == 100" class="img-star-stat" src="../images/star.png" alt="star">
      </div>

      

    </div>


    <div class="cards-study-session-bottom">


    </div>
  </div>
  </div>

</template>

<script>
import StudySessionService from "../services/StudySessionService";
import DeckService from "../services/DeckService";

export default {
  data() {
    return {
      isLoading: true,
      studySessions: [],  
      deckNames: [],
      currentDeckName: ""
    };
  },
  created() {
    this.getAllSessions();
   
    
  },
  components: {
  
  },
  methods: {
    getAllSessions() {
      StudySessionService
        .getStudySessions(this.$store.getters.user.id)
        .then((response) => {
          this.studySessions = response.data;
          this.isLoading = false;
          
          this.reverseOrderSessions();
          this.setDeckNames();
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("No Sessions Available");
            this.$router.push({ name: "loggedInHome" });
          }
        });
    },
    //   getSession() {
    //       studySessionService.getStudySessionById(this.getSessionId).then(response => {
    //           this.getStudySessionById = response.data;
    //       }).catch((error) => {
    //           if (error.response && error.response.status === 404) {
    //               alert("No sessions available")
    //           }
    //       })
    //   }

    cancelSession() {
      this.$router.push({
        name: "LoggedInHome",
        params: { studySessionId: this.studySessionId },
      });
    },

    reverseOrderSessions(){
        this.studySessions.reverse();
    },

    setDeckNames(){
        for(let i=0; i<this.studySessions.length; i++){
            DeckService.getDeckById(this.studySessions[i].deckId)
            .then(response => {
                // this.studySessions[i].push(response.data.deckName);
                this.deckNames.push(response.data.deckName)
                // this.deckNames.push(response.data)
            })
            .catch(error => {
                console.log(error);
                this.deckNames[i] = '';
            })
        }
        this.deckNames.reverse();
    }
  },
};
</script>


<style>

.stat-dyk{
    text-align: center;
    text-decoration: underline;
}

.stat-p{
    padding: 10%;
    padding-top: 3%;
    text-align: center;
}

.no-stats-top{
    display: flex;
    margin-bottom: 10%;
}

/* .no-stats-top-img{
    size: 5%;
} */

.did-you-know{
    display: flex;
    justify-content: center;
}


.no-stats-directions{
    display: flex;
    justify-content: center;
    align-items: center;
    text-emphasis: bold;
}

.no-stats{
    margin: 10%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.img-new-user-arrow{
    transform: scaleX(-1);
    width: 30%;
    margin-right: 50px;
}

.deck-name-link{
    color: black
}


.img-stat{
    height: 20px;
    margin-left: 5px;
}

.img-star-stat{
    height: 40px;
    margin-left: 10px;
}

.container-stats {
  /* background-color: rgb(90, 90, 226); */
  height: 50%;
  margin-left: 10%;
  margin-top: 10%;
  /* display: grid; */
  /* grid-template-areas: 
    "card-study-session"
    "cards-study-session"; */
  
}

.cards-study-session {

  display: grid;
  grid-template-areas: 
    "deck-name number-of-red number-of-yellow number-of-green number-correct percent-correct confidence-percent";

  grid-template-columns: 3fr 1fr 1fr 1fr 2fr 2fr 2fr;

  grid-template-rows: row;


  height: 100%;
  width: 100%;
  justify-content: space-evenly;
  /* margin: 10%; */
  margin-top: 0%;
  /* border-bottom: 1px solid black; */
  /* color: white; */
  background-color: rgba(255, 255, 255, 0.541);
  border-bottom: 1px solid black;
  font-weight: bold;
  align-items: center;
  padding-top: 4%;
  padding-bottom: 4%;
  
}

.cards-study-session-bottom {
  height: 200px;
}

.card-study-session {
  display: grid;
  grid-template-areas: 
    "deck-name-label unfamiliar-terms still-learning mastered-terms cards-correct percent-correct-label confidence-percent-label";

  grid-template-columns: 3fr 1fr 1fr 1fr 2fr 2fr 2fr;



  justify-content: space-evenly;
  background-color: #F9DCC4;
  border: 2px solid black;
  opacity: 0.8;

}

#deck-name {
    grid-area: deck-name;
    display: flex;
    justify-content: center;
    align-items: center;
    
}

#number-of-red{
    grid-area: number-of-red;
    display: flex;
    justify-content: center;
    align-items: center;
}

#number-of-yellow{
    grid-area: number-of-yellow;
    display: flex;
    justify-content: center;
    align-items: center;
}

#number-of-green{
    grid-area: number-of-green;
    display: flex;
    justify-content: center;
    align-items: center
}

#number-correct{
    grid-area: number-correct;
    display: flex;
    justify-content: center;
}

#percent-correct{
    grid-area: percent-correct;
    display: flex;
    justify-content: center;
    align-items: center;
}

#confidence-percent{
    grid-area: confidence-percent;
    display: flex;
    justify-content: center;
    align-items: center;
}







#deck-name-label {
    grid-area: deck-name-label;
    /* display: flex;
    justify-content: center; */
    text-align: center;
    
}

#unfamiliar-terms{
    grid-area: unfamiliar-terms;
    /* display: flex;
    justify-content: center;
    align-items: center; */
    text-align: center;
}

#still-learning{
    grid-area:still-learning;
    /* display: flex;
    justify-content: center;
    align-items: center; */
    text-align: center;
}

#mastered-terms{
    grid-area: mastered-terms;
    /* display: flex;
    justify-content: center;
    align-items: center; */
    text-align: center;
}

#cards-correct{
    grid-area: cards-correct;
    /* display: flex;
    justify-content: center; */
    text-align: center;
}

#percent-correct-label{
    grid-area: percent-correct-label;
    /* display: flex;
    justify-content: center; */
    text-align: center;
}

#confidence-percent-label{
    grid-area: confidence-percent-label;
    /* display: flex;
    justify-content: center; */
    text-align: center;
}

.img-deck-stat{
    height: 50px;
    display: flex;
    align-items: center;
}



/* #number-of-cards {
  position: relative;
  right: 60px;
}

#number-correct {
  position: relative;
  right: 50px;
}

#number-of-green {
  position: relative;
  right: 0px;
}

#number-of-yellow {
  position: relative;
  right: -40px;
}

#number-of-red {
  position: relative;
  left: 80px;
}

#confidence-percent {
  position: relative;
  left: 90px;
}

#percent-correct {
  position: relative;
  left: 60px;
} */
</style>