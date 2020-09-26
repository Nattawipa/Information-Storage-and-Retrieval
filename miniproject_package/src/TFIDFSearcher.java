//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TFIDFSearcher extends Searcher {
	public TFIDFSearcher(String docFilename) {
		/************* YOUR CODE HERE ******************/
		super(docFilename);
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> queryWords = super.tokenize(queryString);
		List<SearchResult> result = new ArrayList<SearchResult>();
		HashMap<String, Double> termFreqMap = new HashMap<String, Double>();

		for (int document = 0; document < 1; document++) {
			List<String> wordsEachDocument = this.documents.get(document).getTokens();
			for (String token : queryWords) {
				double count = 0;
				for (String eachwords : wordsEachDocument) {
					if (token.equals(eachwords)) {
						count++;
					}
				}
				termFreqMap.put(token, count);
			}
		}
		System.out.println(termFreqMap);

		return null;
		/***********************************************/
	}
}
