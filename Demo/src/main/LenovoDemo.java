package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Result;

import org.junit.Test;

public class LenovoDemo {
	/**
	 * Part one
	 * @param query  string, what the user has said	
	 * @param candidates list of  string, each string is a key phrase
	 * @return int, index of the key phrase that matches the query, -1 of not of the key phrases match.
	 */
	public static Integer find_best_match_basic(String query,List<String> candidates){
		//validate params
		if(validateNull(query,candidates))
			return -1;
		//separate query from space
		String[] terms = parse(query);
		//filter worthless term
		terms = getFeatures(terms);
		//do
		for (String key : terms) {
			int i = candidates.indexOf(key);
			if(i<0)
				continue;
			else
				return i; 
		}
		return -1;
	}


	//Part two 
	public static Integer find_best_match_document_distance(String query,List<String> candidates){
		//validate
		if(validateNull(query,candidates))
			return -1;
		String[] terms = parse(query);
		List<Result> rList  = calcute(terms,candidates);
		// get the smallest distance
		Collections.sort(rList,new Comparator<Result>() {
			@Override
			public int compare(Result o1, Result o2) {
				return o1.getValue()-o2.getValue()>0?1:(o1.getValue()-o2.getValue()<0?-1:0);
			}
		});
		Result result = rList.get(0);
		return result.getIndex();
	}
	
	private static List<Result> calcute(String[] terms, List<String> candidates) {
		List<Result> list = new ArrayList<Result>();
		for(int i=0;i<candidates.size();i++){
			String candidate = candidates.get(i);
			String[] cans = parse(candidate);
			Double val = compute(terms,cans);
			Result r = new Result(i,val);
		}
		return null;
	}



	private static Double compute(String[] terms, String[] cans) {
		Double dis = 0d;
		for (String term : terms) {
			Double minDis = 0d;
			for (String can : cans) {
				Double d = distance(term,can);
				if(minDis>d)
					minDis=d;
			}
			dis+=minDis;
		}
		
		return dis;
	}

	
	
	/**
	 *  The distance of two words is the eucleandian distance of their vectors/embeddings.
	 * @param term
	 * @param can
	 * @return
	 */
	public static Double distance(String term,String can){
		//TODO
		//sorry, i cant download the resource you writing in the README from github for java
		return 0d;
	}
	
	/**
	 * filter  worthless term  like a an the ..
	 * @param terms
	 * @return
	 */
	private static String[] getFeatures(String[] terms) {
		//TODO  you think its  worthless
		return terms;
	}

	/**
	 * separate from space
	 * @param query
	 * @return
	 */
	private static String[] parse(String query) {
		query = query.trim();
		return query.split(" ");
		
	}
	/**
	 * validate
	 * @param string
	 * @param candidates
	 * @return
	 */
	public static Boolean validateNull(String string,List<String> candidates){
		if(string==null||string.trim().length()==0||candidates==null||candidates.size()==0)
			return true;
		else
			return false;
	}
	
	//Part One 
	@Test
	public void testOne(){
		
		String query = "listen to a song";
		List<String> candidates = new ArrayList<String>();
		candidates.add("restart");
		candidates.add("song");
		candidates.add("s");
		candidates.add("sss");
		Integer integer = find_best_match_basic(query, candidates);
		System.out.println(integer);
	}
}
