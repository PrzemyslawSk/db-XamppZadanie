package pl.edu.wszib.CUI;

import pl.edu.wszib.db.DBConnector;

import pl.edu.wszib.model.Product;

import java.util.Scanner;


public class UserInterface
{
    Scanner scanner = new Scanner(System.in);
    private int i;
    private int id;
    private int idMax = 1;



    public void ShowInterface()
    {

        if(i == 0) {

            for (DBConnector.getProduct(i++); DBConnector.getProduct(i) != null; ) {
                i = id + 1;
                id = i;
                if(idMax<i)
                idMax=i;
            }
        }
        System.out.println(id);

        System.out.println("Choose your option:");
        System.out.println("1. Show product list.");
        System.out.println("2. Add product.");
        System.out.println("3. Delete product.");
        System.out.println("4. Exit program.");
        String variable = scanner.nextLine();



        switch (variable)
        {
            //  Show product list.
            case "1":
                i = 0;
                try {
                    System.out.println("==== Product List ==== ");
                    for (DBConnector.getProduct(i); i < idMax; ) {
                        Product productFromDb = DBConnector.getProduct(i);

                            System.out.println(productFromDb);
                            i++;
                    }

                }catch (java.util.InputMismatchException e)
                {
                    System.out.println("Something went wrong: " + e.toString());
                    scanner.nextLine();
                }
                break;
            //  Add product.
            case "2":
                i=0;
                Product productFromDb = DBConnector.getProduct(i);
                try {
                    if (productFromDb == null) {

                        Product product = new Product();
                        product.setProductID(i);

                        System.out.println("Set product name: ");
                        product.setProductName(scanner.next());
                        System.out.println("Set product category: ");
                        product.setProductCategory(scanner.next());
                        System.out.println("Set product price: ");
                        product.setProductPrice(scanner.nextDouble());
                        scanner.nextLine();

                        DBConnector.addProduct(product);
                        i++;
                    }else {

                        //Calibration ID

                        if(i == 0) {
                            for (DBConnector.getProduct(i++); DBConnector.getProduct(i) != null; ) {
                                i = i + 1;
                                id = i;

                            }
                        }

                        Product product = new Product();
                        product.setProductID((i));


                        System.out.println("Set product name: ");
                        product.setProductName(scanner.next());
                        System.out.println("Set product category: ");
                        product.setProductCategory(scanner.next());
                        System.out.println("Set product price: ");
                        product.setProductPrice(scanner.nextDouble());
                        scanner.nextLine();


                        DBConnector.addProduct(product);

                    }
                    if(idMax<=i)
                        idMax = i+1;
                }catch (java.util.InputMismatchException e)
                {
                    System.out.println("Something went wrong: " + e.toString());
                    scanner.nextLine();
                }
                break;
            //    Delete product.
            case "3":
                try {
                    Product product = new Product();
                    System.out.println("Set product ID: ");
                    product.setProductID(scanner.nextInt());
                    scanner.nextLine();

                    DBConnector.deleteProduct(product);

                }catch (java.util.InputMismatchException e)
                {
                    System.out.println("Something went wrong: " + e.toString());
                    scanner.nextLine();
                }
                break;
            //  Exit program.
            case "4":
                System.out.println("Exiting program..");
                System.exit(0);
                break;
            default:
                System.out.println("Wrong value! Try again.");
                System.out.println("=======================================");
                break;
        }

    }
}
