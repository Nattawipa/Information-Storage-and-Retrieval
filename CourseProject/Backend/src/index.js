const express = require("express");
const dotenv = require("dotenv");
const app = express();
const cors = require("cors");
const bodyParse = require("body-parser");
const search = require('./routes/search/index')
dotenv.config();
app.use(
  express.urlencoded({
    extended: true,
  })
);
app.use(cors());
app.use(bodyParse.json());
app.use("/",search);
app.listen(process.env.PORT, () => {
  console.log(`http://localhost:${process.env.PORT}`);
});