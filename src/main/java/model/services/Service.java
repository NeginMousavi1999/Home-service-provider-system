package model.services;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private Set<SubService> subServices = new HashSet<>();
}
