<template>
<cards-list></cards-list>
  
</template>

<script>
import CardsList from '../components/CardsList';

export default {
      components: {
    CardsList
  },
  data() {
    return {
     deckId: 0
    };
  },
  created() {
    this.deckId = parseInt(this.$route.params.id);
    this.$store.commit("SET_ACTIVE_DECK", this.deckId);
  }

}
</script>

<style>

</style>