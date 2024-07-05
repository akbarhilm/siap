package ebkus.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 *
 * @author sapto
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder
                .addDecoratorPath("*/common/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/list*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/tambahba*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/editba*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/hapusba*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/arsipba*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/panelsimpanpop", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/viewrincisetor/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/print/*", "/WEB-INF/template/print.jsp")
                .addDecoratorPath("/*", "/WEB-INF/template/maintemplate.jsp")
                .addExcludedPath("/login")
                .addExcludedPath("/depan")
                .addExcludedPath("/deny")
                .addExcludedPath("/expired")
                .addExcludedPath("/json/*")
                .addExcludedPath("*/reports/*")
                .addExcludedPath("/xml/*")
                .addExcludedPath("*/printvalidasi/*");
    }
}
