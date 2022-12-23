import java.io.IOException;

/**
 *@author Юля Кузнецова (3 к. 7 гр.)
 *@version 1.0
 */
public class Main {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        SomeBean sb = (new Injector<SomeBean>("src/Prop/data.properties").inject(new SomeBean()));
        sb.foo();
    }
}