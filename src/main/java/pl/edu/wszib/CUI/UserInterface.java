package pl.edu.wszib.CUI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.wszib.configuration.AppConfiguration;
import pl.edu.wszib.dao.IProductDAO;
import pl.edu.wszib.model.Product;
import pl.edu.wszib.services.IProductService;

import java.util.Scanner;


public class UserInterface
{
    Scanner scanner = new Scanner(System.in);

    public void ShowInterface()
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        System.out.println("Choose your option:\n" +
                "1. Show product list.\n" +
                "2. Add product.\n" +
                "3. Delete product.\n" +
                "4. Exit program.");
        String variable = scanner.nextLine();

        IProductDAO productDAO = context.getBean(IProductDAO.class);
        switch (variable)
        {
            //  Show product list.
            case "1":
                int i = 1;
                while(productDAO.getProductById(i) != null)
                {
                    System.out.println(productDAO.getProductById(i));
                    i++;
                }
                break;
            //  Add product.
            case "2":
                try{
                    Product product = new Product(1);
                    System.out.println("Set product name: ");
                    product.setProductName(scanner.next());
                    System.out.println("Set product category: ");
                    product.setProductCategory(scanner.next());
                    System.out.println("Set product price: ");
                    product.setProductPrice(scanner.nextDouble());
                    scanner.nextLine();

                    IProductService productService = context.getBean(IProductService.class);
                    productService.saveProduct(product);
                }catch (java.util.InputMismatchException e)
                {
                    System.out.println("Something went wrong: " + e.toString());
                }
                break;
            //    Delete product.
            case "3":
                try{
                    Product product1 = new Product();
                    System.out.println("Which product do you want to delete?");
                    product1.setProductID(scanner.nextInt());
                    productDAO.updateProduct(product1);
                }catch (java.util.InputMismatchException e)
                {
                    System.out.println("Something went wrong: " + e.toString());
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
