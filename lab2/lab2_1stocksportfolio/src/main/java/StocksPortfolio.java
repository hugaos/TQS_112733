
import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {

    private List<Stock> stocks;
    private IStockmarketService stockmarket;

    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public double totalValue() {
        double total = 0.0;

        for (Stock stock : stocks) {
            total += stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity();
        }

        return total;
    }
    /**
     * @param topN the number of most valuable stocks to return
     * @return a list with the topN most valuable stocks in the portfolio
     */
    public List<Stock> mostValuableStocks(int topN) {
        List<Stock> mostValuable = new ArrayList<>(stocks);
        mostValuable.sort((s1, s2) -> {
            double s1Value = stockmarket.lookUpPrice(s1.getLabel()) * s1.getQuantity();
            double s2Value = stockmarket.lookUpPrice(s2.getLabel()) * s2.getQuantity();
            return Double.compare(s2Value, s1Value);
        });
        return mostValuable.subList(0, topN);
    }
}