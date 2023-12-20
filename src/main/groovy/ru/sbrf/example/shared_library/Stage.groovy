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
    final void executeStage(Script script) throws Exception {
        logging(script,'Start stage')
        script.stage(name) {
            execute(script)
        }
        logging(script,'Complete stage')
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

    /**
     * Реализует вывод логов в пространстве Jenkins
     * @param script рабочая область Jenkins для исполнения команд плагинов
     * @param message логируемое сообщение
     */
    public final void logging(Script script, String message) {
        script.echo(message)
    }

}
