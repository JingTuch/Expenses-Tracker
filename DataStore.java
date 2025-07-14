import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.lang.reflect.Type;

public class DataStore{
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
            @Override
            public void write(JsonWriter out, LocalDate value) throws IOException {
                out.value(value.toString());
            }

            @Override
            public LocalDate read(JsonReader in) throws IOException {
                return LocalDate.parse(in.nextString());
            }
        })
        .setPrettyPrinting()
        .create();
    private static final String FILE_NAME = "expense.json";

    public static void saveExpenses(List<Expense> expense){
         try(FileWriter writer = new FileWriter(FILE_NAME)){
            gson.toJson(expense, writer);
            System.out.println("Expense save to: " + FILE_NAME);
         }catch (IOException e){
            e.printStackTrace();
         }
    } 
    public static List<Expense> LoadExpense(){
            try(FileReader reader = new FileReader(FILE_NAME)){
                Type expenseListType = new TypeToken<List<Expense>>() {}.getType();
                return gson.fromJson(reader, expenseListType);
            }catch (IOException e){
                return new java.util.ArrayList<>();
            }
    }
}