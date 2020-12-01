const express = require("express");
const search = express.Router();
const searchElastic  = require("../../services/search");

search.post("/", async (req, res) => {
    console.log(req.body)
    const recive = req.body;
    const data = await searchElastic(recive);
    res.status(200).send(data);
});

module.exports = search;