<template>
<div id="main-grid" class="container">
    <nav id="topBar">























    </nav>






  <div id="register" class="text-center2">
    <form class="form-register" @submit.prevent="register">
      <h2 class="h3 mb-3 font-weight-normal">Create Account</h2>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button id="create-button" class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>

    <div id="register-about">
        <h1>Yer Getting There!</h1>
        <h2>Create an account, and enjoy<br> all the benefits
        YEGET has to offer!</h2>

      </div>
  </div>
  
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
 /* .form-register {
   display: flex;
   justify-content: flex-end;
   align-items: baseline;
   height: 35px;
 } */

 /* .text-center > .form-register {
   display: flex;
   justify-content: flex-end;
 } */

 .form-control {
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border: none;
  /* border-bottom: 1px solid black; */
  border-radius: 5px;
  box-shadow: inset 0 0 5px #000000;
}

 .container {
     background-image: url("../images/study-photo.jpg");
 }

 #main-grid {
  display: grid;
  grid-template-areas:
    "topBar    topBar"
    "register  register-about"
    ;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 3fr;

  align-items: center;

  row-gap: 20px;
  column-gap: 50px;
}


 /* #login { 
    background-color: rgb(122, 120, 120, 0.7);
    height: 60px;
    padding: 10px;

}  */

 .text-center2 .form-register {
  display: flex;
  flex-direction: column;
  width: 500px;
    background-color: #FFB5A7;
  border: 15px solid #F8EDEB;
  padding: 25px;
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);

  border-radius: 5px;
  margin-right: 50px;
}

#topBar {
  grid-area: topBar;
}

#register {
  grid-area: register;
  margin-top: 20px;
}

#register-about {
  grid-area: register-about;
  color: white;
  margin-left: 75px;
}

.text-center2 {
  display: flex;
  
  height: 500px;
}

#create-button {
 background-color: #F9DCC4;
  color: black;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
  transition-duration: 1s;

}

#create-button:hover {
   background-color: #F9DCC4;
  opacity: 0.6;
}
</style>
