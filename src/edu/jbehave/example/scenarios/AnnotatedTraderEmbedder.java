package edu.jbehave.example.scenarios;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.InjectableEmbedder;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.jbehave.example.scenarios.AnnotatedTraderEmbedder.MyDateConverter;
import edu.jbehave.example.scenarios.AnnotatedTraderEmbedder.MyReportBuilder;
import edu.jbehave.example.scenarios.AnnotatedTraderEmbedder.MyStoryControls;
import edu.jbehave.example.scenarios.AnnotatedTraderEmbedder.MyStoryLoader;

@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyControls = MyStoryControls.class, storyLoader = MyStoryLoader.class, storyReporterBuilder = MyReportBuilder.class, 
        parameterConverters = { MyDateConverter.class })
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = true, verboseFailures = true,
                storyTimeoutInSecs = 100, threads = 2, metaFilters = "-skip")
@UsingSteps(instances = { StackSteps.class})
public class AnnotatedTraderEmbedder extends InjectableEmbedder {
 
    @Test
    public void run() {
        List<String> storyPaths = new StoryFinder().findPaths(CodeLocations.codeLocationFromClass( getClass() ), "**/*.story", "");
        injectedEmbedder().runStoriesAsPaths(storyPaths);
    }
 
    public static class MyStoryControls extends StoryControls {
        public MyStoryControls() {
            doDryRun(false);
            doSkipScenariosAfterFailure(false);
        }
    }
 
    public static class MyStoryLoader extends LoadFromClasspath {
        public MyStoryLoader() {
            super(AnnotatedTraderEmbedder.class.getClassLoader());
        }
    }
 
    public static class MyReportBuilder extends StoryReporterBuilder {
        public MyReportBuilder() {
            this.withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML).withDefaultFormats();
        }
    }
 
    public static class MyRegexPrefixCapturingPatternParser extends RegexPrefixCapturingPatternParser {
        public MyRegexPrefixCapturingPatternParser() {
            super("%");
        }
    }
 
    public static class MyDateConverter extends DateConverter {
        public MyDateConverter() {
            super(new SimpleDateFormat("yyyy-MM-dd"));
        }
    }
 
}