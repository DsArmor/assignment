package main.groovy.ru.sbrf.example.shared_library

/**
 * Класс является входной точкой в наш фреймворк, аккумулирует в себе последовательность стейджей
 *
 * <p>
 *     Доступный разработчику метод {@link Pipeline#execute execute} выполняет роль обвязки,
 *     которая оборачивает ваш код и наши наработки в методы,
 *     предоставляемые плагинами Jenkins для обеспечения базовых вещей,
 *     таких как отображение времени рядом с логом и установление узла выполнения
 * </p>
 *
 * Вызов метода запустит в цикле все полученные шаги в порядке следования в списке
 */
public class Pipeline implements Serializable {

    /**
     * Рабочая область Jenkins для исполнения команд плагинов
     * Подсказка - The Groovy script is compiled to a class named WorkflowScript
     */
    public Script script

    /** Шаги, получаемые в конструкторе для основного блока */
    public List<Stage> stages

    public Pipeline(
            Script script,
            List<Stage> stages
    ) throws RuntimeException {
        if (!script) {
            throw new RuntimeException("Context is needed to work")
        }
        if (!stages) {
            throw new RuntimeException("Basic stages are missing")
        }
        this.script = script
        this.stages = stages
    }

    /**
     * Внутри обертки вызывает блоки выполнения заявленных шагов
     */
    public void execute() {
        script.timestamps {
            script.node() {
                executeStages(stages)
            }
        }
    }

    /**
     * Выполняет шаги, получаемые из конструктора.
     * @param stages набор шагов
     */
    protected void executeStages(List<Stage> stages) throws Exception {
        stages.each { it ->
            it.executeStage(script)
        }
    }

}
