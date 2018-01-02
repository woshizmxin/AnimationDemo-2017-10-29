package  com.sankuai.buildsrc;

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 开发只针对当前项目的Gradle插件
 * https://www.jianshu.com/p/d53399cd507b
 */

public class SecondPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("========================");
        System.out.println("this is the second plugin");
        System.out.println("========================");
    }
}