package com.dev.godgunner.programmingquestions.desiignpatterns.openclose;

import java.util.List;
import java.util.stream.Stream;

/*
    This is a class designed to represent an ECOMM portal's functionality for filtering based on a user specified
    criteria. Here we have a class Product, which has a name and two specification, color and size, based on which we
    allow the user to filter products. We have specified the enums COLOR and SIZE for three values currently. We have
    two filter mechanisms, ProductFilter which is a simple filter but has the problem that it needs to be modified for
    every specification filter that we add to it, and BetterFilter which is scalable and any additional criteria for
    filtering of products can be handled without modification.

    The main problem for scalability faced in out requirement is if we want to include more factors/specification for
    our usecases, how should the program be designed so that modification of the pre existing code is avoided.

    We have created an Interface to represent any Specification with an abstract method isSatisfied() to enforce every
    new specification to implement the method isSatisfied. This method takes in the desired object and returns a boolean
    if the product satisfies the specification.

    We have created an Interface to represent a Filter with an abstract method filter() which takes in a List of objects
    and a Specification of the same object. This method returns a stream of objects that satisfy the filter criteria.

    We have three specifications implementing the Specification interface and one filter implementing the Filter
    interface.

    ColorSpecification is a class implementing the Specification interface, it has a constructor and an implementation
    of the isSatisfied() method.

    SizeSpecification is a class implementing the Specification interface, it has a constructor and an implementation
    of the isSatisfied() method.

    BetterFilter is a class implementing Filter interface, it has the filter() implementation.

    AndSpecification is a class implementing the Specification interface. It has two Specification objects that can be
    set through the constructor, and has the isSatisfied() method which returns true only if both the specifications are
    satisfied.

    BetterFilter follows Open Close Principle which says:
    A program should be designed in such a way that it is open for extension and closed for modification
 */

public class Demo {
    public static void main(String[] args){
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old):");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new):");
        bf.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Large Blue iteems:");
        bf.filter(products,new AndSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
        )).forEach(p -> System.out.println(
                " - " + p.name + " is blue and large"));
    }
}

enum Color{
    RED, GREEN, BLUE
}

enum Size{
    SMALL, MEDIUM, LARGE, HUGE
}

class Product{
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return  products.stream().filter(p -> p.color == color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return  products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterBySizeAndColor(
            List<Product> products,
            Color color,
            Size size){
        return products.stream().filter(p -> p.size == size && p.color == color);
    }
}

interface Specification<T>{
    boolean isSatisfied(T item);
}

interface  Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product>{

    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.color == item.color;
    }
}

class SizeSpecification implements Specification<Product>{

    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class BetterFilter implements Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(p -> spec.isSatisfied(p));
    }
}

class AndSpecification<T> implements Specification<T>{

    private Specification<T> first,second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}