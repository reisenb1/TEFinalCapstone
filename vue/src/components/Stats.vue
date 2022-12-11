<template>
  <div class="container-stats">
    <div class="card-study-session">
      <h3>Deck ID</h3>
      <h3>Number Correct</h3>
      <h3>Number of Green</h3>
      <h3>Number of Yellow</h3>
      <h3>Number of Red</h3>
      <h3>Confidence %</h3>
      <h3>Correct(%)</h3>

    </div>


    <div class="cards-study-session" v-for="session in studySessions" v-bind:key="session.studySessionId">
      <div id="number-of-cards">
        {{session.numberOfCards}}
      </div>

      <div id="number-correct">
        {{session.numberCorrect}}
      </div>

      <div id="number-of-green">
        {{session.numberGreen}}
      </div>

      <div id="number-of-yellow">
        {{session.numberYellow}}
      </div>
      
      <div id="number-of-red">
        {{session.numberRed}}
      </div>

      <div id="confidence-percent">
        {{session.confidencePercent}}%
      </div>

      <div id="percent-correct">
        {{session.correctPercent}}%
      </div>

    </div>


    <div class="cards-study-session-bottom">


    </div>
  </div>
</template>

<script>
import StudySessionService from "../services/StudySessionService";

export default {
  data() {
    return {
      isLoading: true,
      studySessions: [],
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
  },
};
</script>


<style>
.container-stats {
  /* background-color: rgb(90, 90, 226); */
  height: 50%;
  margin: 10%;
  
}

.cards-study-session {
  display: flex;
  height: 100%;
  width: 90%;
  justify-content: space-evenly;
  /* margin: 10%; */
  margin-top: 0%;
  border: 2px solid black;
  /* border-bottom: 1px solid black; */
  /* color: white; */
  background-color: rgba(255, 255, 255, 0.541);
  border-top: none;
  font-weight: bold;
  align-items: center;
  padding: 5%;
  
}

.cards-study-session-bottom {
  height: 200px;
}

.card-study-session {
  display: flex;
  justify-content: space-evenly;
  background-color: #F9DCC4;
  border: 2px solid black;
  opacity: 0.8;

}

#number-of-cards {
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
}
</style>