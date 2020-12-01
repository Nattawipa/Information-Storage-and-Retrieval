<template>
  <div>
    <div class="flex">
      <div class="cnk">
        CNK SEARCH
      </div>
      <input v-model="name" type="text" placeholder="search..." />
      <button @click="search()" class="btn">Search</button>
    </div>
    <card
      v-for="({ _source }, index) in this.data"
      :key="`-${index}`"
      :name="_source.name"
      :description="_source.description"
      :ingredients="_source.ingredients"
      :numberOfIngredients="_source.number_of_ingredients"
      :nuumber_of_steps="_source.nuumber_of_steps"
      :steps="_source.steps"
    />
  </div>
</template>

<script>
import axios from "axios";
import card from "./components/card.vue";
export default {
  data() {
    return {
      name: "",
      data: [],
    };
  },
  name: "App",
  components: {
    card,
  },
  methods: {
    search: async function() {
      const { data } = await axios.post("http://localhost:8081", {
        name: this.name,
      });
      this.data = data.body.hits.hits;
      console.log(this.data);
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=B612&display=swap");
.cnk {
  font-size: 60px;
  text-align: center;
  width: 100vw;
}
* {
  font-family: "B612", sans-serif;
  box-shadow: none;
}
.flex {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}
body {
  background-color: rgb(231, 165, 209);
  margin: 0;
}
input {
  width: calc(60% + 20px);
  padding: 10px;
  font-size: 20px;
  border-radius: 9px;
  background-color: #fffefe;
  color: rgb(2, 13, 24);
  border: solid 1px #6c6969;
}
.btn {
  margin-left: 10px;
  border-radius: 5px;
  padding: 20px;
  border: none;
  background-color: rgba(233, 219, 25, 0.932);
  color: rgb(12, 12, 12);
  font-weight: 500;
}
</style>
