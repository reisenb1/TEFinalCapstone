<template>
  <div>
  <div class="card" v-for="card in cards" v-bind:key="card.cardId" >
    <div>Front: {{card.front}}</div>
    <div>Back: {{card.back}}</div>
    <div>
      <router-link :to="{name: 'EditCard', params:{cardId:card.cardId}}">Edit</router-link>
    </div>
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

</style>