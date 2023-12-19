import main.groovy.ru.sbrf.example.shared_library.Pipeline
import main.groovy.ru.sbrf.example.shared_library.Stage

class HelloWorldStage extends Stage {

    HelloWorldStage(String name) {
        super(name)
    }

    @Override
    protected void execute(Script script) {
        logging(script, 'Hello world')
    }
}

Pipeline pipeline = new Pipeline(
        this,
        [
                new HelloWorldStage('Hello world')
        ]
)
pipeline.execute()