package com.example.managerblog.entities;

import com.example.managerblog.entities.Blog;
import com.example.managerblog.entities.core.BaseObject;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseObject {
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Blog> blogs;
}
