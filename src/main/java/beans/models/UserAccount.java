package beans.models;

/**
 * Created by Igor_Lapin on 5/9/2018.
 */
public class UserAccount {

    private long userId;
    private double cash;

    public UserAccount() {
    }

    public UserAccount(long userId, double cash) {
        this.userId = userId;
        this.cash = cash;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (userId != that.userId) return false;
        return Double.compare(that.cash, cash) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (userId ^ (userId >>> 32));
        temp = Double.doubleToLongBits(cash);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
