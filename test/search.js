const elasticSearch = require('./')

const searchByName = async(q) => {
    const result = await esclient.search({
        index: 'udemy',
        body: {
            query: {
              match: {
                "name": `${q}`
              }
            }
          }
      }, {
        ignore: [404],
        maxRetries: 3
      })
      return result
}

module.exports = searchByName