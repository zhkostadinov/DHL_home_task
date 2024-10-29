# Project structure
`cucumber-reports` -  HTMl report generated from execution

    `src` - main project folder

        `resources` 
            `features` - feature files
        `test`
            `java`
               `page_objects` - POM
               `step_definition` - Gherkin step implementation

`pom.xml`- main configuration

# Dependencies
`mvn v3.9.5`

`Java v21.0.0`

# Run from your project's root directory
`mvn clean install -DskipTests` - will build project

`mvn clean install` - will build project and run tests
