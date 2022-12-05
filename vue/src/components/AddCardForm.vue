<template>
  <div>
    <form v-on:submit.prevent="submitForm" class="addCard">
        
        <div>
            <label for="front">Front:</label>
            <input type="text" name="front" v-model="card.front"/>
        </div>

         <div>
            <label for="back">Back:</label>
            <input type="text" name="back" v-model="card.back"/>
        </div>

        <button class="btn btn-submit">Submit</button>
        
        <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>

    </form>
  </div>
</template>

<script>
import CardService from "../services/CardService";
export default {

    data(){
        return {
            card: {
                front:'',
                back: '',
            }
        }
    },

    methods: {
        submitForm() {
            const newCard = {
                deckId: this.$route.params.deckId,
                front: this.card.front,
                back: this.card.back
            };

            CardService.addCard(newCard)
            .then(response => {
                if(response.status === 200) {
                    location.reload();
                }
            }) 
            .catch(error => {
                console.log(error)
            })
            //catch error 
        },

        cancelForm() {
            location.reload();
        },

        

    }

}
</script>

<style>

</style>