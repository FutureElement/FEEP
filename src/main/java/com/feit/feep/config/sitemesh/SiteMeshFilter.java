package com.feit.feep.config.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * siteMesh装饰器配置
 * Created by ZhangGang on 2015/9/2 0002.
 */
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

    public static final String FEEP_TAG = "feep";
    public static final String REDIRECT_TAG = "feep-redirect";

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/Resource/decorator/default-decorator.jsp")
                .addDecoratorPath("/pm/*/link.feep", "/Resource/decorator/platform-menu-decorator.jsp")
                .addDecoratorPath("/m/*/link.feep", "/Resource/decorator/system-menu-decorator.jsp")
                .addExcludedPath("*service.feep");
        TagRuleBundle feep = new TagRuleBundle() {
            @Override
            public void install(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
                state.addRule(FEEP_TAG, new ExportTagToContentRule(siteMeshContext, contentProperty.getChild(FEEP_TAG), false));
            }

            @Override
            public void cleanUp(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

            }
        };
        TagRuleBundle redirect = new TagRuleBundle() {
            @Override
            public void install(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
                state.addRule(REDIRECT_TAG, new ExportTagToContentRule(siteMeshContext, contentProperty.getChild(REDIRECT_TAG), false));
            }

            @Override
            public void cleanUp(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

            }
        };
        builder.addTagRuleBundle(feep).addTagRuleBundle(redirect);
    }

}
