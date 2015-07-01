package rayacevedo45.c4q.nyc.art;

/**
 * Created by c4q-Abass on 6/30/15.
 */
public class Stock {

    static String APIurl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22YHOO%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
     private String id;
      private String daysLow = "";
      private String daysHigh = "";
        private String yearLow = "";
        private String YearHigh = "";



    private String mktCap;

    public Stock( String id, String lastTradePriceOnly, String yearLow, String daysHigh, String daysLow, String yearHigh, String daysRange, String change) {
        this.id = id;
        this.lastTradePriceOnly = lastTradePriceOnly;
        this.yearLow = yearLow;
        this.daysHigh = daysHigh;
        this.daysLow = daysLow;
        this.YearHigh = yearHigh;
        this.DaysRange = daysRange;
        this.change = change;
    }

    public Stock(String id, String daysHigh){
        this.id = id;
        this.daysHigh = daysHigh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDaysLow() {
        return daysLow;
    }

    public void setDaysLow(String daysLow) {
        this.daysLow = daysLow;
    }

    public String getDaysHigh() {
        return daysHigh;
    }

    public void setDaysHigh(String daysHigh) {
        this.daysHigh = daysHigh;
    }

    public String getYearLow() {
        return yearLow;
    }

    public void setYearLow(String yearLow) {
        this.yearLow = yearLow;
    }

    public String getYearHigh() {
        return YearHigh;
    }

    public void setYearHigh(String yearHigh) {
        YearHigh = yearHigh;
    }

    public String getLastTradePriceOnly() {
        return lastTradePriceOnly;
    }

    public void setLastTradePriceOnly(String lastTradePriceOnly) {
        this.lastTradePriceOnly = lastTradePriceOnly;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getDaysRange() {
        return DaysRange;
    }

    public void setDaysRange(String daysRange) {
        DaysRange = daysRange;
    }

    private String lastTradePriceOnly = "";
        private String change = "";
        private String DaysRange = "";

    public Stock(String id){
        this.id = id;

    }



    @Override
    public String toString() {
        return (this.getId() +  this.getDaysHigh());
    }
}
