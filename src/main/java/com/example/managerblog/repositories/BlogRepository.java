package com.example.managerblog.repositories;

import com.example.managerblog.entities.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    // Tìm tất cả các blog có status = true và sắp xếp theo publishedAt giảm dần (mới nhất trước) và phân trang.
    Page<Blog> findByStatusTrue(Pageable pageable);

    Page<Blog> findByStatus(Boolean status, Pageable pageable);

    // Tìm kiếm blog theo từ khóa chứa trong title.
    List<Blog> findByTitleContainingIgnoreCase(String title);

    // Lấy danh sách bài viết áp dụng category name và status = true và sắp xếp theo publishedAt giảm dần (mới nhất trước)
    @Query("""
            SELECT b
            FROM Blog b
            JOIN b.categories c
            WHERE c.name = ?1 AND b.status = true
            ORDER BY b.publishedAt DESC
       """)
    List<Blog> findByCategoryName(String name);
    Optional<Blog> findBySlugAndStatusTrue(String slug);
}
