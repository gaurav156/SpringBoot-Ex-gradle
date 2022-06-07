package com.springboot.configdemo.MyApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class BookMenu {

    @Autowired
    private BookManager bookManager;

    public void bookMenu(){
        boolean exit = false;
        while (!exit){
            System.out.println("*** MAIN MENU ***\n" +
                    "Choose an option : \n" +
                    "1)\tView all Books\n" +
                    "2)\tView Books in a Category\n" +
                    "3)\tView Books of an Author\n" +
                    "4)\tNumber of Books Available in a Category\n" +
                    "5)\tExit\n");
            Scanner sMenu = new Scanner(System.in);
            int resMenu = sMenu.nextInt();
            switch (resMenu){
                case 1: {
//                    1)    View all Books
                    try {
                        bookManager.printBooks(bookManager.getList());
                    }
                    catch (Exception e){
                        System.out.println("Error");
                    }
                    boolean c1 = true;
                    while(c1) {
                    System.out.println("\nEnter a Choice : \n" +
                            "1)\tBack to Main BookMenu\n" +
                            "2)\tExit\n");

                    Scanner s1 = new Scanner(System.in);
                    int res1 = s1.nextInt();
                        if (res1 == 1) {
                            break;
                        } else if (res1 == 2) {
                            exit = true;
                            break;
                        } else {
                            System.out.println("Invalid Choice");
                        }
                    }
                    break;
                }
                case 2: {
//                    2)    View Books in a Category
                        System.out.println("\nEnter a Category : ");
                    boolean c2 = true;
                    while(c2) {
                        Scanner s2 = new Scanner(System.in);
                        String category = s2.nextLine();
                        System.out.println();
                        if (bookManager.getList().stream().anyMatch(p -> p.getBookCategory().equals(category))) {
                            bookManager.printBooks(bookManager.categoryFilter(category));
                            c2 = false;
                        } else {
                            System.out.println("Enter a valid Category : ");
                        }
                    }
                    boolean c21 = true;
                    while(c21) {
                        System.out.println("\nEnter a Choice : \n" +
                                "1)\tBack to Main BookMenu\n" +
                                "2)\tExit\n");

                        Scanner s21 = new Scanner(System.in);
                        int res1 = s21.nextInt();
                        if (res1 == 1) {
                            exit = false;
                            break;
                        } else if (res1 == 2) {
                            exit = true;
                            break;
                        } else {
                            System.out.println("\nInvalid Choice");
                        }
                    }
                    break;
                }
                case 3: {
//                    3)    View Books of an Author
                    System.out.println("\nEnter an Author : " );
                    boolean c3 = true;
                    while(c3) {
                        Scanner s3 = new Scanner(System.in);
                        String author = s3.nextLine();
                        System.out.println();
                        if (bookManager.getList().stream().anyMatch(p -> p.getBookAuthor().equals(author))) {
                            bookManager.printBooks(bookManager.authorFilter(author));
                            c3 = false;
                        } else {
                            System.out.println("Enter a valid Author : ");
                        }
                    }
                    boolean c31 = true;
                    while(c31) {
                        System.out.println("\nEnter a Choice : \n" +
                                "1)\tBack to Main BookMenu\n" +
                                "2)\tExit\n");

                        Scanner s21 = new Scanner(System.in);
                        int res1 = s21.nextInt();
                        if (res1 == 1) {
                            exit = false;
                            break;
                        } else if (res1 == 2) {
                            exit = true;
                            break;
                        } else {
                            System.out.println("\nInvalid Choice");
                        }
                    }
                    break;
                }case 4: {
//                    4)	Number of Books Available in a Category

                    System.out.println("Enter a Category to get Count : ");
                    boolean c4 = true;
                    while(c4) {
                        Scanner s4 = new Scanner(System.in);
                        String category = s4.nextLine();
                        System.out.println();
                        if (bookManager.getList().stream().anyMatch(p -> p.getBookCategory().equals(category))) {
                            bookManager.printCount(bookManager.categoryCount(category));
                            c4 = false;
                        } else {
                            System.out.println("\nEnter a valid Category :");
                        }
                    }
                    boolean c41 = true;
                    while(c41) {
                        System.out.println("\nEnter a Choice : \n" +
                                "1)\tBack to Main BookMenu\n" +
                                "2)\tExit\n");

                        Scanner s41 = new Scanner(System.in);
                        int res1 = s41.nextInt();
                        if (res1 == 1) {
                            exit = false;
                            break;
                        } else if (res1 == 2) {
                            exit = true;
                            break;
                        } else {
                            System.out.println("\nInvalid Choice");
                        }
                    }
                    break;
                }
                case 5: {
//                    5)	Exit
                    exit = true;
                break;
                }
                default:
                    System.out.println("\nEnter a valid Choice : \n");
            }
        }
    }
}
