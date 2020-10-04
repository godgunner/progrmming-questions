package com.dev.godgunner.programmingquestions.designpatterns.solid.interfacesegregation;

public class Demo {
}

class Document{
    public static void main(String args[]){
        MultifunctionPrinter prog = new MultifunctionPrinter();
        prog.print();
    }
}

// YAGNI = You Ain't Going to Need It
interface Machine{
    void print();
    void fax(Document d);
    void scan(Document d);
}

class MultifunctionPrinter implements Machine{

    @Override
    public void print() {
        System.out.println("Printed");
    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }

}

// Better implementation is to segregate the interface into smaller meaningful interfaces

interface Printer{
    void print(Document d);
}

interface Scanner{
    void scan(Document d);
}

class AdvancedDevice implements Printer, Scanner{

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }

}

interface MultiFunctionDevice extends Printer,Scanner {
    void fax(Document d);
}

class MultifunctionMachine implements MultiFunctionDevice{

    //Decorator Pattern example
    private Printer printer;
    private Scanner scanner;

    public MultifunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }

    @Override
    public void fax(Document d) {

    }
}
