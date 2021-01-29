package com.odde;

import java.util.HashMap;
import java.util.Map;

public class OrdersWriter {

    public static final String ID = "id";
    public static final String PRODUCTS = "products";
    public static final String CODE = "code";
    public static final String COLOR = "color";
    public static final String SIZE = "size";
    public static final String PRICE = "price";
    public static final String CURRENCY = "currency";
    public static final String ORDERS = "orders";
    public static final String INVALID_SIZE = "Invalid Size";
    public static final String XS = "XS";
    public static final String S = "S";
    public static final String M = "M";
    public static final String L = "L";
    public static final String XL = "XL";
    public static final String XXL = "XXL";
    public static final String NO_COLOR = "no color";
    public static final String YELLOW = "yellow";
    public static final String RED = "red";
    public static final String BLUE = "blue";
    private final Orders orders;
    private final JsonBuilder jsonBuilder;
    private final Map<Integer,String> sizes;
    private final Map<Integer,String> colors;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
        jsonBuilder = new JsonBuilder();
        colors = new HashMap<>();
        sizes = new HashMap<>();
        initColors();
        initSizes();
    }

    private void initColors() {
        colors.put(1, BLUE);
        colors.put(2, RED);
        colors.put(3, YELLOW);
    }

    private void initSizes() {
        sizes.put(1, XS);
        sizes.put(2, S);
        sizes.put(3, M);
        sizes.put(4, L);
        sizes.put(5, XL);
        sizes.put(6, XXL);
    }

    private String getSizeFor(int id) {
        return sizes.getOrDefault(id, INVALID_SIZE);
    }

    private String getColorFor(int id) {
        return colors.getOrDefault(id, NO_COLOR);
    }

    public String getContents() {
        jsonBuilder.addStartKey();
        jsonBuilder.addArray(ORDERS);
        for (int i = 0; i < orders.getOrdersCount(); i++)
            parseOrder(orders.getOrder(i));

        deleteCommaIfNumberIsGreaterThanZero(orders.getOrdersCount());
        jsonBuilder.endArray();
        jsonBuilder.addEndKey();
        return jsonBuilder.getString();
    }

    private void deleteCommaIfNumberIsGreaterThanZero(int number) {
        if (number > 0)
            jsonBuilder.delete(jsonBuilder.getString().length() - 2, jsonBuilder.getString().length());
    }

    private void parseOrder(Order order) {
        jsonBuilder.addStartKey();
        jsonBuilder.addKeyIntegerValue(ID,order.getOrderId());
        jsonBuilder.addComma();
        jsonBuilder.addArray(PRODUCTS);

        for (int j = 0; j < order.getProductsCount(); j++)
            parseProduct(order.getProduct(j));

        deleteCommaIfNumberIsGreaterThanZero(order.getProductsCount());
        jsonBuilder.endArray();
        jsonBuilder.addEndKey();
        jsonBuilder.addComma();
    }

    private void parseProduct(Product product) {
        jsonBuilder.addStartKey();
        jsonBuilder.addKeyStringValue(CODE,product.getCode());
        jsonBuilder.addComma();
        jsonBuilder.addKeyStringValue(COLOR,getColorFor(product.getColor()));
        jsonBuilder.addComma();

        if (product.getSize() != Product.SIZE_NOT_APPLICABLE) {
            jsonBuilder.addKeyStringValue(SIZE,getSizeFor(product.getSize()));
            jsonBuilder.addComma();
        }
        jsonBuilder.addKeyDoubleValue(PRICE,product.getPrice());
        jsonBuilder.addComma();
        jsonBuilder.addKeyStringValue(CURRENCY,product.getCurrency());
        jsonBuilder.addEndKey();
        jsonBuilder.addComma();
    }
}