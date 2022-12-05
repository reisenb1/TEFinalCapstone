<template>
  <div id="login" class="text-center">
    <form class="form-sign-in" @submit.prevent="login">
      <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>

    <div id="welcome">
      <h1>Welcome!</h1>
       <!-- <img src="images/study-photo.jpg" alt="study"> -->

    <p>The greatest place for your study needs, Emily's flashcards gives you the best opportunity to hone in on and retain the information that you need.</p>
    </div>


    <div id="categories">
      <h1>Categories</h1>
      <p>Here is more information</p>
    </div>
      

    <div id="about">
      <h1>About</h1>
      <p>Here is more information</p>



      <a id="top" href="#">Back to top</a>
      

    </div> 


    



    
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style>
  html {
    scroll-behavior: smooth;
  }

  /* #login {
    background-color: rgb(122, 120, 120, 0.7);
    height: 60px;
    padding: 10px;

  } */

  #id {
    display: flexbox;
    width: 100%;
  }


 .form-sign-in {
   display: flex;
   justify-content: right;
   align-items: baseline;
   height: 35px;
 }

 /* need to figure out how to add modal form to login  */
 /* .sr-only  {
   display: flex;
   justify-items: center;
   background-size: auto;
   align-items: center;
   
   
 }

 .form-sign-in, .sr-only {
   background-color: red;
   margin: 5px;
   display: flexbox;
   align-items:center;
   width: 300px;
   padding: 12px 20px;
   box-sizing: border-box;
   
 } */

  #id {
    display: flex;
    justify-content: flex-end;
  }

  #welcome {
    display: flex;
    align-items: center;
    flex-direction: column;
  
    padding-top: 50px;
    color: white;
    height: 750px;
    overflow-y: auto;

  }

  #categories {
    display: flex;
    align-items: center;
    flex-direction: column;
    color: white;
    scroll-behavior: smooth;
    background-color: rgba(110, 101, 238, 0.89);
    height: 750px;
    width: 100%;
  }

  #about {
    display: flex;
    color: white;
    background-color: rgb(235, 83, 109, 0.89);
    height: 250px;
    align-items: center;
    flex-direction: column;
  }

  #about p {
    display: flex;
    align-items: baseline;
    justify-content: baseline;
  }

  #top {
    display: flex;
    justify-content: center;
    color: white;
    text-decoration: none;
  }

  /* width */
::-webkit-scrollbar {
  width: 15px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey;
  border-radius: 10px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: rgb(32, 189, 149, 0.7);
  border-radius: 15px;
}
</style>