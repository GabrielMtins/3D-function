package app;

import java.util.HashMap;

/*  use this class to create a parser that solves simple expressions */
public class Parser{
	private HashMap<String, Double> variables_map;

	public Parser(){
		variables_map = new HashMap<>();
	}

	public void setVariable(String key, double value){
		variables_map.put(key, value);
	}

	public double solveExp(String exp) throws Exception{
		exp = exp.trim();
		System.out.println(exp);

		/* Check if there is a parenthesis like this (exp) */
		if(exp.charAt(0) == '('){
			int num_of_parenthesis = 1;

			for(int i = 1; i < exp.length(); i++){
				if(exp.charAt(i) == '(') num_of_parenthesis++;
				if(exp.charAt(i) == ')'){
					num_of_parenthesis--;

					if(num_of_parenthesis == 0 && i != exp.length() - 1){
						break;
					}
					else
						return solveExp(exp.substring(1, exp.length() - 1));
				}
			}
		}

		for(int i = exp.length() - 1; i >= 0; i--){
			/* find parenthesis and skip it*/
			if(exp.charAt(i) == ')'){
				int num_of_parenthesis = 1;
				i--;

				while(num_of_parenthesis != 0){
					if(exp.charAt(i) == '(') num_of_parenthesis--;
					if(exp.charAt(i) == ')') num_of_parenthesis++;
					i--;
				}

				i++;
				continue;
			}

			if(exp.charAt(i) == '+'){
				return 
					solveExp(exp.substring(0, i)) +
					solveExp(exp.substring(i + 1, exp.length()));
			}

			if(exp.charAt(i) == '-'){
				return 
					solveExp(exp.substring(0, i)) -
					solveExp(exp.substring(i + 1, exp.length()));
			}
		}

		for(int i = exp.length() - 1; i >= 0; i--){
			/* find parenthesis and skip it*/
			if(exp.charAt(i) == ')'){
				int num_of_parenthesis = 1;
				i--;

				while(num_of_parenthesis != 0){
					if(exp.charAt(i) == '(') num_of_parenthesis--;
					if(exp.charAt(i) == ')') num_of_parenthesis++;
					i--;
				}

				i++;
				continue;
			}

			if(exp.charAt(i) == '*'){
				return 
					solveExp(exp.substring(0, i)) *
					solveExp(exp.substring(i + 1, exp.length()));
			}

			if(exp.charAt(i) == '/'){
				double tmp = solveExp(exp.substring(i+1, exp.length()));
				if(tmp == 0) throw new Exception();

				return 
					solveExp(exp.substring(0, i)) /
					tmp;
			}
		}

		for(int i = exp.length() - 1; i >= 0; i--){
			/* find parenthesis and skip it*/
			if(exp.charAt(i) == ')'){
				int num_of_parenthesis = 1;
				i--;

				while(num_of_parenthesis != 0){
					if(exp.charAt(i) == '(') num_of_parenthesis--;
					if(exp.charAt(i) == ')') num_of_parenthesis++;
					i--;
				}

				i++;
				continue;
			}

			if(exp.charAt(i) == '^'){
				double res1 = solveExp(exp.substring(0, i));
				double res2 = solveExp(exp.substring(i + 1, exp.length()));
				double res3 = Math.pow(res1, res2);

				if(!Double.isNaN(res3)) return res3;
				else throw new Exception();
			}
		}

		/* check if there is a special function or variable that is on the hashmap */
		double final_res = 0;

		if(exp.contains("cos")){
			final_res = Math.cos(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("sin")){
			final_res = Math.sin(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("log")){
			final_res = Math.log(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("abs")){
			final_res = Math.abs(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("sqrt")){
			final_res = Math.sqrt(solveExp(exp.substring(4, exp.length())));
			System.out.println(final_res);
		}
		else if(variables_map.containsKey(exp))
			final_res = variables_map.get(exp);
		else{
			final_res = Double.valueOf(exp);
		}

		if(Double.isNaN(final_res) || Double.isInfinite(final_res)) throw new Exception();
		else return final_res;
	}
}
