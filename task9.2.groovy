import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.plugins.ws_cleanup.*
import hudson.tasks.Shell;
import jenkins.model.Jenkins;

job = Jenkins.instance.createProject(FreeStyleProject, 'maniukevich_groovy_SCM')

def url = "https://github.com/Desh96/hello_world"
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/master")]
job.scm = gitScm

job.buildersList.add(new Shell('It is ok'))
job.save()

job.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))
