import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class IO {
    
    synchronized int get_init() {
        String iterations_input = JOptionPane.showInputDialog(null, "How many account you ganna enter?\n\nOnly positive number (Ex.: 1)");
        boolean checked = check_init(iterations_input);
        if (checked == false) {output_error_init(); return -1;}
        int iterations = Integer.parseInt(iterations_input);
        if (iterations < 0) {output_error_init(); return -1;}
        return iterations;
    }

    synchronized String[] get_data() {
        String[] account = new String[3];
        try{
            account[0] = JOptionPane.showInputDialog(null, "Number Account of 8 digits\n\nEx.: 12345678");
            check_data(account, 0);
            account[1] = JOptionPane.showInputDialog(null, "Open date (yyyy/mm/dd)\n\nEx.: 2022/04/06");
            check_data(account, 1);
            account[2] = JOptionPane.showInputDialog(null, "1. Daily Savings\n" +
                                                        "2. Early Account\n" + 
                                                        "3. Standart\n\n" + "Only enter the number");
            check_data(account, 2);
            return account;
        }

        catch (NoSuchFieldError error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
            System.out.println("Error on class 'Get': Get.get_data()\n" + 
                                "Throw by: Get.check_data(), when checked previous method");
        }

        finally {return account;}
    }

    synchronized float get_balance() {
        try {
            String balance_input = JOptionPane.showInputDialog(null, "Balance amount");
            float balance = Float.parseFloat(balance_input);
            if (balance < 0) {throw new NumberFormatException();}
            return balance;
        }
        catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
            System.out.println("Error on class 'Get': Get.get_balance()\n" + 
                                "Throw by: Get.get_balance(), when checked previous method");
            return 0;
        }        
    }

    protected void check_data(String[] data, int element) {
        boolean check = true; Pattern regex; Matcher match;
        switch (element) {
            case 0:
                regex = Pattern.compile("[\\d]{8}");
                match = regex.matcher(data[0]);
                check = match.matches()? true:false; break;
            case 1:
                regex = Pattern.compile("[\\d]{4}/[\\d]{2}/[\\d]{2}");
                match = regex.matcher(data[1]);
                check = match.matches()? true:false; break;
            case 2:
                regex = Pattern.compile("[1-3]");
                match = regex.matcher(data[2]);
                check = data[2].length() == 1 && match.matches()? true:false; break;
        }

        if (check == false) {error_cached();}
    }

    protected boolean check_init(String init) {
        Pattern regex = Pattern.compile("[^a-zA-Z]");
        Matcher match = regex.matcher(init);
        boolean check = match.matches()? true:false;
        return check;
    }

    public String error_cached() {
        throw new NoSuchFieldError("The data entered does not meet the required parameters,\n" + 
                                    "enter them as indicated");
    }

    final void output_error_init() {
        JOptionPane.showMessageDialog(null, "The data entered does not meet the required parameters,\n" + 
                                            "enter them as indicated");
        System.out.println("Error on class 'Get': Get.get_init()\n" + 
                            "Throw by: Get.check_init() or Get.get_init(), when checked previous method");
    }


    final void output_iterations(String account, float old_balance, float interest, float new_balance) {
        JOptionPane.showMessageDialog(null, "N. Account: ".concat(account + "\n") +
                                            "Balance: ".concat(old_balance + "\n") +
                                            "Interest: ".concat(interest + "\n") +
                                            "Total: ".concat(new_balance + "\n"));
    }

    final synchronized void output_final(float total_interest, float total_accounts) {
        JOptionPane.showMessageDialog(null, "The total revenue/interest generated was: ".concat(total_interest + "\n") + 
                                            "The total capital in all accounts it's: ".concat(total_accounts + "\n"));
    }

    final synchronized void output_credits() {JOptionPane.showMessageDialog(null, "Coded by Cromega");}
}
