import java.util.*;
import java.time.LocalDate;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpenseManager manager = new ExpenseManager();

    public static void main(String[] args) {
        manager.setExpenses(DataStore.LoadExpense());
        while (true) {
            showMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> addExpense();
                case "2" -> updateExpense();
                case "3" -> deleteExpense();
                case "4" -> viewExpenses();
                case "5" -> showSummary();
                case "6" -> summaryByMonth();
                case "7" -> filter();   
                case "0" -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void showMenu() {
        System.out.println("""
                \nExpense Tracker Menu:
                1. Add Expense
                2. Update Expense
                3. Delete Expense
                4. View All Expenses
                5. View Total Summary
                6. View Summary by Month
                7. filter expenses by category
                0. Exit
                Choose an option: """);
    }

    static void addExpense() {
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Category: ");
        String category = scanner.nextLine();
        manager.addExpense(new Expense(description, amount, date, category));
        System.out.println("Added.");
        DataStore.saveExpenses(manager.getAllExpenses());
    }
        static void updateExpense() {
        System.out.print("Index: ");
        int index = Integer.parseInt(scanner.nextLine());
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Category: ");
        String category = scanner.nextLine();
        Expense updated = new Expense(description, amount, date, category);
        manager.updateExpense(index, updated);
        System.out.println("Updated.");
        DataStore.saveExpenses(manager.getAllExpenses());

    }
    static void deleteExpense(){
        System.out.print("Index: ");
        int index = Integer.parseInt(scanner.nextLine());
        manager.deleteExpense(index);
        System.out.println("Deleted.");
        DataStore.saveExpenses(manager.getAllExpenses());
    }
    static void viewExpenses(){
        List <Expense> all = manager.getAllExpenses();
        for(int i = 0; i < all.size(); i++){
            System.out.println(i + ": " + all.get(i));
        } 
    }
    static void showSummary(){
        double summary = manager.getTotalExpenses();    
        System.out.println("Summary of Expense:"+summary);
    } 
    static void  summaryByMonth(){
        int month = Integer.parseInt(scanner.nextLine());
        List <Expense> monthlyExpenses = manager.getExpensesByMonth(month);
        double total = 0;
        for (Expense e : monthlyExpenses){
            total += e.getAmount();
        }
    System.out.println("Total expenses for month " + month + ": " + total);
    }
    static void filter() {
    System.out.print("Enter category to filter: ");
    String category = scanner.nextLine();

    List<Expense> filteredExpenses = manager.filter(category); 

    if (filteredExpenses.isEmpty()) {
        System.out.println("No expenses found in category: " + category);
    } else {
        for (int i = 0; i < filteredExpenses.size(); i++) {
            System.out.println(i + ": " + filteredExpenses.get(i));
        }
    }
}
}
