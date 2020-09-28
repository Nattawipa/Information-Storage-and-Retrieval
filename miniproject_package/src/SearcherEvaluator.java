//Name: 
//Section: 
//ID: 

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class SearcherEvaluator {
	private List<Document> queries = null; // List of test queries. Each query can be treated as a Document object.
	private Map<Integer, Set<Integer>> answers = null; // Mapping between query ID and a set of relevant document IDs

	public List<Document> getQueries() {
		return queries;
	}

	public Map<Integer, Set<Integer>> getAnswers() {
		return answers;
	}

	/**
	 * Load queries into "queries" Load corresponding documents into "answers" Other
	 * initialization, depending on your design.
	 * 
	 * @param corpus
	 */
	public SearcherEvaluator(String corpus) {
		String queryFilename = corpus + "/queries.txt";
		String answerFilename = corpus + "/relevance.txt";

		// load queries. Treat each query as a document.
		this.queries = Searcher.parseDocumentFromFile(queryFilename);
		this.answers = new HashMap<Integer, Set<Integer>>();
		// load answers
		try {
			List<String> lines = FileUtils.readLines(new File(answerFilename), "UTF-8");
			for (String line : lines) {
				line = line.trim();
				if (line.isEmpty())
					continue;
				String[] parts = line.split("\\t");
				Integer qid = Integer.parseInt(parts[0]);
				String[] docIDs = parts[1].trim().split("\\s+");
				Set<Integer> relDocIDs = new HashSet<Integer>();
				for (String docID : docIDs) {
					relDocIDs.add(Integer.parseInt(docID));
				}
				this.answers.put(qid, relDocIDs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns an array of 3 numbers: precision, recall, F1, computed from the top
	 * *k* search results returned from *searcher* for *query*
	 * 
	 * @param query
	 * @param searcher
	 * @param k
	 * @return
	 */
	public double[] getQueryPRF(Document query, Searcher searcher, int k) {
		/*********************** YOUR CODE HERE *************************/
		double precision, recall, f1Score = 0;
		List<SearchResult> searchResult = searcher.search(query.getRawText(), k);
		Set<Integer> relevantId = getAnswers().get(query.getId());
		Set<Integer> relevantRetrievedId = getAnswers().get(query.getId());
		Set<Integer> retrievedId = new HashSet<Integer>();

		searchResult.forEach((data) -> {
			retrievedId.add(data.getDocument().getId());
		});

		retrievedId.removeAll(relevantId);
		int retrievedIdSize = retrievedId.size();
		int relevantIdSize = relevantId.size();
		precision = (retrievedIdSize * 1.0) / (retrievedIdSize * 1.0);
		recall = (relevantIdSize * 1.0) / (relevantIdSize * 1.0);
		f1Score = 2.0 * ((precision * recall) / (precision + recall));

		if (Double.isNaN(f1Score)) {
			f1Score = 0;
		}
		double result[] = { precision, recall, f1Score };
		return result;
		/****************************************************************/
	}

	/**
	 * Test all the queries in *queries*, from the top *k* search results returned
	 * by *searcher* and take the average of the precision, recall, and F1.
	 * 
	 * @param searcher
	 * @param k
	 * @return
	 */
	public double[] getAveragePRF(Searcher searcher, int k) {
		/*********************** YOUR CODE HERE *************************/
		double avgPrecision = 0, avgRecall = 0, avgF1Score = 0;
		for (Document data : this.queries) {
			Document Q = data;
			double[] results = getQueryPRF(Q, searcher, k);
			avgPrecision += results[0];
			avgRecall += results[1];
			avgF1Score += results[2];
		}
		int queriesSize = this.queries.size();
		avgPrecision = avgPrecision / queriesSize;
		avgRecall = avgRecall / queriesSize;
		avgF1Score = avgF1Score / queriesSize;

		if (Double.isNaN(avgF1Score)) {
			avgF1Score = 0;
		}

		double[] finalResult = { avgPrecision, avgRecall, avgF1Score };

		return finalResult;
		/****************************************************************/
	}
}
