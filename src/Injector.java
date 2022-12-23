import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс Injector, который осуществляет внедрение зависимостей в любой объект , который содержит поля, помеченные аннотацией
 */
public class Injector<T> {
    private final Properties properties;

    /**
     * Конструктор класса
     * @param path - путь к файлу
     * @throws IOException IOException An exception that is thrown when an I/O error occurs
     */
    Injector(String path) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(new File(path)));
    }

    /**
     * inject принимает произвольный объект, исследует существующие в нем поля, и смотрит, аннотированы ли они
     * нужной аннотацией.
     * Если да, то метод бы смотрит тип этого поля и ищет реализацию в файле data.properties
     * @param object - произвольный объект
     * @return возвращает объект с иницализированными палями с аннотацией AutoInjectable
     */
    T inject(T object) throws IOException, IllegalAccessException, InstantiationException {

        Class dependency;
        Class cl = object.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field: fields) {

            Annotation a = field.getAnnotation(AutoInjectable.class);
            if (a != null) {

                String[] fieldType = field.getType().toString().split(" ");
                String equalsClassName = properties.getProperty(fieldType[1], null);

                if (equalsClassName != null) {
                    try {
                        dependency = Class.forName(equalsClassName);
                    } catch (ClassNotFoundException e){
                        System.out.println("Не найден класс " + equalsClassName);
                        continue;
                    }
                    field.setAccessible(true);
                    field.set(object, dependency.newInstance());
                }
                else
                    System.out.println("Не найдена реализация " + fieldType[1]);
            }
        }
        return object;
    }
}