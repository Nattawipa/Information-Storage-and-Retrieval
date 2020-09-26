//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
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
				if (!allIdf.containsKey(a))
					allIdf.put(a, 0.0);
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
		HashMap<String, Double> termFreqTF = new HashMap<String, Double>();
		if (queryString.isEmpty()) {
			for (int i = 0; i < k; i++) {
				result.add(new SearchResult(documents.get(i), Double.NaN));
			}
			return result;
		}
		for (int document = 0; document < docSize; document++) {
			List<String> wordsEachDocument = this.documents.get(document).getTokens();
			for (String token : queryWords) {
				double count = 0;
				for (String eachwords : wordsEachDocument) {
					if (token.equals(eachwords)) {
						count++;
					}
				}
				if (count != 0) {
					termFreqTF.put(token, 1 + Math.log10(count));
				} else {
					termFreqTF.put(token, 0.0);
				}

			}
		}
		// System.out.println(termFreqMap);

		return null;
		/***********************************************/
	}
}
