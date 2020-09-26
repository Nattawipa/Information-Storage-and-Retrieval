//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JaccardSearcher extends Searcher {

	public JaccardSearcher(String docFilename) {

		/************* YOUR CODE HERE ******************/
		super(docFilename);
		/***********************************************/
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> query = super.tokenize(queryString);
		List<SearchResult> result = new ArrayList<SearchResult>();

		for (int i = 0; i < this.documents.size(); i++) {
			double score = 0;
			int unionSize = 0;
			int intersectSize = 0;
			
			if(queryString == "") {
				result.add(new SearchResult(this.documents.get(i), score));
			}
			
			Set<String> documentSet = setOfString(this.documents.get(i).getTokens());
			Set<String> querySet = setOfString(query);
			unionSize = union(querySet, documentSet);
			querySet.retainAll(documentSet);
			intersectSize = querySet.size();
			score = (double)intersectSize / unionSize;
			result.add(new SearchResult(this.documents.get(i), score));
		}
			Collections.sort(result); 
		return result.subList(0, k);
		/***********************************************/
	}

	public Set<String> setOfString(List<String> data) {
		Set<String> result1 = new HashSet<String>();
		
		data.forEach((word) -> {
			result1.add(word);
		});
		return result1;
	}

	public int union(Set<String> querySet, Set<String> documentSet) {
		Set<String> newSet  = new HashSet<String>();
		
		newSet.addAll(querySet);
		newSet.addAll(documentSet);
		return newSet.size();
	}
}
