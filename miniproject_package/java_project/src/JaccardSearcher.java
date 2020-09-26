//Name: 
//Section: 
//ID: 

import java.util.ArrayList;
import java.util.List;

public class JaccardSearcher extends Searcher{

	public JaccardSearcher(String docFilename) {
		
		/************* YOUR CODE HERE ******************/
		super(docFilename);
		/***********************************************/
	}

	@Override
	public List<SearchResult> search(String queryString, int k) {
		/************* YOUR CODE HERE ******************/
		List<SearchResult> result = new ArrayList<SearchResult>();
		System.out.println(this.documents.get(0).getRawText());
		return null;
		/***********************************************/
	}

}
