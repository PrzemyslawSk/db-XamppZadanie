package pl.edu.wszib.CUI;

import pl.edu.wszib.db.DBConnector;
import pl.edu.wszib.model.Product;

import java.util.Scanner;


public class UserInterface
{
    Scanner scanner = new Scanner(System.in);

    public void ShowInterface()
    {
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
                int i = 0;
                try {
                    System.out.println("==== Product List ==== ");
                    while (true) {
                        Product productFromDb = DBConnector.getProduct(i);
                        if(productFromDb == null)
                            break;
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
                try {
                    Product product = new Product();
                    System.out.println("Set product ID: ");
                    product.setProductID(scanner.nextInt());
                    System.out.println("Set product name: ");
                    product.setProductName(scanner.next());
                    System.out.println("Set product category: ");
                    product.setProductCategory(scanner.next());
                    System.out.println("Set product price: ");
                    product.setProductPrice(scanner.nextDouble());
                    scanner.nextLine();

                    DBConnector.addProduct(product);
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
