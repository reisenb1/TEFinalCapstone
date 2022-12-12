<template>
  <div>

      <div class="header-edit"></div>
      <h1>Edit Card</h1>
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
.header-edit {
    height: 100px;
}

</style>