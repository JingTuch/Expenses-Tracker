    import java.util.*;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void updateExpense(int index, Expense updatedExpense) {
        if (index >= 0 && index < expenses.size()) {
            expenses.set(index, updatedExpense);
        }
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
        }
        else{
            System.out.println("Invalid Index");
        }
    }

    public List<Expense> getAllExpenses() {
        return expenses;
    }

    public double getTotalExpenses() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public List<Expense> getExpensesByMonth(int month) {
        return expenses.stream()
            .filter(e -> e.getDate().getMonthValue() == month)
            .toList();
    }
    public void setExpenses(List<Expense> expenses){
        this.expenses = expenses;
    }
    public List<Expense> filter(String category){
        return expenses.stream()
            .filter(e -> e.getCategory().equalsIgnoreCase(category))
            .toList();
    }
    // Add filter by category, budget warning, etc.
}
