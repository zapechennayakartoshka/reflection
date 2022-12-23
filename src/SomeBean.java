/** Класс, содержащий две аннотации */
class SomeBean {
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;
    /** Конструктор класса по умолчанию */
    public SomeBean() {}
    /** Вызов методов интерфейса */
    public void foo(){
        field1.doSomething();
        field2.doSomeOther();
    }
}