package app;

// Main.java

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("TV", "Appliances", 240.0)
        );

        Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        System.out.println("Products per category: ");
        productsByCategory.forEach((category, productList) -> {
            System.out.println("Category: " + category);
            productList.forEach(product ->
                    System.out.println(" - " + product.getName() + " (Price: " + product.getPrice() + ")"));
        });


        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)));
        System.out.println("Average price per category: " + averagePriceByCategory);

        String categoryWithHighestAveragePrice = averagePriceByCategory.entrySet()
                .stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).
                orElse("No category");
        System.out.println("Category with highest average price: " + categoryWithHighestAveragePrice);
    }
}
