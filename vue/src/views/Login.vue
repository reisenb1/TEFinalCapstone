<template>
  <div id="main-grid" class="container">
    <nav id="topBar">
      <div class="header">
        <!-- <h2>Emily's FlashCards</h2> -->
      </div>

      <router-link
        style="text-decoration: none; color: blue"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >
      &nbsp;|&nbsp;<a
        style="text-decoration: none; color: blue"
        href="#categories"
        >Categories</a
      >
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">Categories</router-link> -->
      &nbsp;|&nbsp;<a style="text-decoration: none; color: blue" href="#about"
        >About</a
      >
      <!-- &nbsp;|&nbsp;<router-link style="text-decoration: none; color: white" v-bind:to="{ name: '' }">About</router-link> -->
      &nbsp;|&nbsp;
      <div id="registerLogin">
        <router-link
          v-bind:to="{ name: 'login' }"
          v-show="$store.state.token == ''"
          >Sign In</router-link
        >
      </div>

      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </nav>
    <div id="login" class="text-center">
      <form class="form-sign-in2" @submit.prevent="login">
        <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
        <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
          Invalid username and password!
        </div>
        <div
          class="alert alert-success"
          role="alert"
          v-if="this.$route.query.registration"
        >
          Thank you for registering, please sign in.
        </div>
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control2"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control2"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <router-link :to="{ name: 'register' }">Need an account?</router-link>
        <button id="submit" type="submit">Sign in</button>
      </form>

      <div id="login-about">
        <h1>Ye Got It!</h1>
        <h2>Sign in and let's get started!</h2>
      </div>

      <!-- <div id="main-grid">
        <div id="welcome">
          <h1>Welcome!</h1>

          <p>
            The greatest place for your study needs, Emily's flashcards gives
            you the best opportunity to hone in on and retain the information
            that you need.
          </p>
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
      </div> -->
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
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push({ name: "loggedInHome" });
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
html {
  scroll-behavior: smooth;
}

body {
}

.container {
  display: grid;
  background-image: url("../images/study-photo.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
}

#topBar {
  grid-area: topBar;
}

#login {
  grid-area: login;
  margin-top: 20px;
}

#login-about {
  grid-area: login-about;
  color: white;
  margin-left: 75px;
}

.text-center {
  display: flex;
  height: 500px;
}

#main-grid {
  display: grid;
  grid-template-areas:
    "topBar topBar"
    "login  login-about";
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

#id {
  display: flexbox;
  width: 100%;
}

#submit {
  background-color: #F9DCC4;
  color: black;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  transition-duration: 1s;
  border-radius: 5px;
}

#submit:hover {
  background-color: #F9DCC4;
  opacity: 0.6;
}

.form-control2 {
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  /* border-bottom: 1px solid black; */
  box-shadow: inset 0 0 5px #000000;
  border-radius: 5px;
}

.text-center .form-sign-in2 {
  display: flex;
  flex-direction: column;
  background-color: #FFB5A7;
  border: 15px solid #F8EDEB;
  width: 500px;
  padding: 25px;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  border-radius: 5px;
  margin-right: 75px;
}

/* need to figure out how to add modal form to login  */

#id {
  display: flex;
  justify-content: flex-end;
}

/* #welcome {
  display: flex;
  align-items: center;
  flex-direction: column;

  padding-top: 50px;
  color: white;
  height: 750px;
  overflow-y: auto;

  background-image: url("../images/study-photo.jpg");
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
  height: 500px;
  align-items: center;
  flex-direction: column;
  width: 100%;
} */

/* #about p {
  display: flex;
  align-items: baseline;
  justify-content: baseline;
} */

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
  background: rgba(255, 255, 255, 0.7);
  border-radius: 15px;
}
</style>