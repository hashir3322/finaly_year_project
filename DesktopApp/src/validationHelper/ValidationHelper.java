package validationHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    public ValidationHelper() {
    }

    public boolean validateNumber(String inputStr){
        String re = "[\\d]{1,}";
        Pattern pt = Pattern.compile(re);
        Matcher mt= pt.matcher(inputStr);
        boolean result = mt.matches();
        if(result == true){
            return true;
        }else{
            return false;
        }
    }
    public boolean validateName(String inputStr){
        String re = "[a-zA-Z|\\s]{3,30}";
        Pattern pt = Pattern.compile(re);
        Matcher mt= pt.matcher(inputStr);
        boolean result = mt.matches();
        if(result == true){
            return true;
        }else{
            return false;
        }
    }
    public boolean validateUserName(String inputStr){
        String re = "^[a-zA-Z]+[0-9]+$";
        Pattern pt = Pattern.compile(re);
        Matcher mt= pt.matcher(inputStr);
        boolean result = mt.matches();
        if(result == true && inputStr.length() >= 8 && inputStr.length() <=30){
            return true;
        }else{
            return false;
        }
    }
    public boolean validatePassword(String inputStr){
        String re = "^[0-9]+[a-zA-Z]+$";
        Pattern pt = Pattern.compile(re);
        Matcher mt= pt.matcher(inputStr);
        boolean result = mt.matches();
        if(result == true && inputStr.length() >= 8 && inputStr.length() <=20){
            System.out.println("Yes");
            return true;
        }else{
            System.out.println("No");
            return false;
        }
    }
    public boolean validateMedName(String inputStr){
        String re = "[a-zA-Z0-9|\\-|\\s]{1,30}";
        Pattern pt = Pattern.compile(re);
        Matcher mt= pt.matcher(inputStr);
        boolean result = mt.matches();
        if(result == true){
            return true;
        }else{
            return false;
        }
    }
    public boolean validateLicense(String inputStr){
        String re = "[a-zA-Z0-9\\-]{1,40}";
        Pattern pt = Pattern.compile(re);
        Matcher mt= pt.matcher(inputStr);
        boolean result = mt.matches();
        if(result == true){
            return true;
        }else{
            return false;
        }
    }
}
