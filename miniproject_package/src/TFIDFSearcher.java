//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TFIDFSearcher extends Searcher {
	private int docSize = this.documents.size();
	private HashSet<String> setIdf;
	private HashMap<String, Double> allIdf = new HashMap<String, Double>();

	private HashMap<Document, HashMap<String, Double>> vectorDocument = new HashMap<>();

	public TFIDFSearcher(String docFilename) {
		/************* YOUR CODE HERE ******************/
		super(docFilename);
		for (int i = 0; i < docSize; i++) {
			setIdf = new HashSet<>(this.documents.get(i).getTokens());
			for (String a : setIdf) {
				if (!allIdf.containsKey(a)) {
					allIdf.put(a, 0.0);
				}
				allIdf.replace(a, allIdf.get(a) + 1);
			}
		}

		for (String a : allIdf.keySet()) {
			allIdf.replace(a, Math.log10(1 + (docSize / allIdf.get(a))));
		}

		for (Document x : this.documents) {
			HashMap<String, Double> data = new HashMap<>();
			setIdf = new HashSet<>(x.getTokens());
			for (String token : setIdf) {
				int tfFrequency = Collections.frequency(x.getTokens(), token);
				double idf = allIdf.get(token);
				double tf = checkCountWord(tfFrequency);
				double tfIdf = idf * tf;
				data.put(token, tfIdf);
			}
			vectorDocument.put(x, data);
		}

		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/

		List<String> queryWords = super.tokenize(queryString);
		List<SearchResult> result = new ArrayList<SearchResult>();
		HashSet<String> tokenSet = new HashSet<String>(queryWords);
		if (queryString.isEmpty()) {
			for (int i = 0; i < k; i++) {
				result.add(new SearchResult(documents.get(i), Double.NaN));
			}
			return result;
		}

		for (Document doc : this.documents) {
			double queryTf, dotProductResult = 0, vectorQuery = 0, vertorDocument = 0;
			tokenSet = new HashSet<String>(queryWords);
			tokenSet.addAll(doc.getTokens());

			for (String word : tokenSet) {
				int queryFrequency = Collections.frequency(queryWords, word);
				Double docTfidf = vectorDocument.get(doc).get(word);
				if (docTfidf == null) {
					docTfidf = 0.0;
				}
				queryTf = checkCountWord(queryFrequency);
				Double queryidf = allIdf.get(word);
				if (queryidf == null) {
					queryidf = 0.0;
				}
				dotProductResult += (queryTf * queryidf) * docTfidf;
				vectorQuery += Math.pow(queryTf * queryidf, 2.0);
				vertorDocument += Math.pow(docTfidf, 2.0);
			}
			double SqrtQM = Math.sqrt(vectorQuery) * Math.sqrt(vertorDocument);
			double score = dotProductResult / SqrtQM;
			result.add(new SearchResult(doc, score));
		}
		Collections.sort(result, SearchResult::compareTo);
		return result.subList(0, k);/***********************************************/
	}

	public double checkCountWord(int countWord) {
		double result;
		if (countWord != 0) {
			result = 1 + Math.log10(countWord);
		} else {
			result = 0;
		}
		return result;
	}
}
