package csci201_final_project.util;

import java.util.regex.Pattern;

public class Constant {
	static public String DBURL = "jdbc:mysql://localhost/PROJECT";
    static public String DBUserName = "root";
    static public String DBPassword = "root";  // FIXME: Change
    // private static String FileName = "nfl_data.json";

    static public Pattern namePattern = Pattern.compile("^[ A-Za-z]+$");
    static public Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$");

}