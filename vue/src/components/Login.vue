<template>


<div>
<auth-service />
<h1>Emily's FlashCard</h1>
</div>





</template>

<script>
import AuthService from '../services/AuthService.vue'


export default {
    name: "auth-service",
    components: {
        AuthService
    }

}
</script>

<style>

</style>