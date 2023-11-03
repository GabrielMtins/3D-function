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

	public double solveExp(String exp){
		exp = exp.trim();

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
				if(tmp == 0) tmp = 0.01; /* don't divide by zero */

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
				if(res2 < 1 && res2 > 0){
					if(res1 < 0) return 0;
				}
				return 
					Math.pow(solveExp(exp.substring(0, i)),
					solveExp(exp.substring(i + 1, exp.length())));
			}
		}

		/* check if there is a special function or variable that is on the hashmap */
		if(exp.contains("cos")){
			return Math.cos(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("sin")){
			return Math.sin(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("log")){
			return Math.log(solveExp(exp.substring(3, exp.length())));
		}
		else if(exp.contains("sqrt")){
			return Math.log(solveExp(exp.substring(4, exp.length())));
		}
		if(variables_map.containsKey(exp))
			return variables_map.get(exp);
		else{
			return Double.valueOf(exp);
		}
	}
}
