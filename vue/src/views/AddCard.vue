<template>
  <div>
      <h1>Add New Card</h1>
      <card-form v-bind:cardId=0></card-form>
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