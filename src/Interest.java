public class Interest {
    synchronized float revenue(float balance, float interest_percent) {
        return balance*interest_percent;
    }

    synchronized float amount(String type_account) {
        float interest;
        switch (type_account) {
            case "1": interest = 0.015f; break;
            case "2": interest = 0.017f; break;
            case "3": interest = 0.016f; break;
            default: interest = 0;
        }
        return interest;
    }

}
