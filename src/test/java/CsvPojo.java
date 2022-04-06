import java.util.List;

public class CsvPojo {
    String id;
    List<Integer> removeTeeth;
    Integer price;
    Integer discount;

    public CsvPojo(String id, List<Integer> removeTeeth, Integer price, Integer discount) {
        this.id = id;
        this.removeTeeth = removeTeeth;
        this.price = price;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public List<Integer> getRemoveTeeth() {
        return removeTeeth;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "CsvPojo{" +
                "id='" + id + '\'' +
                ", removeTeeth=" + removeTeeth +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
