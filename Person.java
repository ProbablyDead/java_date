import java.time.LocalDate;
import java.io.File;
import java.time.DateTimeException;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
  /** Имя */
  private String name;
  /** Фамилия */
  private String surname;
  /** Отчество */
  private String patronymic;
  /** Изображение */
  private File image; 
  /** Дата рождения */
  private String BDDate;

  /**
   * Конструктор человека
   * @param input строка с данными (фамилия имя отчество дд.мм.гггг)
  */
  Person (String input) {
    String[] data = input.split(" ");

    surname = data[0]; 
    name = data[1]; 
    patronymic = data[2]; 
    BDDate = data[3]; 

  }

  /**
   * Установить изображение
   * @param image
  */
  public void setImage (File image) {
    this.image = image;
  }
  
  /**
   * Установить имя
   * @param str
  */
  public void setName (String str) {
    name = str;
  }

  /**
   * Установить фамилию
   * @param str
  */
  public void setSurname (String str) {
    surname = str;
  }

  /**
   * Установить отчество
   * @param str
  */
  public void setPatronymic (String str) {
    patronymic = str;
  }

  /**
   * Установить дату рождения
   * @param str
  */
  public void setBDDate (String str) {
    BDDate = str;
  }

  /**
   * Получить результирующую строку
   * @return
   * @throws DateTimeException
  */
  public String format () throws DateTimeException {
    return String.format("%s %s %s", getInitial(), getSex(), getAge());
  }

  /**
   * Получить изображение
   * @return
  */
  public File getImage () {
    return image;
  }

  /**
   * Получить возраст
   * @return
   * @throws DateTimeException
  */
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

  /**
   * Получить иниалы
   * @return
  */
  public String getInitial () {
    return String.format("%s %s.%s.", surname, name.substring(0, 1), patronymic.substring(0, 1));
  }

  /**
   * Получить пол
   * @return
  */
  public String getSex () {
    return patronymic.endsWith("ич") || patronymic.endsWith("лы") ? "Муж" : "Жен";
  }

  /**
   * Получить отформатированный возраст
   * @param age
   * @return
  */
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
