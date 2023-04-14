import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
  private String name;
  private String surname;
  private String patronymic;
  private String BDDate;


  Person (String input) {
    String[] data = input.split(" ");

    surname = data[0]; 
    name = data[1]; 
    patronymic = data[2]; 
    BDDate = data[3]; 

  }

  Person () {}

  public void setName (String str) {
    name = str;
  }

  public void setSurname (String str) {
    surname = str;
  }

  public void setPatronymic (String str) {
    patronymic = str;
  }

  public void setBDDate (String str) {
    BDDate = str;
  }

  public String format () throws DateTimeException {
    return String.format("%s %s %s", getInitial(), getSex(), getAge());
  }


  public String getAge () throws DateTimeException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate bDate;

    try {
      bDate = LocalDate.parse(BDDate, formatter); 
    }
    catch (DateTimeException ex) {
      throw new DateTimeException("Invalid date");
    }

    return formatAge(Period.between(bDate, LocalDate.now()).getYears());
  }


  public String getInitial () {
    return String.format("%s %s.%s.", surname, name.substring(0, 1), patronymic.substring(0, 1));
  }


  public String getSex () {
    return patronymic.endsWith("ич") || patronymic.endsWith("лы") ? "Муж" : "Жен";
  }


  private String formatAge (int age) {
    int lastNum = age % 10;
    String post;

    if (lastNum == 1) {
      post = "год";
    }
    else if (lastNum == 0 || lastNum >= 5 && lastNum <= 9) {
      post = "лет";
    }
    else if (lastNum >= 2 && lastNum <= 4) {
      post = "года";
    }
    else {
      post = "лет";
    }

    return String.format("%d %s", age, post);

  }


}
