package rayacevedo45.c4q.nyc.art;

/**
 * Created by c4q-Abass on 6/30/15.
 */
public class Stock {

    static String APIurl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22YHOO%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    static String YHOOurl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22YHOO%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    private String stockSymbol;
    static String stockDaysLow = "";
    static String stockDaysHigh = "";
    static String stockChange = "";
    private String dailyStockString;

}
