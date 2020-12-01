const elasticSearch = require('../../elasticsearch')

const searchByName = async(query) => {
    const result = await elasticSearch.search({
        index: 'cooking',
        body: {
            query: {
              match: query
            }
          }
      }, {
        ignore: [404],
        maxRetries: 3
      })
      return result
}

module.exports = searchByName