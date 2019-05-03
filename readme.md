# Basic log4j2 use
**Small description**

This just show how to create a log file based on date and time and have two different output (console/file).

**Things to do:**

* add dependency from maven 
    *  org.apache.logging.log4j:log4j-api:2.11.2
    *  org.apache.logging.log4j:log4j-core:2.11.2
    
* Create a new "log4j2.xml" in the compiled java directory, copypaste the code below then run.

```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn" name="MyApp" packages="">
    <Appenders>
        <Console name="ConsoleAppender" target="SYSTEM_OUT" follow="true">
            <PatternLayout>
                <Pattern>%highlight{[%level] %date{dd-MM-yyyy HH:mm:ss.SSS}}{FATAL=bg_red fg_yellow bright} [%file] -&gt; [%method] (%thread) - &lt;%message&gt;%n%throwable{short}</Pattern>
                <disableAnsi>false</disableAnsi>
            </PatternLayout>
        </Console>
        <RollingFile name="MyFile" filePattern="logs/$${date:dd-MM-yyyy}/app-%d{HH-mm-ss}.log" append="true">
            <PatternLayout>
                <Pattern>%date{dd-MM-yyyy HH:mm:ss.SSS} [%file] -> [%method] (%thread) - &lt;%message&gt;%n%throwable{full}</Pattern>
            </PatternLayout>
            <Policies>
                <TimeBasedTriggeringPolicy/>
            </Policies>
        </RollingFile>
    </Appenders>
    <Loggers>
        <Root level="ALL">
            <AppenderRef ref="MyFile"/>
            <AppenderRef ref="ConsoleAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

**THE SHORT HELP VERSION**

`<Console name="ConsoleAppender" target="SYSTEM_OUT" follow="true">`

Basically this means that the output is the same as `System.out.println();`

`<Pattern>%highlight{[%level] %date{dd-MM-yyyy HH:mm:ss.SSS}}{FATAL=bg_red fg_yellow bright} [%file] -&gt; [%method] (%thread) - &lt;%message&gt;%n%throwable{short}</Pattern>`

This will highlight the output in the console (colors), the throwable (stacktrace) is set to short, the full one will be in the log file  

`<RollingFile name="MyFile" filePattern="logs/$${date:dd-MM-yyyy}/app-%d{HH-mm-ss}.log" append="true">`

Basically this means that the output goes in a log file. 
This will generate a new log file name, the name is based on this `%d{HH-mm-ss}` that means that each second will have a different file name.
Keep in mind that this is only for testing, for example if you delete the `-ss` the file name will change each minute. 

**THE LONG HELP VERSION**
Read the [layouts](https://logging.apache.org/log4j/2.x/manual/layouts.html) if you want to change the layout or [the whole help](https://logging.apache.org/log4j/2.x/manual) for all.