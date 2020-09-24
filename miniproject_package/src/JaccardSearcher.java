//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JaccardSearcher extends Searcher{

	public JaccardSearcher(String docFilename) {
		
		/************* YOUR CODE HERE ******************/
		super(docFilename);
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<String> query = super.tokenize(queryString);
		List<SearchResult> result = new ArrayList<SearchResult>();
		Set<String> querySet = setOfString(query);
		
		for(int i=0;i<this.documents.size();i++) {
			double score = 0;
			Set<String> documentSet = setOfString(this.documents.get(i).getTokens());
			if(queryString == "") {
				result.add(new SearchResult(this.documents.get(i), score));
			}
		}
//		System.out.println(this.documents.get(0).getTokens());
		return result.subList(0, k);
		/***********************************************/
	}
	
	public Set<String> setOfString(List<String> data){
		Set<String> result1 = new HashSet<String>();
		data.forEach((word) -> 
		{
			result1.add(word);
		});
		return result1;
	}

}
