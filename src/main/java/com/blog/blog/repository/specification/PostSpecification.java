package com.blog.blog.repository.specification;

import com.blog.blog.dto.PostFiltersDTO;
import com.blog.blog.entity.PostEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostSpecification {

    public Specification<PostEntity> getByFilters(PostFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDTO.getCategory())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("category")),
                                "%" + filtersDTO.getCategory().toLowerCase() + "%"
                        )
                );
            }
            //Remove duplicates
            query.distinct(true);

            //orden
            String orderByField = "creationDate";
            query.orderBy(criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
