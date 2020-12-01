<template>
  <div class="card">
    <div class="text">
      <div><b>Name :</b> {{ name }}</div>
      <div><b>description:</b> {{ description }}</div>
      <br />
      <div>
        <b>Ingredient:</b
        ><span> {{ ingredients.slice(1, ingredients.length - 1) }}</span>
      </div>
      <div><b>numberOfIngredients:</b> {{ numberOfIngredients }}</div>
      <div><b>nuumber_of_steps:</b> {{ nuumber_of_steps }}</div>
      <div><b>steps:</b> {{ steps.slice(1, steps.length - 1) }}</div>
    </div>
  </div>
</template>
<style scoped>
.text {
  padding: 20px 50px;
  word-break: break-all;
}
.card {
  margin: 20px;
  border-radius: 4px;
  background-color: rgb(238, 145, 169);
}
</style>
<script>
export default {
  data() {
    return {
      count: 0,
    };
  },
  props: {
    name: String,
    description: String,
    ingredients: String,
    numberOfIngredients: Number,
    nuumber_of_steps: Number,
    steps: String,
    color: String,
  },
  methods: {
    countfunc: function() {
      return this.count++;
    },
  },
};
</script>
