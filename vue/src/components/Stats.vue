<template>
  <study-session-service></study-session-service>
</template>

<script>
import studySessionService from "../services/StudySessionService";

export default {
    data() {
    return {
      isLoading: true,
      studySessions: [],
      
    };
  },
  components: {
    studySessionService,
  },

  methods: {
    getAllSessions() {
      studySessionService
        .getStudySessions(this.$store.getters.user.id)
        .then((response) => {
          this.getStudySessions = response.data;
          this.isLoading = false;
        })
        .catch((error) => {
          if (error.response && error.response.status === 404) {
            alert("No Sessions Available");
            this.$router.push({ name: "loggedInHome" });
          }
        });
    },
    getSession() {
        studySessionService.getStudySessionById(this.getSessionId).then(response => {
            this.getStudySessionById = response.data;
        }).catch((error) => {
            if (error.response && error.response.status === 404) {
                alert("No sessions available")
            }
        })
    }
  },
  cancelSession() {
      this.$router.push({ name: "LoggedInHome", params: { studySessionId: this.studySessionId } });
    },
  created() {
    if (this.cardId != 0) {
      studySessionService.getStudySessionById(this.studySession)
        .then((response) => {
          this.studySession = response.data;
        })
        .catch((error) => {
          if (error.response && error.response.status == 404) {
            alert("Could not find session");
          }
          this.$router.push({ name: "LoggedInHome", params: { studySessionId: this.studySessionId } });
        });
    }
  }
}
</script>


<style>
</style>