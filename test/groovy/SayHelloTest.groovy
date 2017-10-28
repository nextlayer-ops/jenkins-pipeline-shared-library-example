#!/usr/bin/env groovy
import org.junit.Before
import org.junit.Test

import java.util.regex.Pattern

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource

import com.lesfurets.jenkins.unit.BasePipelineTest

class SayHelloTest extends BasePipelineTest {

    String sharedLibs = this.class.getResource('./').getFile()



    @Override
    @Before
    void setUp() throws Exception {

        println sharedLibs

        String dirPath = new File( System.getProperty("user.dir") )
                .getAbsoluteFile()
                .getParentFile()
                .getAbsolutePath()

        def library = library()
                .name('nextlayerci-example')
                .allowOverride(false)
                .retriever(localSource(sharedLibs))
                .targetPath(sharedLibs)
                .defaultVersion("master")
                .implicit(true)
                .build()
        helper.registerSharedLibrary(library)

//        setBaseScriptRoot()
        setScriptRoots([ 'src', 'vars', 'test/groovy', 'test/resources', 'test/pipelines', './' ] as String[])
        setScriptExtension('groovy')




/*
        helper.registerAllowedMethod('node', )
*/

        super.setUp()
    }

    @Test
    void should_execute_without_errors() throws Exception {

        println("hallo")




        /*Script script = loadScript("io/nextlayer/ci/pipelines/docker/BuildPipeline.groovy")*/
        Script script = loadScript("HelloPipeline.groovy")
        script.execute()
        printCallStack()
        assertJobStatusSuccess()
    }
}
