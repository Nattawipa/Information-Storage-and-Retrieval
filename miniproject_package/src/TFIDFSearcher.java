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
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/

		List<String> queryWords = super.tokenize(queryString);
		List<SearchResult> result = new ArrayList<SearchResult>();
		HashSet<String> tokenSet = new HashSet<>(queryWords);
		if (queryString.isEmpty()) {
			for (int i = 0; i < k; i++) {
				result.add(new SearchResult(documents.get(i), Double.NaN));
			}
			return result;
		}
		for (int document = 0; document < docSize; document++) {
			HashMap<String, Double> termFreqTF = new HashMap<String, Double>();
			List<String> wordsEachDocument = this.documents.get(document).getTokens();
			double queryTf, documentTf, tfidf, dotProductResult = 0, vectorQuery = 0, vertorDocument = 0;
			tokenSet.addAll(this.documents.get(document).getTokens());

			for (String token : tokenSet) {
				int documentFrequency = Collections.frequency(wordsEachDocument, token);
				int queryFrequency = Collections.frequency(queryWords, token);

				documentTf = checkCountWord(documentFrequency);
				queryTf = checkCountWord(queryFrequency);

				dotProductResult += (queryTf * allIdf.get(token)) * (documentTf * allIdf.get(token));
				vectorQuery += Math.pow(queryTf * allIdf.get(token), 2.0);
				vertorDocument += Math.pow(documentTf * allIdf.get(token), 2.0);
			}
			double SqrtQM = Math.sqrt(vectorQuery) * Math.sqrt(vertorDocument);
			double score = dotProductResult / SqrtQM;
			result.add(new SearchResult(this.documents.get(document), score));
		}
		Collections.sort(result);
		return result.subList(0, k);
		/***********************************************/
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
