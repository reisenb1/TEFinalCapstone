<template>
  <div>
      <hi>Edit Card</hi>
      <card-form v-bind:cardId="parseInt($route.params.cardId)"></card-form>
  </div>
</template>

<script>
import CardForm from "../components/CardForm.vue";
export default {
    components: {
        CardForm
    }
}
</script>

<style>

</style>