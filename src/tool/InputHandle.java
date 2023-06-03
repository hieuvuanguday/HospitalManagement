package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandle {

    public static Scanner sc = new Scanner(System.in);

    //ép kiểu nhập số nguyên
    public static int getInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    //ép kiểu nhập số nguyên trong giới hạn
    public static int getInt(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                if (lowerBound > upperBound) {
                    upperBound = upperBound + lowerBound - (lowerBound = upperBound);
                }
                if (inputNum < lowerBound || inputNum > upperBound) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                String msg = String.format("Must be in [%d,%d]", lowerBound, upperBound);
                System.out.println(msg);
            }
        }
    }

    //ép kiểu nhập số nguyên dương
    public static int getPositiveInt(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                int inputNum = Integer.parseInt(sc.nextLine());
                if (inputNum <= 0) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0!");
            }
        }
    }

    //ép kiểu nhập số thực dương
    public static double getPositiveReal(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                double inputNum = Double.parseDouble(sc.nextLine());
                if (inputNum <= 0) {
                    throw new Exception();
                }
                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            } catch (Exception e) {
                System.out.println("Must be greater than 0");
            }
        }
    }

    //ép kiểu nhập chuỗi không có " "
    public static String getString(String inputMsg, String errorMsg) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (inputStr.contains(" ")) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //ép kiểu nhập chuỗi bình thường
    public static String getString(String inputMsg) {
        System.out.print(inputMsg);
        String inputStr = sc.nextLine();
        return inputStr;
    }

    //ép kiểu nhập chuỗi với regex
    public static String getString(String inputMsg, String errorMsg, String regex) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (!inputStr.matches(regex)) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //ép kiểu nhập chuỗi trong giới hạn độ dài
    public static String getString(String inputMsg, String errorMsg, int minLength, int maxLength) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                int strLen = inputStr.length();
                if (strLen < minLength || strLen > maxLength) {
                    throw new Exception();
                }
                return inputStr;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    //nhập lại String nếu nhập null
    public static boolean getStringNonBlank(String inputStr) {
        if (inputStr.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
//    while(!getStringNonBlank(inputStr)) {
//        inputStr = getString("Enter again!");
//    }
    //ép kiểu nhập Date hợp lệ

    public static String getDate(String inputMsg, String dateFormat) {
        while (true) {
            try {
                System.out.print(inputMsg);
                String inputStr = sc.nextLine();
                if (checkValidDate(inputStr, dateFormat)) {
                    return inputStr;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid date: " + dateFormat);
            }
        }
    }
    //Hàm check unique ID

    //hàm kiểm tra hợp lệ của Date
    private static boolean checkValidDate(String date, String dateFormat) {
        try {
            if (!isValidDateFormat(date, dateFormat)) {
                throw new IllegalArgumentException("Invalid date format");
            }
            int day, month, year;
            String[] dateParts = date.split("[- /.]");
            if (dateFormat.equals("dd/mm/yyyy")) {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
            } else {
                day = Integer.parseInt(dateParts[1]);
                month = Integer.parseInt(dateParts[0]);
            }
            year = Integer.parseInt(dateParts[2]);

            //check năm nhuận
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    throw new IllegalArgumentException("Invalid date value");
                } else {
                    return true;
                }
            } else if (month == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    if (day > 29) {
                        throw new IllegalArgumentException("Invalid date value");
                    } else {
                        return true;
                    }
                } else {
                    if (day > 28) {
                        throw new IllegalArgumentException("Invalid date value");
                    } else {
                        return true;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
    //regex cho Date
    private static final String DDMMYYYY_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    private static final String MMDDYYYY_REGEX = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";

    //kiểm tra hợp lệ cho DateFormat
    private static boolean isValidDateFormat(String date, String dateFormat) {
        String regex = (dateFormat.equals("dd/mm/yyyy")) ? DDMMYYYY_REGEX : MMDDYYYY_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
    
    
    //trans to Date datatype
    public static Date toDate(String date, String format) {
        Date ret = new Date() ;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            ret = sdf.parse(date);
        }catch(ParseException e){}
        return ret ;
    }
}
