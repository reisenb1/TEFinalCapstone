<!-- <template>
<div>
    <div>
        <router-link :to="{name: 'AddCard'}">Add Card</router-link>
    </div>
    <cards-list></cards-list>
</div>
</template>

<script>
import CardsList from '../components/CardsList';


export default {
      components: {
    CardsList,

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
-->