// Coded by Cromega
// Github: https://github.com/Cromega08

public class App {
    
    public static void main(String[] args) throws Exception {
        try {
            IO I_O = new IO(); Interest interest = new Interest();
            float total_interest = 0, total_accounts = 0;
            for (int init = I_O.get_init(), count = 0; count < init; ++count) {
                String[] account = I_O.get_data();
                if (account[2] == null) {I_O.error_cached();}
                float balance = I_O.get_balance(),
                    interest_percent = interest.amount(account[2]),
                    interest_monthly  = interest.revenue(balance, interest_percent),
                    new_balance = balance + interest_monthly;
                total_interest += interest_monthly; total_accounts += new_balance;
                I_O.output_iterations(account[0], balance, interest_monthly, new_balance);
            }
            I_O.output_final(total_interest, total_accounts);
        }
        catch (NoSuchFieldError error) {System.out.println();}

        finally {IO I_O =  new IO(); I_O.output_credits();}
    }
}
