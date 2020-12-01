<template>
  <div class="card" :style="{backgroundColor:color}">
    <div class="text">
      <div><b>Name :</b> {{name}}</div>
      <div><b>description:</b> {{description}}</div>
      <br>
      <div><b>Ingredient:</b><span v-for="(data,index) in ingredients "
          :key="`-${index}`">{{data}}</span>
      </div>
      <div><b>numberOfIngredients:</b> {{numberOfIngredients}}</div>
      <div><b>nuumber_of_steps:</b> {{nuumber_of_steps}}</div>
      <div><b>steps:</b> {{steps}}</div>
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
  props: {
    name:String,
    description: String,
    ingredients:Array,
    numberOfIngredients:Number,
    nuumber_of_steps:Number,
    steps:Array,
    color:String
  },
  methods: {
    
  }
};
</script>