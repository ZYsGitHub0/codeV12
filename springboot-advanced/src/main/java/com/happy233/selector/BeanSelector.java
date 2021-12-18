package com.happy233.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @author zy136
 * @date 2021/12/18 15:01
 */
public class BeanSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.happy233.pojo.Account"};
    }
}
