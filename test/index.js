const express = require("express");
const app = express();
const cors = require("cors");
const bodyParse = require("body-parser");
const searchByName = require("./search")
const port = 3000
app.use(
  express.urlencoded({
    extended: true,
  })
);
app.use(cors());
app.use(bodyParse.json());
app.get('/', async(req, res) => {
    console.log(req.query)
    const {q} = req.query
    const result = await searchByName(q)
    const sources = result.body.hits.hits.map(doc => {
        return doc._source
    })
  res.send(sources)
})

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})