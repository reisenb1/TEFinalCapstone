<template>
<div>
<cards-list></cards-list>
<add-card-form></add-card-form>
</div>
</template>

<script>
import CardsList from '../components/CardsList';
import AddCardForm from '../components/AddCardForm';

export default {
      components: {
    CardsList,
    AddCardForm
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