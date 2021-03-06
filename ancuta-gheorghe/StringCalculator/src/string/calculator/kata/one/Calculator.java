/**
 * 
 */
package string.calculator.kata.one;

/**
 * @author ancuta
 *
 */
public class Calculator {

	private static final String ESCAPE_CHARACTER = "\\";
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 1000;
	private static final int SECOND_POSITION_IN_INPUT = 1;
	private static final int FIRST_POSITION_IN_INPUT = 0;
	private static final String DEFAULT_VALUE_FOR_EMTPY_INPUT = "0";
	private static final String NEW_LINE = "\n";
	private static final String DEFAULT_DELIMITER = ";";

	public int add(String stringOfDelimiterAndNumbers) throws Throwable{
		String[] numbers = null;
		if (stringOfDelimiterAndNumbers.split(NEW_LINE).length > 1){
			String delimiter = escapeDelimiter(getDelimiter(stringOfDelimiterAndNumbers.split(NEW_LINE)[FIRST_POSITION_IN_INPUT]));
			numbers = stringOfDelimiterAndNumbers.split(NEW_LINE)[SECOND_POSITION_IN_INPUT].split(delimiter);
		} else {
			numbers = getDelimiter(stringOfDelimiterAndNumbers).split(DEFAULT_DELIMITER);
		}
		int sum = 0;
		String negativeNumbers = "";
		for (String number: numbers){
			number = (number!=null&&!"".equals(number))?number:DEFAULT_VALUE_FOR_EMTPY_INPUT;
			if (Integer.parseInt(number) <= MAX_VALUE) {
				sum += Integer.parseInt(number);
			}
			
			if (Integer.parseInt(number) < MIN_VALUE) {
				negativeNumbers += " " + number;
			}
		}
		if (negativeNumbers != null && negativeNumbers.length() > 0){
			throw NegativeNumberException.showMessage(negativeNumbers);
		}
		return sum;
	}

	private String escapeDelimiter(String delimiter){
		String output = "";
		for (int i = 0; i< delimiter.length(); i++){
			output += ESCAPE_CHARACTER + delimiter.substring(i, i+1);
		}
		return output;
	}
	private String getDelimiter(String stringOfDelimiter) {
		// //[***]
		String output = stringOfDelimiter.substring(3);
		output = output.substring(0, output.length() - 1);
		return output;
	}

}
