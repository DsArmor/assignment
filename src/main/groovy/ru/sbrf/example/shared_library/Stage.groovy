package main.groovy.ru.sbrf.example.shared_library

/**
 * Объект класса будет исполняться как шаг (Jenkins stage) в скрипте
 */
abstract class Stage implements Serializable {

    /** Название шага */
    String name

    Stage(String name) {
        this.name = (name && name != '') ? name : 'Something action'
    }

    /**
     * Выполняет шаг с логированием
     * @param script скрипт, предоставляет методы плагинов jenkins
     */
    void executeStage(Script script) throws Exception {
        logging('Start stage')
        script.stage(name) {
            execute(script)
        }
        logging('Complete stage')
    }

    /**
     * Реализует бизнес-логику шага
     * <p>
     * Выполняйте все необходимые манипуляции над полученными ресурсами.
     * Производите новые ресурсы
     * </p>
     * @param script скрипт, предоставляет методы плагинов jenkins
     */
    abstract protected void execute(Script script)

    protected final logging(String message) {
        script.echo(message)
    }

}
