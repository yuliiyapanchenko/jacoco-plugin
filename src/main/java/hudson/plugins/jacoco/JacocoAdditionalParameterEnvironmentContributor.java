package hudson.plugins.jacoco;

import hudson.EnvVars;
import hudson.Extension;
import hudson.model.EnvironmentContributor;
import hudson.model.Run;
import hudson.model.TaskListener;

import java.io.IOException;

@Extension
public class JacocoAdditionalParameterEnvironmentContributor extends EnvironmentContributor {
    @Override
    public void buildEnvironmentFor(Run run, EnvVars envVars, TaskListener taskListener)
            throws IOException, InterruptedException {

        JacocoBuildAction action = run.getAction(JacocoBuildAction.class);
        if (action == null) {
            return;
        }

        putEnvVar(envVars, "lineCoverage", String.valueOf(action.getLineCoverage().getPercentageFloat()));
    }

    private static void putEnvVar(EnvVars envs, String name, String value) {
        envs.put(name, getString(value, ""));
    }

    private static String getString(String actual, String d) {
        return actual == null ? d : actual;
    }

}
