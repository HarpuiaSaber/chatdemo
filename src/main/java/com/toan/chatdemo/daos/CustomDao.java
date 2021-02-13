package com.toan.chatdemo.daos;

import com.toan.chatdemo.models.search.GridParam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

public interface CustomDao<T> extends JpaSpecificationExecutor<T> {

    static <T> Specification<T> getGridResult(@Nullable Specification<T> spec, GridParam param) {
        return spec == null ? (root, query, builder) -> {
            return null;
        } : spec;
    }
}
