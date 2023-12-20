# Библиотека для Jenkins

Репозиторий с подключаемыми классами shared_library

## Установка

Подключите данный репозиторий в своем Jenkins на уровне проекта или всей системы

## Использование

```groovy
//подлючение либы в скрипт
@Library('something') _

import main.groovy.ru.sbrf.example.shared_library.Pipeline
import main.groovy.ru.sbrf.example.shared_library.Stage

class HelloWorldStage extends Stage {

    HelloWorldStage(String name) { super(name) }

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
```

## Локальная проверка

1. Соберите проект
```
mvn clean install
```
2. Добавьте зависимость на него в pom.xml вашего проекта
```xml
<dependency>
    <groupId>ru.sbrf.example.shared_library</groupId>
    <artifactId>lib</artifactId>
    <version>1.0</version>
</dependency>
```



